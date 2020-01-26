package com.mobile.android.chameapps.timebaskets.screens.viewpager

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.mobile.android.chameapps.timebaskets.screens.categories.impl.CategoriesFragment
import com.mobile.android.chameapps.timebaskets.screens.timetable.TimetableFragment

/**
 * Created by n.zhabitskaya on 2019-07-09.
 */
class ViewPagerAdapter(fragmentManager: FragmentManager?) :
    FragmentPagerAdapter(fragmentManager!!) {

    // Returns total number of pages
    override fun getCount(): Int {
        return 2
    }

    // Returns the fragment to display for that page
    override fun getItem(position: Int): Fragment {
        return if (position == 0) {
            CategoriesFragment.newInstance()
        }
        else {
            TimetableFragment.newInstance()
        }
    }

    // Returns the page title for the top indicator
    override fun getPageTitle(position: Int): CharSequence? {
        return "Page $position"
    }
}