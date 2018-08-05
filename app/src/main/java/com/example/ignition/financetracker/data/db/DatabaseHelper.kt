package com.example.ignition.financetracker.data.db

import com.example.ignition.financetracker.entities.ExchangeRate
import com.example.ignition.financetracker.entities.Operation
import com.example.ignition.financetracker.entities.RepeatableOperation
import com.example.ignition.financetracker.entities.Wallet
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

    override fun insertOperation(t: Operation) = db.transactionDao().insertOperation(t)
    override fun getOperationById(operationId: Long) = db.transactionDao().getOperationById(operationId)
    override fun getWalletOperations(name: String) = db.transactionDao().cardOperations(name)
    override fun getOperations() = db.transactionDao().allOperations()
    override fun getAllCategories() = db.categoryDao().getAllCategories()

    override fun getUserCurrencies() = db.currencyDao().getUserCurrencies()
    override fun insertRate(r: ExchangeRate) = db.exchangeRateDao().insert(r)
    override fun getRate(from: String, to: String) = db.exchangeRateDao().getRate("${from}_$to")

    override fun insertRepeatableOperation(ro: RepeatableOperation) = db.repeatableOperationDao().insert(ro)
    override fun insertAllRepeatableOperations(ros: List<RepeatableOperation>) = db.repeatableOperationDao().insertAll(ros)
    override fun getAllRepeatableOperations() = db.repeatableOperationDao().getAllRepeatableOperations()
    override fun getRepeatableOperationsByDate(date: Int) = db.repeatableOperationDao().getRepeatableOperationsByDate(date)
}