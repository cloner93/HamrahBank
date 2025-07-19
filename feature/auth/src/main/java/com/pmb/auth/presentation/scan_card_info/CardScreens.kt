package com.pmb.auth.presentation.scan_card_info

import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.pmb.auth.presentation.scan_card_info.card_confirm.CardInformationConfirmScreen
import com.pmb.auth.presentation.scan_card_info.card_confirm.viewModel.CardInformationConfirmViewModel
import com.pmb.auth.presentation.scan_card_info.card_info.CardInfoScreen
import com.pmb.auth.presentation.scan_card_info.card_info.viewModel.CardInfoViewModel
import com.pmb.auth.presentation.scan_card_info.scan_card.ScanCardScreen
import com.pmb.auth.presentation.scan_card_info.scan_card.viewModel.ScanCardViewModel
import com.pmb.navigation.moduleScreen.CardScreens

fun NavGraphBuilder.cardScreenHandler() {
    navigation(
        route = CardScreens.CardGraph.route,
        startDestination = CardScreens.CardInformation.route
    ) {
        composable(
            route = CardScreens.CardInformation.route
        ) {
            CardInfoScreen(
                viewModel = hiltViewModel<CardInfoViewModel>(),
            )
        }
        composable(route = CardScreens.ScanCard.route) {
            ScanCardScreen(
                viewModel = hiltViewModel<ScanCardViewModel>()
            )
        }
        composable(route = CardScreens.CardInformationConfirmation.route) {
            CardInformationConfirmScreen(
                viewModel = hiltViewModel<CardInformationConfirmViewModel>()
            )
        }
    }
}
