package com.example.ignition.financetracker.db

import android.arch.persistence.room.Room
import com.example.ignition.financetracker.data.db.AppDatabase
import android.support.test.InstrumentationRegistry
import org.junit.After
import org.junit.Before

/**
 * Created by Elbek D. on 06.08.2018.
 */
abstract class DbTest {
    private lateinit var _db: AppDatabase
    val db: AppDatabase
        get() = _db

    @Before
    fun initDb() {
        _db = Room.inMemoryDatabaseBuilder(
                InstrumentationRegistry.getContext(),
                AppDatabase::class.java
        ).build()
    }

    @After
    fun closeDb() {
        _db.close()
    }
}
