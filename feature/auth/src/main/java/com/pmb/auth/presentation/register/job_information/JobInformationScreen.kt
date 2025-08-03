package com.pmb.auth.presentation.register.job_information

import android.content.Intent
import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.pmb.auth.R
import com.pmb.auth.presentation.component.UploadDocumentsSection
import com.pmb.auth.presentation.register.RegisterSharedViewState
import com.pmb.auth.presentation.register.job_information.viewModel.JobInformationViewActions
import com.pmb.auth.presentation.register.job_information.viewModel.JobInformationViewEvents
import com.pmb.auth.presentation.register.job_information.viewModel.JobInformationViewModel
import com.pmb.auth.presentation.register.job_information.viewModel.JobInformationViewState
import com.pmb.ballon.component.AlertComponent
import com.pmb.ballon.component.CustomSpinner
import com.pmb.ballon.component.MenuBottomSheet
import com.pmb.ballon.component.base.AppButton
import com.pmb.ballon.component.base.AppClickableReadOnlyTextField
import com.pmb.ballon.component.base.AppContent
import com.pmb.ballon.component.base.AppLoading
import com.pmb.ballon.component.base.AppTopBar
import com.pmb.ballon.models.MenuSheetModel
import com.pmb.ballon.ui.theme.AppTheme
import com.pmb.core.utils.CollectAsEffect
import com.pmb.domain.model.openAccount.jobLevel.JobLevel
import com.pmb.navigation.manager.LocalNavigationManager
import com.pmb.navigation.manager.NavigationManager
import com.pmb.navigation.moduleScreen.RegisterScreens

@Composable
fun JobInformationScreen(
    viewModel: JobInformationViewModel,
    sharedState: State<RegisterSharedViewState>,
    updateState: (JobInformationViewState) -> Unit
) {
    val navigationManager: NavigationManager = LocalNavigationManager.current
    val viewState by viewModel.viewState.collectAsState()
    val context = LocalContext.current

    var showShareBottomSheet by remember { mutableStateOf(false) }
    val permissionLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestPermission()
    ) { isGranted ->
        viewModel.onSinglePermissionResult(isGranted)
    }
    val launcher =
        rememberLauncherForActivityResult(ActivityResultContracts.TakePicture()) { success ->
            if (!success) {
                viewModel.handle(JobInformationViewActions.ClearPhoto)
            } else {
                viewModel.handle(JobInformationViewActions.TookPhoto)
            }
        }
    val fileLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.OpenDocument()
    ) { uri: Uri? ->
        uri?.let {
            try {
                context.contentResolver.takePersistableUriPermission(
                    it,
                    Intent.FLAG_GRANT_READ_URI_PERMISSION
                )
            } catch (e: SecurityException) {
                e.printStackTrace()
            }
            viewModel.handle(JobInformationViewActions.GetFileFromStorage(it))
        }
    }
    LaunchedEffect(viewState.hasCameraPermission) {
        if (viewState.hasCameraPermission) {
            viewState.fileUri?.let { launcher.launch(it) }
        }
    }
    navigationManager.getCurrentScreenFlowData<JobLevel?>(
        "jobInformation",
        null
    )?.CollectAsEffect {
        it.takeIf {
            it != null
        }?.also {
            viewModel.handle(JobInformationViewActions.SetJobInformation(it))
        }
    }

    LaunchedEffect(Unit) {
        viewModel.viewEvent.collect { event ->
            when (event) {
                JobInformationViewEvents.SendJobInformationSucceed -> {
                    updateState(viewState)
                    navigationManager.navigate(RegisterScreens.CheckPostalCode)
                }
            }
        }
    }
    AppContent(
        modifier = Modifier.padding(horizontal = 16.dp),
        topBar = {
            AppTopBar(
                title = stringResource(R.string.job_information),
                onBack = {
                    navigationManager.navigateBack()
                }
            )
        }, footer = {

            AppButton(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 16.dp, end = 16.dp, top = 16.dp),
                enable = !viewState.isLoading && viewState.data != null && viewState.jobInformation != null,
                title = stringResource(R.string._continue),
                onClick = {
                    updateState(viewState)
                    if (viewState.isTookPhoto)
                        viewModel.handle(
                            JobInformationViewActions.UploadArchiveDoc(
                                sharedState.value.nationalCode ?: ""
                            )
                        )
                    else{
                        navigationManager.navigate(RegisterScreens.CheckPostalCode)
                    }


                })
        }) {
        AppClickableReadOnlyTextField(
            onClick = {
                navigationManager.navigate(RegisterScreens.SelectJobInformation)
            },
            value = viewState.jobInformation?.jobName ?: "",
            label = "انتخاب شغل",
            trailingIcon = {
                Icon(
                    imageVector = Icons.Default.KeyboardArrowDown,
                    contentDescription = "down Icon",
                    tint = AppTheme.colorScheme.onBackgroundNeutralDefault
                )
            },
        )
        Spacer(modifier = Modifier.size(24.dp))
        CustomSpinner(
            modifier = Modifier
                .fillMaxWidth(),
            options = sharedState.value.verifyCodeResponse?.annualIncomeTypes?.map {
                it.incomePredictDescription ?: ""
            },
            labelString = "پیش بینی درآمد سالیانه",
            displayText = viewState.data?.let {
                it.incomePredictDescription
            } ?: "",
            isEnabled = true
        ) { type ->
            val annualIncomeTypes =
                sharedState.value.verifyCodeResponse?.annualIncomeTypes?.findLast { it.incomePredictDescription == type }
            annualIncomeTypes?.let { viewModel.handle(JobInformationViewActions.SetAnnualIncome(it)) }
        }
        UploadDocumentsSection(
            images = if (viewState.isTookPhoto) viewState.fileUri else null,
            onAddClicked = {
                showShareBottomSheet = true
            },
            onRemoveImage = {
                viewModel.handle(JobInformationViewActions.ClearPhoto)
            }
        )
    }
    if (viewState.isLoading) {
        AppLoading()
    }
    if (viewState.alertModelState != null) {
        AlertComponent(viewState.alertModelState!!)
    }
    if (showShareBottomSheet) {
        MenuBottomSheet(
            title = stringResource(R.string.uploading_type),
            items = listOf(
                MenuSheetModel(
                    title = stringResource(R.string.choose_from_existing_file),
                    icon = com.pmb.ballon.R.drawable.ic_image,
                    iconTint = { AppTheme.colorScheme.onBackgroundNeutralCTA },
                    showEndIcon = false,
                    onClicked = {
                        fileLauncher.launch(arrayOf("image/*", "application/pdf"))
                    }
                ),
                MenuSheetModel(
                    title = stringResource(R.string.image_capturing),
                    icon = com.pmb.ballon.R.drawable.ic_image_camera,
                    iconTint = { AppTheme.colorScheme.onBackgroundNeutralCTA },
                    showEndIcon = false,
                    onClicked = {
                        viewModel.handle(
                            JobInformationViewActions.RequestCameraPermission(
                                permissionLauncher
                            )
                        )
                    }
                )

            ),
            onDismiss = {
                showShareBottomSheet = false
            }
        )
    }
}