package com.example.ignition.financetracker.entities


object CurrencyCourseModel {
    data class USD(val ID: String, val NumCode: String, val CharCode: String, val Nominal: Int, val Name: String, val Value: Double, val Previous: Double)

    data class Valute(val USD : USD)

    data class Data(val Date : String, val PreviousDate : String, val PreviousURL : String, val Timestamp : String, val Valute : Valute)
}