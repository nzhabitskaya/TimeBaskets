package com.mobile.android.chameapps.timebaskets.ui.categories.impl

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.mobile.android.chameapps.timebaskets.R
import com.mobile.android.chameapps.timebaskets.application.MyApplication
import com.mobile.android.chameapps.timebaskets.room.enitities.Item
import com.mobile.android.chameapps.timebaskets.ui.categories.CategoriesContract
import com.mobile.android.chameapps.timebaskets.ui.categories.ui.CategiryCard
import com.mobile.android.chameapps.timebaskets.ui.dialog.AddCategoryDialogActivity
import com.mobile.android.chameapps.timebaskets.ui.timetable.ui.CustomSpaceView
import kotlinx.android.synthetic.main.fragment_demo.*
import javax.inject.Inject
import javax.inject.Singleton


/**
 * Created by Natallia Zhabitskaya on 10/26/2019.
 */

class CategoriesFragment : Fragment(), CategoriesContract.View {

    @Inject
    lateinit var presenter: CategoriesContract.Presenter

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
        mView = inflater.inflate(R.layout.fragment_categories, container, false)
        mLinearLayout = mView.findViewById(R.id.cards_container)

        addCategory()
        addCategory()
        addCategory()
        addCategory()
        addCategory()
        addCategory()
        addCategory()
        addCategory()
        addCategory()
        addCategory()
        addButton()

        return mView
    }

    override fun displayItems(list: List<Item>) {
        for (items in list) {
            displayCard()
        }
    }

    private fun addCategory() {
        val layout: View =
            LayoutInflater.from(context).inflate(R.layout.category, mLinearLayout, false)
        mLinearLayout.addView(layout)
    }

    private fun addButton() {
        val layout: View =
            LayoutInflater.from(context).inflate(R.layout.category_add, mLinearLayout, false)
        mLinearLayout.addView(layout)
        layout.setOnClickListener {
            val myIntent = Intent(context, AddCategoryDialogActivity::class.java)
            context?.startActivity(myIntent)
        }
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
        fun newInstance(): CategoriesFragment {
            val fragment = CategoriesFragment()
            return fragment
        }
    }
}