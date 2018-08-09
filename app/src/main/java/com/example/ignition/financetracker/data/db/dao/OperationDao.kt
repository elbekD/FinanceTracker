package com.example.ignition.financetracker.data.db.dao

import android.arch.persistence.room.*
import com.example.ignition.financetracker.entities.Operation
import io.reactivex.Flowable
import io.reactivex.Single

/**
 * Created by Elbek D. on 02.08.2018.
 */
@Dao
interface OperationDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertOperation(t: Operation): Long

    @Query("select * from operation where id = :operationId")
    fun getOperationById(operationId: Long): Operation

    @Query("select * from operation where walletName = :name")
    fun walletOperations(name: String): Flowable<List<Operation>>

    @Query("select * from operation")
    fun getOperations(): Flowable<List<Operation>>

    @Delete
    fun removeOperation(operation: Operation)

    @Query("select count(*) from operation where walletName = :wName")
    fun walletOperationCount(wName: String): Single<Int>
}