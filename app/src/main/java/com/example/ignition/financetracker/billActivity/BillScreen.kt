package com.example.ignition.financetracker.billActivity


import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import android.widget.Toast
import com.example.ignition.financetracker.FragmentNavigator
import com.example.ignition.financetracker.R
import com.example.ignition.financetracker.networking.CurrencyCourseAPI
import com.example.ignition.financetracker.repository.CurrencyInfoRepository
import com.example.ignition.financetracker.repository.ICurrencyInfoRepository
import com.example.ignition.financetracker.settingsActivity.SettingsActivity
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.observers.DisposableObserver
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.bill_screen.*


/**
 * Main activity about user balance
 * TODO: fragment for showing balance, MVP, close subscription
 */
class BillScreen : AppCompatActivity() {
    var disposable: Disposable? = null

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

    fun beginSearch() {
        disposable = CurrencyCourseAPI.create().getCourse()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        { result -> /*todo buisness logic*/},
                        { error -> /*todo buisness logic*/ }
                )
    }

}
