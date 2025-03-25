package com.pmb.camera.platform

import androidx.activity.compose.ManagedActivityResultLauncher
import androidx.camera.view.PreviewView
import androidx.lifecycle.LifecycleOwner
import com.pmb.core.platform.BaseViewAction

interface VideoViewActions : BaseViewAction {

    data class RequestCameraPermission(
        val managedActivityResultLauncher: ManagedActivityResultLauncher<String, Boolean>
    ) : VideoViewActions

    data class RequestFilePermission(val managedActivityResultLauncher: ManagedActivityResultLauncher<Array<String>, Map<String, Boolean>>) :
        VideoViewActions

    data object CapturingVideo : VideoViewActions
    data object VideoCaptured : VideoViewActions
    data object ToggleCamera : VideoViewActions
    data class PreviewCamera(
        val previewView: PreviewView,
        val lifecycleOwner: LifecycleOwner
    ) : VideoViewActions
}