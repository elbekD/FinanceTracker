package com.example.ignition.financetracker.ui.main.walletFragment

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.ignition.financetracker.R
import com.example.ignition.financetracker.adapters.CardsPageAdapter
import com.example.ignition.financetracker.adapters.createWalletModelFrom
import com.example.ignition.financetracker.entities.Operation
import com.example.ignition.financetracker.entities.Wallet
import com.example.ignition.financetracker.ui.main.addCardDialog.AddCardDialog
import com.example.ignition.financetracker.ui.main.addOperationDialog.AddOperationDialog
import kotlinx.android.synthetic.main.cards_fragment.*
import java.math.BigDecimal

class WalletsFragment : Fragment(),
        CardFragmentContract.View,
        AddCardDialog.AddCardListener,
        AddOperationDialog.AddOperationListener {

    companion object {
        val TAG = WalletsFragment::class.java.simpleName
        fun newInstance(): Fragment = WalletsFragment()
    }

    private lateinit var presenter: CardFragmentContract.Presenter
    private val listOfCards = mutableListOf<WalletModel>()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.cards_fragment, container, false)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        presenter = WalletFragmentModule.provideCardFragmentPresenter()
        presenter.attachView(this)
        presenter.load()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        addCard.setOnClickListener { presenter.onOpenAddWalletClick() }
        menu_addtransaction.setOnClickListener { presenter.onAddTransactionClick() }
    }

    override fun showAddWalletDialog() {
        AddCardDialog.newInstance().show(childFragmentManager, null)
    }

    override fun showTransactionDialog() {
        AddOperationDialog.newInstance().show(childFragmentManager, null)
    }

    override fun onDestroy() {
        presenter.detachView()
        if (isRemoving) presenter.destroy()
        super.onDestroy()
    }

    override fun setCardAdapter(wallets: List<WalletModel>) {
        listOfCards.addAll(wallets)
        val adapter = CardsPageAdapter(context, listOfCards)
        cardsPager.adapter = adapter
    }

    override fun onAddWalletClick(c: Wallet) {
        presenter.addWallet(c)
    }

    override fun onAddOperationClick(o: Operation) {
        presenter.commitOperation(o)
    }

    override fun addWalletToPager(c: Wallet) {
        listOfCards.add(createWalletModelFrom(c))
        cardsPager.adapter?.notifyDataSetChanged()
        cardsPager.currentItem = listOfCards.size - 1
    }

    override fun updateWalletModel(o: Operation) {
        val m = (cardsPager.adapter as CardsPageAdapter).getWalletModel(o.walletName)
        val balance = m.balance + o.sum
        val secondaryBalance = m.secondaryBalance + o.sum / o.rate
        val (deltaIn, deltaOut) = if (o.sum > BigDecimal.ZERO) (o.sum to BigDecimal.ZERO) else (BigDecimal.ZERO to -o.sum)
        val updatedModel = WalletModel(m.w, balance,
                secondaryBalance,
                m.incomeValue + deltaIn,
                m.outcomeValue + deltaOut)

        listOfCards.find { it.w.name == o.walletName }?.let {
            val i = listOfCards.indexOf(it)
            listOfCards.removeAt(i)
            listOfCards.add(i, updatedModel)
            cardsPager.adapter?.notifyDataSetChanged()
        }
    }
}
