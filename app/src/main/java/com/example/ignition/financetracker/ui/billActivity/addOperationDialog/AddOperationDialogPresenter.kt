package com.example.ignition.financetracker.ui.billActivity.addOperationDialog

import com.example.ignition.financetracker.entities.AvailableuUserActions
import com.example.ignition.financetracker.entities.CardEntity
import com.example.ignition.financetracker.entities.OperationsModel
import com.example.ignition.financetracker.repository.Repository
import com.example.ignition.financetracker.ui.base.BaseContract
import io.reactivex.disposables.CompositeDisposable
import java.math.BigDecimal

class AddOperationDialogPresenter : AddOperationDialogContract.Presenter {
    override fun loadToReprository(operation: OperationsModel, cardId: Int) {
        Repository().addToCardOperation(operation, cardId)
    }

    lateinit var view: AddOperationDialogContract.View

    private val subscriptions = CompositeDisposable()


    override fun getInputData(operation: AvailableuUserActions, sum: BigDecimal, date: String): OperationsModel {
        return OperationsModel(operation, sum, date)
    }

    override fun subscribe() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun unsubscribe() {
        subscriptions.clear()
    }

    override fun attach(view: AddOperationDialogContract.View) {
        this.view = view
    }


}