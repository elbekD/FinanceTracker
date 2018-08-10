package com.example.ignition.financetracker.ui.main.walletOperations

import com.example.ignition.financetracker.data.DataSource
import com.example.ignition.financetracker.di.AppModule
import com.example.ignition.financetracker.utils.SchedulersProvider

/**
 * Created by Elbek D. on 09.08.2018.
 */
object WalletOperationsModule {
    fun providePeriodicOperationPresenter(dataSource: DataSource = AppModule.provideDataSource(),
                                          sp: SchedulersProvider = AppModule.provideSchedulerProvider()):
            WalletOperationContract.Presenter {
        return WalletOperationPresenter(dataSource, sp)
    }
}