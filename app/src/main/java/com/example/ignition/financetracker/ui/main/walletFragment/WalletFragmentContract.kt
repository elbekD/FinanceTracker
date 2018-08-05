package com.example.ignition.financetracker.ui.main.walletFragment

import com.example.ignition.financetracker.entities.Operation
import com.example.ignition.financetracker.entities.Wallet
import com.example.ignition.financetracker.ui.base.MvpPresenter
import com.example.ignition.financetracker.ui.base.MvpView
import java.math.BigDecimal

interface CardFragmentContract {
    interface View : MvpView {
        fun setCardAdapter(wallets: List<WalletModel>)
        fun addWalletToPager(c: Wallet)
        fun showAddWalletDialog()
        fun showTransactionDialog()
        fun updateWalletModel(wOp: WalletOperation)
        fun showError(msg: String)
    }

    interface Presenter : MvpPresenter<CardFragmentContract.View> {
        fun load()
        fun addWallet(w: Wallet)
        fun commitOperation(o: Operation)
        fun onOpenAddWalletClick()
        fun onAddTransactionClick()
    }
}

data class WalletModel(val w: Wallet,
                       val balance: BigDecimal,
                       val secondaryBalance: BigDecimal,
                       val incomeValue: BigDecimal,
                       val outcomeValue: BigDecimal)

data class WalletOperation(val operation: Operation,
                           val mainToSecondaryRate: BigDecimal)