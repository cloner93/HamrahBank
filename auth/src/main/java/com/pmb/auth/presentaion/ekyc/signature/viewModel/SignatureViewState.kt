package com.pmb.auth.presentaion.ekyc.signature.viewModel

import com.pmb.core.platform.AlertModelState
import com.pmb.core.platform.BaseViewState

data class SignatureViewState(
    val hasCameraPermission: Boolean = false,
    val hasFilePermissions: Boolean = false,
    val isCameraReady: Boolean = false,
    val isFrontCamera: Boolean = false,
    val isCapturingPhoto: Boolean = false,
    val photoCaptured: Boolean = false,
    val savedFileUri: String? = null,
    val error: String? = null,
    val isLoading: Boolean = false,
    val alertModelState: AlertModelState? = null,
) :BaseViewState