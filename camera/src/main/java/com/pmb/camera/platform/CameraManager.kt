package com.pmb.camera.platform

import androidx.camera.core.CameraSelector
import androidx.camera.view.PreviewView
import androidx.lifecycle.LifecycleOwner
import java.io.File

interface CameraManager {
    fun isFrontCamera(): Boolean
    fun toggleCamera()
    fun toggleCamera(cameraSelector: CameraSelector)
}