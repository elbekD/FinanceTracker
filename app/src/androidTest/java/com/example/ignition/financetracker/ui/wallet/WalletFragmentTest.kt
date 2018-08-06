package com.example.ignition.financetracker.ui.wallet

import android.support.test.espresso.Espresso.onData
import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.UiController
import android.support.test.espresso.ViewAction
import android.support.test.espresso.action.ViewActions.click
import android.support.test.espresso.action.ViewActions.typeText
import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.matcher.ViewMatchers.*
import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4
import android.view.View
import android.widget.TextView
import com.example.ignition.financetracker.R
import com.example.ignition.financetracker.ui.main.BillScreen
import org.hamcrest.CoreMatchers.*
import org.hamcrest.Matcher
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

/**
 * Created by Elbek D. on 06.08.2018.
 */
@RunWith(AndroidJUnit4::class)
class WalletFragmentTest {
    @Rule
    @JvmField
    val activityRule = ActivityTestRule(BillScreen::class.java, true, true)

    @Before
    fun init() {

    }

    @Test
    fun openSettingsActivity_andOpenAboutActivity() {
        onView(withId(R.id.menu_settings)).perform(click())
        onView(withId(R.id.aboutAppText)).check(matches(isDisplayed()))

        onView(withId(R.id.aboutAppClick)).perform(click())
        onView(withId(R.id.versionNumber)).check(matches(isDisplayed()))
    }

    @Test
    fun openAddCardDialog_andAddNewCard() {
        onView(withId(R.id.addCard)).perform(click())
        onView(withId(R.id.input_newWalletName)).check(matches(isDisplayed()))

        onView(withId(R.id.input_newWalletName)).perform(typeText("wallet"))

        onView(withId(R.id.input_walletType)).perform(typeText("type 1"))

        onView(withText(android.R.string.ok)).perform(click())

        onView(withText("wallet")).check(matches(isDisplayed()))
    }

    @Test
    fun openAddOperationDialog_andAddNewOperation() {
        onView(withId(R.id.menu_addtransaction)).perform(click())
        onView(withId(R.id.input_amount)).perform(typeText("500"))

        // todo cannot perform click on spinner
//        onView(withId(R.id.spinner_operationcurrency)).perform(click())
//        onData(allOf(`is`(instanceOf(String::class.java)), `is`("USD"))).perform(click())

        onView(withId(R.id.income)).perform(click())
        onView(withText(android.R.string.ok)).perform(click())
    }
}