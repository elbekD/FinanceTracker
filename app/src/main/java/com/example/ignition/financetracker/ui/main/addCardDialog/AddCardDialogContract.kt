package com.example.ignition.financetracker.ui.main.addCardDialog

import com.example.ignition.financetracker.ui.base.MvpPresenter
import com.example.ignition.financetracker.ui.base.MvpView

/**
 * Created by Elbek D. on 03.08.2018.
 */

interface AddCardDialogContract {
    interface View : MvpView {
        fun setCurrencyAdapter(c: List<String>)
    }

    interface Presenter : MvpPresenter<View> {
        fun start()
    }
}