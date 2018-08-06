package com.example.ignition.financetracker.ui.main.addCardDialog

import com.example.ignition.financetracker.data.DataSource
import com.example.ignition.financetracker.di.AppModule
import com.example.ignition.financetracker.utils.SchedulersProvider

/**
 * Created by Elbek D. on 03.08.2018.
 */
object AddCardModule {
    fun provideAddCardModule(
            dataSource: DataSource = AppModule.provideDataSource(),
            sp: SchedulersProvider = AppModule.provideSchedulerProvider()
    ): AddCardDialogContract.Presenter {
        return AddCardDialogPresenter.get(dataSource, sp)
    }
}