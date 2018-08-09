package com.example.ignition.financetracker.entities

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import java.math.BigDecimal

@Entity(tableName = "operation")
data class Operation(@PrimaryKey(autoGenerate = true)
                     val id: Long = 0,
                     val walletName: String,
                     val operationType: String,
                     val sum: BigDecimal,
                     val currency: String,
                     val date: Long,
                     val rate: BigDecimal = BigDecimal.ONE)

data class OperationFilter(val from: Long,
                           val to: Long,
                           val income: Boolean,
                           val expense: Boolean,
                           val all: Boolean,
                           val category: String,
                           val anyCategory: Boolean)