package com.pmb.account.presentation.account

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.BackdropScaffold
import androidx.compose.material.BackdropScaffoldState
import androidx.compose.material.BackdropValue
import androidx.compose.material.Button
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.rememberBackdropScaffoldState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
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
import com.pmb.ballon.component.EmptyList
import com.pmb.ballon.component.MenuBottomSheet
import com.pmb.ballon.component.MenuItem
import com.pmb.ballon.component.MenuItemDefaults
import com.pmb.ballon.component.base.AppButtonIcon
import com.pmb.ballon.component.base.IconType
import com.pmb.ballon.component.base.RoundedTopColumn
import com.pmb.ballon.models.IconStyle
import com.pmb.ballon.models.MenuSheetModel
import com.pmb.ballon.models.TextStyle
import com.pmb.ballon.ui.theme.AppTheme
import com.pmb.domain.model.TransactionModel
import com.pmb.navigation.manager.LocalNavigationManager
import com.pmb.navigation.moduleScreen.AccountScreens

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DepositsScreen(
    viewModel: DepositsViewModel
) {
    val viewState by viewModel.viewState.collectAsState()
    val navigationManager = LocalNavigationManager.current
    val backdropState: BackdropScaffoldState =
        rememberBackdropScaffoldState(BackdropValue.Revealed)

    LocalContext.current

    LaunchedEffect(Unit) {
        viewModel.viewEvent.collect { event ->
            when (event) {
                is DepositsViewEvents.DepositSelectionChanged -> {

                }

                is DepositsViewEvents.NavigateBack -> {
                }

                is DepositsViewEvents.NavigateToTransactionDetails -> {
                    navigationManager.navigateWithString(
                        AccountScreens.TransactionReceipt.createRoute(
                            viewState.selectedDeposit?.depositNumber ?: "",
                            event.transaction
                        )
                    )
                }

                is DepositsViewEvents.NavigateToTransactionsList -> {
                    navigationManager.navigate(AccountScreens.Transactions.AllTransactionsList)
                }

                is DepositsViewEvents.RefreshCompleted -> {
                }

                is DepositsViewEvents.ShowError -> {

                }

                is DepositsViewEvents.ShowToast -> {

                }

                is DepositsViewEvents.NavigateToBalanceScreen -> {
                    navigationManager.navigate(AccountScreens.Balance)
                }

                is DepositsViewEvents.NavigateToDetailsScreen -> {
                    navigationManager.navigateWithString(
                        AccountScreens.DepositDetailsScreen.createRoute(
                            event.detail
                        )
                    )
                }
            }
        }
    }

    rememberCoroutineScope()

    BackdropScaffold(
        scaffoldState = backdropState,
        backLayerContent = {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(LocalConfiguration.current.screenHeightDp.dp * 0.35f)
            ) {
                Box(
                    modifier = Modifier
                        .matchParentSize()
                        .background(
                            brush = Brush.verticalGradient(
                                colors = listOf(
                                    Color(0xFFDF445F), Color(0xFFC11332)
                                )
                            )
                        )
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.mellat_logo_line),
                        contentDescription = null,
                        contentScale = ContentScale.Crop,
                        modifier = Modifier.fillMaxSize()
                    )
                }
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(
                            top = WindowInsets.statusBars.asPaddingValues().calculateTopPadding()
                        )
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

                    Spacer(modifier = Modifier.height(24.dp))
                }
            }
        },
        backLayerBackgroundColor = Color(0xFFC11332),
        frontLayerShape = MaterialTheme.shapes.large.copy(
            topStart = CornerSize(20.dp),
            topEnd = CornerSize(20.dp)
        ),
        frontLayerScrimColor = Color.Unspecified,
        frontLayerContent = {

            RoundedTopColumn(
                modifier = Modifier.fillMaxSize()
            ) {

                MenuItem(
                    modifier = Modifier
                        .clip(RoundedCornerShape(16.dp))
                        .background(color = AppTheme.colorScheme.backgroundTintNeutralDefault),
                    title = stringResource(R.string.deposit_card_sheba),
                    horizontalDividerPadding = MenuItemDefaults.horizontalDividerPadding.copy(
                        vertical = 0.dp
                    ),
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

                TransactionLazyList(
                    viewModel.transactionFlow.collectAsLazyPagingItems(),
                    viewState.isAmountVisible,
                ) { transaction ->
                    viewModel.handle(
                        DepositsViewActions.NavigateToTransactionDetailScreen(
                            transaction
                        )
                    )
                }
            }
        },
        appBar = {
        },
        peekHeight = 92.dp,
    )

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
            items = listOf(
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
                ), MenuSheetModel(
                    title = stringResource(R.string.deposit_details),
                    icon = com.pmb.ballon.R.drawable.ic_deposit_details,
                    onClicked = {
                        viewModel.handle(DepositsViewActions.OpenDepositDetailsScreen)
                    }
                )
            ),
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

@Composable
fun TransactionLazyList(
    transaction: LazyPagingItems<TransactionModel>,
    amountVisible: Boolean,
    onItemClick: (TransactionModel) -> Unit
) {
    val loadState = transaction.loadState

    when {
        loadState.refresh is LoadState.Loading -> {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator()
            }
        }

        loadState.refresh is LoadState.Error -> {
            val error = loadState.refresh as LoadState.Error
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    EmptyList(
                        modifier = Modifier.fillMaxSize(),
                        iconType = IconType.Painter(painterResource(R.drawable.empty_list)),
                        message = "خطا در بارگذاری بیشتر - ${error.error.message}"
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Button(onClick = { transaction.retry() }) {
                        Text("تلاش مجدد")
                    }
                }
            }
        }

        transaction.itemCount == 0 -> {
            EmptyList(
                modifier = Modifier.fillMaxWidth(),
                iconType = IconType.Painter(painterResource(R.drawable.empty_list)),
                message = "تراکنشی یافت نشد!"
            )
        }

        else -> {
            LazyColumn(
                modifier = Modifier.fillMaxSize()
            ) {
                items(transaction.itemCount) { index ->
                    transaction[index]?.let { item ->
                        Spacer(modifier = Modifier.height(8.dp))
                        TransactionRow(
                            item = item,
                            isAmountVisible = amountVisible,
                            onClick = { onItemClick(item) }
                        )
                    }
                }

                if (loadState.append is LoadState.Loading) {
                    item {
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(16.dp),
                            contentAlignment = Alignment.Center
                        ) {
                            CircularProgressIndicator()
                        }
                    }
                }

                if (loadState.append is LoadState.Error) {
                    val error = loadState.append as LoadState.Error
                    item {
                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(16.dp),
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            EmptyList(
                                modifier = Modifier.fillMaxSize(),
                                iconType = IconType.Painter(painterResource(R.drawable.empty_list)),
                                message = "خطا در بارگذاری بیشتر - ${error.error.message}"
                            )
                            Spacer(modifier = Modifier.height(8.dp))
                            Button(onClick = { transaction.retry() }) {
                                Text("تلاش مجدد")
                            }
                        }
                    }
                }
            }
        }
    }
}