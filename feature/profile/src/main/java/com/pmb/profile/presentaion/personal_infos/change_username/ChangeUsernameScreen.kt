package com.pmb.profile.presentaion.personal_infos.change_username

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
import com.pmb.ballon.component.UsernameConditions
import com.pmb.ballon.component.base.AppButton
import com.pmb.ballon.component.base.AppContent
import com.pmb.ballon.component.base.AppLoading
import com.pmb.ballon.component.base.AppSingleTextField
import com.pmb.ballon.component.base.AppTopBar
import com.pmb.ballon.ui.theme.AppTheme
import com.pmb.navigation.manager.LocalNavigationManager
import com.pmb.profile.R
import com.pmb.profile.presentaion.personal_infos.PersonalInfoSharedState
import com.pmb.profile.presentaion.personal_infos.change_username.viewmodel.ChangeUsernameViewActions
import com.pmb.profile.presentaion.personal_infos.change_username.viewmodel.ChangeUsernameViewEvents
import com.pmb.profile.presentaion.personal_infos.change_username.viewmodel.ChangeUsernameViewModel


@Composable
fun ChangeUsernameScreen(
    viewModel: ChangeUsernameViewModel,
    sharedState: PersonalInfoSharedState,
    result: (username: String) -> Unit
) {
    val navigationManager = LocalNavigationManager.current
    val viewState by viewModel.viewState.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.handle(ChangeUsernameViewActions.UpdateShareState(sharedState))
    }

    LaunchedEffect(Unit) {
        viewModel.viewEvent.collect { event ->
            when (event) {
                is ChangeUsernameViewEvents.NavigateBackToPersonalInfo -> {
                    result.invoke(event.newUsername)
                    navigationManager.navigateBack()
                }
            }
        }
    }

    AppContent(
        modifier = Modifier
            .padding(horizontal = 16.dp)
            .padding(top = 24.dp),
        backgroundColor = AppTheme.colorScheme.background3Neutral,
        topBar = {
            AppTopBar(
                title = stringResource(R.string.change_username),
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
                    viewModel.handle(ChangeUsernameViewActions.SubmitUsername)
                })
        },
        content = {
            AppSingleTextField(
                modifier = Modifier.fillMaxWidth(),
                value = viewState.username,
                label = stringResource(R.string.new_username),
                onValueChange = {
                    viewModel.handle(ChangeUsernameViewActions.UsernameChanged(it))
                }
            )
            Spacer(modifier = Modifier.size(40.dp))
            UsernameConditions(viewState.usernameConditions)
        }
    )

    if (viewState.loading) AppLoading()
    viewState.alertState?.let { AlertComponent(it) }
}