package com.example.ignition.financetracker.ui.main.addWalletDialog

import com.example.ignition.financetracker.data.DataSource
import com.example.ignition.financetracker.ui.base.BasePresenter
import com.example.ignition.financetracker.utils.SchedulersProvider

/**
 * Created by Elbek D. on 03.08.2018.
 */
class AddWalletDialogPresenter private constructor(dataSource: DataSource, sp: SchedulersProvider)
    : BasePresenter<AddWalletDialogContract.View>(dataSource, sp), AddWalletDialogContract.Presenter {

    companion object {
        private var INSTANCE: AddWalletDialogContract.Presenter? = null
        fun get(dataSource: DataSource, sp: SchedulersProvider): AddWalletDialogContract.Presenter {
            if (INSTANCE == null) INSTANCE = AddWalletDialogPresenter(dataSource, sp)
            return INSTANCE!!
        }
    }

    override fun start() {
        dataSource.getUserCurrencies()
                .subscribeOn(sp.io())
                .observeOn(sp.ui())
                .subscribe { list -> view?.setCurrencyAdapter(list.map { it.name }) }
    }
}