package com.example.ignition.financetracker.di.component

import com.example.ignition.financetracker.di.module.FragmentModule
import com.example.ignition.financetracker.ui.billActivity.addOperationDialog.AddOperationDialog
import com.example.ignition.financetracker.ui.billActivity.CardFragment.FragmentCards
import com.example.ignition.financetracker.ui.billStatisticAvtivity.StatisticFragment
import dagger.Component

@Component(modules = arrayOf(FragmentModule::class))
interface FragmentComponent {

    fun inject(fragmentCard: FragmentCards)

    fun inject(addOperationDialog: AddOperationDialog)

    fun inject(statisticFragment: StatisticFragment)
    //fun inject(listFragment: ListFragment)

}