package com.example.ignition.financetracker.billActivity


import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.example.ignition.financetracker.R
import com.example.ignition.financetracker.addFragment
import kotlinx.android.synthetic.main.bill_screen.*


/**
 * Main activity about user balance
 * TODO MVP
 */
class BillScreen : AppCompatActivity() {
    val FRAGMENT_BILL_TAG = "fragment_bill_tag"
    val FRAGMENT_CARDS_TAG = "fragment_cards_tag"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.bill_screen)

        initViews()

        if (savedInstanceState == null) {
            initFragments()
        }

    }

    fun initViews() {
        addOperation.setOnClickListener { AddOperationDialog().show(fragmentManager, "Tag") }

    }

    fun initFragments() {
        addFragment(FragmentBill(), R.id.frgmCont, FRAGMENT_BILL_TAG)
        addFragment(FragmentCards(), R.id.cardsViewFragment, FRAGMENT_CARDS_TAG)
    }

    override fun onBackPressed() {
        finish()
    }
}
