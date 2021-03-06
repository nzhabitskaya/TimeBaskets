package com.mobile.android.chameapps.timebaskets.screens.categories

import com.mobile.android.chameapps.timebaskets.mvp.BaseContract
import com.mobile.android.chameapps.timebaskets.room.enitities.Category
import io.reactivex.Observable

class CategoriesContract {

    interface View : BaseContract.View {

        fun displayItems(list: List<Category>)
    }

    interface Presenter : BaseContract.Presenter<View> {

        fun loadItems()
    }

    interface Model : BaseContract.Model {

        fun loadItems(): Observable<List<Category>>
    }
}