package com.example.ignition.financetracker.di.component

import com.example.ignition.financetracker.BaseApp
import com.example.ignition.financetracker.di.module.ApplicationModule
import dagger.Component

@Component(modules = arrayOf(ApplicationModule::class))
interface ApplicationComponent {

    fun inject(application: BaseApp)

}