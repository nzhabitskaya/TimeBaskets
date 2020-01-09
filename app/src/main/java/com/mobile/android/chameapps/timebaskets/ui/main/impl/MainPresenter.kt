package com.mobile.android.chameapps.timebaskets.ui.main.impl

import com.mobile.android.chameapps.timebaskets.ui.main.MainActivityContract
import io.reactivex.disposables.CompositeDisposable

class MainPresenter(model: MainModel) : MainActivityContract.Presenter {

    private val subscriptions = CompositeDisposable()
    private lateinit var view: MainActivityContract.View
    private val model: MainModel

    init {
        this.model = model
    }

    override fun unsubscribe() {
        subscriptions.clear()
    }

    override fun attach(view: MainActivityContract.View) {
        this.view = view
    }
}