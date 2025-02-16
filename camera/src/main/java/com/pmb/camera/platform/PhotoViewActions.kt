package com.pmb.camera.platform

import androidx.activity.compose.ManagedActivityResultLauncher
import androidx.camera.view.PreviewView
import androidx.lifecycle.LifecycleOwner
import com.pmb.core.platform.BaseViewAction

interface PhotoViewActions : BaseViewAction {
    data class RequestCameraPermission(
        val managedActivityResultLauncher: ManagedActivityResultLauncher<String, Boolean>
    ) : PhotoViewActions

    data class RequestFilePermission(val managedActivityResultLauncher: ManagedActivityResultLauncher<Array<String>, Map<String, Boolean>>) :
        PhotoViewActions

    data object CapturePhoto : PhotoViewActions
    data object ToggleCamera : PhotoViewActions
    data class PreviewCamera(
        val previewView: PreviewView,
        val lifecycleOwner: LifecycleOwner
    ) : PhotoViewActions
}