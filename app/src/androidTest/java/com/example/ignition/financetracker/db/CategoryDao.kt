package com.example.ignition.financetracker.db

import android.support.test.runner.AndroidJUnit4
import com.example.ignition.financetracker.TestUtils
import org.junit.Test
import org.junit.runner.RunWith

/**
 * Created by Elbek D. on 06.08.2018.
 */
@RunWith(AndroidJUnit4::class)
class CategoryDao : DbTest(){
    @Test
    fun insertAndRead() {
        val cat = TestUtils.createCategory()
        db.categoryDao().insertAll(cat)
        val res = db.categoryDao().getAllCategories().test()
        res.assertValue { it.size == 1 }
    }
}