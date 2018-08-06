package com.example.ignition.financetracker.ui.main.addOperationDialog

import com.example.ignition.financetracker.data.DataSource
import com.example.ignition.financetracker.di.AppModule
import com.example.ignition.financetracker.utils.SchedulersProvider


/**
 * Created by Elbek D. on 03.08.2018.
 */
object AddOperationModule {

    fun provideAddOperationPresenter(
            dataSource: DataSource = AppModule.provideDataSource(),
            sp: SchedulersProvider = AppModule.provideSchedulerProvider()
    ): AddOperationDialogContract.Presenter {
        return AddOperationDialogPresenter.get(dataSource, sp)
    }
}