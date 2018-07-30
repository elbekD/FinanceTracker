package com.example.ignition.financetracker.billActivity

import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.view.ViewPager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.ignition.financetracker.R
import com.example.ignition.financetracker.settingsActivity.SettingsActivity
import kotlinx.android.synthetic.main.cards_fragment.*


class FragmentCards : Fragment() {


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.cards_fragment, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()

    }

    fun initViews() {
        val viewPager: ViewPager = cardsSwitcher
        val swipeAdapter = SwipeAdapter(context)
        viewPager.adapter = swipeAdapter

        settingMenu.setOnClickListener { startActivity(Intent(context, SettingsActivity::class.java)) }

    }

}

