package com.example.ignition.financetracker.ui.billStatisticAvtivity

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.ignition.financetracker.R
import com.example.ignition.financetracker.Utils
import com.example.ignition.financetracker.di.component.DaggerFragmentComponent
import com.example.ignition.financetracker.di.module.ActivityModule
import com.example.ignition.financetracker.di.module.FragmentModule
import com.example.ignition.financetracker.entities.CurrencyType
import com.example.ignition.financetracker.repository.ICurrencyInfoRepository
import com.example.ignition.financetracker.repository.Repository
import com.github.mikephil.charting.charts.BarChart
import com.github.mikephil.charting.data.*
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.diagram_fragment.*
import java.math.BigDecimal
import kotlinx.android.synthetic.main.activity_bill_statistic.*
import javax.inject.Inject


class StatisticFragment : Fragment(), StatisticFragmentContract.View {


    private var currentShowedCurrency = CurrencyType.RUB

    @Inject
    lateinit var presenter: StatisticFragmentContract.Presenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        injectDependency()

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.diagram_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        presenter.attach(this)
        presenter.subscribe()
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


        val set = presenter.onLoadDiagramValues()
        chart.data = BarData(set)

    }

    override fun onChangeCurrency() {

    }


    private fun injectDependency() {
        val fragmentStatistic = DaggerFragmentComponent.builder()
                .fragmentModule(FragmentModule())
                .build()

        fragmentStatistic.inject(this)
    }

    private val repository = ICurrencyInfoRepository.provideCurrencyCurseValue()
            .usdCourseValue()
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
}