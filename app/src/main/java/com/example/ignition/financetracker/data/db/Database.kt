package com.example.ignition.financetracker.data.db

import com.example.ignition.financetracker.entities.*
import io.reactivex.Maybe
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
    fun insertOperation(t: Operation)
    fun getWalletOperations(name: String): Single<List<Operation>>
    fun getOperations(): Single<List<Operation>>

    // categories
    fun getAllCategories(): Single<List<Category>>

    // currency
    fun getUserCurrencies(): Single<List<Currency>>

    // exchange rate
    fun insertRate(r: ExchangeRate)
    fun getRate(from: String, to: String): Single<ExchangeRate>
}