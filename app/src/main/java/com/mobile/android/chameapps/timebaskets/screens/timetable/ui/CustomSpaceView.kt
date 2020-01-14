package com.mobile.android.chameapps.timebaskets.screens.timetable.ui

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.LinearLayout
import com.mobile.android.chameapps.timebaskets.R

/**
 * Created by Natallia Zhabitskaya on 12/30/2019.
 */

class CustomSpaceView @JvmOverloads constructor(
    context: Context?,
    attrs: AttributeSet? = null,
    defStyle: Int = 0
) : LinearLayout(context, attrs, defStyle) {
    init {
        val mRoot =
            View.inflate(context, R.layout.spaceview_layout, null)
        addView(mRoot, -1, -1)
        //mView = (ImageView) mRoot.findViewById(R.id.view);
    }
}