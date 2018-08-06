package com.example.ignition.financetracker.ui.main.addCardDialog

import com.example.ignition.financetracker.data.DataSource
import com.example.ignition.financetracker.ui.base.BasePresenter
import com.example.ignition.financetracker.utils.SchedulersProvider

/**
 * Created by Elbek D. on 03.08.2018.
 */
class AddCardDialogPresenter private constructor(dataSource: DataSource, sp: SchedulersProvider)
    : BasePresenter<AddCardDialogContract.View>(dataSource, sp), AddCardDialogContract.Presenter {

    companion object {
        private var INSTANCE: AddCardDialogContract.Presenter? = null
        fun get(dataSource: DataSource, sp: SchedulersProvider): AddCardDialogContract.Presenter {
            if (INSTANCE == null) INSTANCE = AddCardDialogPresenter(dataSource, sp)
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