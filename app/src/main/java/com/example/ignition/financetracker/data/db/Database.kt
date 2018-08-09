package com.example.ignition.financetracker.data.db

import com.example.ignition.financetracker.entities.*
import io.reactivex.Flowable
import io.reactivex.Single

/**
 * Created by Elbek D. on 02.08.2018.
 */
interface Database {
    // card
    fun insertWallet(c: Wallet): Single<Boolean>
    fun getWallets(): Single<List<Wallet>>
    fun getWalletByName(name: String): Single<Wallet>

    // operation
    fun insertOperation(t: Operation): Long
    fun getOperationById(operationId: Long): Operation
    fun getWalletOperations(name: String): Flowable<List<Operation>>
    fun getOperations(): Flowable<List<Operation>>
    fun removeOperation(o: Operation): Single<Boolean>
    fun getWalletsOperations(): Flowable<List<WalletOperationsModel>>
    fun walletOperationCount(wName: String): Single<Int>

    // repeatable operation
    fun insertRepeatableOperation(ro: RepeatableOperation)
    fun getWalletPeriodicOperations(wName: String): Single<PeriodicOperation>
    fun removePeriodicOperation(ro: RepeatableOperation): Single<Boolean>
    fun getRepeatableOperationsByDate(date: Int): Single<List<RepeatableOperation>>
    fun walletRepeatableOperationCount(wName: String): Single<Int>

    // categories
    fun getAllCategories(): Single<List<Category>>

    // currency
    fun insertCurrency(c: Currency)
    fun insertAllCurrencies(c: List<Currency>)
    fun getUserCurrencies(): Flowable<List<Currency>>

    // exchange rate
    fun insertRate(r: ExchangeRate)
    fun getRate(from: String, to: String): Single<ExchangeRate>
}