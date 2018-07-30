package com.example.ignition.financetracker.billStatisticAvtivity

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.example.ignition.financetracker.R
import com.example.ignition.financetracker.addFragment

class BillStatisticActivity : AppCompatActivity() {
    private val FRAGMENT_TAG_STATIC_SCREEN = "static_fragment_tag"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_bill_statistic)

        initFragments()
    }

    private fun initFragments() {
        addFragment(StatisticFragment(), R.id.fragmentStatistic, FRAGMENT_TAG_STATIC_SCREEN)
    }

    override fun onBackPressed() {
        finish()
    }
}
