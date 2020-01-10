package com.mobile.android.chameapps.timebaskets.application

import com.mobile.android.chameapps.timebaskets.room.di.RoomModule
import com.mobile.android.chameapps.timebaskets.ui.categories.CategoriesContract
import com.mobile.android.chameapps.timebaskets.ui.categories.di.CategoriesModule
import com.mobile.android.chameapps.timebaskets.ui.categories.impl.CategoriesFragment
import com.mobile.android.chameapps.timebaskets.ui.timetable.TimetableContract
import com.mobile.android.chameapps.timebaskets.ui.timetable.di.TimetableModule
import com.mobile.android.chameapps.timebaskets.ui.timetable.impl.TimetableFragment
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