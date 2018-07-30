package com.example.ignition.financetracker.billStatisticAvtivity

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.example.ignition.financetracker.R
import com.example.ignition.financetracker.addFragment

class BillStatisticActivity : AppCompatActivity() {
    val STATISTIC_FRAGMENT_TAG = "STATISTIC_FRAGMENT_TAG"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_bill_statistic)

        initFragments()
    }

    fun initFragments() {
        addFragment(StatisticFragment(), R.id.fragmentStatistic, STATISTIC_FRAGMENT_TAG)
    }

    override fun onBackPressed() {
        finish()
    }
}
