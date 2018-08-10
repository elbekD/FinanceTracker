package com.example.ignition.financetracker.data.db

import com.example.ignition.financetracker.entities.*
import io.reactivex.Flowable
import io.reactivex.Single
import java.math.BigDecimal

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
            db.walletDao().insertWallet(c)
            true
        }
    }

    override fun getWallets() = db.walletDao().getWallets()
    override fun getWalletByName(name: String) = db.walletDao().walletByName(name)

    override fun insertOperation(t: Operation) = db.operationDao().insertOperation(t)
    override fun getOperationById(operationId: Long) = db.operationDao().getOperationById(operationId)
    override fun getWalletOperations(name: String) = db.operationDao().walletOperations(name)
    override fun getOperations() = db.operationDao().getOperations()
    override fun walletOperationCount(wName: String) = db.operationDao().walletOperationCount(wName)

    override fun getWalletsOperations(): Flowable<List<WalletOperationsModel>> {
        return db.walletDao().getWalletsOperations()
                .map { wOps ->
                    val res = mutableListOf<WalletOperationsModel>()
                    wOps.forEach { ops ->
                        val income = ops.operations!!.asSequence()
                                .filter { it.sum > BigDecimal.ZERO }
                                .sumByDouble { (it.sum * it.rate).toDouble() }
                                .toBigDecimal()
                        val outcome = ops.operations!!.asSequence()
                                .filter { it.sum < BigDecimal.ZERO }
                                .sumByDouble { (it.sum * it.rate).toDouble() }
                                .toBigDecimal().negate()
                        res.add(WalletOperationsModel(ops.w!!, income, outcome, ops.operations!!))
                    }
                    res
                }
    }

    override fun removeOperation(o: Operation): Single<Boolean> {
        return Single.fromCallable {
            db.operationDao().removeOperation(o)
            true
        }
    }

    override fun getAllCategories() = db.categoryDao().getAllCategories()

    override fun insertRate(r: ExchangeRate) = db.exchangeRateDao().insert(r)
    override fun getRate(from: String, to: String) = db.exchangeRateDao().getRate("${from}_$to")

    override fun insertCurrency(c: Currency) = db.currencyDao().insert(c)
    override fun insertAllCurrencies(c: List<Currency>) = db.currencyDao().insertAll(c)
    override fun getUserCurrencies() = db.currencyDao().getUserCurrencies()

    override fun insertRepeatableOperation(ro: RepeatableOperation) = db.repeatableOperationDao().insert(ro)
    override fun getWalletPeriodicOperations(wName: String) = db.repeatableOperationDao().getWalletPeriodicOperations(wName)
    override fun removePeriodicOperation(ro: RepeatableOperation): Single<Boolean> {
        return Single.fromCallable {
            db.repeatableOperationDao().removePeriodicOperation(ro)
            true
        }
    }

    override fun getRepeatableOperationsByDate(date: Int) = db.repeatableOperationDao().getRepeatableOperationsByDate(date)
    override fun walletRepeatableOperationCount(wName: String) = db.repeatableOperationDao().walletRepeatableOperationCount(wName)
}