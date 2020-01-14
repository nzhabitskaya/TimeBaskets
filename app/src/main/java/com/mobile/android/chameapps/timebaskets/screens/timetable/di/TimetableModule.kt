package com.mobile.android.chameapps.timebaskets.screens.timetable.di

import com.mobile.android.chameapps.timebaskets.room.dao.RulesDao
import com.mobile.android.chameapps.timebaskets.screens.timetable.TimetableContract
import com.mobile.android.chameapps.timebaskets.screens.timetable.impl.TimetableModel
import com.mobile.android.chameapps.timebaskets.screens.timetable.impl.TimetablePresenter
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class TimetableModule {

    @Provides
    fun providePresenter(model: TimetableContract.Model): TimetableContract.Presenter {
        return TimetablePresenter(model)
    }

    @Provides
    @Singleton
    fun provideModel(rulesDao: RulesDao): TimetableContract.Model {
        return TimetableModel(rulesDao)
    }
}