package com.example.ignition.financetracker.Repository

import com.example.ignition.financetracker.FinancialOperations.CurrencyEntity
import java.math.BigDecimal

class Repository : IRepository {

    private val data = arrayListOf<CurrencyEntity>(
            CurrencyEntity(BigDecimal.valueOf(300)),
            CurrencyEntity(BigDecimal.valueOf(150)),
            CurrencyEntity(BigDecimal.valueOf(150)),
            CurrencyEntity(BigDecimal.valueOf(100))
    )

    override fun getData() = data
}