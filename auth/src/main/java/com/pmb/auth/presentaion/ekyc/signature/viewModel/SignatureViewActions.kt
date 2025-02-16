package com.pmb.auth.presentaion.ekyc.signature.viewModel

import com.pmb.camera.platform.PhotoViewActions

sealed interface SignatureViewActions :PhotoViewActions{
    data object ClearAlert : SignatureViewActions
}