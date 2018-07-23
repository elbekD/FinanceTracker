package com.example.ignition.financetracker.FinancialOperations

import java.math.BigDecimal

//financial operations
class CurrencyOperation {

    companion object {
        /**
         * Method to get current balance after operatins
         * @param listOfOperations - get list of operations, which were made
         * @return - total balance
         * **/
        fun getCurrentBalance(listOfOperations: List<CurrencyEntity>): BigDecimal {
            var totalBalance = BigDecimal.ZERO
            for (element in listOfOperations) {
                totalBalance += element.value
            }

            return totalBalance
        }

        /**
         * Convert one currency to another
         * @param amount - currency, which will be converted
         * @oaram coefficient - coef for converting
         * @return - amount after converting
         * **/
        fun convertCurrency(amount: BigDecimal, coefficient: BigDecimal): BigDecimal = amount * coefficient

        /**
         * Method to operate with INCOM, OUTCOME operations
         * @param - current balance
         * @param moneyToOperate - sum of money which were used in operation
         * @param operation - type of operation
         * @return balance after operations
         */
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