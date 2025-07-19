package com.pmb.account.presentation.transactions

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.pmb.account.R
import com.pmb.account.presentation.component.CustomAppTopBar
import com.pmb.account.presentation.transactions.filterScreen.viewmodel.entity.TransactionFilter
import com.pmb.account.presentation.transactions.viewmodel.TransactionsViewActions
import com.pmb.account.presentation.transactions.viewmodel.TransactionsViewEvents
import com.pmb.account.presentation.transactions.viewmodel.TransactionsViewModel
import com.pmb.account.utils.mapToDepositMenu
import com.pmb.account.utils.mapToDepositModel
import com.pmb.ballon.component.DepositBottomSheet
import com.pmb.ballon.component.DynamicTabSelector
import com.pmb.ballon.component.EmptyList
import com.pmb.ballon.component.base.AppContent
import com.pmb.ballon.component.base.ChipWithIcon
import com.pmb.ballon.component.base.ClickableIcon
import com.pmb.ballon.component.base.IconType
import com.pmb.ballon.models.IconStyle
import com.pmb.ballon.ui.theme.AppTheme
import com.pmb.core.utils.CollectAsEffect
import com.pmb.navigation.manager.LocalNavigationManager
import com.pmb.navigation.moduleScreen.AccountScreens

/**
TODO checkList TransactionsScreen.kt
 *
 * - apply filter on shown list
 * - open each item on receipt screen
 * - handle search button
 * - change change icon of transactions
 * - apply paddings
 * - pass filter to filter screen if TransactionFilter nonNull
 */

@Composable
fun TransactionsScreen() {
    val viewModel = hiltViewModel<TransactionsViewModel>()
    val viewState by viewModel.viewState.collectAsState()
    val navigationManager = LocalNavigationManager.current
    val selectedOption = remember { mutableIntStateOf(0) }
    val optionTexts = listOf("همه", "برداشت", "واریز")

    LaunchedEffect(Unit) {
        viewModel.viewEvent.collect { event ->
            when (event) {
                is TransactionsViewEvents.ShowError -> {

                }

                is TransactionsViewEvents.ShowToast -> {

                }

                is TransactionsViewEvents.DepositSelectionChanged -> {

                }

                TransactionsViewEvents.NavigateBack -> {
                    navigationManager.navigateBack()
                }

                TransactionsViewEvents.NavigateToDepositStatementScreen -> {
                    navigationManager.navigate(AccountScreens.DepositStatement)
                }

                TransactionsViewEvents.NavigateToTransactionFilterScreen -> {
                    navigationManager.navigate(AccountScreens.TransactionsFilter)
                }

                is TransactionsViewEvents.NavigateToTransactionInfoScreen -> {
                    navigationManager.navigateWithString(
                        AccountScreens.TransactionReceipt.createRoute(
                            viewState.selectedDeposit?.depositNumber ?: "",
                            event.transaction
                        )
                    )
                }

                TransactionsViewEvents.NavigateToTransactionSearchScreen -> {
                    navigationManager.navigateWithString(
                        AccountScreens.TransactionSearch.createRoute(
                            viewState.selectedDeposit?.depositNumber!!
                        )
                    )
                }
            }
        }
    }

    navigationManager.getCurrentScreenFlowData<TransactionFilter?>("transactionFilter", null)
        ?.CollectAsEffect {
            it.takeIf {
                it != null
            }?.also {
                viewModel.handle(TransactionsViewActions.UpdateFilterList(it))
            }
        }

    AppContent(
        modifier = Modifier.padding(horizontal = 16.dp),
        scrollState = null,
        topBar = {
            CustomAppTopBar(
                startIcon = ClickableIcon(
                    IconType.ImageVector(Icons.Filled.ArrowForward),
                    tint = AppTheme.colorScheme.onBackgroundNeutralDefault,
                    onClick = {
                        viewModel.handle(TransactionsViewActions.NavigateBack)
                    }),
                endIcon = if (selectedOption.intValue == 0) ClickableIcon(
                    IconType.ImageVector(Icons.Filled.Search),
                    tint = AppTheme.colorScheme.onBackgroundNeutralDefault,
                    onClick = {
                        viewModel.handle(TransactionsViewActions.NavigateToTransactionSearchScreen)
                    }
                ) else null,
                middleContent = {
                    ChipWithIcon(
                        modifier = Modifier.border(
                            width = 1.dp,
                            color = AppTheme.colorScheme.strokeNeutral1Default,
                            shape = RoundedCornerShape(12.dp)
                        ),
                        roundedShape = 12.dp,
                        value = viewState.selectedDeposit?.desc
                            ?: (viewState.selectedDeposit?.depositNumber).toString(),
                        startIcon = Icons.Default.ArrowDropDown,
                        startIconStyle = IconStyle(
                            tint = AppTheme.colorScheme.onBackgroundNeutralDefault
                        ),
                        clickable = {
                            viewModel.handle(TransactionsViewActions.ShowDepositListBottomSheet)
                        },
                        color = Color.Transparent,
                        assetColor = AppTheme.colorScheme.onBackgroundNeutralDefault
                    )
                })
        }) {
        DynamicTabSelector(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 4.dp, end = 4.dp, top = 16.dp, bottom = 32.dp),
            containerColor = AppTheme.colorScheme.backgroundTintNeutralDefault,
            unselectedTextColor = AppTheme.colorScheme.onBackgroundNeutralSubdued,
            tabs = optionTexts,
            selectedOption = selectedOption.intValue
        ) {
            selectedOption.intValue = it
        }

        when (selectedOption.intValue) {
            0 -> {
                if (viewState.allTransactions.isEmpty()) {
                    EmptyList(
                        modifier = Modifier.fillMaxSize(),
                        iconType = IconType.Painter(painterResource(R.drawable.empty_list)),
                        message = "تراکنشی یافت نشد!"
                    )
                } else {
                    AllTransactionsSection(
                        transactionList = viewState.allTransactions,
                        transactionFilter = viewState.transactionFilter,
                        onFilterClick = {
                            viewModel.handle(TransactionsViewActions.NavigateToTransactionFilterScreen)
                        }, onFilterItemClick = {
                            viewModel.handle(TransactionsViewActions.RemoveFilterFromList(it))
                        }, onStatementClick = {
                            viewModel.handle(TransactionsViewActions.NavigateToDepositStatementScreen)
                        }) { transactionId ->
                        viewModel.handle(
                            TransactionsViewActions.NavigateToTransactionInfoScreen(
                                transactionId
                            )
                        )
                    }
                }
            }

            1 -> {
                SendTransactionsSection(
                    viewState.totalSendTransactions,
                ) { }
            }

            2 -> {
                ReceiveTransactionsSection(viewState.totalReceiveTransactions) {}
            }
        }

        if (viewState.showDepositListBottomSheet) DepositBottomSheet(
            title = "سپرده ها",
            items = viewState.deposits.mapToDepositMenu(),
            onDismiss = {
                viewModel.handle(TransactionsViewActions.CloseDepositListBottomSheet(null))
            }) {
            viewModel.handle(
                TransactionsViewActions.CloseDepositListBottomSheet(
                    it.mapToDepositModel()
                )
            )
        }
    }
}