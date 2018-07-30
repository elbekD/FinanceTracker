package com.example.ignition.financetracker.billStatisticAvtivity

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.ignition.financetracker.R
import com.example.ignition.financetracker.Utils
import com.example.ignition.financetracker.entities.CurrencyType
import com.example.ignition.financetracker.repository.ICurrencyInfoRepository
import com.example.ignition.financetracker.repository.Repository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.diagram_fragment.*
import java.math.BigDecimal

class StatisticFragment : Fragment() {
    private var currentShowedCurrency = CurrencyType.RUB
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.diagram_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()

    }

    private fun initViews() {
        tvBalance.text = Utils.formatDecimalNumber(Repository().currentBalance())

        backToBillScreen.setOnClickListener { activity?.onBackPressed() }

        changeCourseCurrency.setOnClickListener {
            if (currentShowedCurrency == CurrencyType.RUB) {
                changeCourseCurrency.setImageResource(R.drawable.currency_rub)
                repository.subscribe(
                        { result ->
                            tvBalance.text = Utils.formatDecimalNumber(Repository().currentBalance() / result.Valute.USD.Value.toBigDecimal())
                            currentCurrencyImg.setImageResource(R.drawable.currency_usd)
                            currentShowedCurrency = CurrencyType.USD
                        },
                        { error("Network error") })
            } else {
                changeCourseCurrency.setImageResource(R.drawable.currency_usd)
                repository.subscribe(
                        { result ->
                            tvBalance.text = Utils.formatDecimalNumber(BigDecimal(tvBalance.text.toString().replace(",", ".")) * result.Valute.USD.Value.toBigDecimal())
                            currentCurrencyImg.setImageResource(R.drawable.currency_rub)
                            currentShowedCurrency = CurrencyType.RUB

                        },
                        { error("Network error") })
            }
        }
    }

    private val repository = ICurrencyInfoRepository.provideCurrencyCurseValue()
            .usdCourseValue()
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
}