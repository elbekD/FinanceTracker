package com.example.ignition.financetracker.data.db.dao

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy
import android.arch.persistence.room.Query
import com.example.ignition.financetracker.entities.Wallet
import io.reactivex.Single

/**
 * Created by Elbek D. on 02.08.2018.
 */
@Dao
interface WalletDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertWallet(w: Wallet)

    @Query("select * from wallet")
    fun allWallet(): Single<List<Wallet>>

    @Query("select * from wallet where name = :walletName")
    fun walletByName(walletName: String): Single<Wallet>
}