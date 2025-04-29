package com.pmb.account.presentation.deposits

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.pmb.account.R
import com.pmb.account.presentation.component.DepositCarouselWidget
import com.pmb.account.presentation.component.ShareDepositBottomSheet
import com.pmb.account.presentation.component.ShareDepositBottomSheetContent
import com.pmb.account.presentation.component.TransactionRow
import com.pmb.account.presentation.deposits.viewmodel.DepositsViewActions
import com.pmb.account.presentation.deposits.viewmodel.DepositsViewEvents
import com.pmb.account.presentation.deposits.viewmodel.DepositsViewModel
import com.pmb.account.utils.mapToDepositMenu
import com.pmb.account.utils.mapToDepositModel
import com.pmb.ballon.component.DepositBottomSheet
import com.pmb.ballon.component.MenuBottomSheet
import com.pmb.ballon.component.MenuItem
import com.pmb.ballon.component.MenuItemDefaults
import com.pmb.ballon.component.base.AppButtonIcon
import com.pmb.ballon.component.base.RoundedTopColumn
import com.pmb.ballon.models.IconStyle
import com.pmb.ballon.models.MenuSheetModel
import com.pmb.ballon.models.TextStyle
import com.pmb.ballon.ui.theme.AppTheme
import com.pmb.navigation.manager.LocalNavigationManager
import com.pmb.navigation.moduleScreen.AccountScreens

