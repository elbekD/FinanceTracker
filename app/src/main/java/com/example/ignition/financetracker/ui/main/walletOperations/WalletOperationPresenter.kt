package com.example.ignition.financetracker.ui.main.walletOperations

import com.example.ignition.financetracker.data.DataSource
import com.example.ignition.financetracker.entities.Operation
import com.example.ignition.financetracker.entities.RepeatableOperation
import com.example.ignition.financetracker.ui.base.BasePresenter
import com.example.ignition.financetracker.utils.SchedulersProvider
import io.reactivex.Observable

/**
 * Created by Elbek D. on 09.08.2018.
 */
class WalletOperationPresenter(dataSource: DataSource,
                               sp: SchedulersProvider
) : BasePresenter<WalletOperationContract.View>(dataSource, sp), WalletOperationContract.Presenter {

    override fun removePeriodicOperation(o: RepeatableOperation) {
        compositeDisposable.add(dataSource.removePeriodicOperation(o)
                .subscribeOn(sp.io())
                .subscribe())
    }

    override fun loadPeriodic(walletName: String) {
        compositeDisposable.add(dataSource.getWalletPeriodicOperations(walletName)
                .subscribeOn(sp.io())
                .observeOn(sp.ui())
                .subscribe { ops -> view?.setPeriodicData(ops) })
    }

    override fun loadOperations(walletName: String) {
        compositeDisposable.add(dataSource.getWalletOperations(walletName)
                .subscribeOn(sp.io())
                .observeOn(sp.ui())
                .subscribe { ops -> view?.setOperationData(ops) })
    }

    override fun onEditOperation(o: Operation) {
        view?.showEditOperationDialog(o)
    }

    override fun removeOperation(o: Operation) {
        compositeDisposable.add(dataSource.removeOperation(o)
                .subscribeOn(sp.io())
                .subscribe())
    }

    override fun saveEditedOperation(o: Operation) {
        Observable.fromCallable { dataSource.insertOperation(o) }.subscribeOn(sp.io()).subscribe()
    }

    override fun onOperationFilterClick() {
        view?.showOperationFilter()
    }
}