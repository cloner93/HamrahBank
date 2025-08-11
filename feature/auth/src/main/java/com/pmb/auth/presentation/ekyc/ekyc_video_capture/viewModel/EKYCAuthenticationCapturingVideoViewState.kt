package com.pmb.auth.presentation.ekyc.ekyc_video_capture.viewModel

import com.pmb.auth.presentation.first_login_confirm.viewModel.TimerState
import com.pmb.auth.presentation.first_login_confirm.viewModel.TimerTypeId
import com.pmb.camera.platform.VideoViewState
import com.pmb.core.platform.AlertModelState
import com.pmb.domain.model.openAccount.FetchAdmittanceTextResponse
import java.io.File

data class EKYCAuthenticationCapturingVideoViewState(
    val isLoading :Boolean = false,
    val alertModelState: AlertModelState?=null,
    val admittanceTextResponse: FetchAdmittanceTextResponse? = null,
    val timerState: Map<TimerTypeId, TimerState>? = null,
    val videoFileBase64File: File? = null,
    override val hasCameraPermission: Boolean = false,
    override val hasAudioPermissions: Boolean = false,
    override val isCameraReady: Boolean = false,
    override val isFrontCamera: Boolean = false,
    override val isCapturingVideo: Boolean = false,
    override val videoCaptured: Boolean = false,
    override val savedFileUri: String? = null,
    override val cameraHasError: String? = null,
    override val isCameraLoading: Boolean = false,
    override val isCompressing: Boolean = false
) : VideoViewState{
    fun calculateSecond(timerTypeId: TimerTypeId) =
        (((timerState?.get(timerTypeId)?.remainingTime ?: 0)) % 60).toString().padStart(2, '0')

    fun calculateMinute(timerTypeId: TimerTypeId) =
        (((timerState?.get(timerTypeId)?.remainingTime
            ?: 0) / (60)) % 60).toString()
            .padStart(2, '0')


}
