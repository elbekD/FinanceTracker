package com.example.ignition.financetracker.ui.billStatisticAvtivity

import com.example.ignition.financetracker.entities.CardEntity
import com.example.ignition.financetracker.ui.base.BaseContract
import com.github.mikephil.charting.data.BarDataSet

class StatisticFragmentContract {
    interface View : BaseContract.View {
        fun onChangeCurrency()
    }

    interface Presenter : BaseContract.Presenter<StatisticFragmentContract.View> {
        fun onChangeCursePresed(currencyValue: String): String
        fun onLoadCourseCurrency()
        fun onLoadDiagramValues(): BarDataSet
    }

    interface Model {
        fun diagramOperations(data: List<CardEntity>): BarDataSet
    }

}