package com.mobile.android.chameapps.timebaskets.screens.categories.impl

import com.mobile.android.chameapps.timebaskets.room.enitities.Item
import com.mobile.android.chameapps.timebaskets.room.dao.RulesDao
import com.mobile.android.chameapps.timebaskets.tools.Util
import com.mobile.android.chameapps.timebaskets.screens.categories.CategoriesContract
import io.reactivex.Observable
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

/**
 * Created by Natallia Zhabitskaya on 12/26/2019.
 */

class CategoriesModel(private val rulesDao: RulesDao) : CategoriesContract.Model {

    private var subscriptions: CompositeDisposable? = null

    override fun unsubscribe() {
        subscriptions?.dispose()
    }

    override fun loadItems(): Observable<List<Item>> {
        return rulesDao.findAll()
    }

    override fun saveItem(title: String, description: String) {
        val time = Util.getCurrentTime()
        val item = Item(
            title,
            description,
            time,
            0
        )

        subscriptions?.add(
            Observable.just(rulesDao)
                .subscribeOn(Schedulers.io())
                .subscribe { db -> db.insert(item) }
        )
    }
}
