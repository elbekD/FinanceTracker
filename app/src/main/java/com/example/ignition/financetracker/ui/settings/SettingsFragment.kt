package com.example.ignition.financetracker.ui.settings

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.ignition.financetracker.R
import kotlinx.android.synthetic.main.fragment_settings.*

/**
 * Created by Elbek D. on 09.08.2018.
 */
class SettingsFragment : Fragment() {
    companion object {
        fun newInstance() = SettingsFragment()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_settings, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        switch1.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked)
                Toast.makeText(activity, R.string.all_all, Toast.LENGTH_SHORT).show()
            else
                Toast.makeText(activity, getString(R.string.all_enough), Toast.LENGTH_SHORT).show()
        }
    }
}