package com.example.ignition.financetracker.db

import android.support.test.runner.AndroidJUnit4
import com.example.ignition.financetracker.TestUtils
import org.junit.Test
import org.junit.runner.RunWith

/**
 * Created by Elbek D. on 06.08.2018.
 */
@RunWith(AndroidJUnit4::class)
class WalletDaoTest : DbTest(){
    @Test
    fun insertAndRead() {
        val w = TestUtils.createWallet()
        db.cardDao().insertWallet(w)
        val res = db.cardDao().walletByName(w.name).test()
        res.assertValue { w.name == it.name }
    }
}