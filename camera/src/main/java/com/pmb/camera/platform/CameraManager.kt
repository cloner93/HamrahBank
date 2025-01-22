package com.pmb.camera.platform

import android.content.Context
import androidx.camera.core.CameraSelector
import androidx.camera.core.ImageCapture
import androidx.camera.core.ImageCaptureException
import androidx.camera.core.Preview
import androidx.camera.lifecycle.ProcessCameraProvider
import androidx.camera.view.PreviewView
import androidx.core.content.ContextCompat
import androidx.lifecycle.LifecycleOwner
import com.pmb.core.fileManager.FileManager
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CameraManager @Inject constructor(
    @ApplicationContext private val context: Context,
    private val fileManager: FileManager,
) {
    private var imageCapture: ImageCapture? = null
    private var cameraSelector: CameraSelector = CameraSelector.DEFAULT_BACK_CAMERA
    fun startCamera(
        previewView: PreviewView,
        lifecycleOwner: LifecycleOwner,
        onSuccess: () -> Unit,
        onError: (String) -> Unit
    ) {
        val cameraProviderFuture = ProcessCameraProvider.getInstance(context)
        cameraProviderFuture.addListener({
            try {
                val cameraProvider: ProcessCameraProvider = cameraProviderFuture.get()
                val preview = Preview.Builder().build().also {
                    it.surfaceProvider = previewView.surfaceProvider
                }
                imageCapture = ImageCapture.Builder().build()
                cameraProvider.unbindAll()
                cameraProvider.bindToLifecycle(
                    lifecycleOwner,
                    cameraSelector,
                    preview,
                    imageCapture
                )
                onSuccess()
            } catch (e: Exception) {
                onError("Failed to start camera: ${e.message}")
            }
        }, ContextCompat.getMainExecutor(context))
    }

    fun takePhoto(
        onPhotoCaptured: (String) -> Unit,
        onError: (String) -> Unit
    ) {
        val imageCapture = imageCapture ?: run {
            onError("ImageCapture is not initialized")
            return
        }
        val photoFile = fileManager.createImageFile()
        val outputOptions = ImageCapture.OutputFileOptions.Builder(photoFile).build()
        imageCapture.takePicture(
            outputOptions,
            ContextCompat.getMainExecutor(context),
            object : ImageCapture.OnImageSavedCallback {
                override fun onImageSaved(outputFileResults: ImageCapture.OutputFileResults) {
                    val savedUri = fileManager.getFileUri(photoFile)
                    onPhotoCaptured(savedUri.toString())
                }

                override fun onError(exception: ImageCaptureException) {
                    onError("Photo capture failed: ${exception.message}")
                }
            })
    }

    fun isFrontCamera(): Boolean = cameraSelector == CameraSelector.DEFAULT_FRONT_CAMERA
    fun toggleCamera() {
        cameraSelector = if (cameraSelector == CameraSelector.DEFAULT_BACK_CAMERA) {
            CameraSelector.DEFAULT_FRONT_CAMERA
        } else {
            CameraSelector.DEFAULT_BACK_CAMERA
        }
    }
}