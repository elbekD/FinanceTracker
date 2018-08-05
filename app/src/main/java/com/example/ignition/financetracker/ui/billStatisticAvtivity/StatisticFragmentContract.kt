package com.example.ignition.financetracker.ui.billStatisticAvtivity

import com.example.ignition.financetracker.ui.base.MvpPresenter
import com.example.ignition.financetracker.ui.base.MvpView

class StatisticFragmentContract {
    interface View : MvpView {

    }

    interface Presenter : MvpPresenter<View> {
        fun start()
    }

}