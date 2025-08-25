package com.pmb.auth.presentation.reentry.reentry_face_detection.viewModel

import com.pmb.core.platform.AlertModelState
import com.pmb.core.platform.BaseViewState

data class ReentryFaceDetectionViewState(
    val isLoading: Boolean = false,
    val alertModelState: AlertModelState? = null
) : BaseViewState
