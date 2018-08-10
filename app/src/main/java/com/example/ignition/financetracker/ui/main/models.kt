package com.example.ignition.financetracker.ui.main

import com.example.ignition.financetracker.entities.Operation
import com.example.ignition.financetracker.entities.Wallet
import java.math.BigDecimal

/**
 * Created by Elbek D. on 06.08.2018.
 */
data class RepeatableOperationModel(val operation: Operation, val repeatDate: Int)

data class WalletModel(val walletName: String,
                       val balance: BigDecimal,
                       val incomeValue: BigDecimal,
                       val outcomeValue: BigDecimal,
                       val operations: List<Operation>)

data class OperationFilter(val from: Long,
                           val to: Long,
                           val income: Boolean,
                           val expense: Boolean,
                           val all: Boolean,
                           val category: String,
                           val anyCategory: Boolean)