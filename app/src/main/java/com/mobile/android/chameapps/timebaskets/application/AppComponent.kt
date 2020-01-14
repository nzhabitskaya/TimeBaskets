package com.mobile.android.chameapps.timebaskets.application

import com.mobile.android.chameapps.timebaskets.room.di.RoomModule
import com.mobile.android.chameapps.timebaskets.screens.categories.CategoriesContract
import com.mobile.android.chameapps.timebaskets.screens.categories.di.CategoriesModule
import com.mobile.android.chameapps.timebaskets.screens.categories.impl.CategoriesFragment
import com.mobile.android.chameapps.timebaskets.screens.timetable.TimetableContract
import com.mobile.android.chameapps.timebaskets.screens.timetable.di.TimetableModule
import com.mobile.android.chameapps.timebaskets.screens.timetable.impl.TimetableFragment
import dagger.Component
import javax.inject.Singleton

/**
 * Created by n.zhabitskaya on 9/28/18.
 */

@Singleton
@Component(modules = [ApplicationModule::class, TimetableModule::class, CategoriesModule::class, RoomModule::class])
interface AppComponent {

    val application: MyApplication?

    val timetablePresenter: TimetableContract.Presenter

    val categoriesPresenter: CategoriesContract.Presenter

    fun inject(activity: TimetableFragment?)

    fun inject(activity: CategoriesFragment?)
}