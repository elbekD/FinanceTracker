package com.example.ignition.financetracker.ui.main

import com.example.ignition.financetracker.ui.base.MvpPresenter
import com.example.ignition.financetracker.ui.base.MvpView

interface BillScreenContract {
    interface View : MvpView {
        fun showBalance()
        fun showSettings()
        fun showAbout()
    }

    interface Presenter : MvpPresenter<BillScreenContract.View> {
        fun onBalanceClick()
        fun onSettingsClick()
        fun onAboutClick()
        fun load()
    }
}