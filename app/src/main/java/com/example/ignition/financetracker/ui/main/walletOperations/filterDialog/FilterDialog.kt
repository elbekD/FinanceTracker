package com.example.ignition.financetracker.ui.main.walletOperations.filterDialog

import android.app.AlertDialog
import android.app.DatePickerDialog
import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.support.v4.app.DialogFragment
import android.view.LayoutInflater
import android.view.View
import com.example.ignition.financetracker.R
import com.example.ignition.financetracker.entities.Category
import com.example.ignition.financetracker.ui.main.OperationFilter
import com.example.ignition.financetracker.utils.Utils
import kotlinx.android.synthetic.main.dialog_filter.view.*
import java.util.*

/**
 * Created by Elbek D. on 09.08.2018.
 */
class FilterDialog : DialogFragment(), FilterDialogContract.View {
    interface FilterDialogListener {
        fun applyFilter(filter: OperationFilter)
    }

    private lateinit var v: View
    private lateinit var presenter: FilterDialogContract.Presenter

    private val calendarFrom = Calendar.getInstance()
    private val datePickerListenerFrom = DatePickerDialog.OnDateSetListener { _, year, month, dayOfMonth ->
        v.tv_transactionsFromDate.text = Utils.formatDate(activity,
                calendarFrom.apply { set(year, month, dayOfMonth) }.timeInMillis)
    }
    private lateinit var datePickerFrom: DatePickerDialog

    private val calendarTo = Calendar.getInstance()
    private val datePickerListenerTo = DatePickerDialog.OnDateSetListener { _, year, month, dayOfMonth ->
        v.tv_transactionsToDate.text = Utils.formatDate(activity,
                calendarTo.apply { set(year, month, dayOfMonth) }.timeInMillis)
    }
    private lateinit var datePickerTo: DatePickerDialog

    private var listener: FilterDialogListener? = null

    companion object {
        fun newInstance() = FilterDialog()
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        presenter = FilterDialogModule.provideFilterDialogPresenter()
        presenter.attachView(this)

        val inflater = LayoutInflater.from(activity)
        v = inflater.inflate(R.layout.dialog_filter, null)

        val d = AlertDialog.Builder(activity!!)
                .setView(v)
                .setPositiveButton(android.R.string.ok) { _, _ -> listener?.applyFilter(gatherFilter()) }
                .setNegativeButton(android.R.string.cancel) { _, _ -> }
                .create()

        initViews()
        presenter.load()
        return d
    }

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        listener = when {
            parentFragment is FilterDialogListener -> parentFragment as FilterDialogListener
            activity is FilterDialogListener -> activity as FilterDialogListener
            else -> null
        }
    }

    override fun onDetach() {
        listener = null
        super.onDetach()
    }

    override fun setCategoryAdapter(c: List<Category>) {
        v.spinner_filterTypes.adapter = Utils.createAdapterWith<String>(activity)
                .apply { addAll(c.map { it.name }); add(getString(R.string.all_all)) }
    }

    private fun initViews() {
        with(v) {
            tv_transactionsFromDate.setOnClickListener { datePickerFrom.show() }
            tv_transactionsFromDate.text = Utils.formatDate(activity, calendarFrom.timeInMillis)
            tv_transactionsToDate.setOnClickListener { datePickerTo.show() }
            tv_transactionsToDate.text = Utils.formatDate(activity, calendarTo.timeInMillis)
        }

        datePickerFrom = DatePickerDialog(activity,
                datePickerListenerFrom,
                calendarFrom[Calendar.YEAR],
                calendarFrom[Calendar.MONTH],
                calendarFrom[Calendar.DAY_OF_MONTH])

        datePickerTo = DatePickerDialog(activity,
                datePickerListenerTo,
                calendarTo[Calendar.YEAR],
                calendarTo[Calendar.MONTH],
                calendarTo[Calendar.DAY_OF_MONTH])
    }

    private fun gatherFilter() = with(v) {
        val from = calendarFrom.apply { clear(Calendar.HOUR); clear(Calendar.MINUTE); clear(Calendar.SECOND) }.timeInMillis
        val to = calendarTo.timeInMillis
        val (all, income, expense) = when (group_filter.checkedRadioButtonId) {
            R.id.filter_all -> Triple(true, false, false)
            R.id.filter_income -> Triple(false, true, false)
            else -> Triple(false, false, true)
        }
        val category = spinner_filterTypes.selectedItem.toString()
        val anyCategory = category == getString(R.string.all_all)
        OperationFilter(from, to, income, expense, all, category, anyCategory)
    }
}