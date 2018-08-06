package com.example.ignition.financetracker.utils

import org.hamcrest.core.Is.`is`
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.junit.Assert.assertThat
import java.math.BigDecimal

/**
 * Created by Elbek D. on 06.08.2018.
 */
@RunWith(JUnit4::class)
class UtilsTest {
    @Test
    fun testMateDecimalNegative() {
        val a = "1.25"
        val b = Utils.makeNegativeDecimal(a, true)
        assertThat(b, `is`(BigDecimal(a).negate()))
    }
}