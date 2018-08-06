package com.example.ignition.financetracker.entities

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import java.math.BigDecimal

/**
 * Created by Elbek D. on 04.08.2018.
 */

/**
 * [fromTo] must be like USD_RUB
 */
@Entity(tableName = "exchange_rate")
data class ExchangeRate(
        @PrimaryKey
        @ColumnInfo(name = "from_to")
        val fromTo: String,
        val rate: BigDecimal,
        @ColumnInfo(name = "last_update")
        val lastUpdate: Long) {

    companion object {
        fun default(fromTo: String): ExchangeRate {
            return ExchangeRate(fromTo, BigDecimal.ONE, 0L)
        }
    }
}