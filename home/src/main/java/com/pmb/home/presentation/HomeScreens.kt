package com.pmb.home.presentation

import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.pmb.facilities.charge.presentation.chargeGraphHandler
import com.pmb.home.presentation.home.HomeScreen
import com.pmb.home.presentation.home.viewModel.HomeViewModel
import com.pmb.navigation.moduleScreen.ChargeScreens
import com.pmb.navigation.moduleScreen.HomeScreens


fun NavGraphBuilder.homeScreensHandle() {
    navigation(
        route = HomeScreens.HomeGraph.route,
        startDestination = HomeScreens.Home.route
    ) {
        composable(route = HomeScreens.Home.route) {
            HomeScreen(
                viewModel = hiltViewModel<HomeViewModel>()
            )
        }
        chargeGraphHandler()
    }
}