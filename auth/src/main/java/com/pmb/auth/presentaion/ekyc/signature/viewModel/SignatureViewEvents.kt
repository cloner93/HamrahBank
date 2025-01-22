package com.pmb.auth.presentaion.ekyc.signature.viewModel

import com.pmb.core.platform.BaseViewEvent

sealed interface SignatureViewEvents : BaseViewEvent {
    data object SignaturePhotoCaptured : SignatureViewEvents
}