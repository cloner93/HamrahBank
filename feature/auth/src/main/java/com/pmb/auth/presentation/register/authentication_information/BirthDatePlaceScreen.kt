package com.pmb.auth.presentation.register.authentication_information

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshotFlow
import com.pmb.auth.presentation.component.CitySearchComponent
import com.pmb.auth.presentation.register.RegisterSharedViewState
import com.pmb.domain.model.openAccount.accountVerifyCode.CityOfBirthInfoDTO
import com.pmb.navigation.manager.LocalNavigationManager
import com.pmb.navigation.manager.NavigationManager
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.debounce

@Composable
fun BirthDatePlaceScreen(
    sharedState: RegisterSharedViewState,
    updateState: (CityOfBirthInfoDTO) -> Unit
){
    val navigationManager: NavigationManager = LocalNavigationManager.current
    var searchQuery by remember { mutableStateOf("") }
    var cityList by remember { mutableStateOf(sharedState.verifyCodeResponse?.cityOfBirthInfoDTOList) }
    LaunchedEffect(searchQuery) {
        snapshotFlow { searchQuery }.debounce(300L).collectLatest {
            cityList = if (it.isNotEmpty() && it.length >= 2) {
                sharedState.verifyCodeResponse?.cityOfBirthInfoDTOList?.filter {cities-> cities.cityName?.contains(it) == true }
            } else {
                sharedState.verifyCodeResponse?.cityOfBirthInfoDTOList
            }
        }
    }
    CitySearchComponent(
        searchQuery = searchQuery,
        cityList = cityList,
        onSearchResult = {
            searchQuery = it
        },
        onBackClick = {
            navigationManager.navigateBack()
        },
    ) { cityName ->
        updateState(cityName)
        navigationManager.navigateBack()
    }
}