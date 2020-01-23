package com.mobile.android.chameapps.timebaskets.screens.todolist

import com.mobile.android.chameapps.timebaskets.mvp.BaseContract
import com.mobile.android.chameapps.timebaskets.room.enitities.Item
import io.reactivex.Observable

class TodoListContract {

    interface View : BaseContract.View {

        fun displayItems(list: List<Item>)
    }

    interface Presenter : BaseContract.Presenter<View> {

        fun loadItems()

        fun saveItem(item: Item)
    }

    interface Model : BaseContract.Model {

        fun loadItems(): Observable<List<Item>>

        fun saveItem(item: Item)
    }
}