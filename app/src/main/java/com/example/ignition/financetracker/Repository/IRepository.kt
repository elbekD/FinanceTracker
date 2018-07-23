package com.example.ignition.financetracker.Repository

import com.example.ignition.financetracker.FinancialOperations.CurrencyEntity

interface IRepository {
    fun getData() = arrayListOf<CurrencyEntity>()
}