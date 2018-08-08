package com.example.ignition.financetracker


import com.example.ignition.financetracker.ui.main.addCardDialog.AddCardDialogContract
import com.example.ignition.financetracker.ui.main.addCardDialog.AddCardModule
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.verify
import org.mockito.MockitoAnnotations

/**
 * Created by Elbek D. on 06.08.2018.
 */
class AddWalletDialogPresenterTest {
    @Mock
    lateinit var view: AddCardDialogContract.View
    lateinit var presenter: AddCardDialogContract.Presenter

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)
        presenter = AddCardModule.provideAddCardModule()
        presenter.attachView(view)
    }

    @Test
    fun onPresenterStart_shouldSetAdapter() {
        presenter.start()
        verify(view).setCurrencyAdapter(listOf("RUB", "USD"))
    }

    @After
    fun tearDown() {
        presenter.detachView()
    }
}