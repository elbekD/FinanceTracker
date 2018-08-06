package com.example.ignition.financetracker.db

import android.support.test.runner.AndroidJUnit4
import com.example.ignition.financetracker.TestUtils
import org.junit.Test
import org.junit.runner.RunWith

/**
 * Created by Elbek D. on 06.08.2018.
 */
@RunWith(AndroidJUnit4::class)
class ExchangeRateTest : DbTest() {
    @Test
    fun insertAndRead() {
        val rate = TestUtils.createExchangeRate()
        db.exchangeRateDao().insert(rate)
        val res = db.exchangeRateDao().getRate(rate.fromTo).test()
        res.assertValue { rate.fromTo == it.fromTo }
    }
}