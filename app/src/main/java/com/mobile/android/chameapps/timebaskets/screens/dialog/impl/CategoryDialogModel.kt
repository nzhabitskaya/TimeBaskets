package com.mobile.android.chameapps.timebaskets.screens.dialog.impl

import com.mobile.android.chameapps.timebaskets.room.dao.CategoriesDao
import com.mobile.android.chameapps.timebaskets.room.enitities.Item
import com.mobile.android.chameapps.timebaskets.room.dao.RulesDao
import com.mobile.android.chameapps.timebaskets.room.enitities.Category
import com.mobile.android.chameapps.timebaskets.tools.Util
import com.mobile.android.chameapps.timebaskets.screens.categories.CategoriesContract
import com.mobile.android.chameapps.timebaskets.screens.dialog.CategoryDialogContract
import io.reactivex.Observable
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

/**
 * Created by Natallia Zhabitskaya on 12/26/2019.
 */

class CategoryDialogModel(private val categoriesDao: CategoriesDao) : CategoryDialogContract.Model {

    private var subscriptions: CompositeDisposable? = null

    override fun unsubscribe() {
        subscriptions?.dispose()
    }

    override fun saveItem(category: Category) {
        subscriptions?.add(
            Observable.just(categoriesDao)
                .subscribeOn(Schedulers.io())
                .subscribe { db -> db.insert(category) }
        )
    }
}
