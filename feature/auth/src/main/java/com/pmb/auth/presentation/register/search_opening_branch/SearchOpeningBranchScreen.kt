package com.pmb.auth.presentation.register.search_opening_branch

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.pmb.auth.R
import com.pmb.auth.domain.register.search_opening_branch.entity.OpeningBranch
import com.pmb.auth.presentation.register.search_opening_branch.viewModel.SearchOpeningBranchViewActions
import com.pmb.auth.presentation.register.search_opening_branch.viewModel.SearchOpeningBranchViewModel
import com.pmb.ballon.component.AlertComponent
import com.pmb.ballon.component.HeadlineTextIcon
import com.pmb.ballon.component.RoundedCornerCheckboxComponent
import com.pmb.ballon.component.base.AppButton
import com.pmb.ballon.component.base.AppContent
import com.pmb.ballon.component.base.AppLoading
import com.pmb.ballon.component.base.AppSearchTextField
import com.pmb.ballon.component.base.AppTopBar
import com.pmb.ballon.component.base.ClickableIcon
import com.pmb.ballon.component.base.IconType
import com.pmb.domain.model.openAccount.branchName.Branch
import com.pmb.navigation.manager.LocalNavigationManager
import com.pmb.navigation.manager.NavigationManager
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.debounce

@OptIn(FlowPreview::class)
@Composable
fun SearchOpeningBranchScreen(
    viewModel: SearchOpeningBranchViewModel,
) {
    val navigationManager: NavigationManager = LocalNavigationManager.current
    val viewState by viewModel.viewState.collectAsState()
    var searchQuery by remember { mutableStateOf("") }
    var searchVisible by remember { mutableStateOf(false) }
    LaunchedEffect(searchQuery) {
        snapshotFlow { searchQuery }.debounce(300L).collectLatest {
            if (it.isNotEmpty() && it.length >= 2) {
                viewModel.handle(SearchOpeningBranchViewActions.SearchOpeningBranchData(it))
            } else {
                viewModel.handle(SearchOpeningBranchViewActions.ClearSearchData)
            }
        }
    }
    AppContent(
        modifier = Modifier.padding(horizontal = 16.dp),
        topBar = {
            AppTopBar(
                title = stringResource(R.string.opening_branches_list),
                onBack = {
                    navigationManager.navigateBack()
                },
                endIcon = ClickableIcon(
                    icon = IconType.Painter(painterResource(com.pmb.ballon.R.drawable.ic_search)),
                    onClick = {
                        searchVisible = !searchVisible
                    })
            )
        },
        footer = {
            AppButton(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 16.dp, end = 16.dp, top = 16.dp),
                enable = viewState.selectedBranch != null,
                title = stringResource(com.pmb.ballon.R.string.confirm),
                onClick = {
                    navigationManager.setPreviousScreenData<Branch?>(
                        "openingBranch",
                        viewState.selectedBranch
                    )
                    navigationManager.navigateBack()
                })
        }) {

        Spacer(modifier = Modifier.size(24.dp))
        viewState.headTitle?.let {
            HeadlineTextIcon(
                title = it,
                iconSize = 24.dp,
                icon = com.pmb.ballon.R.drawable.ic_pin_location
            )
        }
        Spacer(modifier = Modifier.size(16.dp))
        AnimatedVisibility(visible = searchVisible) {
            AppSearchTextField(
                modifier = Modifier.padding(end = 16.dp),
                hint = "لیست شعب",
                query = searchQuery,
                onValueChange = { searchQuery = it }
            )
        }
        Spacer(modifier = Modifier.size(16.dp))
        viewState.searchedBranchList?.forEach { openingBranch ->
            RoundedCornerCheckboxComponent(
                title = "${openingBranch.branchName},${openingBranch.branchCode}",
                caption = openingBranch.address,
                isChecked = openingBranch.branchCode == viewState.selectedBranch?.branchCode
            ) {
                viewModel.handle(SearchOpeningBranchViewActions.SelectOpeningBranchId(openingBranch))
            }
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