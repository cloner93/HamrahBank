@file:Suppress("REDUNDANT_ELSE_IN_WHEN")

package com.pmb.profile.presentaion.update.viewmodel

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
import com.pmb.ballon.component.base.AppContent
import com.pmb.ballon.component.base.AppLoading
import com.pmb.ballon.component.base.AppTopBar
import com.pmb.ballon.component.base.BodyMediumText
import com.pmb.ballon.ui.theme.AppTheme
import com.pmb.navigation.manager.LocalNavigationManager
import com.pmb.profile.R
import com.pmb.profile.presentaion.update.detail.viewmodel.DetailVersionViewModel

@Composable
fun DetailVersionScreen(viewModel: DetailVersionViewModel) {
    val navigationManager = LocalNavigationManager.current
    val viewState by viewModel.viewState.collectAsStateWithLifecycle()

    LaunchedEffect(Unit) {
        viewModel.viewEvent.collect { event ->
            when (event) {
                else -> Unit
            }
        }
    }


    AppContent(
        modifier = Modifier
            .padding(horizontal = 16.dp)
            .padding(top = 16.dp),
        backgroundColor = AppTheme.colorScheme.background1Neutral,
        topBar = {
            AppTopBar(
                title = stringResource(
                    R.string.version_param,
                    viewState.versionEntity?.version ?: ""
                ),
                onBack = {
                    navigationManager.navigateBack()
                })
        },
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        viewState.versionEntity?.message?.let {
            BodyMediumText(
                text = it,
                color = AppTheme.colorScheme.onBackgroundNeutralSubdued
            )
        }
    }

    if (viewState.loading) AppLoading()
    viewState.alertState?.let { AlertComponent(it) }
}