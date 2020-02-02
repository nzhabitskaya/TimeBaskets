package com.mobile.android.chameapps.timebaskets.screens.adddialog.di

import com.mobile.android.chameapps.timebaskets.room.dao.CategoriesDao
import com.mobile.android.chameapps.timebaskets.screens.adddialog.CategoryDialogContract
import com.mobile.android.chameapps.timebaskets.screens.adddialog.impl.CategoryDialogModel
import com.mobile.android.chameapps.timebaskets.screens.adddialog.impl.CategoryDialogPresenter
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