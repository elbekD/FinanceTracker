package com.example.ignition.financetracker.entities

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

@Entity(tableName = "wallet")
data class Wallet(
        @PrimaryKey(autoGenerate = true)
        val id: Long = 0,
        val name: String,
        val mainCurrency: String,
        val secondaryCurrency: String,
        val type: String)