package com.example.ignition.financetracker.data

import com.example.ignition.financetracker.data.db.Database
import com.example.ignition.financetracker.data.networking.CurrencyApi
import com.example.ignition.financetracker.entities.RepeatableOperation
import io.reactivex.Single

/**
 * Created by Elbek D. on 02.08.2018.
 */
interface DataSource : Database, CurrencyApi {
    fun repeatOperation(ro: RepeatableOperation)
    fun hasWallets(): Single<Boolean>
    fun hasPeriodicOperations(wName: String): Single<Boolean>
    fun hasOperations(wName: String): Single<Boolean>
}