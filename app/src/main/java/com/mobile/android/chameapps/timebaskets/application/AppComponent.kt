package com.mobile.android.chameapps.timebaskets.application

import com.mobile.android.chameapps.timebaskets.room.di.RoomModule
import com.mobile.android.chameapps.timebaskets.screens.categories.CategoriesContract
import com.mobile.android.chameapps.timebaskets.screens.categories.di.CategoriesModule
import com.mobile.android.chameapps.timebaskets.screens.categories.impl.CategoriesFragment
import com.mobile.android.chameapps.timebaskets.screens.dialog.CategoryDialogContract
import com.mobile.android.chameapps.timebaskets.screens.dialog.di.CategoryDialogModule
import com.mobile.android.chameapps.timebaskets.screens.dialog.impl.CategoryDialogActivity
import dagger.Component
import javax.inject.Singleton

/**
 * Created by n.zhabitskaya on 9/28/18.
 */

@Singleton
@Component(modules = [ApplicationModule::class, CategoriesModule::class, CategoryDialogModule::class, RoomModule::class])
interface AppComponent {

    val application: MyApplication?

    val categoriesPresenter: CategoriesContract.Presenter

    val categoryDialogPresenter: CategoryDialogContract.Presenter

    fun inject(fragment: CategoriesFragment?)

    fun inject(activity: CategoryDialogActivity?)
}