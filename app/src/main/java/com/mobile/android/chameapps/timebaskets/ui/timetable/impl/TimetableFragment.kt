package com.mobile.android.chameapps.timebaskets.ui.timetable.impl

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.mobile.android.chameapps.timebaskets.R
import com.mobile.android.chameapps.timebaskets.application.MyApplication
import com.mobile.android.chameapps.timebaskets.room.enitities.Item
import com.mobile.android.chameapps.timebaskets.ui.categories.ui.CategiryCard
import com.mobile.android.chameapps.timebaskets.ui.piechart.PieChart
import com.mobile.android.chameapps.timebaskets.ui.timetable.TimetableContract
import com.mobile.android.chameapps.timebaskets.ui.timetable.ui.CustomSpaceView
import kotlinx.android.synthetic.main.fragment_demo.*
import javax.inject.Inject
import javax.inject.Singleton


/**
 * Created by Natallia Zhabitskaya on 10/26/2019.
 */

class TimetableFragment : Fragment(), TimetableContract.View {

    @Inject
    lateinit var presenter: TimetableContract.Presenter

    private lateinit var mView: View

    private lateinit var mLinearLayout: ViewGroup

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        injectDependency()
        presenter.attach(this)
        presenter.loadItems()
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

    override fun displayItems(list: List<Item>) {
        Log.e("ABC", "Items: " + list.size)
        for (items in list) {
            displayCard()
        }
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
        val datas = FloatArray(3)
        datas[0] = 30f
        datas[1] = 30f
        datas[2] = 30f
        pieChart.setData(datas)

        val labels = arrayOfNulls<String>(3)
        labels[0] = getString(R.string.hint_5)
        labels[1] = getString(R.string.hint_6)
        labels[2] = getString(R.string.hint_7)
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

    override fun displayCard() {
        cards_container.addView(
            CategiryCard(
                context
            )
        )
        cards_container.addView(CustomSpaceView(context))
    }

    private fun injectDependency() {
        (activity!!.application as MyApplication).getAppComponent(context!!)!!.inject(this)
    }

    companion object {
        @Singleton
        fun newInstance(): TimetableFragment {
            val fragment = TimetableFragment()
            return fragment
        }
    }
}