package com.example.ignition.financetracker.ui.main.addOperationDialog

import com.example.ignition.financetracker.entities.Operation
import com.example.ignition.financetracker.ui.base.MvpPresenter
import com.example.ignition.financetracker.ui.base.MvpView

class AddOperationDialogContract {
    interface View : MvpView {
        fun changeMainCurrency(currency: String)
        fun setupCurrencyAdapter(currencies: List<String>)
        fun setupCategoryAdapter(categories: List<String>)
        fun setupCardsAdapter(cards: List<String>)
    }

    interface Presenter : MvpPresenter<AddOperationDialogContract.View> {
        fun load()
        fun onCardSelected(cardName: String)
    }
}