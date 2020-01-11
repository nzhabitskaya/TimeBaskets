package com.mobile.android.chameapps.timebaskets.ui.timetable.impl

import android.app.Dialog
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import androidx.fragment.app.Fragment
import com.mobile.android.chameapps.timebaskets.R
import com.mobile.android.chameapps.timebaskets.application.MyApplication
import com.mobile.android.chameapps.timebaskets.room.enitities.Item
import com.mobile.android.chameapps.timebaskets.ui.categories.ui.CategiryCard
import com.mobile.android.chameapps.timebaskets.ui.piechart.PieChart
import com.mobile.android.chameapps.timebaskets.ui.timetable.TimetableContract
import com.mobile.android.chameapps.timebaskets.ui.timetable.ui.CustomSpaceView
import kotlinx.android.synthetic.main.dialog_add_post.*
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
        val datas = FloatArray(6)
        datas[0] = 34f
        datas[1] = 24f
        datas[2] = 32f
        datas[3] = 24f
        datas[4] = 53f
        datas[5] = 23f
        pieChart.setData(datas)

        val labels = arrayOfNulls<String>(6)
        labels[0] = "JOHN"
        labels[1] = "GEORGE"
        labels[2] = "RAYMOND"
        labels[3] = "STEPHEN"
        labels[4] = "JACK"
        labels[5] = "BOBBY"
        pieChart.setLabels(labels)
    }

    private fun addCard4() {
        val layout: View =
            LayoutInflater.from(context).inflate(R.layout.cardview_layout_4, mLinearLayout, false)
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

    override fun openDialog() {
        if (context == null) {
            return
        }
        val dialog = Dialog(context!!)
        dialog.setContentView(R.layout.dialog_add_post)
        dialog.setCancelable(true)

        val lp = WindowManager.LayoutParams()
        lp.copyFrom(dialog.window!!.attributes)
        lp.width = WindowManager.LayoutParams.MATCH_PARENT
        lp.height = WindowManager.LayoutParams.MATCH_PARENT

        dialog.et_post.addTextChangedListener(
            object :
                TextWatcher {
                override fun beforeTextChanged(
                    s: CharSequence,
                    start: Int,
                    count: Int,
                    after: Int
                ) {
                }

                override fun onTextChanged(
                    s: CharSequence,
                    start: Int,
                    before: Int,
                    count: Int
                ) {
                    dialog.bt_submit.setEnabled(!s.toString().trim { it <= ' ' }.isEmpty())
                }

                override fun afterTextChanged(s: Editable) {}
            })

        dialog.bt_submit.setOnClickListener({
            dialog.dismiss()
            presenter.saveItem(dialog.et_title.text.toString(), dialog.et_post.text.toString())
        })

        dialog.bt_close.setOnClickListener { dialog.dismiss() }

        dialog.show()
        dialog.window!!.attributes = lp
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