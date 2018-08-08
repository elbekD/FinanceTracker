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
    fun getWalletOperations(name: String): Single<List<Operation>>
    fun getOperations(): Flowable<List<Operation>>
    fun removeOperation(o: Operation): Single<Boolean>

    // repeatable operation
    fun insertRepeatableOperation(ro: RepeatableOperation)
    fun insertAllRepeatableOperations(ros: List<RepeatableOperation>)
    fun getAllRepeatableOperations(): Single<List<RepeatableOperation>>
    fun getRepeatableOperationsByDate(date: Int): Single<List<RepeatableOperation>>

    // categories
    fun getAllCategories(): Flowable<List<Category>>

    // currency
    fun insertCurrency(c: Currency)
    fun insertAllCurrencies(c: List<Currency>)
    fun getUserCurrencies(): Flowable<List<Currency>>

    // exchange rate
    fun insertRate(r: ExchangeRate)
    fun getRate(from: String, to: String): Single<ExchangeRate>
}