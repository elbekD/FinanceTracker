package com.example.ignition.financetracker.adapters

import android.support.v7.util.DiffUtil
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import com.example.ignition.financetracker.R
import com.example.ignition.financetracker.entities.Operation
import com.example.ignition.financetracker.entities.PeriodicOperation
import com.example.ignition.financetracker.ui.main.OperationFilter
import com.example.ignition.financetracker.ui.main.walletOperations.WalletOperationContract
import com.example.ignition.financetracker.utils.Utils
import kotlinx.android.synthetic.main.itemview_operation.view.*

/**
 * Created by Elbek D. on 09.08.2018.
 */
class PeriodicOperationAdapter(_items: PeriodicOperation,
                               private val presenter: WalletOperationContract.Presenter)
    : RecyclerView.Adapter<PeriodicOperationAdapter.ViewHolder>() {

    private val ro = _items.rOperation!!
    private val items = _items.operations!!.toMutableList()
    private var originalItems: MutableList<Operation> = _items.operations!!.toMutableList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent.context)
                .inflate(R.layout.itemview_operation, parent, false)
        return ViewHolder(v)
    }

    override fun getItemCount() = items.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindView(items[position])
    }

    fun applyFilter(filter: OperationFilter) {
        val res = Utils.filterOperationList(originalItems, filter)
        items.clear()
        items.addAll(res)
        val diff = OperationDiffUtilCallback(originalItems, res)
        val diffResult = DiffUtil.calculateDiff(diff)
        diffResult.dispatchUpdatesTo(this)
    }

    private fun removePeriodicOperation(o: Operation) {
        items.remove(o)
        notifyDataSetChanged()
    }

    inner class ViewHolder(private val v: View) : RecyclerView.ViewHolder(v) {
        private val type: TextView = v.itemview_operation_type
        private val amount: TextView = v.itemview_operation_amount
        private val currency: TextView = v.itemview_operation_currency
        private val date: TextView = v.itemview_operation_date
        private val remove: ImageButton = v.itemview_operation_remove
        private val edit: ImageButton = v.itemview_operation_edit

        fun bindView(o: Operation) {
            type.text = o.operationType
            amount.text = Utils.formatDecimalNumber(o.sum)
            currency.text = o.currency
            date.text = Utils.formatDate(v.context, o.date)
            remove.setOnClickListener {
                presenter.removePeriodicOperation(ro)
                removePeriodicOperation(o)
            }
            edit.visibility = View.GONE
        }
    }

}