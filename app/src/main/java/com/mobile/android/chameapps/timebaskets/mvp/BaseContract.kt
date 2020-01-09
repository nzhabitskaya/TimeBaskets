package com.mobile.android.chameapps.timebaskets.mvp

class BaseContract {

    interface View

    interface Presenter<in T> {
        fun attach(view: T)
        fun unsubscribe()
    }

    interface Model {
        fun unsubscribe()
    }
}