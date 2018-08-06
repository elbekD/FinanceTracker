package com.example.ignition.financetracker.ui.base

import com.example.ignition.financetracker.data.DataSource
import com.example.ignition.financetracker.utils.SchedulersProvider
import io.reactivex.disposables.CompositeDisposable

/**
 * Created by Elbek D. on 02.08.2018.
 */
abstract class BasePresenter<V : MvpView>(
        val dataSource: DataSource,
        val sp: SchedulersProvider
) : MvpPresenter<V> {

    val compositeDisposable = CompositeDisposable()
    protected var view: V? = null

    override fun attachView(view: V) {
        this.view = view
    }

    override fun detachView() {
        this.view = null
    }

    override fun destroy() {
        compositeDisposable.dispose()
    }

    override fun isViewAttached() = view != null
}