package com.example.ignition.financetracker.ui.billActivity.operationHistoryList

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.ignition.financetracker.R
import com.example.ignition.financetracker.adapters.OperationHistoryAdapter
import com.example.ignition.financetracker.repository.Repository
import kotlinx.android.synthetic.main.history_operation_list.*

class HistoryListFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.history_operation_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val items = Repository().getCardData()[0].cardOperations

        rv_history_list.layoutManager = LinearLayoutManager(context!!)

        rv_history_list.adapter = OperationHistoryAdapter(items, context!!)
    }
}