package com.example.ignition.financetracker.ui.billActivity.CardFragment

import com.example.ignition.financetracker.adapters.SwipeAdapter
import com.example.ignition.financetracker.entities.CardEntity
import com.example.ignition.financetracker.repository.Repository
import io.reactivex.disposables.CompositeDisposable
import java.util.*

class CardFragmentPresenter : CardFragmentContract.Presenter {
    val cardData = Repository().getCardData()

    lateinit var view: CardFragmentContract.View

    override fun addNewCard() {

    }

    override fun loadCardData(): List<CardEntity> {
        return cardData
    }

    private val subscriptions = CompositeDisposable()


    override fun subscribe() {
    }

    override fun unsubscribe() {
        subscriptions.clear()
    }

    override fun attach(view: CardFragmentContract.View) {
        this.view = view
    }


}