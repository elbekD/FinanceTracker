package com.example.ignition.financetracker.ui.main.walletOperations.filterDialog

import com.example.ignition.financetracker.entities.Category
import com.example.ignition.financetracker.ui.base.MvpPresenter
import com.example.ignition.financetracker.ui.base.MvpView

/**
 * Created by Elbek D. on 09.08.2018.
 */
interface FilterDialogContract {
    interface View : MvpView {
        fun setCategoryAdapter(c: List<Category>)
    }

    interface Presenter : MvpPresenter<View> {
        fun load()
    }
}