package com.example.ignition.financetracker.ui.billActivity.CardFragment

import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.view.ViewPager
import android.support.v7.app.AppCompatActivity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.ignition.financetracker.R
import com.example.ignition.financetracker.adapters.SwipeAdapter

import com.example.ignition.financetracker.entities.CardEntity
import com.example.ignition.financetracker.ui.settingsActivity.SettingsActivity
import kotlinx.android.synthetic.main.cards_fragment.*
import javax.inject.Inject
import com.example.ignition.financetracker.di.component.DaggerFragmentComponent
import com.example.ignition.financetracker.di.module.ApplicationModule
import com.example.ignition.financetracker.di.module.FragmentModule
import com.example.ignition.financetracker.entities.AvailableuUserActions
import com.example.ignition.financetracker.replaceFragment
import com.example.ignition.financetracker.seViewPagertCustomListener
import com.example.ignition.financetracker.ui.billActivity.BillScreen
import com.example.ignition.financetracker.ui.billStatisticAvtivity.StatisticFragment


class FragmentCards : Fragment(), CardFragmentContract.View {


    @Inject
    lateinit var presenter: CardFragmentContract.Presenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        injectDependency()
    }


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.cards_fragment, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        presenter.attach(this)
        presenter.subscribe()
        initViews()

    }

    fun initViews() {
        val data = presenter.loadCardData()

        val viewPager: ViewPager = cardsSwitcher
        val swipeAdapter = SwipeAdapter(context)
        viewPager.adapter = swipeAdapter
        viewPager.seViewPagertCustomListener(viewPager, data, setViewChange)

        onSettingsSelected()
        statisticBillSelected()
    }


    private fun injectDependency() {
        val fragmentCard = DaggerFragmentComponent.builder()
                .fragmentModule(FragmentModule())
                .build()

        fragmentCard.inject(this)
    }


    val setViewChange: (Int, data: List<CardEntity>) -> Unit = { position, data ->
        balanceTv.text = String.format(data[position].cardBalance.toString())
        incomeValue.text = String.format(data[position].cardOperations.first { v -> v.operation == AvailableuUserActions.TRANSFER }.sum.toString())
        outcomeValue.text = String.format(data[position].cardOperations.first { v -> v.operation != AvailableuUserActions.TRANSFER }.sum.toString())
    }


    override fun onSettingsSelected() {
        settingMenu.setOnClickListener { startActivity(Intent(context, SettingsActivity::class.java)) }
    }

    override fun statisticBillSelected() {
        val fragmentManager = fragmentManager
        balanceTv.setOnClickListener { fragmentManager?.beginTransaction()?.replace(R.id.fragment_holder, StatisticFragment())?.addToBackStack(null)?.commit() }
    }

}



