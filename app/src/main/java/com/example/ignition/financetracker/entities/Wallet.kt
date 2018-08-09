package com.example.ignition.financetracker.entities

import android.arch.persistence.room.Embedded
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import android.arch.persistence.room.Relation
import java.math.BigDecimal

@Entity(tableName = "wallet")
data class Wallet(
        @PrimaryKey(autoGenerate = true)
        val id: Long = 0,
        val name: String,
        val mainCurrency: String,
        val secondaryCurrency: String,
        val type: String)

class WalletOperations {
    @Embedded
    var w: Wallet? = null
    @Relation(entity = Operation::class,
            parentColumn = "name",
            entityColumn = "walletName")
    var operations: List<Operation>? = null
}

data class WalletOperationsModel(val wallet: Wallet,
                                 val income: BigDecimal,
                                 val expense: BigDecimal,
                                 val operations: List<Operation>,
                                 val balance: BigDecimal = income - expense)