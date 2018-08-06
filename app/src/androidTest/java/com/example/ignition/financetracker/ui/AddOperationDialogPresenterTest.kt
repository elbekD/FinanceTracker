package com.example.ignition.financetracker.ui

import android.support.test.runner.AndroidJUnit4
import com.example.ignition.financetracker.ui.main.addOperationDialog.AddOperationDialogContract
import com.example.ignition.financetracker.ui.main.addOperationDialog.AddOperationModule
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.verify
import org.mockito.MockitoAnnotations

/**
 * Created by Elbek D. on 06.08.2018.
 */
@RunWith(AndroidJUnit4::class)
class AddOperationDialogPresenterTest {
    @Mock
    lateinit var view: AddOperationDialogContract.View
    lateinit var presenter: AddOperationDialogContract.Presenter

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)
        presenter = AddOperationModule.provideAddOperationPresenter()
        presenter.attachView(view)
    }

    @Test
    fun onWalletSelected_shouldChangeMainCurrency() {
        presenter.onWalletSelected("test")
        verify(view).changeMainCurrency("USD")
    }

    @Test
    fun onShowPeriod_shouldShowPeriod() {
        presenter.periodClick(true)
        verify(view).showPeriodPicker(true)
    }

    @After
    fun tearDown() {
        presenter.detachView()
    }
}