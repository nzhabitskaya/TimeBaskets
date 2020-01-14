package com.mobile.android.chameapps.timebaskets.screens.categories

import com.mobile.android.chameapps.timebaskets.mvp.BaseContract
import com.mobile.android.chameapps.timebaskets.room.enitities.Item
import io.reactivex.Observable

class CategoriesContract {

    interface View : BaseContract.View {

        fun displayItems(list: List<Item>)

        fun displayCard()
    }

    interface Presenter : BaseContract.Presenter<View> {

        fun saveItem(title: String, description: String)

        fun loadItems()
    }

    interface Model : BaseContract.Model {

        fun saveItem(title: String, description: String)

        fun loadItems(): Observable<List<Item>>
    }
}