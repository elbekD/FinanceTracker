package com.example.ignition.financetracker.repository

import com.example.ignition.financetracker.entities.CurrencyCourseModel

import com.example.ignition.financetracker.networking.CurrencyCourseAPI
import io.reactivex.Single


class CurrencyInfoRepository(private val currencyCourseAPI: CurrencyCourseAPI) {

    fun usdCourseValue(): Single<CurrencyCourseModel.Data> {
        return currencyCourseAPI.getCourse()
    }


}
