package com.mobile.android.chameapps.timebaskets.ui.main.di

import com.mobile.android.chameapps.timebaskets.ui.main.MainActivityContract
import com.mobile.android.chameapps.timebaskets.ui.main.impl.MainModel
import com.mobile.android.chameapps.timebaskets.ui.main.impl.MainPresenter
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class MainModule {

    @Provides
    @Singleton
    fun provideModel(): MainModel {
        return MainModel()
    }

    @Provides
    fun providePresenter(model: MainModel): MainActivityContract.Presenter {
        return MainPresenter(model)
    }
}