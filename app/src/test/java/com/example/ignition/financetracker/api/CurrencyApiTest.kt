package com.example.ignition.financetracker.api

import com.example.ignition.financetracker.BuildConfig
import com.example.ignition.financetracker.data.networking.CurrencyApi
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Created by Elbek D. on 06.08.2018.
 */
@RunWith(JUnit4::class)
class CurrencyApiTest {
    private lateinit var service: CurrencyApi

    @Before
    fun createService() {
        service = Retrofit.Builder()
                .baseUrl(BuildConfig.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build()
                .create(CurrencyApi::class.java)
    }

    @Test
    fun getUsdToRubExchangeRate() {
        val response = service.getCourse(mapOf("q" to "USD_RUB", "compact" to "ultra")).test()
        response.assertNoErrors()
        response.assertValue { it.has("USD_RUB") }
    }
}