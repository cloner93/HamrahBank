package com.pmb.auth.presentation.register.job_information.viewModel

import android.net.Uri
import com.pmb.auth.domain.register.job_information.entity.JobInformationEntity
import com.pmb.auth.domain.register.select_job_information.entity.JobInformation
import com.pmb.core.platform.AlertModelState
import com.pmb.core.platform.BaseViewState
import com.pmb.domain.model.openAccount.accountVerifyCode.AnnualIncomeType

data class JobInformationViewState(
    val isLoading: Boolean = false,
    val alertModelState: AlertModelState? = null,
    val data: AnnualIncomeType? = null,
    val jobInformation: JobInformation? = null,
    val hasCameraPermission: Boolean = false,
    val cameraHasError: String? = null,
    val fileUri :Uri?=null,
    val isTookPhoto : Boolean  = false
) : BaseViewState
