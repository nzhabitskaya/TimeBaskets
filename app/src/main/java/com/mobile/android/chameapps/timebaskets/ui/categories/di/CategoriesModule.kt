package com.mobile.android.chameapps.timebaskets.ui.categories.di

import com.mobile.android.chameapps.timebaskets.room.dao.RulesDao
import com.mobile.android.chameapps.timebaskets.ui.categories.CategoriesContract
import com.mobile.android.chameapps.timebaskets.ui.categories.impl.CategoriesModel
import com.mobile.android.chameapps.timebaskets.ui.categories.impl.CategoriesPresenter
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
    fun provideModel(categoriesDao: RulesDao): CategoriesContract.Model {
        return CategoriesModel(categoriesDao)
    }
}