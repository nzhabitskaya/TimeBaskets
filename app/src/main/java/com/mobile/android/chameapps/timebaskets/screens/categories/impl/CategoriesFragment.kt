package com.mobile.android.chameapps.timebaskets.screens.categories.impl

import android.content.Intent
import android.graphics.BitmapFactory
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.mobile.android.chameapps.timebaskets.R
import com.mobile.android.chameapps.timebaskets.application.MyApplication
import com.mobile.android.chameapps.timebaskets.room.enitities.Category
import com.mobile.android.chameapps.timebaskets.screens.categories.CategoriesContract
import com.mobile.android.chameapps.timebaskets.screens.categories.dialog.impl.CategoryDialogActivity
import kotlinx.android.synthetic.main.category.view.*
import java.io.ByteArrayInputStream
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
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mView = inflater.inflate(R.layout.fragment_categories, container, false)
        mLinearLayout = mView.findViewById(R.id.cards_container)
        return mView
    }

    override fun displayItems(list: List<Category>) {
        mLinearLayout.removeAllViews()
        for (item in list) {
            addCategory(item)
        }
        addButton()
    }

    override fun onResume() {
        super.onResume()
        presenter.loadItems()
    }

    private fun addCategory(category: Category) {
        val categoryLayout: View =
            LayoutInflater.from(context).inflate(R.layout.category, mLinearLayout, false)
        mLinearLayout.addView(categoryLayout)
        categoryLayout.title.text = category.title
        categoryLayout.title_shadow.text = category.title
        val inputStream = ByteArrayInputStream(category.byteArray)
        categoryLayout.iv.setImageBitmap(BitmapFactory.decodeStream(inputStream))
    }

    private fun addButton() {
        val layout: View =
            LayoutInflater.from(context).inflate(R.layout.category_add, mLinearLayout, false)
        mLinearLayout.addView(layout)
        layout.setOnClickListener {
            val myIntent = Intent(context, CategoryDialogActivity::class.java)
            context?.startActivity(myIntent)
        }
    }

    private fun injectDependency() {
        (activity?.application as MyApplication).getAppComponent(context!!)?.inject(this)
    }

    companion object {
        @Singleton
        fun newInstance(): CategoriesFragment {
            val fragment = CategoriesFragment()
            return fragment
        }
    }
}