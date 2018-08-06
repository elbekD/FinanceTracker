package com.example.ignition.financetracker

import com.example.ignition.financetracker.entities.*

/**
 * Created by Elbek D. on 06.08.2018.
 */
object TestUtils {
    fun createCurrency() = Currency("USD", true)

    fun createOperation() = Operation("test",
            "food",
            10.toBigDecimal(),
            "USD",
            0)

    fun createCategory() = Category("food")

    fun createExchangeRate() = ExchangeRate("USD_RUB", 63.toBigDecimal(), 0)

    fun createWallet() = Wallet("wallet", "USD", "RUB", "CASH")
}