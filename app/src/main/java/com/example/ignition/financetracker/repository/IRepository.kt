package com.example.ignition.financetracker.repository

import com.example.ignition.financetracker.entities.CardEntity
import com.example.ignition.financetracker.entities.CurrencyEntity
import com.example.ignition.financetracker.entities.OperationsModel
import java.math.BigDecimal
import java.text.FieldPosition

interface IRepository {
    fun getData() = arrayListOf<CurrencyEntity>()
    fun getFakeImages() = arrayListOf<Int>()
    fun addItemToRepository(currencyEntity: CurrencyEntity)
    fun currentBalance(): BigDecimal
    fun getOperations() = arrayListOf<String>()
    fun addOperations(operation: String)
    fun getCardData() = arrayListOf<CardEntity>()
    fun addToCardOperation(operation: OperationsModel, position: Int)
}