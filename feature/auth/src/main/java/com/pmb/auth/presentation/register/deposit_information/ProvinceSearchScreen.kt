package com.pmb.auth.presentation.register.deposit_information

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshotFlow
import com.pmb.auth.presentation.component.ProvinceSearchComponent
import com.pmb.auth.presentation.register.RegisterSharedViewState
import com.pmb.domain.model.openAccount.accountType.Province
import com.pmb.navigation.manager.LocalNavigationManager
import com.pmb.navigation.manager.NavigationManager
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.debounce

@Composable
fun ProvinceSearchScreen(
    sharedViewState: RegisterSharedViewState,
    updateProvince: (Province) -> Unit
){
    val navigationManager: NavigationManager = LocalNavigationManager.current
    var searchQuery by remember { mutableStateOf("") }
    var filteredProvinceList by remember { mutableStateOf(sharedViewState.provinceList) }
    LaunchedEffect(searchQuery) {
        snapshotFlow { searchQuery }.debounce(300L).collectLatest {
            filteredProvinceList = if (it.isNotEmpty() && it.length >= 2) {
                sharedViewState.provinceList?.filter {province -> province.provinceName?.contains(it) == true }
            } else {
                sharedViewState.provinceList
            }
        }
    }
    ProvinceSearchComponent (
        searchQuery = searchQuery,
        cityList = filteredProvinceList,
        onSearchResult = {
            searchQuery = it
        },
        onBackClick = {
            navigationManager.navigateBack()
        },
    ) {province->
        updateProvince(province)
        navigationManager.navigateBack()
    }
}