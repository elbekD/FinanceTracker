package com.example.ignition.financetracker.billActivity


import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.example.ignition.financetracker.R
import com.example.ignition.financetracker.addFragment
import kotlinx.android.synthetic.main.bill_screen.*


/**
 * Main activity about user balance
 */
class BillScreen : AppCompatActivity() {
    private val FRAGMENT_BILL_TAG = "fragment_bill_tag"
    private val FRAGMENT_CARDS_TAG = "fragment_cards_tag"
    private val OPERATIONS_DIALOG_FRAGMENT_TAG = "opertion_dialog_tag"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.bill_screen)

        initViews()

        if (savedInstanceState == null) {
            initFragments()
        }

    }

    private fun initViews() {
        addOperation.setOnClickListener { AddOperationDialog().show(fragmentManager, OPERATIONS_DIALOG_FRAGMENT_TAG) }

    }

    private fun initFragments() {
        addFragment(FragmentBill(), R.id.frgmCont, FRAGMENT_BILL_TAG)
        addFragment(FragmentCards(), R.id.cardsViewFragment, FRAGMENT_CARDS_TAG)
    }

    override fun onBackPressed() {
        finish()
    }
}
