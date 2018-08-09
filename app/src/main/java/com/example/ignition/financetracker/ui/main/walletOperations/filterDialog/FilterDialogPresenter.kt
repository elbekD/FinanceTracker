package com.example.ignition.financetracker.ui.main.walletOperations.filterDialog

import com.example.ignition.financetracker.data.DataSource
import com.example.ignition.financetracker.ui.base.BasePresenter
import com.example.ignition.financetracker.utils.SchedulersProvider

/**
 * Created by Elbek D. on 09.08.2018.
 */
class FilterDialogPresenter(dataSource: DataSource, sp: SchedulersProvider) :
        BasePresenter<FilterDialogContract.View>(dataSource, sp), FilterDialogContract.Presenter {

    override fun load() {
        compositeDisposable.add(dataSource.getAllCategories()
                .subscribeOn(sp.io())
                .observeOn(sp.ui())
                .subscribe { c -> view?.setCategoryAdapter(c) })
    }
}