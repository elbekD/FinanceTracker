package com.example.ignition.financetracker.ui.billActivity

import io.reactivex.disposables.CompositeDisposable

class BillScreenPresenter : IBillScreenContract.Presenter {
    lateinit var view: IBillScreenContract.View

    private val subscriptions = CompositeDisposable()

    override fun subscribe() {

    }

    override fun unsubscribe() {
        subscriptions.clear()
    }

    override fun attach(view: IBillScreenContract.View) {
        this.view = view
        view.showCardsFragment()
    }

    override fun onAddOperationClicked() {
        view.showAddOperationDialog()
    }

    override fun onListOpenClicked() {
        view.showOperationList()
    }

}