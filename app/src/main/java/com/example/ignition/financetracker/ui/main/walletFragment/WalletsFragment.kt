package com.example.ignition.financetracker.ui.main.walletFragment

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.ignition.financetracker.R
import com.example.ignition.financetracker.adapters.CardsPageAdapter
import com.example.ignition.financetracker.adapters.createWalletModelFrom
import com.example.ignition.financetracker.entities.Wallet
import com.example.ignition.financetracker.ui.main.RepeatableOperationModel
import com.example.ignition.financetracker.ui.main.WalletModel
import com.example.ignition.financetracker.ui.main.WalletOperationModel
import com.example.ignition.financetracker.ui.main.addCardDialog.AddCardDialog
import com.example.ignition.financetracker.ui.main.addOperationDialog.AddOperationDialog
import kotlinx.android.synthetic.main.cards_fragment.*
import java.math.BigDecimal

class WalletsFragment : Fragment(),
        WalletFragmentContract.View,
        AddCardDialog.AddCardListener,
        AddOperationDialog.AddOperationListener {

    companion object {
        val TAG = WalletsFragment::class.java.simpleName
        fun newInstance(): Fragment = WalletsFragment()
    }

    private lateinit var presenter: WalletFragmentContract.Presenter
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
        menu_addtransaction.setOnClickListener { presenter.onAddOperationClick() }
    }

    override fun showAddWalletDialog() {
        AddCardDialog.newInstance().show(childFragmentManager, null)
    }

    override fun showOperationDialog() {
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

    override fun onAddOperationClick(ro: RepeatableOperationModel) {
        presenter.commitOperation(ro)
    }

    override fun addWalletToPager(c: Wallet) {
        listOfCards.add(createWalletModelFrom(c))
        cardsPager.adapter?.notifyDataSetChanged()
        cardsPager.currentItem = listOfCards.size - 1
    }

    override fun showError(msg: String) {
        Toast.makeText(activity, msg, Toast.LENGTH_LONG).show()
    }

    override fun updateWalletModel(wOp: WalletOperationModel) {
        val m = listOfCards.find { it.w.name == wOp.operation.walletName }
        m?.let { model ->
            val balance = model.balance + wOp.operation.sum * wOp.operation.rate
            val secondaryBalance = balance * wOp.mainToSecondaryRate

            val (deltaIn, deltaOut)
                    = if (wOp.operation.sum > BigDecimal.ZERO) (wOp.operation.sum * wOp.operation.rate to BigDecimal.ZERO)
            else (BigDecimal.ZERO to wOp.operation.sum * wOp.operation.rate.negate())

            val updatedModel = WalletModel(model.w, balance,
                    secondaryBalance,
                    model.incomeValue + deltaIn,
                    model.outcomeValue + deltaOut)

            listOfCards.find { it.w.name == wOp.operation.walletName }?.let {
                val i = listOfCards.indexOf(it)
                listOfCards.removeAt(i)
                listOfCards.add(i, updatedModel)
                cardsPager.adapter?.notifyDataSetChanged()
            }
        }
    }
}
