package com.example.ignition.financetracker.adapters

import com.example.ignition.financetracker.entities.Wallet
import com.example.ignition.financetracker.ui.main.WalletModel
import java.math.BigDecimal

/**
 * Created by Elbek D. on 04.08.2018.
 */
fun createWalletModelFrom(w: Wallet) = with(w) {
    val initialBalance = BigDecimal.ZERO
    WalletModel(w.name, initialBalance, initialBalance, initialBalance, emptyList())
}