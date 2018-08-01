package com.example.ignition.financetracker

import android.app.Application
import com.example.ignition.financetracker.di.component.ApplicationComponent
import com.example.ignition.financetracker.di.component.DaggerApplicationComponent
import com.example.ignition.financetracker.di.module.ApplicationModule

class BaseApp: Application() {

    lateinit var component: ApplicationComponent

    override fun onCreate() {
        super.onCreate()

        instance = this
        setup()

        if (BuildConfig.DEBUG) {
            // some debug options
        }
    }

    fun setup() {
        component = DaggerApplicationComponent.builder()
                .applicationModule(ApplicationModule(this)).build()
        component.inject(this)
    }

    fun getApplicationComponent(): ApplicationComponent {
        return component
    }

    companion object {
        lateinit var instance: BaseApp private set
    }
}