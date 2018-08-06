package com.example.ignition.financetracker.ui.main.addOperationDialog

import com.example.ignition.financetracker.ui.base.MvpPresenter
import com.example.ignition.financetracker.ui.base.MvpView

class AddOperationDialogContract {
    interface View : MvpView {
        fun changeMainCurrency(currency: String)
        fun setupCurrencyAdapter(currencies: List<String>)
        fun setupCategoryAdapter(categories: List<String>)
        fun setupWalletAdapter(cards: List<String>)
        fun showPeriodPicker(show: Boolean)
    }

    interface Presenter : MvpPresenter<AddOperationDialogContract.View> {
        fun load()
        fun onWalletSelected(walletName: String)
        fun periodClick(show: Boolean)
    }
}