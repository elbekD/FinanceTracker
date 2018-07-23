package com.example.ignition.financetracker

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView


import kotlinx.android.synthetic.main.bill_screen.*
import java.math.BigDecimal

class BillScreen : AppCompatActivity() {
    enum class Valuta { RUB, USD }

    private var currentValuta = Valuta.RUB

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.bill_screen)

        initViews()
    }

    fun initViews() {
        settingMenu.setOnClickListener { startActivity(Intent(this, SettingsActivity::class.java)) }

        rubCurrencyButton.setOnClickListener {
            toOtherCurrency(Valuta.RUB, 0.01, currentCurrency, R.drawable.currency_rub, currentValuta)
            currentValuta = Valuta.RUB
        }

        dollarCurrencyButton.setOnClickListener {
            toOtherCurrency(Valuta.USD, 60.0, currentCurrency, R.drawable.currency_usd, currentValuta)
            currentValuta = Valuta.USD
        }


    }


    private fun toOtherCurrency(toCurrency : Valuta = Valuta.RUB, coefficient : Double, imageView : ImageView, resId : Int, currentValuta : Valuta) {
        if (currentValuta != toCurrency) {
            balanceTv.text = String.format(Utils.convertCurrency(balanceTv.text.toString().toBigDecimal(), BigDecimal.valueOf(coefficient))
                    .toString())
            setImage(imageView, resId)
        }

    }

    private fun setImage(imageView: ImageView, resId: Int) = imageView.setImageResource(resId)
}
