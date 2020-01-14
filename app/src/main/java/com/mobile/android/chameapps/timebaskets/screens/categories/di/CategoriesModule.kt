package com.mobile.android.chameapps.timebaskets.screens.categories.di

import com.mobile.android.chameapps.timebaskets.room.dao.CategoriesDao
import com.mobile.android.chameapps.timebaskets.screens.categories.CategoriesContract
import com.mobile.android.chameapps.timebaskets.screens.categories.impl.CategoriesModel
import com.mobile.android.chameapps.timebaskets.screens.categories.impl.CategoriesPresenter
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class CategoriesModule {

    @Provides
    fun providePresenter(model: CategoriesContract.Model): CategoriesContract.Presenter {
        return CategoriesPresenter(model)
    }

    @Provides
    @Singleton
    fun provideModel(categoriesDao: CategoriesDao): CategoriesContract.Model {
        return CategoriesModel(categoriesDao)
    }
}