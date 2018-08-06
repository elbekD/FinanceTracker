package com.example.ignition.financetracker

import android.support.annotation.AnimRes
import android.support.annotation.IdRes
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity


fun AppCompatActivity.replaceFragment(
        fragment: Fragment,
        tag: String,
        allowStateLoss: Boolean = false,
        @IdRes containerViewId: Int,
        @AnimRes enterAnimation: Int = 0,
        @AnimRes exitAnimation: Int = 0,
        @AnimRes popEnterAnimation: Int = 0,
        @AnimRes popExitAnimation: Int = 0) {
    val ft = supportFragmentManager
            .beginTransaction()
            .setCustomAnimations(enterAnimation, exitAnimation, popEnterAnimation, popExitAnimation)
            .replace(containerViewId, fragment, tag)
    if (!supportFragmentManager.isStateSaved) {
        ft.commit()
    } else if (allowStateLoss) {
        ft.commitAllowingStateLoss()
    }
}

fun AppCompatActivity.addFragment(fragment: Fragment, frameId: Int, tag: String) {
    supportFragmentManager.beginTransaction().replace(frameId, fragment, tag).addToBackStack(fragment.javaClass.name).commit()
}

//inline fun ViewPager.seViewPagertCustomListener(viewPager: ViewPager, data: List<Wallet>, crossinline settingViewChanges: (pos: Int, data: List<Wallet>) -> Unit) {
//    viewPager.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
//
//        override fun onPageSelected(position: Int) {
//
//        }
//
//        // This method will be invoked when the current page is scrolled
//        override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {
//            settingViewChanges(position, data)
//        }
//
//        override fun onPageScrollStateChanged(state: Int) {
//        }
//    })
//}
//
//
//
