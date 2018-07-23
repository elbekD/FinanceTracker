package com.example.ignition.financetracker.FinancialOperations

import java.math.BigDecimal

data class CurrencyEntity(val value: BigDecimal, val moneyLocal: CurrencyType = CurrencyType.RUB, val availableOperations: AvailableOperations = AvailableOperations.NONE)

//possible operations on money
enum class AvailableOperations { INCOME, OUTCOME, NONE }

//types of currency
enum class CurrencyType { RUB, USD }


