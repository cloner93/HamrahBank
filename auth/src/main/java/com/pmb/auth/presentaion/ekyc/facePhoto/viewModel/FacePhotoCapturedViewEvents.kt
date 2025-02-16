package com.pmb.auth.presentaion.ekyc.facePhoto.viewModel

import com.pmb.auth.presentaion.ekyc.signature.viewModel.SignatureViewEvents
import com.pmb.core.platform.BaseViewEvent

sealed interface FacePhotoCapturedViewEvents : BaseViewEvent {
    data object SignaturePhotoCaptured : FacePhotoCapturedViewEvents
}