package com.example.ignition.financetracker.adapters

import android.content.Context
import android.support.v4.view.PagerAdapter
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.example.ignition.financetracker.R
import com.example.ignition.financetracker.repository.Repository

class SwipeAdapter(context: Context?) : PagerAdapter() {
    private val fakeImageData = Repository().getCardData()


    private val layoutInflater = LayoutInflater.from(context)

    override fun getCount(): Int = fakeImageData.size

    override fun isViewFromObject(view: View, `object`: Any): Boolean = view === `object` as View

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        container.removeView(`object` as View)
    }

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        val view = layoutInflater.inflate(R.layout.swipe_cards, container, false)


        val imageView: ImageView = view.findViewById(R.id.card_image)
        val resId = fakeImageData[position].cardLogoRes

        imageView.setImageResource(resId)
        container.addView(view)

        return view
    }


}