package com.example.ignition.financetracker.ui.main.addWalletDialog

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.support.v4.app.DialogFragment
import android.support.v7.app.AlertDialog
import android.view.LayoutInflater
import android.view.View
import android.widget.ArrayAdapter
import android.widget.EditText
import android.widget.Spinner
import com.example.ignition.financetracker.R
import com.example.ignition.financetracker.entities.Wallet

/**
 * Created by Elbek D. on 03.08.2018.
 */
class AddWalletDialog : DialogFragment(), AddWalletDialogContract.View {
    interface AddCardListener {
        fun onAddWalletClick(c: Wallet)
    }

    companion object {
        fun newInstance() = AddWalletDialog()
    }

    private lateinit var v: View
    private var listener: AddCardListener? = null
    private lateinit var presenter: AddWalletDialogContract.Presenter

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        presenter = AddWalletModule.provideAddCardModule()
        presenter.attachView(this)

        v = LayoutInflater.from(activity).inflate(R.layout.dialog_addcard, null)
        val d = AlertDialog.Builder(activity!!)
                .setView(v)
                .setPositiveButton(android.R.string.ok) { _, _ -> listener?.onAddWalletClick(gatherCard()) }
                .setNegativeButton(android.R.string.cancel) { _, _ -> dismiss() }
                .create().apply { setCanceledOnTouchOutside(false) }

        presenter.start()
        return d
    }

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        listener = when {
            parentFragment is AddCardListener -> parentFragment as AddCardListener
            activity is AddCardListener -> activity as AddCardListener
            else -> null
        }
    }

    override fun onDetach() {
        listener = null
        super.onDetach()
    }

    override fun setCurrencyAdapter(c: List<String>) {
        v.findViewById<Spinner>(R.id.spinner_newWalletMainCurrency).adapter = ArrayAdapter<String>(activity, android.R.layout.simple_spinner_dropdown_item, c)
        v.findViewById<Spinner>(R.id.spinner_newWalletSecondaryCurrency).adapter = ArrayAdapter<String>(activity, android.R.layout.simple_spinner_dropdown_item, c)
    }

    private fun gatherCard() = with(v) {
        val cardName = findViewById<EditText>(R.id.input_newWalletName).text.toString()
        val mainCurrency = findViewById<Spinner>(R.id.spinner_newWalletMainCurrency).selectedItem.toString()
        val secondaryCurrency = findViewById<Spinner>(R.id.spinner_newWalletSecondaryCurrency).selectedItem.toString()
        val type = findViewById<EditText>(R.id.input_walletType).text.toString()
        Wallet(0,cardName, mainCurrency, secondaryCurrency, type)
    }
}
