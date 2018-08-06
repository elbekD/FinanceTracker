package com.example.ignition.financetracker.ui.base

/**
 * Created by Elbek D. on 02.08.2018.
 */
interface MvpPresenter<V : MvpView> {
    fun attachView(view: V)
    fun detachView()
    fun destroy()
    fun isViewAttached(): Boolean
}