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
import com.example.ignition.financetracker.ui.main.walletFragment.WalletFragmentContract
import com.example.ignition.financetracker.utils.Utils
import kotlinx.android.synthetic.main.itemview_operation.view.*

/**
 * Created by Elbek D. on 07.08.2018.
 */
class OperationAdapter(private val presenter: WalletFragmentContract.Presenter)
    : RecyclerView.Adapter<OperationAdapter.ViewHolder>() {

    private val items = mutableListOf<Operation>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.itemview_operation, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount() = items.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindView(items[position], position)
    }

    fun updateOperations(newData: List<Operation>) {
        val diff = OperationDiffUtilCallback(items, newData)
        val diffResult = DiffUtil.calculateDiff(diff)
        items.clear()
        items.addAll(newData)
        diffResult.dispatchUpdatesTo(this)
    }

    inner class ViewHolder(private val v: View) : RecyclerView.ViewHolder(v) {
        private val type: TextView = v.itemview_operation_type
        private val amount: TextView = v.itemview_operation_amount
        private val currency: TextView = v.itemview_operation_currency
        private val date: TextView = v.itemview_operation_date
        private val remove: ImageButton = v.itemview_operation_remove
        private val edit: ImageButton = v.itemview_operation_edit

        fun bindView(o: Operation, position: Int) {
            with(v) {
                type.text = o.operationType
                amount.text = Utils.formatDecimalNumber(o.sum)
                currency.text = o.currency
                date.text = Utils.formatDate(v.context, o.date)
                remove.setOnClickListener {
                    presenter.removeOperation(o)
                    notifyItemRemoved(position)
                }
                edit.setOnClickListener { presenter.onEditOperation(o) }
            }
        }
    }

    private class OperationDiffUtilCallback(private val oldList: List<Operation>,
                                            private val newList: List<Operation>) : DiffUtil.Callback() {

        override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int) =
                oldList[oldItemPosition].id == newList[newItemPosition].id

        override fun getOldListSize() = oldList.size

        override fun getNewListSize() = newList.size

        override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int) =
                oldList[oldItemPosition] == newList[newItemPosition]
    }
}