package com.example.ignition.financetracker.repository

import com.example.ignition.financetracker.financialOperations.CurrencyEntity

interface IRepository {
    fun getData() = arrayListOf<CurrencyEntity>()
}