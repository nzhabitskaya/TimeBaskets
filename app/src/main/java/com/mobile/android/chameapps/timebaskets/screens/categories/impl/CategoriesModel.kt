package com.mobile.android.chameapps.timebaskets.screens.categories.impl

import com.mobile.android.chameapps.timebaskets.room.dao.CategoriesDao
import com.mobile.android.chameapps.timebaskets.room.enitities.Category
import com.mobile.android.chameapps.timebaskets.screens.categories.CategoriesContract
import io.reactivex.Observable
import io.reactivex.disposables.CompositeDisposable

/**
 * Created by Natallia Zhabitskaya on 12/26/2019.
 */

class CategoriesModel(private val categoriesDao: CategoriesDao) : CategoriesContract.Model {

    private var subscriptions: CompositeDisposable? = CompositeDisposable()

    override fun unsubscribe() {
        subscriptions?.dispose()
    }

    override fun loadItems(): Observable<List<Category>> {
        return categoriesDao.findAll()
    }
}
