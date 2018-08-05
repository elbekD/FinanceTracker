package com.example.ignition.financetracker.data.db.dao

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy
import android.arch.persistence.room.Query
import com.example.ignition.financetracker.entities.Currency
import io.reactivex.Single

/**
 * Created by Elbek D. on 03.08.2018.
 */
@Dao
interface CurrencyDao {
    @Query("select * from currency")
    fun getUserCurrencies(): Single<List<Currency>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(vararg c: Currency)
}