package com.example.ignition.financetracker.repository

import com.example.ignition.financetracker.entities.CurrencyEntity
import java.math.BigDecimal

interface IRepository {
    fun getData() = arrayListOf<CurrencyEntity>()
    fun getFakeImages() = arrayListOf<Int>()
    fun addItemToRepository(currencyEntity: CurrencyEntity)
    fun currentBalance(): BigDecimal
    fun getOperations() = arrayListOf<String>()
    fun addOperations(operation: String)

}