package com.pmb.camera.platform

import androidx.camera.core.CameraSelector
import androidx.camera.view.PreviewView
import androidx.lifecycle.LifecycleOwner
import java.io.File

abstract class CameraManagerPrincipleImpl : CameraManager {

    protected var cameraSelector: CameraSelector = CameraSelector.DEFAULT_BACK_CAMERA
    abstract fun startCamera(
        previewView: PreviewView,
        lifecycleOwner: LifecycleOwner,
        onSuccess: () -> Unit,
        onError: (String) -> Unit
    )

    abstract fun startRecording(
        outputFile: File,
        onCaptured: (Boolean) -> Unit,
        onError: (String) -> Unit
    )

    override fun isFrontCamera(): Boolean = cameraSelector == CameraSelector.DEFAULT_FRONT_CAMERA
    override fun toggleCamera() {
        cameraSelector = if (cameraSelector == CameraSelector.DEFAULT_BACK_CAMERA) {
            CameraSelector.DEFAULT_FRONT_CAMERA
        } else {
            CameraSelector.DEFAULT_BACK_CAMERA
        }
    }
}