package com.example.ignition.financetracker.ui.main.addWalletDialog

import com.example.ignition.financetracker.data.DataSource
import com.example.ignition.financetracker.di.AppModule
import com.example.ignition.financetracker.utils.SchedulersProvider

/**
 * Created by Elbek D. on 03.08.2018.
 */
object AddWalletModule {
    fun provideAddCardModule(
            dataSource: DataSource = AppModule.provideDataSource(),
            sp: SchedulersProvider = AppModule.provideSchedulerProvider()
    ): AddWalletDialogContract.Presenter {
        return AddWalletDialogPresenter.get(dataSource, sp)
    }
}