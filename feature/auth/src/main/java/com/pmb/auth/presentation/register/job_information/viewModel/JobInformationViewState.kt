package com.pmb.auth.presentation.register.job_information.viewModel

import android.net.Uri
import com.pmb.core.platform.AlertModelState
import com.pmb.core.platform.BaseViewState
import com.pmb.domain.model.openAccount.accountVerifyCode.AnnualIncomeType
import com.pmb.domain.model.openAccount.jobLevel.JobLevel

data class JobInformationViewState(
    val isLoading: Boolean = false,
    val alertModelState: AlertModelState? = null,
    val data: AnnualIncomeType? = null,
    val jobInformation: JobLevel? = null,
    val hasCameraPermission: Boolean = false,
    val cameraHasError: String? = null,
    val fileUri: Uri? = null,
    val isTookPhoto: Boolean = false
) : BaseViewState
