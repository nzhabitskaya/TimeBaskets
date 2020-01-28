package com.mobile.android.chameapps.timebaskets.screens.todolist.impl

import com.mobile.android.chameapps.timebaskets.room.enitities.Item
import com.mobile.android.chameapps.timebaskets.screens.todolist.TodoListContract
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

class TodoListPresenter(private val model: TodoListContract.Model) :
    TodoListContract.Presenter {

    private lateinit var view: TodoListContract.View

    private var subscription: Disposable? = null

    override fun unsubscribe() {
        subscription?.dispose()
        model.unsubscribe()
    }

    override fun attach(view: TodoListContract.View) {
        this.view = view
    }

    override fun loadItems(categoryId: Long) {
        subscription = model.loadItems(categoryId)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe {
                view.displayItems(it)
            }
    }

    override fun loadBackground(categoryId: Long) {
        subscription = model.loadBackground(categoryId)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe {
                view.displayBackground(it)
            }
    }

    override fun saveItem(item: Item) {
        model.saveItem(item)
    }
}
