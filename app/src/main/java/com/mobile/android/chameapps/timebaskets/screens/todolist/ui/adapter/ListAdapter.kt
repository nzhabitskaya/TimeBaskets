package com.mobile.android.chameapps.timebaskets.screens.todolist.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.mobile.android.chameapps.timebaskets.R
import com.mobile.android.chameapps.timebaskets.room.enitities.Item
import com.mobile.android.chameapps.timebaskets.screens.todolist.ui.adapter.ListAdapter.ListItemViewHolder

class ListAdapter : RecyclerView.Adapter<ListItemViewHolder>() {
    private var list: List<Item>? = ArrayList()

    fun setItems(list: List<Item>?) {
        this.list = list
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListItemViewHolder {
        val itemView =
            LayoutInflater.from(parent.context).inflate(R.layout.list_row, parent, false)
        return ListItemViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ListItemViewHolder, position: Int) {
        holder.itemName.text = list!![position].title
    }

    override fun getItemCount(): Int {
        return list!!.size
    }

    class ListItemViewHolder(itemView: View) :
        RecyclerView.ViewHolder(itemView) {
        var itemName: TextView

        init {
            itemName =
                itemView.findViewById<View>(R.id.text) as TextView
        }
    }
}