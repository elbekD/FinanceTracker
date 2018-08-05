package com.example.ignition.financetracker.data.db

import android.arch.persistence.room.TypeConverter
import java.math.BigDecimal

/**
 * Created by Elbek D. on 03.08.2018.
 */
class BigDecimalConverter {
    @TypeConverter
    fun fromBigDecimalToString(v: BigDecimal): String {
        return v.toString()
    }

    @TypeConverter
    fun fromStringToBigDecimal(v: String): BigDecimal {
        return BigDecimal(v)
    }
}