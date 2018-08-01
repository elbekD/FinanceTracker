package com.example.ignition.financetracker.entities

import java.math.BigDecimal

//TODO DATE TO DATE CLASS
data class OperationsModel(val operation : AvailableuUserActions, val sum : BigDecimal, val date : String)