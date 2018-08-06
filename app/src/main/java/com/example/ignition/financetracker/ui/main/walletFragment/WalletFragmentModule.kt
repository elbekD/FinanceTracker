package com.example.ignition.financetracker.ui.main.walletFragment

import com.example.ignition.financetracker.data.DataSource
import com.example.ignition.financetracker.di.AppModule
import com.example.ignition.financetracker.utils.SchedulersProvider

/**
 * Created by Elbek D. on 02.08.2018.
 */
object WalletFragmentModule {
    fun provideCardFragmentPresenter(dataSource: DataSource = AppModule.provideDataSource(),
                                     sp: SchedulersProvider = AppModule.provideSchedulerProvider()):
            WalletFragmentContract.Presenter {
        return WalletFragmentPresenter.get(dataSource, sp)
    }
}