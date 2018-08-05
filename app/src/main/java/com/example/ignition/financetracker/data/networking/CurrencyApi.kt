package com.example.ignition.financetracker.data.networking

import com.google.gson.JsonObject
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.QueryMap

/**
 * Created by Elbek D. on 02.08.2018.
 */
interface CurrencyApi {
    @GET("v6/convert")
    fun getCourse(@QueryMap q: Map<String, String>): Single<JsonObject>
}