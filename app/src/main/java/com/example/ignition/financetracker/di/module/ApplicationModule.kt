package com.example.ignition.financetracker.di.module

import android.app.Application
import com.example.ignition.financetracker.BaseApp
import com.example.ignition.financetracker.di.scope.PerApplication
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class ApplicationModule(private val baseApp: BaseApp) {

    @Provides
    @Singleton
    @PerApplication
    fun provideApplication(): Application {
        return baseApp
    }
}