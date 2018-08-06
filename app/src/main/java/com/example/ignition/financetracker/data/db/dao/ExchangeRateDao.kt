package com.example.ignition.financetracker.data.db.dao

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy
import android.arch.persistence.room.Query
import com.example.ignition.financetracker.entities.ExchangeRate
import io.reactivex.Single

@Dao
interface ExchangeRateDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(rate: ExchangeRate)

    @Query("select * from exchange_rate where from_to = :fromTo")
    fun getRate(fromTo: String): Single<ExchangeRate>
}
