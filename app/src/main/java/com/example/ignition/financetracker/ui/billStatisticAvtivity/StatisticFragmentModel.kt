package com.example.ignition.financetracker.ui.billStatisticAvtivity

import com.example.ignition.financetracker.entities.CardEntity
import com.example.ignition.financetracker.repository.Repository
import com.github.mikephil.charting.data.BarData
import com.github.mikephil.charting.data.BarDataSet
import com.github.mikephil.charting.data.BarEntry
import java.math.BigDecimal

class StatisticFragmentModel : StatisticFragmentContract.Model {
    lateinit var prestnter: StatisticFragmentContract.Presenter


    override fun diagramOperations(data: List<CardEntity>): BarDataSet {
        val entries = mutableListOf<BarEntry>()

        val mergArray = mutableSetOf<String>()

        for (i in data) {
            for (j in i.cardOperations) {
                mergArray.add(j.date)
            }
        }

        val mergArray2 = mergArray

        val sum = mutableListOf<BigDecimal>()
        var tmp = 0
        for (i in mergArray) {
            tmp = 0
            for (j in data) {
                if (i == j.cardOperations[tmp].date) {
                    sum.add(tmp, j.cardOperations[tmp].sum)
                }
                tmp++
            }
        }
        for (i in 0 until mergArray.size - 1) {
            entries.add(BarEntry(i.toFloat(), sum[i].toFloat()))
        }

        val set = BarDataSet(entries, "Election Results")
//        val data = BarData(set)

        return set
    }
}