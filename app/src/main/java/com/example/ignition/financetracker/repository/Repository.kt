package com.example.ignition.financetracker.repository

import com.example.ignition.financetracker.R
import com.example.ignition.financetracker.entities.AvailableOperations
import com.example.ignition.financetracker.entities.CurrencyEntity
import com.example.ignition.financetracker.financialOperations.FinancialOperations
import java.math.BigDecimal

class Repository : IRepository {

    private val data = arrayListOf(
            CurrencyEntity(BigDecimal.valueOf(300), availableOperations = AvailableOperations.INCOME),
            CurrencyEntity(BigDecimal.valueOf(150), availableOperations = AvailableOperations.INCOME),
            CurrencyEntity(BigDecimal.valueOf(150), availableOperations = AvailableOperations.INCOME),
            CurrencyEntity(BigDecimal.valueOf(100), availableOperations = AvailableOperations.INCOME),
            CurrencyEntity(BigDecimal.valueOf(150), availableOperations = AvailableOperations.OUTCOME),
            CurrencyEntity(BigDecimal.valueOf(150), availableOperations = AvailableOperations.OUTCOME),
            CurrencyEntity(BigDecimal.valueOf(150), availableOperations = AvailableOperations.OUTCOME)

    )

    private val imageFakeData = arrayListOf(
            R.drawable.card1,
            R.drawable.card2,
            R.drawable.card3
    )

    private val fakeOptionsSelect = arrayListOf(
            "Dress",
            "ComunalPayments",
            "Food",
            "IncomeMoney"
    )

    override fun getFakeImages() = imageFakeData

    override fun getData() = data

    override fun addItemToRepository(currencyEntity: CurrencyEntity) {
        data.add(currencyEntity)
    }

    override fun currentBalance(): BigDecimal {
        return FinancialOperations.getCurrentBalance(data)
    }

    override fun getOperations(): ArrayList<String> {
        return fakeOptionsSelect
    }

    override fun addOperations(operation: String) {
        fakeOptionsSelect.add(operation)
    }
}