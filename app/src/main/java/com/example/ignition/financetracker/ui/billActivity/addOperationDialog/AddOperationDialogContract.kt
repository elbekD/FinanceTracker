package com.example.ignition.financetracker.ui.billActivity.addOperationDialog

import com.example.ignition.financetracker.entities.AvailableOperations
import com.example.ignition.financetracker.entities.AvailableuUserActions
import com.example.ignition.financetracker.entities.CardEntity
import com.example.ignition.financetracker.entities.OperationsModel
import com.example.ignition.financetracker.ui.base.BaseContract
import java.math.BigDecimal

class AddOperationDialogContract {
    interface View : BaseContract.View {
        fun applyOperation()
    }

    interface Presenter : BaseContract.Presenter<View> {
        fun getInputData(operation: AvailableuUserActions, sum: BigDecimal, date: String): OperationsModel
        fun loadToReprository(operation: OperationsModel, cardId: Int)
    }


}