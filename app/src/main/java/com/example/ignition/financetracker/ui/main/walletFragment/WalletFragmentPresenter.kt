package com.example.ignition.financetracker.ui.main.walletFragment

import com.example.ignition.financetracker.data.DataSource
import com.example.ignition.financetracker.entities.ExchangeRate
import com.example.ignition.financetracker.entities.Operation
import com.example.ignition.financetracker.entities.Wallet
import com.example.ignition.financetracker.ui.base.BasePresenter
import com.example.ignition.financetracker.utils.SchedulersProvider
import io.reactivex.Single
import io.reactivex.functions.BiFunction
import java.math.BigDecimal

class WalletFragmentPresenter
private constructor(dataSource: DataSource,
                    sp: SchedulersProvider) :
        BasePresenter<CardFragmentContract.View>(dataSource, sp),
        CardFragmentContract.Presenter {

    companion object {
        private var INSTANCE: CardFragmentContract.Presenter? = null
        fun get(dataSource: DataSource, sp: SchedulersProvider): CardFragmentContract.Presenter {
            if (INSTANCE == null) INSTANCE = WalletFragmentPresenter(dataSource, sp)
            return INSTANCE!!
        }
    }

    override fun load() {
        compositeDisposable.add(dataSource.getWallets()
                .map { wallets ->
                    val res = mutableListOf<WalletModel>()
                    wallets.forEach { wallet ->
                        val fromToRate = "${wallet.mainCurrency}_${wallet.secondaryCurrency}"
                        val rate = dataSource.getRate(wallet.mainCurrency, wallet.secondaryCurrency)
                                .onErrorResumeNext { Single.just(ExchangeRate.default(fromToRate)) }
                                .blockingGet()
                        dataSource.getWalletOperations(wallet.name)
                                .subscribe { ops ->
                                    val income = ops.asSequence()
                                            .filter { it.sum > BigDecimal.ZERO }
                                            .sumByDouble { (it.sum * it.rate).toDouble() }
                                            .toBigDecimal()
                                    val outcome = ops.asSequence()
                                            .filter { it.sum < BigDecimal.ZERO }
                                            .sumByDouble { (it.sum * it.rate).toDouble() }
                                            .toBigDecimal().negate()
                                    val balance = income + outcome
                                    val secondaryBalance = balance * rate.rate
                                    res.add(WalletModel(wallet, balance, secondaryBalance, income, outcome))
                                }
                    }
                    res
                }
                .subscribeOn(sp.io())
                .observeOn(sp.ui())
                .subscribe { cards -> view?.setCardAdapter(cards) })
    }

    override fun onOpenAddWalletClick() {
        view?.showAddWalletDialog()
    }

    override fun onAddTransactionClick() {
        view?.showTransactionDialog()
    }

    override fun addWallet(w: Wallet) {
        compositeDisposable.add(dataSource.insertWallet(w)
                .subscribeOn(sp.io())
                .observeOn(sp.ui())
                .subscribe { _ -> view?.addWalletToPager(w) })
    }

    override fun commitOperation(o: Operation) {
        compositeDisposable.add(dataSource.getWalletByName(o.walletName)
                .flatMap {
                    Single.zip(dataSource.getRate(o.currency, it.mainCurrency),
                            dataSource.getRate(it.mainCurrency, it.secondaryCurrency),
                            BiFunction { r1: ExchangeRate, r2: ExchangeRate -> r1 to r2 })
                }
                .map { rates ->
                    val res = Operation(o.walletName,
                            o.operationType,
                            o.sum,
                            o.currency,
                            o.date,
                            rates.first.rate)
                    dataSource.insertOperation(res)
                    WalletOperation(res, rates.second.rate)
                }
                .subscribeOn(sp.io())
                .observeOn(sp.ui())
                .subscribe({ walletOperation ->
                    view?.updateWalletModel(walletOperation)
                }, { err ->
                    view?.showError(err.message ?: "Unknown error")
                }))
    }
}
