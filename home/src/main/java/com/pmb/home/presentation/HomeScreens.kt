package com.pmb.home.presentation

import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.pmb.home.presentation.home.HomeScreen
import com.pmb.home.presentation.home.viewModel.HomeViewModel
import com.pmb.navigation.moduleScreen.HomeScreens


fun NavGraphBuilder.homeScreensHandle() {
    composable(route = HomeScreens.Home.route) {
        HomeScreen(
            viewModel = hiltViewModel<HomeViewModel>()
        )
    }
}