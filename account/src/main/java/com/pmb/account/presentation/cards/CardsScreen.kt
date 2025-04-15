package com.pmb.account.presentation.cards

import android.annotation.SuppressLint
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.FabPosition
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.pmb.account.R
import com.pmb.account.presentation.AccountScreens
import com.pmb.account.presentation.cards.viewmodel.CardsViewActions
import com.pmb.account.presentation.cards.viewmodel.CardsViewEvents
import com.pmb.account.presentation.cards.viewmodel.CardsViewModel
import com.pmb.account.presentation.component.CardInfo
import com.pmb.ballon.component.MenuBottomSheet
import com.pmb.ballon.component.base.AppButtonIcon
import com.pmb.ballon.component.base.AppFAB
import com.pmb.ballon.models.IconStyle
import com.pmb.ballon.models.MenuSheetModel
import com.pmb.ballon.ui.theme.AppTheme
import com.pmb.core.presentation.NavigationManager

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun CardsScreen(navigationManager: NavigationManager) {
    val viewModel = hiltViewModel<CardsViewModel>()
    val viewState by viewModel.viewState.collectAsState()

    val context = LocalContext.current

    LaunchedEffect(Unit) {
        viewModel.viewEvent.collect { event ->
            when (event) {
                CardsViewEvents.NavigateToBalance -> {
                    navigationManager.navigate(AccountScreens.Balance)
                }

                is CardsViewEvents.ShowError -> {

                    Toast.makeText(context, event.error, Toast.LENGTH_SHORT).show()
                }

                CardsViewEvents.ShowHelp -> {}
            }
        }
    }

    Scaffold(
        floatingActionButton = {
            AppFAB(
                icon = com.pmb.ballon.R.drawable.ic_add,
                containerColor = AppTheme.colorScheme.foregroundNeutralDefault,
                contentColor = AppTheme.colorScheme.onForegroundNeutralDefault,
                onClick = {
                    viewModel.handle(CardsViewActions.ShowFabBottomSheet)
                })
        },
        floatingActionButtonPosition = FabPosition.Start
    ) {

        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(color = AppTheme.colorScheme.background1Neutral)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(92.dp)
                    .padding(horizontal = 8.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                AppButtonIcon(
                    icon = com.pmb.ballon.R.drawable.ic_help,
                    style = IconStyle(tint = Color.Black),
                    onClick = {
                        viewModel.handle(CardsViewActions.ShowHelp)
                    })

                AppButtonIcon(
                    icon = com.pmb.ballon.R.drawable.ic_coins,
                    style = IconStyle(tint = Color.Black),
                    onClick = {
                        viewModel.handle(CardsViewActions.NavigateToBalanceScreen)
                    })
            }

            LazyColumn(
                modifier = Modifier
                    .fillMaxSize(),
                verticalArrangement = Arrangement.spacedBy(16.dp),
                contentPadding = PaddingValues(20.dp)
            ) {
                items(viewState.cards.size) { item ->
                    CardInfo(item = viewState.cards[item], onClick = {
                        viewModel.handle(
                            CardsViewActions.ShowDetailCardBottomSheetBottomSheet(
                                viewState.cards[item]
                            )
                        )
                    })
                }
            }
        }
    }

    val menuItems = listOf(
        MenuSheetModel(
            title = stringResource(R.string.card_reissue),
            icon = com.pmb.ballon.R.drawable.ic_credit_card_refresh_reload
        ),
        MenuSheetModel(
            title = stringResource(R.string.card_connection_disconnection),
            icon = com.pmb.ballon.R.drawable.ic_credit_card_cross_delete_circle
        ),
        MenuSheetModel(
            title = stringResource(R.string.deactivating_the_card),
            icon = com.pmb.ballon.R.drawable.ic_credit_card_lock_circe
        ),
        MenuSheetModel(
            title = stringResource(R.string.password_management),
            icon = com.pmb.ballon.R.drawable.ic_credit_card_lock_circe
        ),
        MenuSheetModel(
            title = stringResource(R.string.cardless_withdrawal),
            icon = com.pmb.ballon.R.drawable.ic_key_password
        ),
        MenuSheetModel(
            title = stringResource(R.string.card_cancellation),
            icon = com.pmb.ballon.R.drawable.ic_money_banknote_hand,
            textColor = { Color(0xFFBA1A1A) },
            iconTint = { Color(0xFFBA1A1A) }
        )
    )

    if (viewState.showDetailCardBottomSheet) MenuBottomSheet(
        title = viewState.selectedCard?.cardNumber!!,
        items = menuItems,
        onDismiss = {
            viewModel.handle(CardsViewActions.ShowDetailCardBottomSheetBottomSheet(null))
        },
        onSelect = { })

    val fabItems = listOf(
        MenuSheetModel(
            title = stringResource(R.string.add_a_new_bank_card),
            icon = com.pmb.ballon.R.drawable.ic_credit_card
        ),
        MenuSheetModel(
            title = stringResource(R.string.adding_and_activating_a_bank_card),
            icon = com.pmb.ballon.R.drawable.ic_add,
            iconTint = { AppTheme.colorScheme.iconColor }
        )
    )

    if (viewState.showFabBottomSheet) MenuBottomSheet(
        items = fabItems,
        onDismiss = {
            viewModel.handle(CardsViewActions.ShowFabBottomSheet)
        },
        onSelect = {

        })
}