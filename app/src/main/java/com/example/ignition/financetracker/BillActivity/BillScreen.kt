package com.example.ignition.financetracker.BillActivity


import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import com.example.ignition.financetracker.R
import com.example.ignition.financetracker.SettingsActivity.SettingsActivity
import kotlinx.android.synthetic.main.bill_screen.*


/**
 * Main activity about user balance
 * TODO: fragment for showing balance, MVP
 */
class BillScreen : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.bill_screen)

        initViews()

        if (savedInstanceState == null) {
            loadFragment(FragmentBill())
        }
    }

    fun initViews() {
        settingMenu.setOnClickListener { startActivity(Intent(this, SettingsActivity::class.java)) }

    }

    fun loadFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction().replace(R.id.frgmCont, fragment).addToBackStack(fragment.javaClass.name).commit()
    }
}
