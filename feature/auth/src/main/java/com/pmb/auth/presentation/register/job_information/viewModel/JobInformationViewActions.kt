package com.pmb.auth.presentation.register.job_information.viewModel

import android.net.Uri
import androidx.activity.compose.ManagedActivityResultLauncher
import com.pmb.auth.domain.register.select_job_information.entity.JobInformation
import com.pmb.camera.platform.PhotoViewActions
import com.pmb.core.platform.BaseViewAction

sealed interface JobInformationViewActions : BaseViewAction {
    data object ClearAlert : JobInformationViewActions
    data object GetAnnualIncomePrediction : JobInformationViewActions
    data class SetJobInformation(val jobInformation: JobInformation) : JobInformationViewActions
    data class SendJonInformation(val annualId: Int, val jobId: Int) :
        JobInformationViewActions
    data class RequestCameraPermission(
        val managedActivityResultLauncher: ManagedActivityResultLauncher<String, Boolean>
    ) : JobInformationViewActions
    data object TookPhoto : JobInformationViewActions
    data object ClearPhoto : JobInformationViewActions
    data class GetFileFromStorage(val fileUri : Uri): JobInformationViewActions
}