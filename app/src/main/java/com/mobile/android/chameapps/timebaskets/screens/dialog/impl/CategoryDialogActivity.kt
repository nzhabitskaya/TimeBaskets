package com.mobile.android.chameapps.timebaskets.screens.dialog.impl

import android.app.Activity
import android.content.Intent
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.mobile.android.chameapps.timebaskets.R
import com.mobile.android.chameapps.timebaskets.application.MyApplication
import com.mobile.android.chameapps.timebaskets.screens.dialog.CategoryDialogContract
import com.mobile.android.chameapps.timebaskets.screens.dialog.ui.imagepicker.ImagePicker
import com.mobile.android.chameapps.timebaskets.screens.dialog.ui.imagepicker.setLocalImage
import io.reactivex.subjects.PublishSubject
import kotlinx.android.synthetic.main.activity_dialog_add_category.*
import javax.inject.Inject

class CategoryDialogActivity : AppCompatActivity(), CategoryDialogContract.View {

    override val clickSave: PublishSubject<Pair<String, Drawable>> = PublishSubject.create()

    @Inject
    lateinit var presenter: CategoryDialogContract.Presenter

    companion object {
        private const val GALLERY_IMAGE_REQ_CODE = 101
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        injectDependency()
        presenter.attach(this)

        setContentView(R.layout.activity_dialog_add_category)
        findViewById<View>(R.id.bt_create_account).setOnClickListener {
            Toast.makeText(
                applicationContext, "Create Account", Toast.LENGTH_SHORT
            ).show()

            clickSave.onNext(Pair(input.text.toString(), bg.background))
        }
    }

    fun pickGalleryImage(view: View) {
        ImagePicker.with(this)
            .crop()
            .galleryOnly()
            .maxResultSize(1080, 1920)
            .start(GALLERY_IMAGE_REQ_CODE)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK) {
            Log.e("TAG", "Path:${ImagePicker.getFilePath(data)}")
            val file = ImagePicker.getFile(data)!!
            when (requestCode) {
                GALLERY_IMAGE_REQ_CODE -> {
                    bg.setLocalImage(file)
                }
            }
        } else if (resultCode == ImagePicker.RESULT_ERROR) {
            Toast.makeText(this, ImagePicker.getError(data), Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(this, "Task Cancelled", Toast.LENGTH_SHORT).show()
        }
    }

    private fun injectDependency() {
        (application as MyApplication).getAppComponent(this)?.inject(this)
    }
}