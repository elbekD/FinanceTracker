package com.example.ignition.financetracker.db

import android.support.test.runner.AndroidJUnit4
import com.example.ignition.financetracker.TestUtils
import org.hamcrest.CoreMatchers.`is`
import org.junit.Assert.assertThat
import org.junit.Test
import org.junit.runner.RunWith

/**
 * Created by Elbek D. on 06.08.2018.
 */
@RunWith(AndroidJUnit4::class)
class OperationDaoTest : DbTest() {
    @Test
    fun insertAndRead() {
        val operation = TestUtils.createOperation()
        val id = db.operationDao().insertOperation(operation)
        val returnedOperation = db.operationDao().getOperationById(id)
        assertThat(operation.walletName, `is`(returnedOperation.walletName))

        val ops = db.operationDao().walletOperations(operation.walletName).test()
        ops.assertValue { it.size == 1 }
    }
}