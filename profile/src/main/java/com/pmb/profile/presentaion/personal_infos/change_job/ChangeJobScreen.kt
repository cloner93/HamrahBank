package com.pmb.profile.presentaion.personal_infos.change_job

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.pmb.ballon.component.AlertComponent
import com.pmb.ballon.component.base.AppButton
import com.pmb.ballon.component.base.AppClickableReadOnlyTextField
import com.pmb.ballon.component.base.AppContent
import com.pmb.ballon.component.base.AppLoading
import com.pmb.ballon.component.base.AppTopBar
import com.pmb.ballon.models.Icons
import com.pmb.navigation.manager.LocalNavigationManager
import com.pmb.navigation.moduleScreen.AuthScreens
import com.pmb.profile.R
import com.pmb.profile.domain.entity.JobEntity
import com.pmb.profile.presentaion.personal_infos.PersonalInfoSharedState
import com.pmb.profile.presentaion.personal_infos.change_job.viewmodel.ChangeJobViewActions
import com.pmb.profile.presentaion.personal_infos.change_job.viewmodel.ChangeJobViewEvents
import com.pmb.profile.presentaion.personal_infos.change_job.viewmodel.ChangeJobViewModel


@Composable
fun ChangeJobScreen(
    viewModel: ChangeJobViewModel,
    sharedState: PersonalInfoSharedState,
    result: (JobEntity) -> Unit
) {
    val navigationManager = LocalNavigationManager.current
    val viewState by viewModel.viewState.collectAsStateWithLifecycle()

    LaunchedEffect(Unit) {
        viewModel.viewEvent.collect { event ->
            when (event) {
                is ChangeJobViewEvents.NavigateBackToPersonalInfo -> {
                    result.invoke(event.jobEntity)
                    navigationManager.navigateBack()
                }

                is ChangeJobViewEvents.NavigateToListJob -> {

                }
            }
        }
    }
    LaunchedEffect(Unit) {
        viewModel.handle(ChangeJobViewActions.UpdateShareState(sharedState))
    }

    AppContent(
        modifier = Modifier
            .padding(horizontal = 16.dp)
            .padding(top = 24.dp),
        topBar = {
            AppTopBar(
                title = stringResource(R.string.change_address),
                onBack = {
                    navigationManager.navigateBack()
                })
        },
        footer = {
            AppButton(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
                enable = viewState.enableButton,
                title = stringResource(if (viewState.selectedJob) R.string.save else R.string.change_job),
                onClick = {
                    viewModel.handle(action = ChangeJobViewActions.SubmitJob)
                })
        },
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        AppClickableReadOnlyTextField(
            onClick = {
                navigationManager.navigate(AuthScreens.SelectJobInformation)
            },
            value = viewState.jobEntity.title,
            label = stringResource(R.string.job),
            trailingIcon = { Icons.ArrowDownIcon() },
        )
    }

    if (viewState.loading) AppLoading()
    viewState.alertState?.let { AlertComponent(it) }
}