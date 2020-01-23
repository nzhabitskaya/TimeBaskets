package com.mobile.android.chameapps.timebaskets.screens.todolist.di

import com.mobile.android.chameapps.timebaskets.room.dao.CategoriesDao
import com.mobile.android.chameapps.timebaskets.room.dao.ItemsDao
import com.mobile.android.chameapps.timebaskets.screens.todolist.TodoListContract
import com.mobile.android.chameapps.timebaskets.screens.todolist.impl.TodoListModel
import com.mobile.android.chameapps.timebaskets.screens.todolist.impl.TodoListPresenter
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class TodoListModule {

    @Provides
    fun providePresenter(model: TodoListContract.Model): TodoListContract.Presenter {
        return TodoListPresenter(model)
    }

    @Provides
    @Singleton
    fun provideModel(itemsDao: ItemsDao): TodoListContract.Model {
        return TodoListModel(itemsDao)
    }
}