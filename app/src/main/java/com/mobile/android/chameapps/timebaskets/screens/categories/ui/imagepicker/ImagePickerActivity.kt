package com.mobile.android.chameapps.timebaskets.screens.categories.ui.imagepicker

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.mobile.android.chameapps.timebaskets.screens.categories.ui.imagepicker.constant.ImageProvider
import com.mobile.android.chameapps.timebaskets.screens.categories.ui.imagepicker.provider.GalleryProvider
import java.io.File

class ImagePickerActivity : AppCompatActivity() {

    companion object {
        private const val TAG = "image_picker"

        /**
         * Key to Save/Retrieve Image File state
         */
        private const val STATE_IMAGE_FILE = "state.image_file"

        internal fun getCancelledIntent(context: Context): Intent {
            val intent = Intent()
            val message = "error_task_cancelled"
            intent.putExtra(ImagePicker.EXTRA_ERROR, message)
            return intent
        }
    }

    private var mGalleryProvider: GalleryProvider? = null

    /** File provided by GalleryProvider or CameraProvider */
    private var mImageFile: File? = null

    /** File provided by CropProvider */
    private var mCropFile: File? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        restoreInstanceState(savedInstanceState)
        loadBundle(savedInstanceState)
    }

    /**
     * Restore saved state
     */
    private fun restoreInstanceState(savedInstanceState: Bundle?) {
        if (savedInstanceState != null) {
            mImageFile = savedInstanceState.getSerializable(STATE_IMAGE_FILE) as File?
        }
    }

    /**
     * Save all appropriate activity state.
     */
    public override fun onSaveInstanceState(outState: Bundle) {
        outState.putSerializable(STATE_IMAGE_FILE, mImageFile)
        super.onSaveInstanceState(outState)
    }

    /**
     * Parse Intent Bundle and initialize variables
     */
    private fun loadBundle(savedInstanceState: Bundle?) {

        // Retrieve Image Provider
        val provider: ImageProvider? =
            intent?.getSerializableExtra(ImagePicker.EXTRA_IMAGE_PROVIDER) as ImageProvider?

        // Create Gallery/Camera Provider
        when (provider) {
            ImageProvider.GALLERY -> {
                mGalleryProvider =
                    GalleryProvider(
                        this
                    )
                // Pick Gallery Image
                savedInstanceState ?: mGalleryProvider?.startIntent()
            }
            else -> {
                // Something went Wrong! This case should never happen
                Log.e(TAG, "Image provider can not be null")
                setError("string.error_task_cancelled")
            }
        }
    }

    /**
     * Dispatch incoming result to the correct provider.
     */
    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        mGalleryProvider?.onRequestPermissionsResult(requestCode)
    }

    /**
     * Dispatch incoming result to the correct provider.
     */
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        mGalleryProvider?.onActivityResult(requestCode, resultCode, data)
    }

    /**
     * Handle Activity Back Press
     */
    override fun onBackPressed() {
        setResultCancel()
    }

    /**
     * {@link CameraProvider} and {@link GalleryProvider} Result will be available here.
     *
     * @param file Capture/Gallery image file
     */
    fun setImage(file: File) {
        mImageFile = file
        setResult(file)
    }

    /**
     * Set Result, Image is successfully capture/picked/cropped/compressed.
     *
     * @param file final image file
     */
    private fun setResult(file: File) {
        val intent = Intent()
        intent.data = Uri.fromFile(file)
        intent.putExtra(ImagePicker.EXTRA_FILE_PATH, file.absolutePath)
        setResult(Activity.RESULT_OK, intent)
        finish()
    }

    /**
     * User has cancelled the task
     */
    fun setResultCancel() {
        setResult(Activity.RESULT_CANCELED,
            getCancelledIntent(
                this
            )
        )
        finish()
    }

    /**
     * Error occurred while processing image
     *
     * @param message Error Message
     */
    fun setError(message: String) {
        val intent = Intent()
        intent.putExtra(ImagePicker.EXTRA_ERROR, message)
        setResult(ImagePicker.RESULT_ERROR, intent)
        finish()
    }
}
