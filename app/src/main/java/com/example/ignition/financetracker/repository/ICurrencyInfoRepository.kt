package com.example.ignition.financetracker.repository

import com.example.ignition.financetracker.networking.CurrencyCourseAPI

object ICurrencyInfoRepository {
    fun provideCurrencyCurseValue(): CurrencyInfoRepository {
        return CurrencyInfoRepository(CurrencyCourseAPI.create())
    }
}