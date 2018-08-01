package com.example.ignition.financetracker.repository

import android.net.Uri
import com.example.ignition.financetracker.R
import com.example.ignition.financetracker.entities.*
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

    private val cardsData = arrayListOf(
            CardEntity("Visa", BigDecimal(10000), mutableListOf(
                    OperationsModel(AvailableuUserActions.CLOTHES, 200.toBigDecimal(), "10.02.2018"),
                    OperationsModel(AvailableuUserActions.COMMUNAL_PAYMENTS, 250.toBigDecimal(), "12.02.2018"),
                    OperationsModel(AvailableuUserActions.PRODUCTS, 500.toBigDecimal(), "15.02.2018"),
                    OperationsModel(AvailableuUserActions.TRANSFER, 400.toBigDecimal(), "16.02.2018")
            ),
                    R.drawable.card1
            ),
            CardEntity("MasterCard", BigDecimal(20000), mutableListOf(
                    OperationsModel(AvailableuUserActions.CLOTHES, 10.toBigDecimal(), "10.02.2018"),
                    OperationsModel(AvailableuUserActions.COMMUNAL_PAYMENTS, 25.toBigDecimal(), "12.02.2018"),
                    OperationsModel(AvailableuUserActions.PRODUCTS, 50.toBigDecimal(), "15.02.2018"),
                    OperationsModel(AvailableuUserActions.TRANSFER, 40.toBigDecimal(), "16.02.2018")
            ),
                    R.drawable.card2
            ),
            CardEntity("YandexCard", BigDecimal(30000), mutableListOf(
                    OperationsModel(AvailableuUserActions.CLOTHES, 2.toBigDecimal(), "10.02.2018"),
                    OperationsModel(AvailableuUserActions.COMMUNAL_PAYMENTS, 2.toBigDecimal(), "12.02.2018"),
                    OperationsModel(AvailableuUserActions.PRODUCTS, 5.toBigDecimal(), "15.02.2018"),
                    OperationsModel(AvailableuUserActions.TRANSFER, 4.toBigDecimal(), "16.02.2018")
            ),
                    R.drawable.card3
            )
    )

    override fun getCardData(): ArrayList<CardEntity> {
        return cardsData
    }

    override fun getFakeImages() = imageFakeData

    override fun getData() = data

    override fun addItemToRepository(currencyEntity: CurrencyEntity) {
        data.add(currencyEntity)
    }

    override fun currentBalance(): BigDecimal {
        return FinancialOperations.getCurrentBalance(data)
    }

    override fun getOperations(): ArrayList<String>  = fakeOptionsSelect

    override fun addOperations(operation: String) {
        fakeOptionsSelect.add(operation)
    }

    override fun addToCardOperation(operation: OperationsModel, int: Int) {
        this.cardsData[int].cardOperations.add(operation)
    }
}