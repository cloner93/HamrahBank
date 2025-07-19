package com.pmb.auth.presentation.register.job_information.viewModel

import android.net.Uri
import android.util.Log
import androidx.lifecycle.viewModelScope
import com.pmb.auth.domain.register.job_information.entity.JobInformationParam
import com.pmb.auth.domain.register.job_information.useCase.GetAnnualIncomePrediction
import com.pmb.auth.domain.register.job_information.useCase.SendJobInformationUseCase
import com.pmb.core.fileManager.FileManager
import com.pmb.core.permissions.PermissionDispatcher
import com.pmb.core.platform.AlertModelState
import com.pmb.core.platform.BaseViewModel
import com.pmb.core.platform.Result
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class JobInformationViewModel @Inject constructor(
    initialState: JobInformationViewState,
    private val getAnnualIncomePrediction: GetAnnualIncomePrediction,
    private val sendJobInformationUseCase: SendJobInformationUseCase,
    private val permissionDispatcher: PermissionDispatcher,
    private val fileManager: FileManager
) :
    BaseViewModel<JobInformationViewActions, JobInformationViewState, JobInformationViewEvents>(
        initialState
    ) {
    override fun handle(action: JobInformationViewActions) {
        when (action) {
            is JobInformationViewActions.ClearAlert -> {
                setState {
                    it.copy(
                        isLoading = false
                    )
                }
            }

            is JobInformationViewActions.SetJobInformation -> {
                handleSetJobInformation(action)
            }

            is JobInformationViewActions.GetAnnualIncomePrediction -> {
                handleGetAnnualIncomePrediction()
            }

            is JobInformationViewActions.SendJonInformation -> {
                handleSendJobInformation(action)
            }

            is JobInformationViewActions.RequestCameraPermission -> {
                requestCameraPermission(action)
            }

            is JobInformationViewActions.TookPhoto -> {
                tookPhoto()
            }

            is JobInformationViewActions.ClearPhoto -> {
                clearPhoto()
            }
            is JobInformationViewActions.GetFileFromStorage -> {
                handleGetFileFromStorage(action.fileUri)
            }
        }
    }

    private fun handleGetFileFromStorage(uri: Uri) {
        val fileName = uri.lastPathSegment ?: "selected_file"
        val success = fileManager.savePickedFileToInternalStorage(
            uri = uri,
            fileName = fileName,
            fileManager = fileManager
        )
        if (success){
            setState {
                it.copy(
                    fileUri = uri,
                    hasCameraPermission = false,
                    cameraHasError = null,
                    isTookPhoto = true,
                )
            }
        }
    }

    private fun clearPhoto() {
        viewModelScope.launch {

            val deleteFile = fileManager.deleteFileFromUri(viewState.value.fileUri)
            if (deleteFile) {
                delay(500)
                setState {
                    it.copy(
                        hasCameraPermission = false,
                        cameraHasError = null,
                        isTookPhoto = false,
                        fileUri = null
                    )
                }
            }

        }
    }

    private fun requestCameraPermission(action: JobInformationViewActions.RequestCameraPermission) {
        viewModelScope.launch {

            permissionDispatcher.initialize(action.managedActivityResultLauncher)
            permissionDispatcher.requestSinglePermission(
                permission = android.Manifest.permission.CAMERA,
                onPermissionGranted = {
                    createFile()
                    setState { state ->
                        state.copy(hasCameraPermission = true)
                    }
                },
                onPermissionDenied = {
                    setState { state ->
                        Log.i("per", "You don't have permission for using camera")
                        state.copy(cameraHasError = "You don't have permission for using camera")
                    }
                }
            )
        }
    }

    private fun tookPhoto() {
        setState {
            it.copy(
                isTookPhoto = true
            )
        }
    }

    private fun handleSetJobInformation(action: JobInformationViewActions.SetJobInformation) {
        setState {
            it.copy(
                jobInformation = action.jobInformation
            )
        }
    }

    fun createFile() {
        val file = fileManager.getFileUriFromProvider(fileManager.createImageFile())
        setState {
            it.copy(
                fileUri = file
            )
        }
    }

    fun onSinglePermissionResult(isGranted: Boolean) {
        permissionDispatcher.onSinglePermissionResult(isGranted)

    }

    private fun handleGetAnnualIncomePrediction() {
        viewModelScope.launch {
            getAnnualIncomePrediction.invoke(Unit).collect { result ->
                when (result) {
                    is Result.Loading -> {
                        setState {
                            it.copy(
                                isLoading = true
                            )
                        }
                    }

                    is Result.Error -> {
                        setState {
                            it.copy(
                                isLoading = false, alertModelState = AlertModelState.Dialog(
                                    title = "خطا",
                                    description = " ${result.message}",
                                    positiveButtonTitle = "تایید",
                                    onPositiveClick = {
                                        setState { state -> state.copy(alertModelState = null) }
                                    }
                                )
                            )
                        }
                    }

                    is Result.Success -> {
                        setState {
                            it.copy(
                                isLoading = false,
                                data = result.data
                            )
                        }
                    }
                }
            }
        }
    }

    private fun handleSendJobInformation(action: JobInformationViewActions.SendJonInformation) {
        viewModelScope.launch {
            sendJobInformationUseCase.invoke(
                JobInformationParam(
                    annualIncomeId = action.annualId,
                    jobId = action.jobId
                )
            ).collect { result ->
                when (result) {
                    is Result.Loading -> {
                        setState {
                            it.copy(
                                isLoading = true
                            )
                        }
                    }

                    is Result.Error -> {
                        setState {
                            it.copy(
                                isLoading = false, alertModelState = AlertModelState.Dialog(
                                    title = "خطا",
                                    description = " ${result.message}",
                                    positiveButtonTitle = "تایید",
                                    onPositiveClick = {
                                        setState { state -> state.copy(alertModelState = null) }
                                    }
                                )
                            )
                        }
                    }

                    is Result.Success -> {
                        setState {
                            it.copy(
                                isLoading = false,
                            )
                        }
                        postEvent(JobInformationViewEvents.SendJobInformationSucceed)
                    }
                }
            }
        }
    }

    init {
        handle(JobInformationViewActions.GetAnnualIncomePrediction)
    }
}