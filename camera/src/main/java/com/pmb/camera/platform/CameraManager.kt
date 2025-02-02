package com.pmb.camera.platform

import androidx.camera.view.PreviewView
import androidx.lifecycle.LifecycleOwner
import java.io.File

interface CameraManager {
    fun startCamera(
        previewView: PreviewView,
        lifecycleOwner: LifecycleOwner,
        onSuccess: () -> Unit,
        onError: (String) -> Unit
    )
    fun takePhoto(
        outputFile: File,
        onPhotoCaptured: (Boolean) -> Unit,
        onError: (String) -> Unit
    )
    fun isFrontCamera(): Boolean
    fun toggleCamera()
}