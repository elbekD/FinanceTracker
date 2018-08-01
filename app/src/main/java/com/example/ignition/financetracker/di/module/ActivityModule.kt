package com.example.ignition.financetracker.di.module

import android.app.Activity
import com.example.ignition.financetracker.ui.billActivity.BillScreenPresenter
import com.example.ignition.financetracker.ui.billActivity.IBillScreenContract
import com.example.ignition.financetracker.ui.billStatisticAvtivity.StatisticFragmentContract
import com.example.ignition.financetracker.ui.billStatisticAvtivity.StatisticFragmentPrestnter
import dagger.Module
import dagger.Provides

@Module
class ActivityModule(private var activity: Activity) {

    @Provides
    fun provideActivity(): Activity {
        return activity
    }

    @Provides
    fun providePresenter(): IBillScreenContract.Presenter {
        return BillScreenPresenter()
    }
    @Provides
    fun provideStatisticPresenter() : StatisticFragmentContract.Presenter{
        return StatisticFragmentPrestnter()
    }

}