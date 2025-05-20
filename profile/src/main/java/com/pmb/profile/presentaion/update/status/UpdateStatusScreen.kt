package com.pmb.profile.presentaion.update.status

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.pmb.ballon.component.AlertComponent
import com.pmb.ballon.component.MenuItem
import com.pmb.ballon.component.TextImage
import com.pmb.ballon.component.base.AppButton
import com.pmb.ballon.component.base.AppContent
import com.pmb.ballon.component.base.AppLoading
import com.pmb.ballon.component.base.AppTopBar
import com.pmb.ballon.component.base.BodySmallText
import com.pmb.ballon.component.base.IconType
import com.pmb.ballon.models.IconStyle
import com.pmb.ballon.models.TextStyle
import com.pmb.ballon.ui.theme.AppTheme
import com.pmb.navigation.manager.LocalNavigationManager
import com.pmb.profile.R
import com.pmb.profile.presentaion.update.status.viewmodel.UpdateStatusViewActions
import com.pmb.profile.presentaion.update.status.viewmodel.UpdateStatusViewEvents
import com.pmb.profile.presentaion.update.status.viewmodel.UpdateStatusViewModel


@Composable
fun UpdateStatusScreen(viewModel: UpdateStatusViewModel) {
    val navigationManager = LocalNavigationManager.current
    val viewState by viewModel.viewState.collectAsStateWithLifecycle()

    LaunchedEffect(Unit) {
        viewModel.viewEvent.collect { event ->
            when (event) {
                is UpdateStatusViewEvents.NavigateToUpdateHistory -> {
                }
            }
        }
    }

    AppContent(
        modifier = Modifier
            .padding(horizontal = 16.dp)
            .padding(top = 24.dp), topBar = {
            AppTopBar(
                title = stringResource(R.string.update), onBack = {
                    navigationManager.navigateBack()
                })
        }, footer = {
            viewState.versionEntity?.takeIf { !it.updated }?.let {
                AppButton(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp),
                    enable = viewState.enableUpdate,
                    title = stringResource(R.string.update),
                    onClick = {
                        viewModel.handle(action = UpdateStatusViewActions.UpdateClicked)
                    })
            }
        }, horizontalAlignment = Alignment.CenterHorizontally
    ) {
        TextImage(
            image = IconType.Painter(painterResource(com.pmb.ballon.R.drawable.profile)),
            text = stringResource(R.string.msg_new_version_for_update_title),
            textStyle = TextStyle(
                color = AppTheme.colorScheme.onBackgroundNeutralDefault,
                typography = AppTheme.typography.headline6
            ),
            spacer = 32.dp
        )
        Spacer(modifier = Modifier.size(4.dp))
        viewState.versionEntity?.takeIf { !it.updated }?.let {
            BodySmallText(
                modifier = Modifier.padding(top = 12.dp),
                text = stringResource(R.string.msg_update_to_last_version),
                color = AppTheme.colorScheme.onBackgroundNeutralSubdued
            )
        }
        BodySmallText(
            modifier = Modifier.padding(top = 12.dp),
            text = stringResource(
                R.string.msg_current_version, viewState.versionEntity?.version ?: ""
            ), color = AppTheme.colorScheme.onBackgroundNeutralSubdued
        )


        viewState.versionEntity?.takeIf { it.updated }?.let {
            MenuItem(
                modifier = Modifier.padding(top = 64.dp),
                title = stringResource(R.string.show_new_changes),
                endIcon = com.pmb.ballon.R.drawable.ic_arrow_left,
                titleStyle = TextStyle(
                    color = AppTheme.colorScheme.foregroundNeutralDefault,
                    typography = AppTheme.typography.buttonMedium
                ),
                endIconStyle = IconStyle(tint = AppTheme.colorScheme.foregroundNeutralRest),
                onItemClick = {
                    viewModel.handle(action = UpdateStatusViewActions.ShowNewChanges)
                }
            )
            MenuItem(
                title = stringResource(R.string.latest_version),
                endIcon = com.pmb.ballon.R.drawable.ic_arrow_left,
                titleStyle = TextStyle(
                    color = AppTheme.colorScheme.foregroundNeutralDefault,
                    typography = AppTheme.typography.buttonMedium
                ),
                endIconStyle = IconStyle(tint = AppTheme.colorScheme.foregroundNeutralRest),
                onItemClick = {
                    viewModel.handle(action = UpdateStatusViewActions.LatestVersion)
                }
            )
        }
    }


    if (viewState.loading) AppLoading()
    viewState.alert?.let { AlertComponent(it) }
}

