package com.mobile.android.chameapps.timebaskets.screens.categories.impl

import com.mobile.android.chameapps.timebaskets.screens.categories.CategoriesContract
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

class CategoriesPresenter(private val model: CategoriesContract.Model) :
    CategoriesContract.Presenter {

    private lateinit var view: CategoriesContract.View

    private var subscription: Disposable? = null

    override fun unsubscribe() {
        subscription?.dispose()
        model.unsubscribe()
    }

    override fun attach(view: CategoriesContract.View) {
        this.view = view
    }

    override fun saveItem(title: String, description: String) {
        model.saveItem(title, description)
        loadItems()
    }

    override fun loadItems() {
        subscription = model.loadItems()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe {
                view.displayItems(it)
            }
    }
}
