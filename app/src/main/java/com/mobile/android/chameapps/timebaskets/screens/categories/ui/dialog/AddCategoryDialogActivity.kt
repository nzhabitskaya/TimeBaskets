package com.mobile.android.chameapps.timebaskets.screens.categories.ui.dialog

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.mobile.android.chameapps.timebaskets.R
import com.mobile.android.chameapps.timebaskets.screens.categories.ui.imagepicker.ImagePicker
import com.mobile.android.chameapps.timebaskets.screens.categories.ui.imagepicker.setLocalImage
import kotlinx.android.synthetic.main.activity_dialog_add_category.*

class AddCategoryDialogActivity : AppCompatActivity() {

    companion object {
        private const val GALLERY_IMAGE_REQ_CODE = 101
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dialog_add_category)
        findViewById<View>(R.id.bt_create_account).setOnClickListener {
            Toast.makeText(
                applicationContext, "Create Account", Toast.LENGTH_SHORT
            ).show()
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

}