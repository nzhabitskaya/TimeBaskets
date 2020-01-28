package com.mobile.android.chameapps.timebaskets.screens.todolist.impl

import android.graphics.BitmapFactory
import android.graphics.Color
import android.graphics.drawable.BitmapDrawable
import android.os.Bundle
import android.view.KeyEvent
import android.widget.EditText
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.mobile.android.chameapps.timebaskets.R
import com.mobile.android.chameapps.timebaskets.application.MyApplication
import com.mobile.android.chameapps.timebaskets.room.enitities.Item
import com.mobile.android.chameapps.timebaskets.screens.categories.impl.CategoriesFragment
import com.mobile.android.chameapps.timebaskets.screens.todolist.TodoListContract
import com.mobile.android.chameapps.timebaskets.screens.todolist.ui.adapter.ListAdapter
import com.mobile.android.chameapps.timebaskets.tools.Util
import java.io.ByteArrayInputStream
import java.util.*
import javax.inject.Inject


/**
 * Created by Natallia Zhabitskaya on 10/26/2019.
 */

class TodoListActivity : AppCompatActivity(), TodoListContract.View {

    @Inject
    lateinit var presenter: TodoListContract.Presenter

    private lateinit var recyclerView: RecyclerView

    private lateinit var editText: EditText

    private lateinit var bgImageView: CoordinatorLayout

    private lateinit var adapter: ListAdapter

    private var categoryId: Long = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        injectDependency()
        presenter.attach(this)
        setContentView(R.layout.fragment_todolist)
        recyclerView = findViewById(R.id.recycler_view)
        editText = findViewById(R.id.edit_text)
        bgImageView = findViewById(R.id.background_container)

        categoryId = intent.getLongExtra(CategoriesFragment.CATEGORY_ID, 0)
        findViewById<LinearLayout>(R.id.foreground_container).setBackgroundColor(generateColor())

        initRecyclerView()
        presenter.loadBackground(categoryId)
    }

    fun initRecyclerView() {
        adapter = ListAdapter()
        recyclerView.setAdapter(adapter)

        recyclerView.setItemAnimator(DefaultItemAnimator())
        recyclerView.setHasFixedSize(true)
        recyclerView.setLayoutManager(LinearLayoutManager(this))
    }

    override fun onKeyUp(keyCode: Int, event: KeyEvent?): Boolean {
        return when (keyCode) {
            KeyEvent.KEYCODE_ENTER -> {
                presenter.saveItem(
                    Item(
                        editText.text.toString(),
                        Util.getCurrentTime(),
                        categoryId
                    )
                )
                true
            }
            else -> super.onKeyUp(keyCode, event)
        }
    }

    override fun displayItems(list: List<Item>) {
        editText.setText("")
        adapter.setItems(list)
        adapter.notifyDataSetChanged()
    }

    override fun displayBackground(byteArray: ByteArray?) {
        val inputStream = ByteArrayInputStream(byteArray)
        bgImageView.background =
            BitmapDrawable(resources, (BitmapFactory.decodeStream(inputStream)))
    }

    override fun onResume() {
        super.onResume()
        presenter.loadItems(categoryId)
    }

    private fun injectDependency() {
        (application as MyApplication).getAppComponent(this)?.inject(this)
    }

    private fun generateColor(): Int {
        val rnd = Random()

        val alpha = 230
        val hue = rnd.nextInt(360).toFloat()
        val sat = 0.5f
        val value = 0.5f

        return Color.HSVToColor(alpha, floatArrayOf(hue, sat, value))
    }
}