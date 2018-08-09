package com.example.ignition.financetracker.data.db.dao

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy
import android.arch.persistence.room.Query
import com.example.ignition.financetracker.entities.Category
import io.reactivex.Flowable
import io.reactivex.Single

/**
 * Created by Elbek D. on 03.08.2018.
 */
@Dao
interface CategoryDao {
    @Query("select * from category")
    fun getAllCategories(): Single<List<Category>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(c: List<Category>)
}