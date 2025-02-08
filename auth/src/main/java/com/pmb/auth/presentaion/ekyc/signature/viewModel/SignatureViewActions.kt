package com.pmb.auth.presentaion.ekyc.signature.viewModel

import androidx.activity.compose.ManagedActivityResultLauncher
import androidx.camera.view.PreviewView
import androidx.compose.ui.unit.IntSize
import androidx.lifecycle.LifecycleOwner
import com.pmb.core.permissions.PermissionDispatcher
import com.pmb.core.platform.BaseViewAction

sealed class SignatureViewActions : BaseViewAction {
    data class RequestCameraPermission(
        val managedActivityResultLauncher: ManagedActivityResultLauncher<String, Boolean>
    ) : SignatureViewActions()

    data object RequestFilePermission : SignatureViewActions()
    data object TakePhoto : SignatureViewActions()
    data object ToggleCamera : SignatureViewActions()
    data class PreviewCamera(
        val previewView: PreviewView,
        val lifecycleOwner: LifecycleOwner
    ) :
        SignatureViewActions()

    data object ClearAlert : SignatureViewActions()
}