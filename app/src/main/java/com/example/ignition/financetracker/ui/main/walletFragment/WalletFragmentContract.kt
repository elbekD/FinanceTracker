package com.example.ignition.financetracker.ui.main.walletFragment

import com.example.ignition.financetracker.entities.OperationTemplate
import com.example.ignition.financetracker.entities.Wallet
import com.example.ignition.financetracker.entities.WalletOperationsModel
import com.example.ignition.financetracker.ui.base.MvpPresenter
import com.example.ignition.financetracker.ui.base.MvpView
import com.example.ignition.financetracker.ui.main.RepeatableOperationModel

interface WalletFragmentContract {
    interface View : MvpView {
        fun loadWallets(wallets: List<WalletOperationsModel>)
        //        fun addWalletToPager(w: Wallet)
        fun showAddWalletDialog()
        fun showOperationDialog()
        fun showError(msg: Int)
        fun showError(msg: String)
        fun openHistoryFragment(walletName: String)
        fun openPeriodicFragment(walletName: String)
    }

    interface Presenter : MvpPresenter<WalletFragmentContract.View> {
        fun load()
        fun addWallet(w: Wallet)
        fun commitOperation(rom: RepeatableOperationModel)
        fun onAddWalletClick()
        fun onAddOperationClick()
        fun onWalletHistoryClick(walletName: String)
        fun onWalletPeriodicClick(walletName: String)
        fun addTemplateOperation(t: OperationTemplate)
    }
}