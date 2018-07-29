package com.example.ignition.financetracker.repository

import com.example.ignition.financetracker.financialOperations.AvailableOperations
import com.example.ignition.financetracker.financialOperations.CurrencyEntity
import java.math.BigDecimal

class Repository : IRepository {

    private val data = arrayListOf<CurrencyEntity>(
            CurrencyEntity(BigDecimal.valueOf(300)),
            CurrencyEntity(BigDecimal.valueOf(150)),
            CurrencyEntity(BigDecimal.valueOf(150)),
            CurrencyEntity(BigDecimal.valueOf(100)),
            CurrencyEntity(BigDecimal.valueOf(150)),
            CurrencyEntity(BigDecimal.valueOf(150)),
            CurrencyEntity(BigDecimal.valueOf(150))

    )

    //history of operation will be removed/upgradet in the future
    private val historyOperatrion = arrayListOf<AvailableOperations>(
            AvailableOperations.INCOME,
            AvailableOperations.INCOME,
            AvailableOperations.INCOME,
            AvailableOperations.OUTCOME,
            AvailableOperations.OUTCOME,
            AvailableOperations.OUTCOME

    )
    override fun getData() = data
}