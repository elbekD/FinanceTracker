package com.example.ignition.financetracker.ui.main

import com.example.ignition.financetracker.data.DataSource
import com.example.ignition.financetracker.di.AppModule
import com.example.ignition.financetracker.utils.SchedulersProvider

/**
 * Created by Elbek D. on 02.08.2018.
 */
object BillScreenModule {
    fun provideBillScreenPresenter(dataSource: DataSource = AppModule.provideDataSource()
                                   , sp: SchedulersProvider = AppModule.provideSchedulerProvider()
    ): BillScreenContract.Presenter {
        return BillScreenPresenter.get(dataSource, sp)
    }
}