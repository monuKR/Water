package com.mindby.water.ui

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager.widget.PagerAdapter
import androidx.viewpager.widget.ViewPager
import androidx.viewpager.widget.ViewPager.OnPageChangeListener
import com.mindby.water.R
import com.mindby.water.utils.MySharedPreferences

class WelcomeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.welcome_activity)

        val viewPager = findViewById<ViewPager>(R.id.view_pager)

        val viewPagerAdapter = ViewPagerAdapter()
        viewPager?.adapter = viewPagerAdapter

        viewPager.addOnPageChangeListener(object : OnPageChangeListener {
            override fun onPageScrolled(
                position: Int,
                positionOffset: Float,
                positionOffsetPixels: Int
            ) {

            }

            override fun onPageSelected(position: Int) {}
            override fun onPageScrollStateChanged(state: Int) {}
        })


    }

    inner class ViewPagerAdapter : PagerAdapter() {
        private var layoutInflater: LayoutInflater? = null
        private var layoutArray = intArrayOf(
            R.layout.welcome_page_1,
            R.layout.welcome_page_2,
            R.layout.welcome_page_3
        )


        override fun instantiateItem(container: ViewGroup, position: Int): Any {


            layoutInflater = getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
            val view = layoutInflater!!.inflate(layoutArray!![position], container, false)
            container.addView(view)
            return view
        }

        override fun getCount(): Int {
            Log.e("TAGG", "Sze = " + layoutArray!!.size)
            return layoutArray!!.size
        }

        override fun isViewFromObject(view: View, obj: Any): Boolean {
            return view == obj
        }

        override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
            if (`object` is View) container.removeView(`object`)
        }
    }

    fun skip(view: View) {
        val sharedPreferences = this.getSharedPreferences(MySharedPreferences.WELCOME, 0)

        val editor = sharedPreferences.edit()

        editor.putBoolean(MySharedPreferences.FIRST_TIME, false)
        editor.apply()
        finish()
    }
}