package com.example.ignition.financetracker.data.db.dao

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy
import android.arch.persistence.room.Query
import com.example.ignition.financetracker.entities.RepeatableOperation
import io.reactivex.Single

/**
 * Created by Elbek D. on 06.08.2018.
 */
@Dao
interface RepeatableOperationDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(ro: RepeatableOperation)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(ros: List<RepeatableOperation>)

    @Query("select * from repeatable_operation")
    fun getAllRepeatableOperations(): Single<List<RepeatableOperation>>

    @Query("select * from repeatable_operation where periodDate = :date")
    fun getRepeatableOperationsByDate(date: Int): Single<List<RepeatableOperation>>
}