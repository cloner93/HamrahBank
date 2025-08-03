package com.pmb.profile.presentaion.update.latest

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.pmb.ballon.component.AlertComponent
import com.pmb.ballon.component.MenuItem
import com.pmb.ballon.component.MenuItemDefaults
import com.pmb.ballon.component.base.AppContent
import com.pmb.ballon.component.base.AppLoading
import com.pmb.ballon.component.base.AppTopBar
import com.pmb.ballon.component.base.CaptionText
import com.pmb.ballon.models.TextStyle
import com.pmb.ballon.ui.theme.AppTheme
import com.pmb.calender.formatSimple
import com.pmb.navigation.manager.LocalNavigationManager
import com.pmb.navigation.moduleScreen.ProfileScreens
import com.pmb.profile.R
import com.pmb.profile.domain.entity.VersionEntity
import com.pmb.profile.presentaion.update.latest.viewmodel.LatestVersionsViewActions
import com.pmb.profile.presentaion.update.latest.viewmodel.LatestVersionsViewEvents
import com.pmb.profile.presentaion.update.latest.viewmodel.LatestVersionsViewModel
import java.util.Date

@Composable
fun LatestVersionsScreen(viewModel: LatestVersionsViewModel, result: (VersionEntity) -> Unit) {
    val navigationManager = LocalNavigationManager.current
    val viewState by viewModel.viewState.collectAsStateWithLifecycle()

    LaunchedEffect(Unit) {
        viewModel.viewEvent.collect { event ->
            when (event) {
                is LatestVersionsViewEvents.NavigateToDetail -> {
                    result(event.versionEntity)
                    navigationManager.navigate(ProfileScreens.Update.Detail)
                }
            }
        }
    }

    AppContent(
        modifier = Modifier
            .padding(horizontal = 16.dp)
            .padding(top = 12.dp),
        backgroundColor = AppTheme.colorScheme.background3Neutral,
        topBar = {
            AppTopBar(
                title = stringResource(R.string.latest_versions),
                onBack = {
                    navigationManager.navigateBack()
                })
        },
        horizontalAlignment = Alignment.CenterHorizontally,
        scrollState = null
    ) {
        Card(
            modifier = Modifier
                .fillMaxWidth(),
            shape = RoundedCornerShape(16.dp),
            colors = CardDefaults.cardColors(containerColor = AppTheme.colorScheme.background1Neutral),
            content = {
                LazyColumn {
                    items(viewState.versionEntities.size) { index ->
                        val item = viewState.versionEntities[index]
                        MenuItem(
                            title = stringResource(R.string.version_param, item.version),
                            endIcon = com.pmb.ballon.R.drawable.ic_arrow_left,
                            bottomDivider = index != viewState.versionEntities.lastIndex,
                            titleStyle = TextStyle(
                                color = AppTheme.colorScheme.foregroundNeutralDefault,
                                typography = AppTheme.typography.bodyMedium
                            ),
                            innerPadding = MenuItemDefaults.innerPadding.copy(start = 16.dp),
                            endContent = {
                                CaptionText(
                                    text = Date(item.updatedAt).formatSimple(),
                                    color = AppTheme.colorScheme.onBackgroundNeutralSubdued
                                )
                            },
                            onItemClick = {
                                viewModel.handle(LatestVersionsViewActions.SelectVersion(item))
                            })
                    }
                }
            }
        )
    }


    if (viewState.loading) AppLoading()
    viewState.alertState?.let { AlertComponent(it) }
}