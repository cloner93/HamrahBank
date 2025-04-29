package com.pmb.profile.presentaion.personal_infos.select_job

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.pmb.ballon.component.AlertComponent
import com.pmb.ballon.component.MenuItem
import com.pmb.ballon.component.MenuItemDefaults
import com.pmb.ballon.component.base.AppButtonIcon
import com.pmb.ballon.component.base.AppContent
import com.pmb.ballon.component.base.AppLoading
import com.pmb.ballon.component.base.AppSearchTextField
import com.pmb.ballon.models.TextStyle
import com.pmb.ballon.ui.theme.AppTheme
import com.pmb.navigation.manager.LocalNavigationManager
import com.pmb.profile.R
import com.pmb.profile.domain.entity.JobEntity
import com.pmb.profile.presentaion.personal_infos.PersonalInfoSharedState
import com.pmb.profile.presentaion.personal_infos.select_job.viewmodel.SelectJobViewActions
import com.pmb.profile.presentaion.personal_infos.select_job.viewmodel.SelectJobViewEvents
import com.pmb.profile.presentaion.personal_infos.select_job.viewmodel.SelectJobViewModel

@Composable
fun SelectJobScreen(
    viewModel: SelectJobViewModel,
    sharedState: PersonalInfoSharedState,
    result: (jobEntity: JobEntity) -> Unit
) {
    val navigationManager = LocalNavigationManager.current
    val viewState by viewModel.viewState.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.handle(SelectJobViewActions.UpdateShareState(sharedState))
    }

    LaunchedEffect(Unit) {
        viewModel.viewEvent.collect { event ->
            when (event) {
                is SelectJobViewEvents.NavigateBackToChangeJob -> {
                    result.invoke(event.jobEntity)
                    navigationManager.navigateBack()
                }
            }
        }
    }

    AppContent(
        scrollState = null, topBar = {
            Spacer(modifier = Modifier.size(14.dp))
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                AppButtonIcon(icon = Icons.Default.Clear) {
                    navigationManager.navigateBack()
                }
                AppSearchTextField(
                    modifier = Modifier.padding(end = 16.dp),
                    hint = stringResource(R.string.search),
                    query = viewState.searchQuery,
                    onValueChange = {
                        viewModel.handle(SelectJobViewActions.UpdateSearchQuery(it))
                    })
            }
        }) {

        Spacer(modifier = Modifier.size(24.dp))

        LazyColumn {
            items(viewState.jobs.size) {
                val item = viewState.jobs[it]
                MenuItem(
                    title = item.title,
                    titleStyle = TextStyle(
                        color = AppTheme.colorScheme.foregroundNeutralDefault,
                        typography = AppTheme.typography.bodyMedium,
                    ),
                    innerPadding = MenuItemDefaults.innerPadding.copy(horizontal = 16.dp, vertical = 12.dp),
                    onItemClick = {
                        viewModel.handle(SelectJobViewActions.SelectJob(item))
                    }
                )
            }
        }
    }
    if (viewState.loading) AppLoading()
    if (viewState.alertState != null) AlertComponent(viewState.alertState!!)
}