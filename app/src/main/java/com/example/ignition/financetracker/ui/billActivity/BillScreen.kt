package com.example.ignition.financetracker.ui.billActivity


import android.app.Fragment
import android.os.Bundle
import android.support.v4.app.FragmentManager
import android.support.v7.app.AppCompatActivity
import com.example.ignition.financetracker.R
import com.example.ignition.financetracker.addFragment
import com.example.ignition.financetracker.di.module.ActivityModule
import com.example.ignition.financetracker.replaceFragment
import com.example.ignition.financetracker.ui.billActivity.CardFragment.FragmentCards
import com.example.ignition.financetracker.ui.billActivity.addOperationDialog.AddOperationDialog
import com.example.ignition.financetracker.ui.billActivity.operationHistoryList.HistoryListFragment
import com.example.ignition.financetracker.ui.billStatisticAvtivity.StatisticFragment
import kotlinx.android.synthetic.main.bill_screen.*
import kotlinx.android.synthetic.main.cards_fragment.*
import javax.inject.Inject


/**
 * Main activity about user balance
 */
class BillScreen : AppCompatActivity(), IBillScreenContract.View {


    @Inject
    lateinit var billScreenPresenter: IBillScreenContract.Presenter

    private val FRAGMENT_STATISTIC_TAG = "statistic_fragment_tag"
    private val HISTORY_LIST_TAG = "history_list_tag"
    private val FRAGMENT_CARDS_TAG = "fragment_cards_tag"
    private val OPERATIONS_DIALOG_FRAGMENT_TAG = "opertion_dialog_tag"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.bill_screen)

        injectDependency()
        setupBottomFragmentNavigation()
        billScreenPresenter.attach(this)

        statisticBillSelected()
    }


    private fun injectDependency() {
        val activityComponent = DaggerActivityComponent.builder()
                .activityModule(ActivityModule(this))
                .build()

        activityComponent.inject(this)
    }

    override fun showCardsFragment() {
        replaceFragment(FragmentCards(), FRAGMENT_CARDS_TAG, false, R.id.fragment_holder)
    }

    override fun showOperationList() {
        replaceFragment(HistoryListFragment(), HISTORY_LIST_TAG, false, R.id.fragment_holder)
    }

    override fun showAddOperationDialog() {
        AddOperationDialog().show(fragmentManager, OPERATIONS_DIALOG_FRAGMENT_TAG)
    }


    fun setupBottomFragmentNavigation() {
        seeList.setOnClickListener { showOperationList() }

        addOperation.setOnClickListener { showAddOperationDialog() }
    }

    override fun statisticBillSelected() {

    }

    override fun onBackPressed() {
        if (fragmentManager.backStackEntryCount > 0) {
            fragmentManager.popBackStack()
        } else {
            super.onBackPressed()
        }
    }
}
