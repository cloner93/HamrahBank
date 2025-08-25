package com.pmb.auth.presentation.register.signature.viewModel

import com.pmb.camera.platform.PhotoViewActions

sealed interface SignatureViewActions : PhotoViewActions {
    data object ClearAlert : SignatureViewActions
    data class SendSignaturePhoto(val uri: String) : SignatureViewActions
}