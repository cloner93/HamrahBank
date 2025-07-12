package com.pmb.facilities.charge.presentation

import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.pmb.facilities.charge.presentation.charge.ChargeScreen
import com.pmb.facilities.charge.presentation.charge.ChargeViewModel
import com.pmb.facilities.charge.presentation.charge_confirm.ChargeConfirmScreen
import com.pmb.facilities.charge.presentation.charge_history.ChargeHistoryScreen
import com.pmb.facilities.charge.presentation.charge_history.viewModel.ChargeHistoryViewModel
import com.pmb.facilities.charge.presentation.charge_receipt.ChargeReceiptScreen
import com.pmb.facilities.charge.presentation.choose_charge_price.ChooseChargePriceScreen
import com.pmb.facilities.charge.presentation.choose_charge_price.viewModel.ChooseChargePriceViewModel
import com.pmb.facilities.charge.presentation.purchase_charge.PurchaseChargeScreen
import com.pmb.facilities.charge.presentation.purchase_charge.PurchaseChargeViewModel
import com.pmb.navigation.manager.navigationManager
import com.pmb.navigation.moduleScreen.ChargeScreens

fun NavGraphBuilder.chargeGraphHandler() {
    navigation(
        route = ChargeScreens.ChargeGraph.route,
        startDestination = ChargeScreens.Charge.route
    ) {
        composable(route = ChargeScreens.Charge.route) {
            val sharedViewModel =
                it.navigationManager.retrieveSharedViewModel<ChargeSharedViewModel>(
                    screen = ChargeScreens.ChargeGraph, navBackStackEntry = it
                )
            val sharedState = sharedViewModel.state.collectAsStateWithLifecycle()
            ChargeScreen(
                viewModel = hiltViewModel<ChargeViewModel>(),
                sharedState = sharedState
            ) { childState ->
                sharedViewModel.updateState {
                    sharedState.value.copy(
                        simNumber = childState.selectedSim,
                        operator = childState.operator
                    )
                }
            }
        }
        composable(route = ChargeScreens.PurchaseCharge.route) {
            val sharedViewModel =
                it.navigationManager.retrieveSharedViewModel<ChargeSharedViewModel>(
                    screen = ChargeScreens.ChargeGraph, navBackStackEntry = it
                )
            val sharedState = sharedViewModel.state.collectAsStateWithLifecycle()
            PurchaseChargeScreen(
                viewModel = hiltViewModel<PurchaseChargeViewModel>(),
                sharedState = sharedState
            ) { childState ->
                sharedViewModel.updateState {
                    sharedState.value.copy(
                        simNumber = childState.mobile,
                        operator = childState.operator
                    )
                }
            }
        }
        composable(route = ChargeScreens.ChargeHistory.route) {
            val sharedViewModel =
                it.navigationManager.retrieveSharedViewModel<ChargeSharedViewModel>(
                    screen = ChargeScreens.ChargeGraph, navBackStackEntry = it
                )
            sharedViewModel.state.collectAsStateWithLifecycle()
            ChargeHistoryScreen(
                viewModel = hiltViewModel<ChargeHistoryViewModel>()
            )
        }
        composable(route = ChargeScreens.ChooseChargePrice.route) {
            val sharedViewModel =
                it.navigationManager.retrieveSharedViewModel<ChargeSharedViewModel>(
                    screen = ChargeScreens.ChargeGraph, navBackStackEntry = it
                )
            val sharedState = sharedViewModel.state.collectAsStateWithLifecycle()
            ChooseChargePriceScreen(
                sharedState = sharedState,
                viewModel = hiltViewModel<ChooseChargePriceViewModel>()
            ) { childState ->
                sharedViewModel.updateState {
                    sharedState.value.copy(
                        chargePrice = childState.selectedPrice
                    )
                }
            }
        }
        composable(route = ChargeScreens.ChargeConfirm.route) {
            val sharedViewModel =
                it.navigationManager.retrieveSharedViewModel<ChargeSharedViewModel>(
                    screen = ChargeScreens.ChargeGraph, navBackStackEntry = it
                )
            sharedViewModel.state.collectAsStateWithLifecycle()
            ChargeConfirmScreen()
        }
        composable(route = ChargeScreens.ChargeConfirm.route) {
            val sharedViewModel =
                it.navigationManager.retrieveSharedViewModel<ChargeSharedViewModel>(
                    screen = ChargeScreens.ChargeGraph, navBackStackEntry = it
                )
            sharedViewModel.state.collectAsStateWithLifecycle()
            ChargeConfirmScreen()
        }
        composable(route = ChargeScreens.ChargeReceipt.route) {
            val sharedViewModel =
                it.navigationManager.retrieveSharedViewModel<ChargeSharedViewModel>(
                    screen = ChargeScreens.ChargeGraph, navBackStackEntry = it
                )
            sharedViewModel.state.collectAsStateWithLifecycle()
            ChargeReceiptScreen()
        }
    }
}