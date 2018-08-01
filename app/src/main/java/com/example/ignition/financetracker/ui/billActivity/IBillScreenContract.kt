package com.example.ignition.financetracker.ui.billActivity

import com.example.ignition.financetracker.ui.base.BaseContract

class IBillScreenContract {
    interface View : BaseContract.View {
        fun showCardsFragment()
        fun showOperationList()
        fun showAddOperationDialog()
        fun statisticBillSelected()
    }

    interface Presenter : BaseContract.Presenter<IBillScreenContract.View> {
        fun onListOpenClicked()
        fun onAddOperationClicked()
    }
}