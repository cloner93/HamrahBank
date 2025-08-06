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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.pmb.account.R
import com.pmb.account.presentation.cards.viewmodel.CardsViewActions
import com.pmb.account.presentation.cards.viewmodel.CardsViewEvents
import com.pmb.account.presentation.cards.viewmodel.CardsViewModel
import com.pmb.account.presentation.component.CardInfo
import com.pmb.ballon.component.EmptyList
import com.pmb.ballon.component.GuideBottomSheet
import com.pmb.ballon.component.MenuBottomSheet
import com.pmb.ballon.component.base.AppButtonIcon
import com.pmb.ballon.component.base.AppFAB
import com.pmb.ballon.component.base.IconType
import com.pmb.ballon.models.IconStyle
import com.pmb.ballon.models.MenuSheetModel
import com.pmb.ballon.ui.theme.AppTheme
import com.pmb.navigation.manager.LocalNavigationManager
import com.pmb.navigation.moduleScreen.AccountScreens

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun CardsScreen() {
    val viewModel = hiltViewModel<CardsViewModel>()
    val viewState by viewModel.viewState.collectAsState()
    val navigationManager = LocalNavigationManager.current
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
//                    .padding(top = 25.dp)
                    .height(92.dp)
                    .padding(horizontal = 8.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                AppButtonIcon(
                    icon = com.pmb.ballon.R.drawable.ic_help,
                    style = IconStyle(tint = AppTheme.colorScheme.foregroundNeutralDefault),
                    onClick = {
                        viewModel.handle(CardsViewActions.ShowGuideBottomSheet)
                    })

                AppButtonIcon(
                    icon = com.pmb.ballon.R.drawable.ic_coins,
                    style = IconStyle(tint = AppTheme.colorScheme.foregroundNeutralDefault),
                    onClick = {
                        viewModel.handle(CardsViewActions.NavigateToBalanceScreen)
                    })
            }

            if (viewState.cards.isNotEmpty())
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
            else
                EmptyList(
                    modifier = Modifier.fillMaxSize(),
                    iconType = IconType.Painter(painterResource(R.drawable.ic_add_card)),
                    message = "هنوز کارتی اضافه نکرده\u200Cاید.\n" +
                            "روی دکمه + در پایین صفحه بزنید و کارت بانکی خود را اضافه کنید."
                )
        }
    }

    val menuItems = listOf(
        MenuSheetModel(
            title = stringResource(R.string.card_reissue),
            icon = com.pmb.ballon.R.drawable.ic_credit_card_refresh_reload,
            onClicked = {

            }
        ),
        MenuSheetModel(
            title = stringResource(R.string.card_connection_disconnection),
            icon = com.pmb.ballon.R.drawable.ic_credit_card_cross_delete_circle,
            onClicked = {

            }
        ),
        MenuSheetModel(
            title = stringResource(R.string.deactivating_the_card),
            icon = com.pmb.ballon.R.drawable.ic_disable_card,
            onClicked = {

            }
        ),
        MenuSheetModel(
            title = stringResource(R.string.password_management),
            icon = com.pmb.ballon.R.drawable.ic_password_manage,
            onClicked = {

            }
        ),
        MenuSheetModel(
            title = stringResource(R.string.card_cancellation),
            icon = com.pmb.ballon.R.drawable.ic_trash_delete_red,
            textColor = { Color(0xFFBA1A1A) },
            iconTint = { Color(0xFFBA1A1A) },
            onClicked = {

            }
        )
    )

    if (viewState.showDetailCardBottomSheet) MenuBottomSheet(
        title = viewState.selectedCard?.cardNumber!!,
        items = menuItems,
        onDismiss = {
            viewModel.handle(CardsViewActions.ShowDetailCardBottomSheetBottomSheet(null))
        })

    val fabItems = listOf(
        MenuSheetModel(
            title = stringResource(R.string.add_a_new_bank_card),
            icon = com.pmb.ballon.R.drawable.ic_credit_card,
            onClicked = {

            }
        ),
        MenuSheetModel(
            title = stringResource(R.string.adding_and_activating_a_bank_card),
            icon = com.pmb.ballon.R.drawable.ic_add,
            onClicked = {

            }
        )
    )

    if (viewState.showFabBottomSheet) MenuBottomSheet(
        items = fabItems,
        onDismiss = {
            viewModel.handle(CardsViewActions.ShowFabBottomSheet)
        })

    if (viewState.isGuideBottomSheetVisible) {
        GuideBottomSheet {
            viewModel.handle(CardsViewActions.CloseGuideBottomSheet)
        }
    }
}