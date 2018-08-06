package com.example.ignition.financetracker.entities

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

@Entity(tableName = "wallet")
data class Wallet(
        val name: String,
        val mainCurrency: String,
        val secondaryCurrency: String,
        val type: String,
        @PrimaryKey(autoGenerate = true)
        val id: Long = 0)