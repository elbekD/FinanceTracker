package com.example.ignition.financetracker.db

import android.support.test.runner.AndroidJUnit4
import com.example.ignition.financetracker.TestUtils
import org.junit.Test
import org.junit.runner.RunWith

/**
 * Created by Elbek D. on 06.08.2018.
 */
@RunWith(AndroidJUnit4::class)
class CurrencyDaoTest : DbTest() {
    @Test
    fun insertAndRead() {
        val currency = TestUtils.createCurrency()
        db.currencyDao().insert(currency)
        val testResponse = db.currencyDao().getUserCurrencies().test()

        testResponse.assertNoErrors()
        testResponse.assertValue { it.size == 1 }
    }
}