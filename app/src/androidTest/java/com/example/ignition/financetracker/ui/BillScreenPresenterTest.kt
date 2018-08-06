package com.example.ignition.financetracker.ui

import android.support.test.runner.AndroidJUnit4
import com.example.ignition.financetracker.ui.main.BillScreenContract
import com.example.ignition.financetracker.ui.main.BillScreenModule
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
class BillScreenPresenterTest {
    @Mock
    lateinit var view: BillScreenContract.View

    lateinit var presenter: BillScreenContract.Presenter

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)
        presenter = BillScreenModule.provideBillScreenPresenter()
        presenter.attachView(view)
    }

    @Test
    fun showSettingsActivity() {
        presenter.onSettingsClick()
        verify(view).showSettings()
    }

    @After
    fun tearDown() {
        presenter.detachView()
    }
}