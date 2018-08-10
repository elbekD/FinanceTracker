package com.example.ignition.financetracker.ui.main.addWalletDialog

import com.example.ignition.financetracker.ui.base.MvpPresenter
import com.example.ignition.financetracker.ui.base.MvpView

/**
 * Created by Elbek D. on 03.08.2018.
 */

interface AddWalletDialogContract {
    interface View : MvpView {
        fun setCurrencyAdapter(c: List<String>)
    }

    interface Presenter : MvpPresenter<View> {
        fun start()
    }
}