package com.pmb.auth.presentation.register.deposit_information

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshotFlow
import com.pmb.auth.presentation.component.CityListSearchComponent
import com.pmb.auth.presentation.register.RegisterSharedViewState
import com.pmb.domain.model.openAccount.cityName.City
import com.pmb.navigation.manager.LocalNavigationManager
import com.pmb.navigation.manager.NavigationManager
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.debounce

@Composable
fun CitySearchScreen(
    sharedViewState: RegisterSharedViewState,
    updateState:(City)-> Unit
){
    val navigationManager: NavigationManager = LocalNavigationManager.current
    var searchQuery by remember { mutableStateOf("") }
    var filteredCityList by remember { mutableStateOf(sharedViewState.cityList)  }
    LaunchedEffect(searchQuery) {
        snapshotFlow { searchQuery }.debounce(300L).collectLatest {
            filteredCityList = if (it.isNotEmpty() && it.length >= 2) {
                sharedViewState.cityList?.filter {province -> province.cityName?.contains(it) == true }
            } else {
                sharedViewState.cityList
            }
        }
    }

    CityListSearchComponent(
        searchQuery = searchQuery,
        cityList = filteredCityList,
        onSearchResult = {
            searchQuery = it
        },
        onBackClick = {
            navigationManager.navigateBack()
        },
        onCityClick = {
            updateState(it)
            navigationManager.navigateBack()
        }
    )
}