package com.example.ignition.financetracker.ui.main.walletOperations

import com.example.ignition.financetracker.entities.Operation
import com.example.ignition.financetracker.entities.OperationFilter
import com.example.ignition.financetracker.entities.PeriodicOperation
import com.example.ignition.financetracker.entities.RepeatableOperation
import com.example.ignition.financetracker.ui.base.MvpPresenter
import com.example.ignition.financetracker.ui.base.MvpView

/**
 * Created by Elbek D. on 09.08.2018.
 */
interface WalletOperationContract {
    interface View : MvpView {
        fun setPeriodicData(data: PeriodicOperation)
        fun setOperationData(data: List<Operation>)
        fun showEditOperationDialog(o: Operation)
        fun showOperationFilter()
    }

    interface Presenter : MvpPresenter<View> {
        fun removePeriodicOperation(o: RepeatableOperation)
        fun loadPeriodic(walletName: String)
        fun loadOperations(walletName: String)
        fun removeOperation(o: Operation)
        fun onEditOperation(o: Operation)
        fun saveEditedOperation(o: Operation)
        fun onOperationFilterClick()
    }
}