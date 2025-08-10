@file:Suppress("REDUNDANT_ELSE_IN_WHEN")

package com.pmb.profile.presentaion.update.viewmodel

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
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
import com.pmb.calender.formatSimple
import com.pmb.navigation.manager.LocalNavigationManager
import com.pmb.profile.R
import com.pmb.profile.presentaion.update.detail.viewmodel.DetailVersionViewModel
import java.util.Date

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
    ) {
        viewState.versionEntity?.message?.let {
            BodyMediumText(
                text = it,
                color = AppTheme.colorScheme.onBackgroundNeutralSubdued
            )
        }
        Spacer(modifier = Modifier.size(24.dp))
        viewState.versionEntity?.let {
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                BodyMediumText(
                    text = "تاریخ انتشار:",
                    color = AppTheme.colorScheme.onBackgroundNeutralDefault
                )
                Spacer(modifier = Modifier.size(4.dp))
                BodyMediumText(
                    text = Date(it.createdAt).formatSimple(),
                    color = AppTheme.colorScheme.onBackgroundNeutralSubdued
                )
            }
        }
    }

    if (viewState.loading) AppLoading()
    viewState.alertState?.let { AlertComponent(it) }
}