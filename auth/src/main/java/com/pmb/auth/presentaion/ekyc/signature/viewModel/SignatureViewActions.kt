package com.pmb.auth.presentaion.ekyc.signature.viewModel

import androidx.camera.view.PreviewView
import androidx.lifecycle.LifecycleOwner
import com.pmb.core.platform.BaseViewAction

sealed class SignatureViewActions : BaseViewAction {
    data object RequestCameraPermission : SignatureViewActions()
    data object RequestFilePermission : SignatureViewActions()
    data object TakePhoto : SignatureViewActions()
    data object ToggleCamera : SignatureViewActions()
    data class PreviewCamera(val previewView: PreviewView, val lifecycleOwner: LifecycleOwner) :
        SignatureViewActions()

    data object ClearAlert : SignatureViewActions()
}