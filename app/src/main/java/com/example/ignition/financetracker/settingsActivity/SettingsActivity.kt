package com.example.ignition.financetracker.settingsActivity

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.example.ignition.financetracker.aboutActivity.InfoActivity
import com.example.ignition.financetracker.R
import kotlinx.android.synthetic.main.activity_settings.*

class SettingsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_settings)

        initViews()
    }

    fun initViews() {
        aboutAppClick.setOnClickListener { startActivity(Intent(this, InfoActivity::class.java)) }
    }
}
