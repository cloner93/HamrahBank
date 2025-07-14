package com.pmb.auth.presentation.activation

import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.pmb.auth.AuthSharedViewModel
import com.pmb.auth.presentation.activation.activate.ActivationScreen
import com.pmb.auth.presentation.activation.activate.viewModel.ActivationViewModel
import com.pmb.auth.presentation.activation.activation_tax_details.ActivationTaxDetailsScreen
import com.pmb.auth.presentation.activation.activation_tax_details.viewModel.ActivationTaxDetailsViewModel
import com.pmb.navigation.manager.navigationManager
import com.pmb.navigation.moduleScreen.ActivationScreens
import com.pmb.navigation.moduleScreen.AuthScreens

fun NavGraphBuilder.activationScreenHandler() {
    navigation(
        route = ActivationScreens.ActivationGraph.route,
        startDestination = ActivationScreens.Activation.route
    ) {

        composable(route = ActivationScreens.Activation.route) {
            ActivationScreen(viewModel = hiltViewModel<ActivationViewModel>())
        }
        composable(route = ActivationScreens.ActivationTaxDetailsScreen.route) {
            val sharedViewModel =
                it.navigationManager.retrieveSharedViewModel<AuthSharedViewModel>(
                    screen = AuthScreens.AuthGraph, navBackStackEntry = it
                )
            val sharedState = sharedViewModel.state.collectAsStateWithLifecycle()
            ActivationTaxDetailsScreen(
                viewModel = hiltViewModel<ActivationTaxDetailsViewModel>(),
                updateSharedState = {
                    sharedViewModel.updateState {
                        sharedState.value.copy(
                            actionType = it
                        )
                    }
                }
            )
        }
    }
}
