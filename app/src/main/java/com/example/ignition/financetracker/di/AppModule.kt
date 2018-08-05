package com.example.ignition.financetracker.di

import android.content.Context
import com.example.ignition.financetracker.BaseApp
import com.example.ignition.financetracker.BuildConfig
import com.example.ignition.financetracker.data.DataSource
import com.example.ignition.financetracker.data.DataSourceHelper
import com.example.ignition.financetracker.data.db.AppDatabase
import com.example.ignition.financetracker.data.db.Database
import com.example.ignition.financetracker.data.db.DatabaseHelper
import com.example.ignition.financetracker.data.networking.CurrencyApi
import com.example.ignition.financetracker.utils.SchedulersProvider
import com.example.ignition.financetracker.utils.SchedulersProviderImpl
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Created by Elbek D. on 02.08.2018.
 */
object AppModule {
    fun provideCurrencyApi(): CurrencyApi {
        return CurrencyService.get()
    }

    fun provideOkHttpClient(): OkHttpClient {
        return OkHttpClient()
    }

    fun provideAppDatabase(ctx: Context = BaseApp.instance): AppDatabase {
        return AppDatabase.get(ctx, BuildConfig.DB_NAME)
    }

    fun provideDatabase(db: AppDatabase = provideAppDatabase()): Database {
        return DatabaseHelper.get(db)
    }

    fun provideDataSource(db: Database = provideDatabase(),
                          api: CurrencyApi = provideCurrencyApi()): DataSource {
        return DataSourceHelper(db, api)
    }

    fun provideSchedulerProvider(): SchedulersProvider {
        return SchedulersProviderImpl
    }

    private object CurrencyService {
        private val instance = with(Retrofit.Builder()) {
            client(provideOkHttpClient())
            baseUrl(BuildConfig.BASE_URL)
            addConverterFactory(GsonConverterFactory.create())
            addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            build().create(CurrencyApi::class.java)
        }

        fun get(): CurrencyApi = instance
    }
}