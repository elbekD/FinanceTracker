package com.example.ignition.financetracker.billActivity

import android.content.Context
import android.support.v4.view.PagerAdapter
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.ignition.financetracker.R
import com.example.ignition.financetracker.repository.Repository


class SwipeDialogVariantsAdapter(context: Context?) : PagerAdapter() {
    private val fakeOptions = Repository().getOperations()

    private val layoutInflater = LayoutInflater.from(context)

    override fun getCount(): Int = fakeOptions.size

    override fun isViewFromObject(view: View, `object`: Any): Boolean = view === `object` as View

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        container.removeView(`object` as View)
    }

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        val view = layoutInflater.inflate(R.layout.swipe_text_operations, container, false)

        val textSwipe: TextView = view.findViewById(R.id.swipe_text_options)
        val resId = fakeOptions[position]

        textSwipe.text = resId
        container.addView(view)

        return view
    }
}