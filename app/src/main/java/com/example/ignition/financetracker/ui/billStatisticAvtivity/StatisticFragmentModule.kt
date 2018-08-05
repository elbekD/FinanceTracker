package com.example.ignition.financetracker.ui.billStatisticAvtivity

import com.example.ignition.financetracker.data.DataSource
import com.example.ignition.financetracker.di.AppModule
import com.example.ignition.financetracker.utils.SchedulersProvider

/**
 * Created by Elbek D. on 03.08.2018.
 */
object StatisticFragmentModule {
    fun provideStatisticFragmentPresenter(dataSource: DataSource = AppModule.provideDataSource(),
                                          sp: SchedulersProvider = AppModule.provideSchedulerProvider()
    ): StatisticFragmentContract.Presenter {
        return StatisticFragmentPresenter.get(dataSource, sp)
    }
}