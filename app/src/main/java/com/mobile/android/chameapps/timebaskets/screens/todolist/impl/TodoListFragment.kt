package com.mobile.android.chameapps.timebaskets.screens.todolist.impl

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.fragment.app.Fragment
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
import javax.inject.Singleton


/**
 * Created by Natallia Zhabitskaya on 10/26/2019.
 */

class TodoListFragment : Fragment(), TodoListContract.View {

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
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_todolist, container, false)
        recyclerView = view.findViewById(R.id.recycler_view)
        emptyView = view.findViewById(R.id.empty_view)
        editText = view.findViewById(R.id.edit_text)
        editText.setOnFocusChangeListener { view, hasFocus -> onFocusChanged(view, hasFocus) }
        emptyView.setOnFocusChangeListener { view, hasFocus -> onFocusChanged(view, hasFocus) }
        initRecyclerView()
        return view
    }

    fun initRecyclerView() {
        adapter = ListAdapter()
        recyclerView.setAdapter(adapter)
        //recyclerView.addItemDecoration(DividerItemDecoration(context, DividerItemDecoration.VERTICAL))

        recyclerView.setItemAnimator(DefaultItemAnimator())
        recyclerView.setHasFixedSize(true)
        recyclerView.setLayoutManager(LinearLayoutManager(context))
    }

    private fun onFocusChanged(view: View, hasFocus: Boolean) {
        if (!hasFocus) {
            presenter.saveItem(Item(editText.text.toString(), Util.getCurrentTime(), 0))
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
        (activity?.application as MyApplication).getAppComponent(context!!)?.inject(this)
    }

    companion object {
        @Singleton
        fun newInstance(): TodoListFragment {
            return TodoListFragment()
        }
    }
}