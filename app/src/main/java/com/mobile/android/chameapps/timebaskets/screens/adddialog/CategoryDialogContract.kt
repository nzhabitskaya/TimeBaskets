package com.mobile.android.chameapps.timebaskets.screens.adddialog

import android.graphics.drawable.Drawable
import com.mobile.android.chameapps.timebaskets.mvp.BaseContract
import com.mobile.android.chameapps.timebaskets.room.enitities.Category
import io.reactivex.subjects.PublishSubject

class CategoryDialogContract {

    interface View : BaseContract.View {

        val clickSave: PublishSubject<Pair<String, Drawable?>>

        fun closeDialog()
    }

    interface Presenter : BaseContract.Presenter<View> {

    }

    interface Model : BaseContract.Model {

        fun saveItem(category: Category)
    }
}