package com.mobile.android.chameapps.timebaskets.screens.todolist.impl

import com.mobile.android.chameapps.timebaskets.room.dao.CategoriesDao
import com.mobile.android.chameapps.timebaskets.room.dao.ItemsDao
import com.mobile.android.chameapps.timebaskets.room.enitities.Item
import com.mobile.android.chameapps.timebaskets.screens.todolist.TodoListContract
import io.reactivex.Observable
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

/**
 * Created by Natallia Zhabitskaya on 12/26/2019.
 */

class TodoListModel(private val itemsDao: ItemsDao, private val categoriesDao: CategoriesDao) : TodoListContract.Model {

    private var subscriptions: CompositeDisposable? = CompositeDisposable()

    override fun unsubscribe() {
        subscriptions?.dispose()
    }

    override fun loadItems(categoryId: Long): Observable<List<Item>> {
        return itemsDao.findByCategoryId(categoryId)
    }

    override fun loadBackground(categoryId: Long): Observable<ByteArray?> {
        return categoriesDao.findById(categoryId.toString()).map { it.byteArray }
    }

    override fun saveItem(item: Item) {
        subscriptions?.add(Observable.just(itemsDao)
            .subscribeOn(Schedulers.io())
            .subscribe { db ->
                db.insert(item)
            }
        )
    }
}
