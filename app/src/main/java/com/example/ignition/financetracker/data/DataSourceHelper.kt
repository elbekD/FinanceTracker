package com.example.ignition.financetracker.data

import com.example.ignition.financetracker.data.db.Database
import com.example.ignition.financetracker.data.networking.CurrencyApi
import com.example.ignition.financetracker.entities.ExchangeRate
import com.example.ignition.financetracker.entities.Operation
import com.example.ignition.financetracker.entities.Wallet
import io.reactivex.Single
import java.util.*

/**
 * Created by Elbek D. on 02.08.2018.
 */
class DataSourceHelper(private val db: Database,
                       private val api: CurrencyApi) : DataSource {

    override fun getWallets() = db.getWallets()
    override fun getWalletByName(name: String) = db.getWalletByName(name)
    override fun getWalletOperations(name: String) = db.getWalletOperations(name)
    override fun insertWallet(c: Wallet) = db.insertWallet(c)
    override fun insertOperation(t: Operation) = db.insertOperation(t)
    override fun getOperations() = db.getOperations()
    override fun getAllCategories() = db.getAllCategories()
    override fun getUserCurrencies() = db.getUserCurrencies()
    override fun getCourse(q: Map<String, String>) = api.getCourse(q)
    override fun insertRate(r: ExchangeRate) = db.insertRate(r)

    // TODO if no internet connection
    override fun getRate(from: String, to: String): Single<ExchangeRate> {
        return db.getRate(from, to)
                .onErrorResumeNext { _ ->
                    val fromTo = "${from}_$to"
                    api.getCourse(mapOf("q" to fromTo, "compact" to "ultra"))
                            .flatMap { rateFromApi ->
                                val rate = rateFromApi[fromTo].asDouble
                                val date = Calendar.getInstance().timeInMillis
                                val exRate = ExchangeRate(fromTo, rate.toBigDecimal(), date)
                                db.insertRate(exRate)
                                Single.just(exRate)
                            }
                }
    }
}