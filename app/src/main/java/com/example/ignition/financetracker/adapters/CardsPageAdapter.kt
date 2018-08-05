package com.example.ignition.financetracker.adapters

import android.content.Context
import android.support.v4.view.PagerAdapter
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.ignition.financetracker.R
import com.example.ignition.financetracker.ui.main.WalletModel
import com.example.ignition.financetracker.utils.Utils
import kotlinx.android.synthetic.main.card_container.view.*

class CardsPageAdapter(private val ctx: Context?, private val wallets: List<WalletModel>) : PagerAdapter() {

    override fun getCount(): Int = wallets.size
    override fun isViewFromObject(view: View, `object`: Any): Boolean = view === `object` as View
    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        container.removeView(`object` as View)
    }

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        val walletModel = wallets[position]
        val inflater = LayoutInflater.from(ctx)
        val v = inflater.inflate(R.layout.card_container, container, false)
        v.tag = walletModel.w.name

        with(v) {
            tv_cardname.text = walletModel.w.name

            card_balance.text = Utils.formatDecimalNumber(walletModel.balance)
            tv_currency.text = walletModel.w.mainCurrency

            card_balance2.text = Utils.formatDecimalNumber(walletModel.secondaryBalance)
            tv_currency2.text = walletModel.w.secondaryCurrency

            tv_income_value.text = Utils.formatDecimalNumber(walletModel.incomeValue)
            tv_outcome_value.text = Utils.formatDecimalNumber(walletModel.outcomeValue)
        }

        container.addView(v)
        return v
    }

    override fun getItemPosition(`object`: Any): Int {
        return POSITION_NONE
    }
}