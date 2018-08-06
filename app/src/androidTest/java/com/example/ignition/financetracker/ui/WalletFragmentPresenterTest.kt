package com.example.ignition.financetracker.ui

import android.support.test.runner.AndroidJUnit4
import com.example.ignition.financetracker.ui.main.walletFragment.WalletFragmentContract
import com.example.ignition.financetracker.ui.main.walletFragment.WalletFragmentModule
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.verify
import org.mockito.MockitoAnnotations
import org.mockito.Mockito.`when` as whenever

/**
 * Created by Elbek D. on 06.08.2018.
 */
@RunWith(AndroidJUnit4::class)
class WalletFragmentPresenterTest {
    @Mock
    lateinit var view: WalletFragmentContract.View
    lateinit var presenter: WalletFragmentContract.Presenter

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)
        presenter = WalletFragmentModule.provideCardFragmentPresenter()
        presenter.attachView(view)
    }

    @Test
    fun showAddNewWalletDialog() {
        presenter.onOpenAddWalletClick()
        verify(view).showAddWalletDialog()
    }

    @Test
    fun showAddNewOperationDialog() {
        presenter.onAddOperationClick()
        verify(view).showOperationDialog()
    }

    @After
    fun tearDown() {
        presenter.detachView()
    }
}