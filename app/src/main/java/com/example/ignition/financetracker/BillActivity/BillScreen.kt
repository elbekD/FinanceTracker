package com.example.ignition.financetracker.BillActivity

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import com.example.ignition.financetracker.FinancialOperations.CurrencyOperation
import com.example.ignition.financetracker.FinancialOperations.CurrencyType
import com.example.ignition.financetracker.R
import com.example.ignition.financetracker.SettingsActivity.SettingsActivity


import kotlinx.android.synthetic.main.bill_screen.*
import java.math.BigDecimal


/**
 * Main activity about user balance
 * TODO: fragment for showing balance, MVP
 */
class BillScreen : AppCompatActivity() {

    private var currentValuta = CurrencyType.RUB

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.bill_screen)

        initViews()
    }

    fun initViews() {
        settingMenu.setOnClickListener { startActivity(Intent(this, SettingsActivity::class.java)) }

        // Case ruble infor button clicked -> show RUB balance and change icon on RUB synbol, it RUB is showed -> do nothing
        rubCurrencyButton.setOnClickListener {
            toOtherCurrency(CurrencyType.RUB, 0.01, currentCurrency, R.drawable.currency_rub, currentValuta)
            currentValuta = CurrencyType.RUB
        }

        dollarCurrencyButton.setOnClickListener {
            toOtherCurrency(CurrencyType.USD, 60.0, currentCurrency, R.drawable.currency_usd, currentValuta)
            currentValuta = CurrencyType.USD
        }


    }

    /**
     * Method for changint USD sum on RUB. Also change currency icon
     * @param type of currency,
     * @param coefficient for changing currency
     * @param toCurrency what currency want to show
     * @param id of icon for currency type
     * @param currentValuta currency which is showing now
     */

    private fun toOtherCurrency(toCurrency: CurrencyType = CurrencyType.RUB, coefficient: Double, imageView: ImageView, resId: Int, currentValuta: CurrencyType) {
        if (currentValuta != toCurrency) {
            balanceTv.text = String.format(CurrencyOperation.convertCurrency(balanceTv.text.toString().toBigDecimal(), BigDecimal.valueOf(coefficient))
                    .toString())
            setImage(imageView, resId)
        }

    }

    /**
     * method for setting image to imageview
     * @param imageView - imageView to be setted
     * @param resId - id of resource to set in IV
     */
    private fun setImage(imageView: ImageView, resId: Int) = imageView.setImageResource(resId)
}
