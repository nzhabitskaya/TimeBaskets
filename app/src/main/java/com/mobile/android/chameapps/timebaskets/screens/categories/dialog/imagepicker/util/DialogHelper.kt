package com.mobile.android.chameapps.timebaskets.screens.categories.dialog.imagepicker.util

import android.content.Context
import android.view.LayoutInflater
import androidx.appcompat.app.AlertDialog
import com.mobile.android.chameapps.timebaskets.R
import com.mobile.android.chameapps.timebaskets.screens.categories.dialog.imagepicker.constant.ImageProvider
import com.mobile.android.chameapps.timebaskets.screens.categories.dialog.imagepicker.listener.ResultListener
import kotlinx.android.synthetic.main.dialog_choose_app.view.*

internal object DialogHelper {

    /**
     * Show Image Provide Picker Dialog. This will streamline the code to pick/capture image
     *
     */
    fun showChooseAppDialog(context: Context, listener: ResultListener<ImageProvider>) {
        val layoutInflater = LayoutInflater.from(context)
        val customView = layoutInflater.inflate(R.layout.dialog_choose_app, null)

        val dialog = AlertDialog.Builder(context)
            .setTitle("title_choose_image_provider")
            .setView(customView)
            .setOnCancelListener {
                listener.onResult(null)
            }
            .show()

        // Handle Camera option click
        customView.lytCameraPick.setOnClickListener {
            listener.onResult(ImageProvider.CAMERA)
            dialog.dismiss()
        }

        // Handle Gallery option click
        customView.lytGalleryPick.setOnClickListener {
            listener.onResult(ImageProvider.GALLERY)
            dialog.dismiss()
        }
    }
}
