package com.example.ignition.financetracker.ui.billStatisticAvtivity

import com.example.ignition.financetracker.entities.CardEntity
import com.example.ignition.financetracker.repository.Repository
import com.example.ignition.financetracker.ui.billActivity.IBillScreenContract
import com.github.mikephil.charting.data.BarDataSet
import io.reactivex.disposables.CompositeDisposable

class StatisticFragmentPrestnter : StatisticFragmentContract.Presenter {


    val data = Repository().getCardData()

    lateinit var view: StatisticFragmentContract.View

    var model: StatisticFragmentContract.Model = StatisticFragmentModel()

    private val subscriptions = CompositeDisposable()

    override fun onChangeCursePresed(currencyValue: String): String {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onLoadCourseCurrency() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }


    override fun subscribe() {
    }

    override fun unsubscribe() {
        subscriptions.clear()
    }

    override fun attach(view: StatisticFragmentContract.View) {
        this.view = view
    }

    override fun onLoadDiagramValues(): BarDataSet {
        return model.diagramOperations(data)

    }

}