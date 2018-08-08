package com.example.ignition.financetracker.data

import com.example.ignition.financetracker.data.db.Database
import com.example.ignition.financetracker.data.networking.CurrencyApi
import com.example.ignition.financetracker.entities.*
import com.example.ignition.financetracker.entities.Currency
import io.reactivex.Single
import java.util.*

/**
 * Created by Elbek D. on 02.08.2018.
 */
class DataSourceHelper(private val db: Database,
                       private val api: CurrencyApi) : DataSource {

    override fun insertWallet(c: Wallet) = db.insertWallet(c)
    override fun getWallets() = db.getWallets()
    override fun getWalletByName(name: String) = db.getWalletByName(name)
    override fun getWalletOperations(name: String) = db.getWalletOperations(name)

    override fun insertOperation(t: Operation) = db.insertOperation(t)
    override fun getOperationById(operationId: Long) = db.getOperationById(operationId)
    override fun getOperations() = db.getOperations()

    override fun getAllCategories() = db.getAllCategories()

    override fun insertRate(r: ExchangeRate) = db.insertRate(r)
    override fun getUserCurrencies() = db.getUserCurrencies()
    override fun getCourse(q: Map<String, String>) = api.getCourse(q)

    override fun insertCurrency(c: Currency) = db.insertCurrency(c)
    override fun insertAllCurrencies(c: List<Currency>) = db.insertAllCurrencies(c)

    // TODO if no internet connection
    override fun getRate(from: String, to: String): Single<ExchangeRate> {
        val fromTo = "${from}_$to"
        if (from == to)
            return Single.just(ExchangeRate.default(fromTo))

        return api.getCourse(mapOf("q" to fromTo, "compact" to "ultra"))
                .flatMap { rateFromApi ->
                    val rate = rateFromApi[fromTo].asDouble
                    val date = Calendar.getInstance().timeInMillis
                    val exRate = ExchangeRate(fromTo, rate.toBigDecimal(), date)
                    db.insertRate(exRate)
                    Single.just(exRate)
                }
                .onErrorResumeNext { _ -> db.getRate(from, to) }
    }

    override fun insertRepeatableOperation(ro: RepeatableOperation) = db.insertRepeatableOperation(ro)
    override fun insertAllRepeatableOperations(ros: List<RepeatableOperation>) = db.insertAllRepeatableOperations(ros)
    override fun getAllRepeatableOperations() = db.getAllRepeatableOperations()
    override fun getRepeatableOperationsByDate(date: Int) = db.getRepeatableOperationsByDate(date)

    override fun repeatOperation(ro: RepeatableOperation) {
        val operation = db.getOperationById(ro.operationId)

        // todo update exchange rate
        val repeatOperation = operation.copy(date = Calendar.getInstance().timeInMillis, id = 0)
        db.insertOperation(repeatOperation)

        // todo yes its awful but it is too late
        val nextPeriod = ro.periodDate / 100 + (ro.periodDate % 100 + 1) % 12
        db.insertRepeatableOperation(ro.copy(periodDate = nextPeriod))
    }

    override fun removeOperation(o: Operation) = db.removeOperation(o)
}