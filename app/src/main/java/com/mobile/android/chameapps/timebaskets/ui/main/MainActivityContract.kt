package com.mobile.android.chameapps.timebaskets.ui.main

import com.mobile.android.chameapps.timebaskets.mvp.BaseContract

class MainActivityContract {

    interface View: BaseContract.View

    interface Presenter: BaseContract.Presenter<View>
}