package com.example.ignition.financetracker.ui.main


import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.example.ignition.financetracker.R
import com.example.ignition.financetracker.ui.main.walletFragment.WalletsFragment
import com.example.ignition.financetracker.ui.settings.SettingsActivity
import kotlinx.android.synthetic.main.activity_main.*

/**
 * Main activity about user balance
 */
class BillScreen : AppCompatActivity(), BillScreenContract.View {
    private lateinit var presenter: BillScreenContract.Presenter

    private val FRAGMENT_STATISTIC_TAG = "statistic_fragment_tag"
    private val HISTORY_LIST_TAG = "history_list_tag"
    private val FRAGMENT_CARDS_TAG = "fragment_cards_tag"
    private val OPERATIONS_DIALOG_FRAGMENT_TAG = "opertion_dialog_tag"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        presenter = BillScreenModule.provideBillScreenPresenter()
        presenter.attachView(this)
        initViews()

        if (savedInstanceState == null)
            supportFragmentManager.beginTransaction()
                    .add(R.id.fragment_holder, WalletsFragment.newInstance(), null)
                    .commit()
    }

    override fun showSettings() {
        startActivity(Intent(this, SettingsActivity::class.java))
    }

    private fun initViews() {
        menu_settings.setOnClickListener { presenter.onSettingsClick() }
    }
}
