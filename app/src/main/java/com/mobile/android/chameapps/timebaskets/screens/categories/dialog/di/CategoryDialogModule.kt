package com.mobile.android.chameapps.timebaskets.screens.categories.dialog.di

import com.mobile.android.chameapps.timebaskets.room.dao.CategoriesDao
import com.mobile.android.chameapps.timebaskets.screens.categories.dialog.CategoryDialogContract
import com.mobile.android.chameapps.timebaskets.screens.categories.dialog.impl.CategoryDialogModel
import com.mobile.android.chameapps.timebaskets.screens.categories.dialog.impl.CategoryDialogPresenter
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class CategoryDialogModule {

    @Provides
    fun providePresenter(model: CategoryDialogContract.Model): CategoryDialogContract.Presenter {
        return CategoryDialogPresenter(model)
    }

    @Provides
    @Singleton
    fun provideModel(categoriesDao: CategoriesDao): CategoryDialogContract.Model {
        return CategoryDialogModel(categoriesDao)
    }
}