package com.example.ignition.financetracker.repository

import com.example.ignition.financetracker.entities.Model

import com.example.ignition.financetracker.networking.CurrencyCourseAPI
import io.reactivex.Single


class CurrencyInfoRepository(val currencyCourseAPI: CurrencyCourseAPI) {

    fun usdCourseValue(): Single<Model.Data> {
        return currencyCourseAPI.getCourse()
    }


}
