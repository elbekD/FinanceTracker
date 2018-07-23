package com.example.ignition.financetracker

import java.math.BigDecimal

class Utils {
    companion object {

        fun convertCurrency(amount : BigDecimal, coefficient : BigDecimal) : BigDecimal = amount * coefficient
    }
}