package com.pmb.auth.presentation.activation

import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.pmb.auth.presentation.activation.activate.ActivationScreen
import com.pmb.auth.presentation.activation.activate.viewModel.ActivationViewModel
import com.pmb.auth.presentation.activation.activation_tax_details.ActivationTaxDetailsScreen
import com.pmb.auth.presentation.activation.activation_tax_details.viewModel.ActivationTaxDetailsViewModel
import com.pmb.navigation.moduleScreen.ActivationScreens

fun NavGraphBuilder.activationScreenHandler() {
    navigation(
        route = ActivationScreens.ActivationGraph.route,
        startDestination = ActivationScreens.Activation.route
    ) {

        composable(route = ActivationScreens.Activation.route) {
            ActivationScreen(viewModel = hiltViewModel<ActivationViewModel>())
        }
        composable(route = ActivationScreens.ActivationTaxDetailsScreen.route) {
            ActivationTaxDetailsScreen(
                viewModel = hiltViewModel<ActivationTaxDetailsViewModel>()
            )
        }
    }
}
