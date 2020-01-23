package com.mobile.android.chameapps.timebaskets.screens.todolist.impl

import com.mobile.android.chameapps.timebaskets.room.dao.CategoriesDao
import com.mobile.android.chameapps.timebaskets.room.dao.ItemsDao
import com.mobile.android.chameapps.timebaskets.room.enitities.Category
import com.mobile.android.chameapps.timebaskets.room.enitities.Item
import com.mobile.android.chameapps.timebaskets.screens.todolist.TodoListContract
import io.reactivex.Observable
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

/**
 * Created by Natallia Zhabitskaya on 12/26/2019.
 */

class TodoListModel(private val itemsDao: ItemsDao) : TodoListContract.Model {

    private var subscriptions: CompositeDisposable? = null

    override fun unsubscribe() {
        subscriptions?.dispose()
    }

    override fun loadItems(): Observable<List<Item>> {
        return itemsDao.findAll()
    }

    override fun saveItem(item: Item) {
        subscriptions?.add(Observable.just(itemsDao)
            .subscribeOn(Schedulers.io())
            .subscribe { db -> db.insert(item) }
        )
    }
}
