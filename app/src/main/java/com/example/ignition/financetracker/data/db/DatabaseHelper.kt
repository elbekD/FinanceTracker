package com.example.ignition.financetracker.data.db

import com.example.ignition.financetracker.entities.*
import io.reactivex.Single

/**
 * Created by Elbek D. on 02.08.2018.
 */
class DatabaseHelper private constructor(private val db: AppDatabase) : Database {
    companion object {
        private var INSTANCE: Database? = null
        fun get(db: AppDatabase): Database {
            if (INSTANCE == null)
                INSTANCE = DatabaseHelper(db)
            return INSTANCE!!
        }
    }

    override fun insertWallet(c: Wallet): Single<Boolean> {
        return Single.fromCallable {
            db.cardDao().insertWallet(c)
            true
        }
    }

    override fun getWallets() = db.cardDao().allWallet()
    override fun getWalletByName(name: String) = db.cardDao().walletByName(name)

    override fun insertOperation(t: Operation) = db.operationDao().insertOperation(t)
    override fun getOperationById(operationId: Long) = db.operationDao().getOperationById(operationId)
    override fun getWalletOperations(name: String) = db.operationDao().walletOperations(name)
    override fun getOperations() = db.operationDao().allOperations()
    override fun getAllCategories() = db.categoryDao().getAllCategories()

    override fun insertRate(r: ExchangeRate) = db.exchangeRateDao().insert(r)
    override fun getRate(from: String, to: String) = db.exchangeRateDao().getRate("${from}_$to")

    override fun insertCurrency(c: Currency) = db.currencyDao().insert(c)
    override fun insertAllCurrencies(c: List<Currency>) = db.currencyDao().insertAll(c)
    override fun getUserCurrencies() = db.currencyDao().getUserCurrencies()

    override fun insertRepeatableOperation(ro: RepeatableOperation) = db.repeatableOperationDao().insert(ro)
    override fun insertAllRepeatableOperations(ros: List<RepeatableOperation>) = db.repeatableOperationDao().insertAll(ros)
    override fun getAllRepeatableOperations() = db.repeatableOperationDao().getAllRepeatableOperations()
    override fun getRepeatableOperationsByDate(date: Int) = db.repeatableOperationDao().getRepeatableOperationsByDate(date)
}