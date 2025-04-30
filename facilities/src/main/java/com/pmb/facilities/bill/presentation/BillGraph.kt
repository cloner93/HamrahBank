package com.pmb.facilities.bill.presentation

import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.pmb.facilities.bill.domain.bill_id.entity.BillIdEntity
import com.pmb.facilities.bill.presentation.bill.BillScreen
import com.pmb.facilities.bill.presentation.bill.viewModel.BillViewModel
import com.pmb.facilities.bill.presentation.bill_confirm.BillConfirmScreen
import com.pmb.facilities.bill.presentation.bill_identify.BillIdentifyScreen
import com.pmb.facilities.bill.presentation.bill_identify.viewModel.BillIdentifyViewModel
import com.pmb.facilities.bill.presentation.bills_history.BillsHistoryScreen
import com.pmb.facilities.bill.presentation.bills_history.viewModel.BillsHistoryViewModel
import com.pmb.navigation.manager.navigationManager
import com.pmb.navigation.moduleScreen.BillScreens

fun NavGraphBuilder.billGraphHandler() {
    navigation(
        route = BillScreens.BillGraph.route,
        startDestination = BillScreens.Bill.route
    ) {
        composable(route = BillScreens.Bill.route) {
            val sharedViewModel =
                it.navigationManager.retrieveSharedViewModel<BillSharedViewModel>(
                    screen = BillScreens.BillGraph, navBackStackEntry = it
                )
            val sharedState = sharedViewModel.state.collectAsStateWithLifecycle()
            BillScreen(
                sharedState = sharedState,
                viewModel = hiltViewModel<BillViewModel>()
            ) { childState ->
                sharedViewModel.updateState {
                    sharedState.value.copy(
                        billType = childState.billType,
                        billIdEntity = if (childState.billIdEntity != null) {
                            childState.billIdEntity
                        } else if (childState.teleCommunicationEntity != null) {
                            BillIdEntity(
                                billImage = childState.teleCommunicationEntity.billImage,
                                billTitle = childState.teleCommunicationEntity.billTitle,
                                billId = childState.teleCommunicationEntity.phoneNumber,
                                billPrice = 1460000.toDouble(),
                                billPriceTitle = "مبلغ بدهی",
                            )
                        } else null
                    )
                }
            }
        }
        composable(route = BillScreens.BillsHistory.route) {
            val sharedViewModel =
                it.navigationManager.retrieveSharedViewModel<BillSharedViewModel>(
                    screen = BillScreens.BillGraph, navBackStackEntry = it
                )
            val sharedState = sharedViewModel.state.collectAsStateWithLifecycle()
            BillsHistoryScreen(
                sharedState = sharedState,
                viewModel = hiltViewModel<BillsHistoryViewModel>()
            ) {
            }
        }
        composable(route = BillScreens.BillIdentify.route) {
            val sharedViewModel =
                it.navigationManager.retrieveSharedViewModel<BillSharedViewModel>(
                    screen = BillScreens.BillGraph, navBackStackEntry = it
                )
            val sharedState = sharedViewModel.state.collectAsStateWithLifecycle()
            BillIdentifyScreen(
                sharedState = sharedState,
                viewModel = hiltViewModel<BillIdentifyViewModel>()
            ) { childState ->
                if (childState.billIdEntity != null) {
                    sharedViewModel.updateState {
                        sharedState.value.copy(
                            billIdEntity = childState.billIdEntity
                        )
                    }
                } else if (childState.teleCommunicationEntity != null) {
                    sharedViewModel.updateState {
                        sharedState.value.copy(
                            billIdEntity = BillIdEntity(
                                billImage = childState.teleCommunicationEntity.billImage,
                                billTitle = childState.teleCommunicationEntity.billTitle,
                                billId = childState.teleCommunicationEntity.phoneNumber,
                                billPrice = 1460000.toDouble(),
                                billPriceTitle = "مبلغ بدهی",
                            )
                        )
                    }
                }
            }

        }
        composable(route = BillScreens.BillPurchaseConfirm.route) {
            val sharedViewModel =
                it.navigationManager.retrieveSharedViewModel<BillSharedViewModel>(
                    screen = BillScreens.BillGraph, navBackStackEntry = it
                )
            val sharedState = sharedViewModel.state.collectAsStateWithLifecycle()
            BillConfirmScreen(
                sharedState = sharedState
            )
        }
    }
}