package com.example.ignition.financetracker.adapters

import android.content.Context
import android.support.v4.view.PagerAdapter
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.ignition.financetracker.R
import com.example.ignition.financetracker.entities.WalletOperationsModel
import com.example.ignition.financetracker.ui.main.walletFragment.WalletFragmentContract
import com.example.ignition.financetracker.utils.Utils
import kotlinx.android.synthetic.main.framelayout_walletcontainer.view.*

class WalletPageAdapter(private val ctx: Context?, private val presenter: WalletFragmentContract.Presenter) : PagerAdapter() {

    private val wallets = mutableListOf<WalletOperationsModel>()

    fun addWalletModel(w: WalletOperationsModel): Int {
        wallets.add(w)
        notifyDataSetChanged()
        return wallets.size - 1
    }

    fun updateWalletModels(w: List<WalletOperationsModel>) {
        wallets.clear()
        wallets.addAll(w)
        notifyDataSetChanged()
    }

    fun getWalletModelByPosition(pos: Int): WalletOperationsModel {
        return wallets[pos]
    }

    override fun getCount(): Int = wallets.size
    override fun isViewFromObject(view: View, `object`: Any): Boolean = view === `object` as View
    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) = container.removeView(`object` as View)
    override fun getItemPosition(`object`: Any) = POSITION_NONE

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        val walletModel = wallets[position]
        val inflater = LayoutInflater.from(ctx)
        val v = inflater.inflate(R.layout.framelayout_walletcontainer, container, false)
        v.tag = walletModel.wallet.name

        with(v) {
            tv_cardname.text = walletModel.wallet.name

            card_balance.text = Utils.formatDecimalNumber(walletModel.balance)
            tv_currency.text = walletModel.wallet.mainCurrency

//            card_balance2.text = Utils.formatDecimalNumber(walletModel.wallet.secondaryBalance)
//            tv_currency2.text = walletModel.wallet.secondaryCurrency

            tv_income_value.text = Utils.formatDecimalNumber(walletModel.income)
            tv_outcome_value.text = Utils.formatDecimalNumber(walletModel.expense)

            wallet_history.setOnClickListener {
                presenter.onWalletHistoryClick(walletModel.wallet.name)
            }

            wallet_periodic.setOnClickListener {
                presenter.onWalletPeriodicClick(walletModel.wallet.name)
            }
        }

        container.addView(v)
        return v
    }
}