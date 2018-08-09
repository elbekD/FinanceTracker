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

@Entity(tableName = "operation_template")
data class OperationTemplate(@PrimaryKey(autoGenerate = true)
                             val id: Long = 0,
                             val walletName: String,
                             val operationType: String,
                             val sum: BigDecimal,
                             val currency: String,
                             val rate: BigDecimal = BigDecimal.ONE)