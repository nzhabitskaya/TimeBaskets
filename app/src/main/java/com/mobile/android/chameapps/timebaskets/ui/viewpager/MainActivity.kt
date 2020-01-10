package com.mobile.android.chameapps.timebaskets.ui.viewpager

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager.widget.ViewPager
import com.mobile.android.chameapps.timebaskets.R

class MainActivity : AppCompatActivity() {

    private var mPager: ViewPager? = null
    private var mAdapter: ViewPagerAdapter? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        mPager = findViewById(R.id.viewpager_container)
        mAdapter =
            ViewPagerAdapter(
                supportFragmentManager
            )
        mPager?.setAdapter(mAdapter)
    }

    override fun onBackPressed() {
        val currentItem = mPager!!.currentItem
        if (currentItem != 0) {
            mPager!!.setCurrentItem(currentItem - 1, true)
        } else {
            super.onBackPressed()
        }
    }
}