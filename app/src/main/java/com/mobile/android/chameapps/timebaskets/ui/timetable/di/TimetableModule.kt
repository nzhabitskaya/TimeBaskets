package com.mobile.android.chameapps.timebaskets.ui.timetable.di

import com.mobile.android.chameapps.timebaskets.room.RulesDao
import com.mobile.android.chameapps.timebaskets.ui.timetable.RulesContract
import com.mobile.android.chameapps.timebaskets.ui.timetable.impl.TimetableModel
import com.mobile.android.chameapps.timebaskets.ui.timetable.impl.TimetablePresenter
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class TimetableModule {

    @Provides
    fun providePresenter(model: RulesContract.Model): RulesContract.Presenter {
        return TimetablePresenter(model)
    }

    @Provides
    @Singleton
    fun provideModel(rulesDao: RulesDao): RulesContract.Model {
        return TimetableModel(rulesDao)
    }
}