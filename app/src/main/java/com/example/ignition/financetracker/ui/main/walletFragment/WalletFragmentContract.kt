package com.example.ignition.financetracker.ui.main.walletFragment

import com.example.ignition.financetracker.entities.Wallet
import com.example.ignition.financetracker.ui.base.MvpPresenter
import com.example.ignition.financetracker.ui.base.MvpView
import com.example.ignition.financetracker.ui.main.RepeatableOperationModel
import com.example.ignition.financetracker.ui.main.WalletModel
import com.example.ignition.financetracker.ui.main.WalletOperationModel

interface CardFragmentContract {
    interface View : MvpView {
        fun setCardAdapter(wallets: List<WalletModel>)
        fun addWalletToPager(c: Wallet)
        fun showAddWalletDialog()
        fun showTransactionDialog()
        fun updateWalletModel(wOp: WalletOperationModel)
        fun showError(msg: String)
    }

    interface Presenter : MvpPresenter<CardFragmentContract.View> {
        fun load()
        fun addWallet(w: Wallet)
        fun commitOperation(rom: RepeatableOperationModel)
        fun onOpenAddWalletClick()
        fun onAddTransactionClick()
    }
}