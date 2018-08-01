package com.example.ignition.financetracker.financialOperations

import com.example.ignition.financetracker.entities.AvailableOperations
import com.example.ignition.financetracker.entities.CurrencyEntity
import java.math.BigDecimal

//financial operations
class FinancialOperations {

    companion object {
        fun getCurrentBalance(listOfOperations: List<CurrencyEntity>): BigDecimal {
            var totalBalance = BigDecimal.ZERO
            for (element in listOfOperations) {
                totalBalance += element.value
            }

            return totalBalance
        }


        fun convertCurrency(amount: BigDecimal, coefficient: BigDecimal): BigDecimal = amount * coefficient


        fun resultAfterOperation(balance: BigDecimal, moneyToOperate: BigDecimal, operation: AvailableOperations): BigDecimal {
            when (operation) {
                AvailableOperations.INCOME -> balance + moneyToOperate
                AvailableOperations.OUTCOME -> balance - moneyToOperate
                AvailableOperations.NONE -> balance
            }

            return balance
        }

    }

}