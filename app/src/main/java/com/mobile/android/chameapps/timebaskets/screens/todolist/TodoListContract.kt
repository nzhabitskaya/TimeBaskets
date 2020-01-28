package com.mobile.android.chameapps.timebaskets.screens.todolist

import com.mobile.android.chameapps.timebaskets.mvp.BaseContract
import com.mobile.android.chameapps.timebaskets.room.enitities.Item
import io.reactivex.Observable

class TodoListContract {

    interface View : BaseContract.View {

        fun displayItems(list: List<Item>)

        fun displayBackground(byteArray: ByteArray?)
    }

    interface Presenter : BaseContract.Presenter<View> {

        fun loadItems(categoryId: Long)

        fun loadBackground(categoryId: Long)

        fun saveItem(item: Item)
    }

    interface Model : BaseContract.Model {

        fun loadItems(categoryId: Long): Observable<List<Item>>

        fun loadBackground(categoryId: Long): Observable<ByteArray?>

        fun saveItem(item: Item)
    }
}