@Composable
fun DepositsScreen() {
    val viewModel = hiltViewModel<DepositsViewModel>()
    val viewState by viewModel.viewState.collectAsState()
    val navigationManager = LocalNavigationManager.current
    val menuItems = listOf(
        MenuSheetModel(
            title = stringResource(R.string.select_for_main_deposit),
            icon = com.pmb.ballon.R.drawable.ic_pin,
            onClicked = {

            }
        ), MenuSheetModel(
            title = stringResource(R.string.cards_connected_to_the_deposit),
            icon = com.pmb.ballon.R.drawable.ic_credit_cards,
            onClicked = {

            }
        ), MenuSheetModel(
            title = stringResource(R.string.request_to_issue_a_card_for_deposit),
            icon = com.pmb.ballon.R.drawable.ic_credit_card,
            onClicked = {

            }
        ), MenuSheetModel(
            title = stringResource(R.string.edit_deposit_title),
            icon = com.pmb.ballon.R.drawable.ic_edit,
            onClicked = {

            }
        )
    )
    val context = LocalContext.current

    LaunchedEffect(Unit) {
        viewModel.viewEvent.collect { event ->
            when (event) {
                is DepositsViewEvents.AmountVisibilityChanged -> {
                    Toast.makeText(context, "TODO ->$event", Toast.LENGTH_LONG).show()

                }

                is DepositsViewEvents.DepositSelectionChanged -> {
//                    Toast.makeText(context, "TODO ->$event", Toast.LENGTH_LONG).show()

                }

                is DepositsViewEvents.NavigateBack -> {
                    Toast.makeText(context, "TODO ->$event", Toast.LENGTH_LONG).show()

                }

                is DepositsViewEvents.NavigateToTransactionDetails -> {
                    Toast.makeText(context, "TODO ->$event", Toast.LENGTH_LONG).show()

                }

                is DepositsViewEvents.NavigateToTransactionsList -> {
                    navigationManager.navigate(AccountScreens.Transactions)
                }

                is DepositsViewEvents.RefreshCompleted -> {
                    Toast.makeText(context, "TODO ->$event", Toast.LENGTH_LONG).show()

                }

                is DepositsViewEvents.ShowError -> {
                    Toast.makeText(context, "TODO ->$event", Toast.LENGTH_LONG).show()

                }

                is DepositsViewEvents.ShowToast -> {
                    Toast.makeText(context, "TODO ->$event", Toast.LENGTH_LONG).show()

                }

                is DepositsViewEvents.NavigateToBalanceScreen -> {
                    navigationManager.navigate(AccountScreens.Balance)
                }
            }
        }
    }

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(350.dp)
            .background(
                brush = Brush.verticalGradient(
                    colors = listOf(
                        Color(0xFFDF445F), Color(0xFFC11332)
                    )
                )
            )
    ) {
        Image(painter = painterResource(R.drawable.bg_mellat_logo), contentDescription = null)
    }

    Column {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 25.dp)
                .height(92.dp)
                .padding(horizontal = 8.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            AppButtonIcon(
                icon = com.pmb.ballon.R.drawable.ic_help,
                style = IconStyle(tint = AppTheme.colorScheme.onForegroundNeutralDefault),
                onClick = {
                    viewModel.handle(DepositsViewActions.ShowHelp)
                })


            AppButtonIcon(
                icon = com.pmb.ballon.R.drawable.ic_coins,
                style = IconStyle(tint = AppTheme.colorScheme.onForegroundNeutralDefault),
                onClick = {
                    viewModel.handle(DepositsViewActions.NavigateToBalanceScreen)
                })
        }

        DepositCarouselWidget(
            depositModel = viewState.selectedDeposit,
            onMoreClick = { viewModel.handle(DepositsViewActions.ShowDepositMoreActionBottomSheet) },
            onAmountVisibilityClick = { viewModel.handle(DepositsViewActions.SetAmountVisibility) },
            onDepositListChipsClick = { viewModel.handle(DepositsViewActions.ShowDepositListBottomSheet) },
            isAmountVisible = viewState.isAmountVisible
        )

        Spacer(modifier = Modifier.height(32.dp))

        RoundedTopColumn(
            modifier = Modifier.fillMaxSize()
        ) {

            MenuItem(
                modifier = Modifier
                    .clip(RoundedCornerShape(16.dp))
                    .background(color = AppTheme.colorScheme.backgroundTintNeutralDefault),
                title = stringResource(R.string.deposit_card_sheba),
                horizontalDividerPadding = MenuItemDefaults.horizontalDividerPadding.copy(vertical = 0.dp),
                startIcon = com.pmb.ballon.R.drawable.ic_racket,
                titleStyle = TextStyle(
                    color = AppTheme.colorScheme.foregroundNeutralDefault,
                    typography = AppTheme.typography.buttonLarge
                ),
                startIconStyle = IconStyle(tint = AppTheme.colorScheme.onBackgroundNeutralSubdued),
                clickable = false,
                onItemClick = {
                    viewModel.handle(DepositsViewActions.ShowShareBottomSheet)
                })
            Spacer(modifier = Modifier.height(12.dp))
            MenuItem(
                modifier = Modifier
                    .clip(RoundedCornerShape(16.dp))
                    .background(
                        color = AppTheme.colorScheme.backgroundTintNeutralDefault,
                        shape = RoundedCornerShape(16.dp)
                    ),
                title = stringResource(R.string.transactions),
                startIcon = com.pmb.ballon.R.drawable.ic_bar_chart_vertical,
                endIcon = com.pmb.ballon.R.drawable.ic_arrow_left,
                titleStyle = TextStyle(
                    color = AppTheme.colorScheme.foregroundNeutralDefault,
                    typography = AppTheme.typography.buttonLarge
                ),
                startIconStyle = IconStyle(tint = AppTheme.colorScheme.onBackgroundNeutralSubdued),
                endIconStyle = IconStyle(tint = AppTheme.colorScheme.onBackgroundNeutralSubdued),
                clickable = false,
                onItemClick = {
                    viewModel.handle(DepositsViewActions.NavigateToTransactionScreen)
                })

            Spacer(modifier = Modifier.height(24.dp))

            LazyColumn {
                items(viewState.transactions.size) { item ->
                    Spacer(modifier = Modifier.height(12.dp))
                    TransactionRow(viewState.transactions[item], viewState.isAmountVisible) {
                        viewModel.handle(DepositsViewActions.NavigateToTransactionDetailScreen)
                    }
                    Spacer(modifier = Modifier.height(12.dp))
                }
            }
        }
    }

    if (viewState.showShareDepositInfoBottomSheet)
        ShareDepositBottomSheet(
            content = {
                ShareDepositBottomSheetContent(
                    info = viewState.selectedDeposit!!,
                    onCopyAllClick = {
                        viewModel.handle(DepositsViewActions.CloseShareBottomSheet(it)) // TODO: clipboard
                    },
                    onShareClick = {
                        viewModel.handle(DepositsViewActions.CloseShareBottomSheet(it)) // TODO: share menu
                    }
                )
            },
            onDismiss = {
                viewModel.handle(DepositsViewActions.CloseShareBottomSheet(null))
            }
        )

    if (viewState.showMoreBottomSheet)
        MenuBottomSheet(
            title = viewState.selectedDeposit?.title,
            items = menuItems,
            onDismiss = { viewModel.handle(DepositsViewActions.CloseDepositMoreActionBottomSheet) }
        )

    if (viewState.showDepositListBottomSheet)
        DepositBottomSheet(
            title = "سپرده ها",
            items = viewState.deposits.mapToDepositMenu(),
            onDismiss = {
                viewModel.handle(DepositsViewActions.CloseDepositListBottomSheet(null))
            }
        ) {
            viewModel.handle(
                DepositsViewActions.CloseDepositListBottomSheet(
                    it.mapToDepositModel()
                )
            )
        }

}