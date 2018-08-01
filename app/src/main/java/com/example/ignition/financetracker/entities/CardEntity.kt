package com.example.ignition.financetracker.entities

import android.net.Uri
import java.math.BigDecimal

data class CardEntity(val name : String, val cardBalance : BigDecimal, val cardOperations : MutableList<OperationsModel>, val cardLogoRes : Int)