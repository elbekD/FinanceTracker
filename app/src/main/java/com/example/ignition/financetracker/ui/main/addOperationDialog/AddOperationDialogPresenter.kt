package com.example.ignition.financetracker.ui.main.addOperationDialog

import com.example.ignition.financetracker.data.DataSource
import com.example.ignition.financetracker.ui.base.BasePresenter
import com.example.ignition.financetracker.utils.SchedulersProvider

class AddOperationDialogPresenter private constructor(
        dataSource: DataSource,
        sp: SchedulersProvider
) : BasePresenter<AddOperationDialogContract.View>(dataSource, sp), AddOperationDialogContract.Presenter {

    companion object {
        private var INSTANCE: AddOperationDialogContract.Presenter? = null
        fun get(dataSource: DataSource, sp: SchedulersProvider): AddOperationDialogContract.Presenter {
            if (INSTANCE == null) INSTANCE = AddOperationDialogPresenter(dataSource, sp)
            return INSTANCE!!
        }
    }

    override fun load() {
        compositeDisposable.addAll(
                dataSource.getWallets()
                        .map { cards -> cards.map { it.name } }
                        .subscribeOn(sp.io())
                        .observeOn(sp.ui())
                        .subscribe { cards -> view?.setupWalletAdapter(cards) },
                dataSource.getAllCategories()
                        .map { categories -> categories.map { it.name } }
                        .subscribeOn(sp.io())
                        .observeOn(sp.ui())
                        .subscribe { cats -> view?.setupCategoryAdapter(cats) },
                dataSource.getUserCurrencies()
                        .map { currencies -> currencies.map { it.name } }
                        .subscribeOn(sp.io())
                        .observeOn(sp.ui())
                        .subscribe { curs -> view?.setupCurrencyAdapter(curs) })
    }

    override fun onWalletSelected(walletName: String) {
        compositeDisposable.add(dataSource.getWalletByName(walletName)
                .subscribeOn(sp.io())
                .observeOn(sp.ui())
                .subscribe { card -> view?.changeMainCurrency(card.mainCurrency) })
    }

    override fun periodClick(show: Boolean) {
        view?.showPeriodPicker(show)
    }
}