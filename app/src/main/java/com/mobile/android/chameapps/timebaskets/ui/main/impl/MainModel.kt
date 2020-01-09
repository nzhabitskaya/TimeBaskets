package com.mobile.android.chameapps.timebaskets.ui.main.impl

import io.reactivex.subjects.PublishSubject

class MainModel {

    val demoToggle = PublishSubject.create<Boolean>()

    val ccToggle = PublishSubject.create<Boolean>()

    val voiceToggle = PublishSubject.create<Boolean>()

    val trainingModeToggle = PublishSubject.create<Boolean>()

    val openProfile = PublishSubject.create<Unit>()

    val openDemo = PublishSubject.create<Unit>()
}