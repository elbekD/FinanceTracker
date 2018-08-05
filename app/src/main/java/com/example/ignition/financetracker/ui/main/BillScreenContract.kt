package com.example.ignition.financetracker.ui.main

import com.example.ignition.financetracker.ui.base.MvpPresenter
import com.example.ignition.financetracker.ui.base.MvpView

interface BillScreenContract {
    interface View : MvpView {
        fun showSettings()
    }

    interface Presenter : MvpPresenter<BillScreenContract.View> {
        fun onSettingsClick()
    }
}