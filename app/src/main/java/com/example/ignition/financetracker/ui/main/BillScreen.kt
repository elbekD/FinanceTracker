package com.example.ignition.financetracker.ui.main


import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.example.ignition.financetracker.R
import com.example.ignition.financetracker.ui.main.walletFragment.WalletsFragment
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
                    .replace(R.id.fragment_holder, WalletsFragment.newInstance(), null)
                    .commit()

        presenter.load()
    }

    override fun onDestroy() {
        presenter.detachView()
        if (isFinishing) presenter.destroy()
        super.onDestroy()
    }

    override fun showBalance() {

    }

    override fun showSettings() {

    }

    override fun showAbout() {

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
