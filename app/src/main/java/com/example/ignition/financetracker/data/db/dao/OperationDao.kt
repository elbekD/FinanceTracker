package com.example.ignition.financetracker.data.db.dao

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy
import android.arch.persistence.room.Query
import com.example.ignition.financetracker.entities.Operation
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
    fun cardOperations(name: String): Single<List<Operation>>

    @Query("select * from operation")
    fun allOperations(): Single<List<Operation>>
}