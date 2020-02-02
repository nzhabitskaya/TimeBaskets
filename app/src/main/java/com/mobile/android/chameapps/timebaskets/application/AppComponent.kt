package com.mobile.android.chameapps.timebaskets.application

import com.mobile.android.chameapps.timebaskets.room.di.RoomModule
import com.mobile.android.chameapps.timebaskets.screens.categories.CategoriesContract
import com.mobile.android.chameapps.timebaskets.screens.categories.di.CategoriesModule
import com.mobile.android.chameapps.timebaskets.screens.categories.impl.CategoriesFragment
import com.mobile.android.chameapps.timebaskets.screens.adddialog.CategoryDialogContract
import com.mobile.android.chameapps.timebaskets.screens.adddialog.di.CategoryDialogModule
import com.mobile.android.chameapps.timebaskets.screens.adddialog.impl.CategoryDialogActivity
import com.mobile.android.chameapps.timebaskets.screens.todolist.TodoListContract
import com.mobile.android.chameapps.timebaskets.screens.todolist.di.TodoListModule
import com.mobile.android.chameapps.timebaskets.screens.todolist.impl.TodoListActivity
import dagger.Component
import javax.inject.Singleton

/**
 * Created by n.zhabitskaya on 9/28/18.
 */

@Singleton
@Component(modules = [ApplicationModule::class, CategoriesModule::class, CategoryDialogModule::class, RoomModule::class, TodoListModule::class])
interface AppComponent {

    val application: MyApplication?

    val categoriesPresenter: CategoriesContract.Presenter

    val categoryDialogPresenter: CategoryDialogContract.Presenter

    val todoListPresenter: TodoListContract.Presenter

    fun inject(fragment: CategoriesFragment?)

    fun inject(fragment: TodoListActivity?)

    fun inject(activity: CategoryDialogActivity?)
}