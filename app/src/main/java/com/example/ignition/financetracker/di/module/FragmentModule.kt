package com.example.ignition.financetracker.di.module

import com.example.ignition.financetracker.ui.billActivity.BillScreenPresenter
import com.example.ignition.financetracker.ui.billActivity.CardFragment.CardFragmentContract
import com.example.ignition.financetracker.ui.billActivity.CardFragment.CardFragmentPresenter
import com.example.ignition.financetracker.ui.billActivity.IBillScreenContract
import com.example.ignition.financetracker.ui.billActivity.addOperationDialog.AddOperationDialog
import com.example.ignition.financetracker.ui.billActivity.addOperationDialog.AddOperationDialogContract
import com.example.ignition.financetracker.ui.billActivity.addOperationDialog.AddOperationDialogPresenter
import com.example.ignition.financetracker.ui.billStatisticAvtivity.StatisticFragment
import com.example.ignition.financetracker.ui.billStatisticAvtivity.StatisticFragmentContract
import com.example.ignition.financetracker.ui.billStatisticAvtivity.StatisticFragmentPrestnter
import dagger.Module
import dagger.Provides

@Module
class FragmentModule {


    @Provides
    fun provideAboutPresenter(): CardFragmentContract.Presenter {
        return CardFragmentPresenter()
    }

    @Provides
    fun provideAddOperationDialog(): AddOperationDialogContract.Presenter {
        return AddOperationDialogPresenter()
    }

    @Provides
    fun provideStatistiFragmentPresenter(): StatisticFragmentContract.Presenter{
        return StatisticFragmentPrestnter()
    }
}