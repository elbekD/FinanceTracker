package com.example.ignition.financetracker.ui.main

import com.example.ignition.financetracker.data.DataSource
import com.example.ignition.financetracker.ui.base.BasePresenter
import com.example.ignition.financetracker.utils.SchedulersProvider

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

}