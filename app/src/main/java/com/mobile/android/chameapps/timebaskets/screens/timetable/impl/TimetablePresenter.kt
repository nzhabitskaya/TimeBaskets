package com.mobile.android.chameapps.timebaskets.screens.timetable.impl

import com.mobile.android.chameapps.timebaskets.screens.timetable.TimetableContract
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

class TimetablePresenter(private val model: TimetableContract.Model) : TimetableContract.Presenter {

    private lateinit var view: TimetableContract.View

    private var subscription: Disposable? = null

    override fun unsubscribe() {
        subscription?.dispose()
        model.unsubscribe()
    }

    override fun attach(view: TimetableContract.View) {
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
