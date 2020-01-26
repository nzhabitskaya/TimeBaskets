package com.mobile.android.chameapps.timebaskets.screens.todolist.impl

import android.os.Bundle
import android.util.Log
import android.view.KeyEvent
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.mobile.android.chameapps.timebaskets.R
import com.mobile.android.chameapps.timebaskets.application.MyApplication
import com.mobile.android.chameapps.timebaskets.room.enitities.Item
import com.mobile.android.chameapps.timebaskets.screens.todolist.TodoListContract
import com.mobile.android.chameapps.timebaskets.screens.todolist.ui.adapter.ListAdapter
import com.mobile.android.chameapps.timebaskets.tools.Util
import javax.inject.Inject


/**
 * Created by Natallia Zhabitskaya on 10/26/2019.
 */

class TodoListActivity : AppCompatActivity(), TodoListContract.View {

    @Inject
    lateinit var presenter: TodoListContract.Presenter

    private lateinit var recyclerView: RecyclerView

    private lateinit var editText: EditText

    private lateinit var emptyView: EditText

    private lateinit var adapter: ListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        injectDependency()
        presenter.attach(this)
        setContentView(R.layout.fragment_todolist)
        recyclerView = findViewById(R.id.recycler_view)
        editText = findViewById(R.id.edit_text)
        initRecyclerView()
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
                presenter.saveItem(Item(editText.text.toString(), Util.getCurrentTime(), 0))
                true
            }
            else -> super.onKeyUp(keyCode, event)
        }
    }

    override fun displayItems(list: List<Item>) {
        adapter.setItems(list)
        adapter.notifyDataSetChanged()
    }

    override fun onResume() {
        super.onResume()
        presenter.loadItems()
    }

    private fun injectDependency() {
        (application as MyApplication).getAppComponent(this)?.inject(this)
    }
}