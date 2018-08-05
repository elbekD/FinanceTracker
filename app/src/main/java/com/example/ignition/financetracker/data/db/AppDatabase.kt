package com.example.ignition.financetracker.data.db

import android.arch.persistence.db.SupportSQLiteDatabase
import android.arch.persistence.room.Database
import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import android.arch.persistence.room.TypeConverters
import android.content.Context
import com.example.ignition.financetracker.data.db.dao.*
import com.example.ignition.financetracker.entities.*
import java.util.concurrent.Executors

/**
 * Created by Elbek D. on 02.08.2018.
 */
@Database(entities = [
    Wallet::class,
    Operation::class,
    Category::class,
    Currency::class,
    ExchangeRate::class,
    RepeatableOperation::class
], version = 1)
@TypeConverters(value = [BigDecimalConverter::class])
abstract class AppDatabase : RoomDatabase() {

    companion object {
        private var INSTANCE: AppDatabase? = null
        @Synchronized
        fun get(ctx: Context, name: String): AppDatabase {
            if (INSTANCE == null) INSTANCE = buildAppDatabase(ctx, name)
            return INSTANCE!!
        }

        private fun buildAppDatabase(ctx: Context, name: String): AppDatabase {
            return Room.databaseBuilder(ctx, AppDatabase::class.java, name)
                    .addCallback(object : Callback() {
                        override fun onCreate(db: SupportSQLiteDatabase) {
                            super.onCreate(db)
                            Executors.newSingleThreadExecutor().execute {
                                with(get(ctx, name)) {
                                    currencyDao().insertAll(Currency("RUB", true),
                                            Currency("USD", true))
                                    categoryDao().insertAll(Category("Food"), Category("House"))
                                }
                            }
                        }
                    }).build()
        }
    }

    abstract fun cardDao(): WalletDao
    abstract fun transactionDao(): OperationDao
    abstract fun categoryDao(): CategoryDao
    abstract fun currencyDao(): CurrencyDao
    abstract fun exchangeRateDao(): ExchangeRateDao
    abstract fun repeatableOperationDao(): RepeatableOperationDao
}