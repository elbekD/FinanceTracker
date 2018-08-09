package com.example.ignition.financetracker.ui.main


import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.example.ignition.financetracker.R
import com.example.ignition.financetracker.ui.about.AboutFragment
import com.example.ignition.financetracker.ui.main.walletFragment.WalletsFragment
import com.example.ignition.financetracker.ui.settings.SettingsFragment
import kotlinx.android.synthetic.main.activity_main.*

/**
 * Main activity about user balance
 */
class BillScreen : AppCompatActivity(), BillScreenContract.View {
    private lateinit var presenter: BillScreenContract.Presenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        presenter = BillScreenModule.provideBillScreenPresenter()
        presenter.attachView(this)
        initViews()

        if (savedInstanceState == null)
            supportFragmentManager.beginTransaction()
                    .replace(R.id.fragment_holder, WalletsFragment.newInstance())
                    .commit()

        presenter.load()
    }

    override fun onDestroy() {
        presenter.detachView()
        if (isFinishing) presenter.destroy()
        super.onDestroy()
    }

    override fun showBalance() {
        supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_holder, WalletsFragment.newInstance())
                .commit()
    }

    override fun showSettings() {
        supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_holder, SettingsFragment.newInstance())
                .commit()
    }

    override fun showAbout() {
        supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_holder, AboutFragment.newInstance())
                .commit()
    }

    private fun initViews() {
        bottom_navigation.setOnNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.menu_balance -> presenter.onBalanceClick()
                R.id.menu_settings -> presenter.onSettingsClick()
                R.id.menu_about -> presenter.onAboutClick()
            }
            true
        }
    }
}
