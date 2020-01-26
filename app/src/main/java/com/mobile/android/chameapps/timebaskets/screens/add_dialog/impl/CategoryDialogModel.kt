package com.mobile.android.chameapps.timebaskets.screens.add_dialog.impl

import com.mobile.android.chameapps.timebaskets.room.dao.CategoriesDao
import com.mobile.android.chameapps.timebaskets.room.enitities.Category
import com.mobile.android.chameapps.timebaskets.screens.add_dialog.CategoryDialogContract
import io.reactivex.Observable
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

/**
 * Created by Natallia Zhabitskaya on 12/26/2019.
 */

class CategoryDialogModel(private val categoriesDao: CategoriesDao) : CategoryDialogContract.Model {

    private var subscription: Disposable? = null

    override fun unsubscribe() {
        subscription?.dispose()
    }

    override fun saveItem(category: Category) {
        subscription = Observable.just(categoriesDao)
                .subscribeOn(Schedulers.io())
                .subscribe { db -> db.insert(category) }
    }
}
