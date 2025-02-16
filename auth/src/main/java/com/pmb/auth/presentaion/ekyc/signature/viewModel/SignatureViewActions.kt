package com.pmb.auth.presentaion.ekyc.signature.viewModel

import com.pmb.camera.platform.PhotoViewActions

sealed interface SignatureViewActions : PhotoViewActions {
    data object ClearAlert : SignatureViewActions
    data class SendSignaturePhoto(val uri: String) : SignatureViewActions
}