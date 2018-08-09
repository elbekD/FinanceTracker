package com.example.ignition.financetracker.ui.main.walletFragment

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.view.ViewPager
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.ignition.financetracker.R
import com.example.ignition.financetracker.adapters.WalletPageAdapter
import com.example.ignition.financetracker.entities.OperationTemplate
import com.example.ignition.financetracker.entities.Wallet
import com.example.ignition.financetracker.entities.WalletOperationsModel
import com.example.ignition.financetracker.ui.main.RepeatableOperationModel
import com.example.ignition.financetracker.ui.main.addOperationDialog.AddOperationDialog
import com.example.ignition.financetracker.ui.main.addWalletDialog.AddWalletDialog
import com.example.ignition.financetracker.ui.main.walletOperations.WalletOperationFragment
import com.github.mikephil.charting.data.PieData
import com.github.mikephil.charting.data.PieDataSet
import com.github.mikephil.charting.data.PieEntry
import com.github.mikephil.charting.utils.ColorTemplate
import kotlinx.android.synthetic.main.fragment_main.*
import java.math.BigDecimal

class WalletsFragment : Fragment(),
        WalletFragmentContract.View,
        AddWalletDialog.AddCardListener,
        AddOperationDialog.AddOperationListener {

    companion object {
        val TAG: String = WalletsFragment::class.java.simpleName
        fun newInstance(): Fragment = WalletsFragment()
    }

    private lateinit var presenter: WalletFragmentContract.Presenter
    private lateinit var walletPagerAdapter: WalletPageAdapter
    private lateinit var walletPageChangeListener: ViewPager.OnPageChangeListener
    private var currentPage = 0

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        presenter = WalletFragmentModule.provideCardFragmentPresenter()
        presenter.attachView(this)
        walletPagerAdapter = WalletPageAdapter(context, presenter)
        walletPageChangeListener = object : ViewPager.OnPageChangeListener {
            override fun onPageScrollStateChanged(state: Int) {
            }

            override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {
            }

            override fun onPageSelected(position: Int) {
                currentPage = position
                updatePieChart()
            }
        }
        presenter.load()
        return inflater.inflate(R.layout.fragment_main, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        menu_addWallet.setOnClickListener { presenter.onAddWalletClick() }
        menu_addOperation.setOnClickListener { presenter.onAddOperationClick() }

        walletsPager.addOnPageChangeListener(walletPageChangeListener)
        walletsPager.adapter = walletPagerAdapter
    }

    override fun showAddWalletDialog() {
        AddWalletDialog.newInstance().show(childFragmentManager, null)
        fab_menu.close(true)
    }

    override fun showOperationDialog() {
        AddOperationDialog.newInstance().show(childFragmentManager, null)
        fab_menu.close(true)
    }

    override fun onDestroyView() {
        presenter.detachView()
        super.onDestroyView()
    }

    override fun onDestroy() {
        walletsPager?.removeOnPageChangeListener(walletPageChangeListener)
        if (isRemoving) presenter.destroy()
        super.onDestroy()
    }

    override fun loadWallets(wallets: List<WalletOperationsModel>) {
        walletPagerAdapter.updateWalletModels(wallets)
        walletsPager.currentItem = currentPage
        if (wallets.isNotEmpty()) updatePieChart()
    }

    override fun onAddWalletClick(c: Wallet) {
        presenter.addWallet(c)
    }

    override fun onAddOperationClick(ro: RepeatableOperationModel) {
        presenter.commitOperation(ro)
    }

    override fun onAddOperationTemplate(t: OperationTemplate) {
        presenter.addTemplateOperation(t)
    }

    override fun showError(msg: Int) {
        showError(getString(msg))
    }

    override fun showError(msg: String) {
        Toast.makeText(activity, msg, Toast.LENGTH_LONG).show()
    }

    override fun openHistoryFragment(walletName: String) {
        val f = WalletOperationFragment.newInstance(
                Bundle().apply { putString(WalletOperationFragment.WALLET_NAME_EXTRA, walletName) })

        fragmentManager
                ?.beginTransaction()
                ?.replace(R.id.fragment_holder, f)
                ?.addToBackStack(null)
                ?.commit()
    }

    override fun openPeriodicFragment(walletName: String) {
        val f = WalletOperationFragment.newInstance(
                Bundle().apply {
                    putString(WalletOperationFragment.WALLET_NAME_EXTRA, walletName)
                    putBoolean(WalletOperationFragment.PERIODIC_EXTRA, true)
                })

        fragmentManager
                ?.beginTransaction()
                ?.replace(R.id.fragment_holder, f)
                ?.addToBackStack(null)
                ?.commit()
    }

    private fun updatePieChart() {
        val ops = walletPagerAdapter.getWalletModelByPosition(currentPage).operations
        val expenses = ops.asSequence().filter { it.sum < BigDecimal.ZERO }.groupBy { it.operationType }
        val amount = expenses.asSequence()
                .sumByDouble { -it.value.sumByDouble { it.sum.toDouble() } }
                .toFloat()

        val list = mutableListOf<PieEntry>()
        expenses.forEach {
            list.add(PieEntry(
                    amount / -it.value.sumByDouble { it.sum.toDouble() }.toFloat(),
                    it.key))
        }

        val pieDataSet = PieDataSet(list, getString(R.string.all_expense))
        pieDataSet.colors = ColorTemplate.MATERIAL_COLORS.asList()

        with(pieChart) {
            data = PieData(pieDataSet)
            invalidate()
        }
    }
}
