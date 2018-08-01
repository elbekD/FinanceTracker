package com.example.ignition.financetracker.ui.billActivity.CardFragment

import com.example.ignition.financetracker.entities.CardEntity
import com.example.ignition.financetracker.ui.base.BaseContract

class CardFragmentContract : BaseContract.View {
    interface View : BaseContract.View {
        fun statisticBillSelected()
        fun onSettingsSelected()
    }

    interface Presenter : BaseContract.Presenter<View> {
        fun addNewCard()
        fun loadCardData(): List<CardEntity>
    }
}