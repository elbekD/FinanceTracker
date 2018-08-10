package com.example.ignition.financetracker.data.db.dao

import android.arch.persistence.room.*
import com.example.ignition.financetracker.entities.Wallet
import com.example.ignition.financetracker.entities.WalletOperations
import io.reactivex.Flowable
import io.reactivex.Single

/**
 * Created by Elbek D. on 02.08.2018.
 */
@Dao
interface WalletDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertWallet(w: Wallet)

    @Query("select * from wallet")
    fun getWallets(): Single<List<Wallet>>

    @Query("select * from wallet where name = :walletName")
    fun walletByName(walletName: String): Single<Wallet>

    @Transaction
    @Query("select * from wallet")
    fun getWalletsOperations(): Flowable<List<WalletOperations>>
}