package com.example.ignition.financetracker.ui.billStatisticAvtivity

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.ignition.financetracker.R

class StatisticFragment : Fragment(), StatisticFragmentContract.View {

    companion object {
        const val CARD_NAME_EXTRA = "walletName"
        fun newInstance(args: Bundle) = StatisticFragment().apply { arguments = args }
    }

    private lateinit var presenter: StatisticFragmentContract.Presenter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.diagram_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        presenter = StatisticFragmentModule.provideStatisticFragmentPresenter()
        presenter.attachView(this)
        initViews()
    }

    private fun initViews() {

    }
}