package com.example.ignition.financetracker.ui.main.addOperationDialog

import android.app.AlertDialog
import android.app.DatePickerDialog
import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.support.v4.app.DialogFragment
import android.text.Editable
import android.text.TextWatcher
import android.text.format.DateUtils
import android.view.View
import android.widget.*
import com.example.ignition.financetracker.R
import com.example.ignition.financetracker.entities.Operation
import com.example.ignition.financetracker.utils.Utils
import java.util.*

class AddOperationDialog : DialogFragment(), AddOperationDialogContract.View {
    interface AddOperationListener {
        fun onAddOperationClick(o: Operation)
    }

    companion object {
        private val amountRegex = """^\d+(\.\d*)?$""".toRegex()
        val TAG = AddOperationDialog::class.java.simpleName
        fun newInstance() = AddOperationDialog()
    }

    private lateinit var presenter: AddOperationDialogContract.Presenter
    private lateinit var v: View
    private lateinit var dialogView: AlertDialog
    private val calendar = Calendar.getInstance()
    private var listener: AddOperationListener? = null

    private val datePickerListener = DatePickerDialog.OnDateSetListener { _, year, month, dayOfMonth ->
        v.findViewById<TextView>(R.id.tv_transaction_date).text = DateUtils.formatDateTime(activity, calendar.apply {
            set(year, month, dayOfMonth)
        }.timeInMillis, DateUtils.FORMAT_SHOW_YEAR or DateUtils.FORMAT_SHOW_DATE)
    }

    private lateinit var datePickerDialog: DatePickerDialog

    private lateinit var cardAdapter: ArrayAdapter<String>
    private lateinit var categoryAdapter: ArrayAdapter<String>
    private lateinit var currencyAdapter: ArrayAdapter<String>

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        presenter = AddOperationModule.provideAddOperationPresenter()
        presenter.attachView(this)

        val inflater = activity!!.layoutInflater
        val builder = AlertDialog.Builder(activity)

        v = inflater.inflate(R.layout.dialog_addoperation, null)

        dialogView = builder
                .setView(v)
                .setPositiveButton(android.R.string.ok) { _, _ -> listener?.onAddOperationClick(gatherOperation()) }
                .setNegativeButton(android.R.string.cancel) { _, _ -> dismiss() }
                .create().apply {
                    setOnShowListener { addAmountTextListener(v) }
                    setCanceledOnTouchOutside(false)
                }

        initViews()
        presenter.load()
        return dialogView
    }

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        listener = when {
            parentFragment is AddOperationListener -> parentFragment as AddOperationListener
            activity is AddOperationListener -> activity as AddOperationListener
            else -> null
        }
    }

    override fun changeMainCurrency(currency: String) {
        with(currencyAdapter) {
            remove(currency)
            insert(currency, 0)
            notifyDataSetChanged()
        }
    }

    override fun setupCurrencyAdapter(currencies: List<String>) = currencyAdapter.addAll(currencies)

    override fun setupCategoryAdapter(categories: List<String>) = categoryAdapter.addAll(categories)

    override fun setupCardsAdapter(cards: List<String>) = cardAdapter.addAll(cards)

    private fun initViews() {
        cardAdapter = ArrayAdapter(activity, android.R.layout.simple_spinner_dropdown_item)
        categoryAdapter = ArrayAdapter(activity, android.R.layout.simple_spinner_dropdown_item)
        currencyAdapter = ArrayAdapter(activity, android.R.layout.simple_spinner_dropdown_item)

        v.findViewById<TextView>(R.id.tv_transaction_date)
                .text = DateUtils.formatDateTime(activity,
                calendar.timeInMillis, DateUtils.FORMAT_SHOW_YEAR or DateUtils.FORMAT_SHOW_DATE)

        datePickerDialog = DatePickerDialog(
                activity,
                datePickerListener,
                calendar[Calendar.YEAR],
                calendar[Calendar.MONTH],
                calendar[Calendar.DAY_OF_MONTH])

        with(v) {
            findViewById<TextView>(R.id.tv_transaction_date).setOnClickListener { datePickerDialog.show() }
            findViewById<Spinner>(R.id.spinner_wallet).apply {
                onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                    override fun onNothingSelected(parent: AdapterView<*>?) {
                    }

                    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                        presenter.onCardSelected(selectedItem.toString())
                    }
                }
                adapter = cardAdapter
            }
            findViewById<Spinner>(R.id.spinner_category).adapter = categoryAdapter
            findViewById<Spinner>(R.id.spinner_operationcurrency).adapter = currencyAdapter
        }
    }

    private fun addAmountTextListener(v: View) {
        with(v.findViewById<EditText>(R.id.input_amount)) {

            val confirmButton = dialogView.getButton(AlertDialog.BUTTON_POSITIVE)
            confirmButton.isEnabled = text?.toString()?.matches(amountRegex) ?: false

            addTextChangedListener(object : TextWatcher {
                override fun afterTextChanged(e: Editable?) {
                    e?.let { confirmButton.isEnabled = it.toString().matches(amountRegex) }
                }

                override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                }

                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                }
            })
        }
    }

    private fun gatherOperation() = with(v) {
        val amount = findViewById<EditText>(R.id.input_amount).text.toString()
        val currency = findViewById<Spinner>(R.id.spinner_operationcurrency).selectedItem.toString()
        val category = findViewById<Spinner>(R.id.spinner_category).selectedItem.toString()
        val wallet = findViewById<Spinner>(R.id.spinner_wallet).selectedItem.toString()
        val isOutcome = findViewById<RadioGroup>(R.id.operation_type).checkedRadioButtonId == R.id.expense
        val dateInMillis = calendar.timeInMillis
        Operation(wallet, category, Utils.makeNegativeDecimal(amount, isOutcome), currency, dateInMillis)
    }
}