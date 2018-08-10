package com.example.ignition.financetracker.ui.main

import com.example.ignition.financetracker.data.DataSource
import com.example.ignition.financetracker.ui.base.BasePresenter
import com.example.ignition.financetracker.utils.SchedulersProvider
import java.util.*

class BillScreenPresenter private constructor(
        dataSource: DataSource,
        sp: SchedulersProvider
) : BasePresenter<BillScreenContract.View>(dataSource, sp), BillScreenContract.Presenter {

    companion object {
        private var INSTANCE: BillScreenContract.Presenter? = null
        fun get(dataSource: DataSource, sp: SchedulersProvider): BillScreenContract.Presenter {
            if (INSTANCE == null) INSTANCE = BillScreenPresenter(dataSource, sp)
            return INSTANCE!!
        }
    }

    override fun onSettingsClick() {
        view?.showSettings()
    }

    override fun onBalanceClick() {
        view?.showBalance()
    }

    override fun onAboutClick() {
        view?.showAbout()
    }

    override fun load() {
        val c = Calendar.getInstance()
        val date = c[Calendar.DAY_OF_MONTH] * 100 + c[Calendar.MONTH]
        // todo batch updates
        compositeDisposable.add(dataSource.getRepeatableOperationsByDate(date)
                .subscribeOn(sp.io())
                .subscribe { ops ->
                    ops.forEach {
                        dataSource.repeatOperation(it)
                    }
                })
    }
}