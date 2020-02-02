package com.mobile.android.chameapps.timebaskets.screens.adddialog.impl

import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import com.mobile.android.chameapps.timebaskets.room.enitities.Category
import com.mobile.android.chameapps.timebaskets.screens.adddialog.CategoryDialogContract
import com.mobile.android.chameapps.timebaskets.tools.Util
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import java.io.ByteArrayOutputStream


class CategoryDialogPresenter(private val model: CategoryDialogContract.Model) :
    CategoryDialogContract.Presenter {

    private lateinit var view: CategoryDialogContract.View

    private var subscription: Disposable? = null

    override fun unsubscribe() {
        subscription?.dispose()
        model.unsubscribe()
    }

    override fun attach(view: CategoryDialogContract.View) {
        this.view = view

        subscription = view.clickSave
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe {(title, image) ->
                if(image != null) {
                    val bitmap = (image as BitmapDrawable).bitmap
                    val stream = ByteArrayOutputStream()
                    bitmap.compress(Bitmap.CompressFormat.JPEG, 100, stream)
                    val bitmapdata: ByteArray = stream.toByteArray()

                    model.saveItem(Category(title, Util.getCurrentTime(), bitmapdata))
                }
                view.closeDialog()
            }
    }
}
