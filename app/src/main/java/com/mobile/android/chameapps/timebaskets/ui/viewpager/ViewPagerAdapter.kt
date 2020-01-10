package com.mobile.android.chameapps.timebaskets.ui.viewpager

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.mobile.android.chameapps.timebaskets.ui.categories.impl.CategoriesFragment
import com.mobile.android.chameapps.timebaskets.ui.timetable.impl.TimetableFragment

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
            TimetableFragment.newInstance()
        }
        else if (position == 1) {
            CategoriesFragment.newInstance()
        }
        else {
            CategoriesFragment.newInstance()
        }
    }

    // Returns the page title for the top indicator
    override fun getPageTitle(position: Int): CharSequence? {
        return "Page $position"
    }
}