package com.mobile.android.chameapps.timebaskets.screens.dialog

import android.graphics.drawable.Drawable
import com.mobile.android.chameapps.timebaskets.mvp.BaseContract
import com.mobile.android.chameapps.timebaskets.room.enitities.Category
import io.reactivex.subjects.PublishSubject

class CategoryDialogContract {

    interface View : BaseContract.View {

        val clickSave: PublishSubject<Pair<String, Drawable>>
    }

    interface Presenter : BaseContract.Presenter<View> {

    }

    interface Model : BaseContract.Model {

        fun saveItem(category: Category)
    }
}