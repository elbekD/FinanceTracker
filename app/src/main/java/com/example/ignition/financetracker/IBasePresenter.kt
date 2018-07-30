package com.example.ignition.financetracker

interface BaseView

interface IBasePresenter<in T : BaseView> {
    fun onAttach(view: T)
    fun onDetach()
}