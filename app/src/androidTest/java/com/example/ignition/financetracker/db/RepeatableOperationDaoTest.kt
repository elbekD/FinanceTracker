package com.example.ignition.financetracker.db

import android.support.test.runner.AndroidJUnit4
import com.example.ignition.financetracker.TestUtils
import com.example.ignition.financetracker.entities.RepeatableOperation
import org.junit.Test
import org.junit.runner.RunWith

/**
 * Created by Elbek D. on 06.08.2018.
 */
@RunWith(AndroidJUnit4::class)
class RepeatableOperationDaoTest :DbTest(){
    @Test
    fun insertAndRead() {
        val op = TestUtils.createOperation()
        val id = db.operationDao().insertOperation(op)
        val ro = RepeatableOperation(0, id, 607)
        db.repeatableOperationDao().insert(ro)
        val res = db.repeatableOperationDao().getAllRepeatableOperations().test()
        res.assertValue { it.size == 1 }
    }
}