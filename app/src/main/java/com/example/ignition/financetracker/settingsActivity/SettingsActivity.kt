package com.example.ignition.financetracker.settingsActivity

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.example.ignition.financetracker.R
import com.example.ignition.financetracker.aboutActivity.InfoActivity
import kotlinx.android.synthetic.main.activity_settings.*

class SettingsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_settings)

        initViews()
    }

    private fun initViews() {
        aboutAppClick.setOnClickListener { startActivity(Intent(this, InfoActivity::class.java)) }
    }
}
