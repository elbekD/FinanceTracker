package com.example.ignition.financetracker.ui.main.walletFragment

import com.example.ignition.financetracker.entities.Operation
import com.example.ignition.financetracker.entities.Wallet
import com.example.ignition.financetracker.ui.base.MvpPresenter
import com.example.ignition.financetracker.ui.base.MvpView
import com.example.ignition.financetracker.ui.main.RepeatableOperationModel
import com.example.ignition.financetracker.ui.main.WalletModel
import com.example.ignition.financetracker.ui.main.WalletOperationModel

interface WalletFragmentContract {
    interface View : MvpView {
        fun setCardAdapter(wallets: List<WalletModel>)
        fun addWalletToPager(c: Wallet)
        fun showAddWalletDialog()
        fun showOperationDialog()
        fun showEditOperationDialog(o: Operation)
        fun updateWalletModel(wOp: WalletOperationModel)
        fun showError(msg: String)
        fun closeMenu()
        fun updateOperationsList(ops: List<Operation>)
    }

    interface Presenter : MvpPresenter<WalletFragmentContract.View> {
        fun load()
        fun addWallet(w: Wallet)
        fun commitOperation(rom: RepeatableOperationModel)
        fun onAddWalletClick()
        fun onAddOperationClick()
        fun removeOperation(o: Operation)
        fun onEditOperation(o: Operation)
        fun onWalletSelected(walletName: String)
    }
}