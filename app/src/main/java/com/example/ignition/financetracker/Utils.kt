package com.example.ignition.financetracker

import java.math.BigDecimal
import java.text.DecimalFormat


class Utils {
    companion object {
        //format to 2 numbers after coma
        fun formatDecimalNumber(value: BigDecimal): String {
            return DecimalFormat("#.00").format(value)
        }
    }
}
