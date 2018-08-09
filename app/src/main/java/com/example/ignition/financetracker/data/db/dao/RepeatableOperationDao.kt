package com.example.ignition.financetracker.data.db.dao

import android.arch.persistence.room.*
import com.example.ignition.financetracker.entities.Operation
import com.example.ignition.financetracker.entities.PeriodicOperation
import com.example.ignition.financetracker.entities.RepeatableOperation
import io.reactivex.Single

/**
 * Created by Elbek D. on 06.08.2018.
 */
@Dao
interface RepeatableOperationDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(ro: RepeatableOperation)

    @Query("select count(*) from repeatable_operation where walletName = :wName")
    fun walletRepeatableOperationCount(wName: String): Single<Int>

    @Transaction
    @Query("select * from repeatable_operation where walletName = :wName")
    fun getWalletPeriodicOperations(wName: String): Single<PeriodicOperation>

    @Delete
    fun removePeriodicOperation(ro: RepeatableOperation)

    @Transaction
    @Query("select * from repeatable_operation where periodDate = :date")
    fun getRepeatableOperationsByDate(date: Int): Single<List<RepeatableOperation>>
}