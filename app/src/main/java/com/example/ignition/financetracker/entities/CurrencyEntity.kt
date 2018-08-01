package com.example.ignition.financetracker.entities

import java.math.BigDecimal

data class CurrencyEntity(val value: BigDecimal, val moneyLocal: CurrencyType = CurrencyType.RUB, val availableOperations: AvailableOperations = AvailableOperations.NONE)

//possible operations on money
enum class AvailableOperations { INCOME, OUTCOME, NONE }

enum class AvailableuUserActions { PRODUCTS, CLOTHES, COMMUNAL_PAYMENTS,  TRANSFER}

//types of currency
enum class CurrencyType { RUB, USD }

enum class OperationsType {INCOME, OUTCOME}
