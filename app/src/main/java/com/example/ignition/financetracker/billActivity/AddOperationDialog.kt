package com.example.ignition.financetracker.billActivity

import android.app.Dialog
import android.app.DialogFragment
import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.ignition.financetracker.R
import com.example.ignition.financetracker.R.id.sum
import com.example.ignition.financetracker.repository.Repository

class AddOperationDialog : DialogFragment() {

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val builder = AlertDialog.Builder(activity)
        val inflater = LayoutInflater.from(activity)

        builder.setView(inflater.inflate(R.layout.add_operation_dialog_fragment, null))
                .setPositiveButton(R.string.yes) { _, _ ->
                    Repository().addOperations(sum.toString())
                }
                .setNegativeButton(R.string.no) { dialog, _ -> dialog.cancel() }


        return builder.create()
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View {

        return LayoutInflater.from(activity).inflate(R.layout.add_operation_dialog_fragment, container)
    }


}