package com.example.ignition.financetracker.ui.main.walletOperations

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.ignition.financetracker.R
import com.example.ignition.financetracker.adapters.OperationAdapter
import com.example.ignition.financetracker.adapters.PeriodicOperationAdapter
import com.example.ignition.financetracker.entities.Operation
import com.example.ignition.financetracker.entities.OperationFilter
import com.example.ignition.financetracker.entities.PeriodicOperation
import com.example.ignition.financetracker.ui.main.RepeatableOperationModel
import com.example.ignition.financetracker.ui.main.addOperationDialog.AddOperationDialog
import com.example.ignition.financetracker.ui.main.walletOperations.filterDialog.FilterDialog
import kotlinx.android.synthetic.main.fragment_operations.*

/**
 * Created by Elbek D. on 09.08.2018.
 */
class WalletOperationFragment : Fragment(), WalletOperationContract.View,
        AddOperationDialog.AddOperationListener,
        FilterDialog.FilterDialogListener {
    companion object {
        const val WALLET_NAME_EXTRA = "wallet_name"
        const val PERIODIC_EXTRA = "is_periodic"
        fun newInstance(args: Bundle) = WalletOperationFragment().apply { arguments = args }
    }

    private lateinit var presenter: WalletOperationContract.Presenter
    private var periodicOperationAdapter: PeriodicOperationAdapter? = null
    private var operationAdapter: OperationAdapter? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_operations, container, false)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        presenter = WalletOperationsModule.providePeriodicOperationPresenter()
        presenter.attachView(this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recyclerview_operations.layoutManager = LinearLayoutManager(context)
        if (arguments?.getBoolean(PERIODIC_EXTRA, false)!!) {
            presenter.loadPeriodic(arguments?.getString(WALLET_NAME_EXTRA)!!)
            tv_operationsType.text = getString(R.string.operations_periodic)
        } else {
            presenter.loadOperations(arguments?.getString(WALLET_NAME_EXTRA)!!)
            tv_operationsType.text = getString(R.string.operations_done)
        }
        btn_operationFilter.setOnClickListener { presenter.onOperationFilterClick() }
    }

    override fun setPeriodicData(data: PeriodicOperation) {
        periodicOperationAdapter = PeriodicOperationAdapter(data, presenter)
        recyclerview_operations.adapter = periodicOperationAdapter
    }

    override fun setOperationData(data: List<Operation>) {
        operationAdapter = OperationAdapter(presenter).apply { updateOperations(data) }
        recyclerview_operations.adapter = operationAdapter
    }

    override fun showEditOperationDialog(o: Operation) {
        AddOperationDialog.newInstance(o).show(childFragmentManager, null)
    }

    override fun onDestroy() {
        presenter.detachView()
        presenter.destroy()
        super.onDestroy()
    }

    override fun onAddOperationClick(ro: RepeatableOperationModel) {
        presenter.saveEditedOperation(ro.operation)
    }

    override fun showOperationFilter() {
        FilterDialog.newInstance().show(childFragmentManager, null)
    }

    override fun applyFilter(filter: OperationFilter) {
        operationAdapter?.applyFilter(filter) ?: periodicOperationAdapter?.applyFilter(filter)
    }
}