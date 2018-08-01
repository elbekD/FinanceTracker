package com.example.ignition.financetracker.adapters

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.ignition.financetracker.R
import com.example.ignition.financetracker.entities.AvailableuUserActions
import com.example.ignition.financetracker.entities.OperationsModel
import kotlinx.android.synthetic.main.history_list_item.view.*

class OperationHistoryAdapter(val items: MutableList<OperationsModel>, val context: Context) : RecyclerView.Adapter<OperationHistoryAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(context).inflate(R.layout.history_list_item, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.tvHistoryOperation?.text = toProperOperation(items[position].operation)

        holder.tvHistoryOperationSum?.text = String.format(items[position].sum.toString())
    }

    // Gets the number of animals in the list
    override fun getItemCount(): Int {
        return items.size
    }


    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val tvHistoryOperation = view.operation_name
        val tvHistoryOperationSum = view.operation_sum
    }

    fun toProperOperation(availableuUserActions: AvailableuUserActions): String {
        val result: String
        when (availableuUserActions) {
            AvailableuUserActions.TRANSFER -> result = "Transfer"
            AvailableuUserActions.PRODUCTS -> result = "Products"
            AvailableuUserActions.COMMUNAL_PAYMENTS -> result = "Communal payments"
            AvailableuUserActions.CLOTHES -> result = "Clothers"
        }

        return result
    }
}