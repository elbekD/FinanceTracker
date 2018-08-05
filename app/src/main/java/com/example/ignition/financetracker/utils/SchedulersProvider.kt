package com.example.ignition.financetracker.utils

import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

/**
 * Created by Elbek D. on 02.08.2018.
 */
interface SchedulersProvider {
    fun io() = Schedulers.io()
    fun ui() = AndroidSchedulers.mainThread()
    fun computation() = Schedulers.computation()
}

object SchedulersProviderImpl : SchedulersProvider