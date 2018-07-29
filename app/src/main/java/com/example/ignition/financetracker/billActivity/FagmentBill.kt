package com.example.ignition.financetracker.billActivity

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Toast
import com.example.ignition.financetracker.financialOperations.FinancialOperations
import com.example.ignition.financetracker.financialOperations.CurrencyType
import com.example.ignition.financetracker.R
import com.example.ignition.financetracker.networking.CurrencyCourseAPI

import com.example.ignition.financetracker.repository.Repository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.main_fragment.*
import java.math.BigDecimal

class FragmentBill : Fragment() {
    private var userCurrency = CurrencyType.RUB
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
        // Case ruble infor button clicked -> show RUB balance and change icon on RUB synbol, it RUB is showed -> do nothing
        rubCurrencyButton.setOnClickListener {
            toOtherCurrency(CurrencyType.RUB, 0.01, currentCurrency, R.drawable.currency_rub, userCurrency)
            userCurrency = CurrencyType.RUB
        }

        dollarCurrencyButton.setOnClickListener {
            toOtherCurrency(CurrencyType.USD, 60.0, currentCurrency, R.drawable.currency_usd, userCurrency)
            userCurrency = CurrencyType.USD
        }
    }


    /**
     * Method for changint USD sum on RUB. Also change currency icon
     * @param type of currency,
     * @param coefficient for changing currency
     * @param toCurrency what currency want to show
     * @param id of icon for currency type
     * @param currentValuta currency which is showing now
     */

    private fun toOtherCurrency(toCurrency: CurrencyType = CurrencyType.RUB, coefficient: Double, imageView: ImageView, resId: Int, currentValuta: CurrencyType) {
        if (currentValuta != toCurrency) {
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


}