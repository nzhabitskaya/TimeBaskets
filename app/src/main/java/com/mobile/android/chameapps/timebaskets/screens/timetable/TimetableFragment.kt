package com.mobile.android.chameapps.timebaskets.screens.timetable

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.mobile.android.chameapps.timebaskets.R
import com.mobile.android.chameapps.timebaskets.screens.timetable.piechart.PieChart
import javax.inject.Singleton


/**
 * Created by Natallia Zhabitskaya on 10/26/2019.
 */

class TimetableFragment : Fragment() {

    private lateinit var mView: View

    private lateinit var mLinearLayout: ViewGroup

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mView = inflater.inflate(R.layout.fragment_demo, container, false)
        mLinearLayout = mView.findViewById(R.id.cards_container)

        addCard1()
        addEmptySpace()
        addCard2()
        addEmptySpace()
        addCard3()
        addEmptySpace()
        addCard4()

        return mView
    }

    private fun addCard1() {
        val layout: View =
            LayoutInflater.from(context).inflate(R.layout.cardview_layout_1, mLinearLayout, false)
        mLinearLayout.addView(layout)
    }

    private fun addCard2() {
        val layout: View =
            LayoutInflater.from(context).inflate(R.layout.cardview_layout_2, mLinearLayout, false)
        mLinearLayout.addView(layout)
    }

    private fun addCard3() {
        val layout: View =
            LayoutInflater.from(context).inflate(R.layout.piechart_layout, mLinearLayout, false)
        mLinearLayout.addView(layout)

        val pieChart: PieChart = layout.findViewById(R.id.pieChart) as PieChart
        val datas = FloatArray(4)
        datas[0] = 15f
        datas[1] = 25f
        datas[2] = 25f
        datas[3] = 35f
        pieChart.setData(datas)

        val labels = arrayOfNulls<String>(4)
        labels[0] = getString(R.string.hint_5)
        labels[1] = getString(R.string.hint_6)
        labels[2] = getString(R.string.hint_7)
        labels[3] = getString(R.string.hint_8)
        pieChart.setLabels(labels)
    }

    private fun addCard4() {
        val layout: View =
            LayoutInflater.from(context).inflate(R.layout.cardview_layout_3, mLinearLayout, false)
        mLinearLayout.addView(layout)
    }

    private fun addEmptySpace() {
        val layout: View =
            LayoutInflater.from(context).inflate(R.layout.spaceview_layout, mLinearLayout, false)
        mLinearLayout.addView(layout)
    }

    companion object {
        @Singleton
        fun newInstance(): TimetableFragment {
            val fragment =
                TimetableFragment()
            return fragment
        }
    }
}