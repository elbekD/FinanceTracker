package com.example.ignition.financetracker.ui.billStatisticAvtivity

import com.example.ignition.financetracker.data.DataSource
import com.example.ignition.financetracker.ui.base.BasePresenter
import com.example.ignition.financetracker.utils.SchedulersProvider

class StatisticFragmentPresenter private constructor(
        dataSource: DataSource,
        sp: SchedulersProvider
) : BasePresenter<StatisticFragmentContract.View>(dataSource, sp), StatisticFragmentContract.Presenter {

    companion object {
        private var INSTANCE: StatisticFragmentContract.Presenter? = null
        fun get(dataSource: DataSource, sp: SchedulersProvider): StatisticFragmentContract.Presenter {
            if (INSTANCE == null) INSTANCE = StatisticFragmentPresenter(dataSource, sp)
            return INSTANCE!!
        }
    }

    override fun start() {

    }
}