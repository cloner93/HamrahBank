package com.pmb.profile.presentaion.personal_infos.change_education

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.pmb.ballon.component.AlertComponent
import com.pmb.ballon.component.CustomSpinner
import com.pmb.ballon.component.base.AppButton
import com.pmb.ballon.component.base.AppContent
import com.pmb.ballon.component.base.AppLoading
import com.pmb.ballon.component.base.AppTopBar
import com.pmb.ballon.ui.theme.AppTheme
import com.pmb.navigation.manager.LocalNavigationManager
import com.pmb.profile.R
import com.pmb.profile.domain.entity.EducationEntity
import com.pmb.profile.presentaion.personal_infos.PersonalInfoSharedState
import com.pmb.profile.presentaion.personal_infos.change_education.viewmodel.ChangeEducationViewActions
import com.pmb.profile.presentaion.personal_infos.change_education.viewmodel.ChangeEducationViewEvents
import com.pmb.profile.presentaion.personal_infos.change_education.viewmodel.ChangeEducationViewModel


@Composable
fun ChangeEducationScreen(
    viewModel: ChangeEducationViewModel,
    sharedState: PersonalInfoSharedState,
    result: (EducationEntity) -> Unit
) {
    val navigationManager = LocalNavigationManager.current
    val viewState by viewModel.viewState.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.viewEvent.collect { event ->
            when (event) {
                is ChangeEducationViewEvents.NavigateBackToPersonalInfo -> {
                    result(event.educationEntity)
                    navigationManager.navigateBack()
                }
            }
        }
    }

    LaunchedEffect(Unit) {
        viewModel.handle(ChangeEducationViewActions.UpdateShareState(sharedState))
    }

    AppContent(
        modifier = Modifier.padding(horizontal = 16.dp),
        backgroundColor = AppTheme.colorScheme.background3Neutral,
        topBar = {
            AppTopBar(
                title = stringResource(R.string.change_education),
                onBack = { navigationManager.navigateBack() })
        },
        footer = {
            AppButton(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
                title = stringResource(R.string.save),
                enable = viewState.enableButton,
                onClick = {
                    viewModel.handle(ChangeEducationViewActions.SubmitEducation)
                })
        },
        content = {
            Spacer(modifier = Modifier.size(24.dp))
            CustomSpinner(
                modifier = Modifier.fillMaxWidth(),
                options = viewState.educationEntities.map { it.title },
                labelString = stringResource(R.string.education),
                displayText = viewState.educationEntity.title,
                isEnabled = true
            ) { title ->
                viewModel.handle(ChangeEducationViewActions.ClickEducation(title))
            }
        })

    if (viewState.loading) AppLoading()
    viewState.alertState?.let { AlertComponent(it) }
}