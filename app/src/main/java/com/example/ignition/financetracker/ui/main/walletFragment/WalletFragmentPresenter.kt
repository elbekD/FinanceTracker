package com.example.ignition.financetracker.ui.main.walletFragment

import com.example.ignition.financetracker.R
import com.example.ignition.financetracker.data.DataSource
import com.example.ignition.financetracker.entities.ExchangeRate
import com.example.ignition.financetracker.entities.RepeatableOperation
import com.example.ignition.financetracker.entities.Wallet
import com.example.ignition.financetracker.ui.base.BasePresenter
import com.example.ignition.financetracker.ui.main.RepeatableOperationModel
import com.example.ignition.financetracker.utils.SchedulersProvider
import io.reactivex.Single
import io.reactivex.functions.BiFunction

class WalletFragmentPresenter
private constructor(dataSource: DataSource,
                    sp: SchedulersProvider) :
        BasePresenter<WalletFragmentContract.View>(dataSource, sp),
        WalletFragmentContract.Presenter {

    companion object {
        private var INSTANCE: WalletFragmentContract.Presenter? = null
        fun get(dataSource: DataSource, sp: SchedulersProvider): WalletFragmentContract.Presenter {
            if (INSTANCE == null) INSTANCE = WalletFragmentPresenter(dataSource, sp)
            return INSTANCE!!
        }
    }

    override fun load() {
        compositeDisposable.add(dataSource.getWalletsOperations()
                .subscribeOn(sp.io())
                .observeOn(sp.ui())
                .subscribe { wallets -> view?.loadWallets(wallets) })
    }

    override fun onAddWalletClick() {
        view?.showAddWalletDialog()
    }

    override fun onAddOperationClick() {
        dataSource.hasWallets()
                .subscribeOn(sp.io())
                .observeOn(sp.ui())
                .subscribe { show ->
                    if (show) {
                        view?.showOperationDialog()
                    } else {
                        view?.showError(R.string.operation_noWallets)
                    }
                }
    }

    override fun addWallet(w: Wallet) {
        compositeDisposable.add(dataSource.insertWallet(w)
                .subscribeOn(sp.io())
                .subscribe())
    }

    override fun commitOperation(rom: RepeatableOperationModel) {
        compositeDisposable.add(dataSource.getWalletByName(rom.operation.walletName)
                .flatMap {
                    Single.zip(
                            dataSource.getRate(rom.operation.currency, it.mainCurrency),
                            dataSource.getRate(it.mainCurrency, it.secondaryCurrency),
                            BiFunction { r1: ExchangeRate, r2: ExchangeRate -> r1 to r2 })
                }
                .map { rates ->
                    val resultingOperation = rom.operation.copy(rate = rates.first.rate)
                    val operationId = dataSource.insertOperation(resultingOperation)
                    if (rom.repeatDate > 0) {
                        dataSource.insertRepeatableOperation(
                                RepeatableOperation(0,
                                        rom.operation.walletName,
                                        operationId,
                                        rom.repeatDate))
                    }
                }
                .subscribeOn(sp.io())
                .subscribe())
    }

    override fun onWalletHistoryClick(walletName: String) {
        compositeDisposable.add(dataSource.hasOperations(walletName)
                .subscribeOn(sp.io())
                .observeOn(sp.ui())
                .subscribe { res ->
                    if (res) view?.openHistoryFragment(walletName)
                    else view?.showError(R.string.operation_noOperations)
                })
    }

    override fun onWalletPeriodicClick(walletName: String) {
        compositeDisposable.add(dataSource.hasPeriodicOperations(walletName)
                .subscribeOn(sp.io())
                .observeOn(sp.ui())
                .subscribe { res ->
                    if (res) view?.openPeriodicFragment(walletName)
                    else view?.showError(R.string.operation_noRepeatableOperations)
                })
    }
}
