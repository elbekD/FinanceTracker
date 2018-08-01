package com.example.ignition.financetracker.ui.billActivity.addOperationDialog

import android.app.Dialog
import android.app.DialogFragment
import android.content.DialogInterface
import android.graphics.Color
import android.os.Bundle
import android.support.v4.view.ViewPager
import android.support.v7.app.AlertDialog
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import com.example.ignition.financetracker.R
import com.example.ignition.financetracker.di.component.DaggerFragmentComponent
import com.example.ignition.financetracker.di.module.FragmentModule
import com.example.ignition.financetracker.repository.Repository
import kotlinx.android.synthetic.main.add_operation_dialog_fragment.*
import javax.inject.Inject
import android.widget.TextView


class AddOperationDialog : DialogFragment() {
    @Inject
    lateinit var presenter: AddOperationDialogContract.Presenter

    val fakeOperationData = Repository().getOperations()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        injectDependency()
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {

        val builder = AlertDialog.Builder(activity)
        val inflater = LayoutInflater.from(activity)
        spinnerText?.adapter = ArrayAdapter(activity, android.R.layout.simple_spinner_dropdown_item, fakeOperationData)
        builder.setView(inflater.inflate(R.layout.add_operation_dialog_fragment, null))
                .setPositiveButton(R.string.yes) { _, _ ->
                    Repository().addOperations(sum.toString())
                }
                .setNegativeButton(R.string.no) { dialog, _ -> dialog.cancel() }

        builder.setView(spinnerText)
        return builder.create()
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View {

        return LayoutInflater.from(activity).inflate(R.layout.add_operation_dialog_fragment, container)

    }


    private fun injectDependency() {
        val addOperationDialog = DaggerFragmentComponent.builder()
                .fragmentModule(FragmentModule())
                .build()

        addOperationDialog.inject(this)
    }

}