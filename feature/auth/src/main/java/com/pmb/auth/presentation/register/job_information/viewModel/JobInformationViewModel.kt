package com.pmb.auth.presentation.register.job_information.viewModel

import android.content.Context
import android.net.Uri
import android.util.Log
import androidx.lifecycle.viewModelScope
import com.pmb.core.fileManager.FileManager
import com.pmb.core.permissions.PermissionDispatcher
import com.pmb.core.platform.AlertModelState
import com.pmb.core.platform.BaseViewModel
import com.pmb.core.platform.Result
import com.pmb.core.utils.Base64FileHelper
import com.pmb.domain.usecae.auth.openAccount.AccountArchiveJobDocParams
import com.pmb.domain.usecae.auth.openAccount.AccountArchiveJobDocUseCase
import com.pmb.domain.usecae.auth.openAccount.FetchLevelJobUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class JobInformationViewModel @Inject constructor(
    initialState: JobInformationViewState,
    private val accountArchiveJobDocUseCase: AccountArchiveJobDocUseCase,
    private val permissionDispatcher: PermissionDispatcher,
    private val fileManager: FileManager,
    @ApplicationContext private val context: Context
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

            is JobInformationViewActions.SetAnnualIncome -> {
                handleSetAnnualIncome(action)
            }

            is JobInformationViewActions.UploadArchiveDoc -> {
                handleUploadArchiveJob(action)
            }
        }
    }


    private fun handleUploadArchiveJob(action: JobInformationViewActions.UploadArchiveDoc) {
        viewModelScope.launch {
            val file = viewState.value.fileUri?.let {
                Base64FileHelper.encodeToBase64(
                    context,
                    it,
                    viewModelScope
                )
            }
            file?.let {
                accountArchiveJobDocUseCase.invoke(
                    AccountArchiveJobDocParams(
                        file = file.await(),
                        nationalCode = action.nationalCode
                    )
                ).collectLatest { result ->
                    when (result) {
                        is Result.Loading -> {
                            setState {
                                it.copy(
                                    isLoading = true
                                )
                            }
                        }

                        is Result.Success -> {
                            setState {
                                it.copy(
                                    isLoading = false,
                                    hasCameraPermission = false
                                )
                            }
                            delay(50)
                            postEvent(JobInformationViewEvents.SendJobInformationSucceed)
                        }

                        is Result.Error -> {
                            setState {
                                it.copy(
                                    isLoading = false,
                                    alertModelState = AlertModelState.Dialog(
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
                    }

                }
            }
        }
    }

    private fun handleSetAnnualIncome(action: JobInformationViewActions.SetAnnualIncome) {
        setState {
            it.copy(
                data = action.annualIncomeType
            )
        }
    }

    private fun handleGetFileFromStorage(uri: Uri) {
        val fileName = uri.lastPathSegment ?: "selected_file"
        val success = fileManager.savePickedFileToInternalStorage(
            uri = uri,
            fileName = fileName,
            fileManager = fileManager
        )
        if (success) {
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


}