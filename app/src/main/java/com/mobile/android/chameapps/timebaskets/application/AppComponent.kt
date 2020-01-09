package com.mobile.android.chameapps.timebaskets.application

import com.mobile.android.chameapps.timebaskets.ui.timetable.RulesContract
import com.mobile.android.chameapps.timebaskets.ui.timetable.di.TimetableModule
import com.mobile.android.chameapps.timebaskets.ui.timetable.impl.TimetableFragment
import com.mobile.android.chameapps.timebaskets.ui.main.MainActivityContract
import com.mobile.android.chameapps.timebaskets.ui.main.di.MainModule
import com.mobile.android.chameapps.timebaskets.ui.main.impl.MainActivity
import com.mobile.android.chameapps.timebaskets.room.di.RoomModule
import dagger.Component
import javax.inject.Singleton

/**
 * Created by n.zhabitskaya on 9/28/18.
 */

@Singleton
@Component(modules = [ApplicationModule::class, MainModule::class, TimetableModule::class, RoomModule::class ])
interface AppComponent {

    val application: MyApplication?

    val mainPresenter: MainActivityContract.Presenter

    val rulesPresenter: RulesContract.Presenter

    fun inject(activity: MainActivity?)

    fun inject(activity: TimetableFragment?)
}