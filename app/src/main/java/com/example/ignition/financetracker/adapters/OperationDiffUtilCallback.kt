package com.example.ignition.financetracker.adapters

import android.support.v7.util.DiffUtil
import com.example.ignition.financetracker.entities.Operation

/**
 * Created by Elbek D. on 09.08.2018.
 */
class OperationDiffUtilCallback(private val oldList: List<Operation>,
                                        private val newList: List<Operation>) : DiffUtil.Callback() {

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int) =
            oldList[oldItemPosition].id == newList[newItemPosition].id

    override fun getOldListSize() = oldList.size

    override fun getNewListSize() = newList.size

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int) =
            oldList[oldItemPosition] == newList[newItemPosition]
}