package com.pmb.auth.presentation.register.select_job_information

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.pmb.auth.R
import com.pmb.auth.presentation.register.select_job_information.viewModel.SelectJobInformationViewActions
import com.pmb.auth.presentation.register.select_job_information.viewModel.SelectJobInformationViewModel
import com.pmb.ballon.component.AlertComponent
import com.pmb.ballon.component.base.AppButtonIcon
import com.pmb.ballon.component.base.AppContent
import com.pmb.ballon.component.base.AppLoading
import com.pmb.ballon.component.base.AppSearchTextField
import com.pmb.ballon.component.base.BodyMediumText
import com.pmb.ballon.ui.theme.AppTheme
import com.pmb.navigation.manager.LocalNavigationManager
import com.pmb.navigation.manager.NavigationManager
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.debounce

@OptIn(FlowPreview::class)
@Composable
fun SelectJobInformationScreen(
    viewModel: SelectJobInformationViewModel
) {
    val navigationManager: NavigationManager = LocalNavigationManager.current
    val viewState by viewModel.viewState.collectAsState()
    var searchQuery by remember { mutableStateOf("") }
    LaunchedEffect(searchQuery) {
        snapshotFlow { searchQuery }.debounce(300L).collectLatest {
            if (it.isNotEmpty() && it.length >= 2) {
                viewModel.handle(SelectJobInformationViewActions.SearchSelectJobInformationData(it))
            } else {
                viewModel.handle(SelectJobInformationViewActions.ClearSearchData)
            }
        }
    }
    AppContent(
        modifier = Modifier.padding(horizontal = 16.dp),
        topBar = {
            Spacer(modifier = Modifier.size(14.dp))
            Row(modifier = Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
                AppButtonIcon(
                    modifier = Modifier.padding(start = 16.dp).size(24.dp),
                    icon = Icons.Default.Close,
                    onClick = {
                        navigationManager.navigateBack()
                    }
                )
                AppSearchTextField(
                    modifier = Modifier
                        .weight(1f)
                        .padding(end = 16.dp, start = 16.dp),
                    hint = stringResource(R.string.search_job_description),
                    query = searchQuery,
                    onValueChange = { searchQuery = it }
                )
            }
        },
        footer = {

        }) {

        Spacer(modifier = Modifier.size(24.dp))

        viewState.selectJobInformation?.forEach { selectJobInformation ->
            BodyMediumText(
                text = selectJobInformation.jobName ?: "",
                modifier = Modifier.clickable {
                    navigationManager.setPreviousScreenData(
                        "jobInformation",
                        selectJobInformation
                    )
                    navigationManager.navigateBack()
                },
                color = AppTheme.colorScheme.onBackgroundNeutralDefault

            )
            Spacer(modifier = Modifier.size(16.dp))
        }

    }
    if (viewState.isLoading) {
        AppLoading()
    }
    if (viewState.alertModelState != null) {
        AlertComponent(viewState.alertModelState!!)
    }
}