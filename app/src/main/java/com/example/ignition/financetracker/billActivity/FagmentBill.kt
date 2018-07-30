package com.example.ignition.financetracker.billActivity

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.example.ignition.financetracker.R
import com.example.ignition.financetracker.billStatisticAvtivity.BillStatisticActivity
import com.example.ignition.financetracker.entities.AvailableOperations
import com.example.ignition.financetracker.entities.CurrencyType
import com.example.ignition.financetracker.financialOperations.FinancialOperations
import com.example.ignition.financetracker.repository.Repository
import kotlinx.android.synthetic.main.main_fragment.*
import java.math.BigDecimal

class FragmentBill : Fragment() {
    private var fakeData = Repository().getData()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.main_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()

    }

    private fun initViews() {

        balanceTv.text = String.format(FinancialOperations.getCurrentBalance(fakeData).toString())
        val currentLastIncome = fakeData.findLast { it.availableOperations == AvailableOperations.INCOME }
        incomeValue.text = currentLastIncome?.value.toString()
        val currentLastOutcome = fakeData.findLast { it.availableOperations == AvailableOperations.OUTCOME }
        outcomeValue.text = currentLastOutcome?.value.toString()

        startBillStatisticActivity(balanceTv, context)
        startBillStatisticActivity(your_balance_text, context)

    }


    /**
     * Method for changint USD sum on RUB. Also change currency icon
     * @param toCurrency of currency,
     * @param coefficient for changing currency
     * @param currentCurrency what currency want to show
     * @param imageView of icon for currency type
     * @param resId currency which is showing now
     */

    private fun toOtherCurrency(toCurrency: CurrencyType = CurrencyType.RUB, coefficient: Double, imageView: ImageView, resId: Int, currentCurrency: CurrencyType) {
        if (currentCurrency != toCurrency) {
            balanceTv.text = String.format(FinancialOperations.convertCurrency(balanceTv.text.toString().toBigDecimal(), BigDecimal.valueOf(coefficient))
                    .toString())
            setImage(imageView, resId)
        }

    }

    /**
     * method for setting image to imageview
     * @param imageView - imageView to be setted
     * @param resId - id of resource to set in IV
     */
    private fun setImage(imageView: ImageView, resId: Int) = imageView.setImageResource(resId)

    private fun startBillStatisticActivity(view: View, context: Context?) {
        view.setOnClickListener { startActivity(Intent(context, BillStatisticActivity::class.java)) }
    }
}