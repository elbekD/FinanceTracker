package com.example.ignition.financetracker.repository

import com.example.ignition.financetracker.R
import com.example.ignition.financetracker.entities.AvailableOperations
import com.example.ignition.financetracker.entities.CurrencyEntity
import com.example.ignition.financetracker.financialOperations.FinancialOperations
import java.math.BigDecimal

class Repository : IRepository {

    private val data = arrayListOf<CurrencyEntity>(
            CurrencyEntity(BigDecimal.valueOf(300), availableOperations = AvailableOperations.INCOME),
            CurrencyEntity(BigDecimal.valueOf(150), availableOperations = AvailableOperations.INCOME),
            CurrencyEntity(BigDecimal.valueOf(150), availableOperations = AvailableOperations.INCOME),
            CurrencyEntity(BigDecimal.valueOf(100), availableOperations = AvailableOperations.INCOME),
            CurrencyEntity(BigDecimal.valueOf(150), availableOperations = AvailableOperations.OUTCOME),
            CurrencyEntity(BigDecimal.valueOf(150), availableOperations = AvailableOperations.OUTCOME),
            CurrencyEntity(BigDecimal.valueOf(150), availableOperations = AvailableOperations.OUTCOME)

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
    private val imageFakeData = arrayListOf<Int>(
            R.drawable.card1,
            R.drawable.card2,
            R.drawable.card3
    )

    private val fakeOptionsSelect = arrayListOf<String>(
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