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
import androidx.compose.material.MaterialTheme
import androidx.compose.material.rememberBackdropScaffoldState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
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
import androidx.navigation.compose.rememberNavController
import com.pmb.account.R
import com.pmb.account.presentation.component.DepositCarouselWidget
import com.pmb.account.presentation.component.ShareDepositBottomSheet
import com.pmb.account.presentation.component.ShareDepositBottomSheetContent
import com.pmb.account.presentation.component.TransactionRow
import com.pmb.account.presentation.deposits.viewmodel.DepositsViewActions
import com.pmb.account.presentation.deposits.viewmodel.DepositsViewEvents
import com.pmb.account.presentation.deposits.viewmodel.DepositsViewModel
import com.pmb.account.presentation.deposits.viewmodel.DepositsViewState
import com.pmb.account.utils.mapToDepositMenu
import com.pmb.account.utils.mapToDepositModel
import com.pmb.ballon.component.DepositBottomSheet
import com.pmb.ballon.component.EmptyList
import com.pmb.ballon.component.MenuBottomSheet
import com.pmb.ballon.component.MenuItem
import com.pmb.ballon.component.MenuItemDefaults
import com.pmb.ballon.component.annotation.AppPreview
import com.pmb.ballon.component.base.AppButtonIcon
import com.pmb.ballon.component.base.IconType
import com.pmb.ballon.component.base.RoundedTopColumn
import com.pmb.ballon.models.IconStyle
import com.pmb.ballon.models.MenuSheetModel
import com.pmb.ballon.models.TextStyle
import com.pmb.ballon.ui.theme.AppTheme
import com.pmb.ballon.ui.theme.HamrahBankTheme
import com.pmb.domain.model.DepositModel
import com.pmb.domain.model.TransactionModel
import com.pmb.domain.model.TransactionRequest
import com.pmb.domain.model.TransactionType
import com.pmb.domain.repository.deposit.DepositsRepository
import com.pmb.domain.repository.transactions.TransactionsByCountRepository
import com.pmb.domain.usecae.deposit.GetUserDepositListUseCase
import com.pmb.domain.usecae.transactions.TransactionsByCountUsaCase
import com.pmb.navigation.manager.LocalNavigationManager
import com.pmb.navigation.manager.NavigationManager
import com.pmb.navigation.moduleScreen.AccountScreens
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DepositsBackDropScreen(viewModel: DepositsViewModel, backdropState: BackdropScaffoldState) {
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
                    navigationManager.navigate(AccountScreens.Transactions)
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

                if (viewState.transactions.isEmpty()) {
                    EmptyList(
                        iconType = IconType.Painter(painterResource(R.drawable.empty_list)),
                        message = "تراکنشی یافت نشد!"
                    )
                } else
                    LazyColumn {
                        items(viewState.transactions.size) { item ->
                            Spacer(modifier = Modifier.height(12.dp))
                            TransactionRow(
                                viewState.transactions[item],
                                viewState.isAmountVisible
                            ) { transaction ->
                                viewModel.handle(
                                    DepositsViewActions.NavigateToTransactionDetailScreen(
                                        transaction
                                    )
                                )
                            }
                            Spacer(modifier = Modifier.height(12.dp))
                        }
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

@Composable
fun rememberFakeNavigationManager(): NavigationManager {
    val navController = rememberNavController()
    return remember(navController) {
        object : NavigationManager(navController) {

        }
    }
}

class MockDepositRepository : DepositsRepository {
    override fun getDepositList(): Flow<com.pmb.core.platform.Result<List<DepositModel>>> {
        return flow {
            val list = buildList {
                add(
                    DepositModel(
                        title = "title",
                        desc = "desc",
                        depositNumber = "1234",
                        categoryCode = 1,
                        amount = 1234.3,
                        currency = "rial",
                        ibanNumber = "",
                        cardNumber = "",
                        isSelected = false,
                    )
                )
            }
            emit(com.pmb.core.platform.Result.Success(list))
        }
    }
}

class MockTransactionsByCountRepository : TransactionsByCountRepository {
    override fun getTransactionsByCount(transactionRequest: TransactionRequest): Flow<com.pmb.core.platform.Result<List<TransactionModel>>> {
        return flow {
            val list = buildList {
                add(
                    TransactionModel(
                        transactionId = "12112",
                        amount = 123412342134.1,
                        date = "14040403"
                    )
                )
                add(
                    TransactionModel(
                        transactionId = "12112",
                        amount = 134212.1,
                        date = "14040403",
                        title = "انتقال وچه",
                        type = TransactionType.TRANSFER
                    )
                )
                add(
                    TransactionModel(
                        transactionId = "12112",
                        amount = 134212.1,
                        date = "14040403",
                        title = "شارژ حساب",
                        type = TransactionType.RECEIVE
                    )
                )
                add(
                    TransactionModel(
                        transactionId = "12112",
                        amount = 123412342134.1,
                        date = "14040403"
                    )
                )
                add(
                    TransactionModel(
                        transactionId = "12112",
                        amount = 134212.1,
                        date = "14040403",
                        title = "انتقال وچه",
                        type = TransactionType.TRANSFER
                    )
                )
                add(
                    TransactionModel(
                        transactionId = "12112",
                        amount = 134212.1,
                        date = "14040403",
                        title = "شارژ حساب",
                        type = TransactionType.RECEIVE
                    )
                )
                add(
                    TransactionModel(
                        transactionId = "12112",
                        amount = 123412342134.1,
                        date = "14040403"
                    )
                )
                add(
                    TransactionModel(
                        transactionId = "12112",
                        amount = 134212.1,
                        date = "14040403",
                        title = "انتقال وچه",
                        type = TransactionType.TRANSFER
                    )
                )
                add(
                    TransactionModel(
                        transactionId = "12112",
                        amount = 134212.1,
                        date = "14040403",
                        title = "شارژ حساب",
                        type = TransactionType.RECEIVE
                    )
                )
                add(
                    TransactionModel(
                        transactionId = "12112",
                        amount = 134212.1,
                        date = "14040403",
                        title = "انتقال وچه",
                        type = TransactionType.TRANSFER
                    )
                )
                add(
                    TransactionModel(
                        transactionId = "12112",
                        amount = 134212.1,
                        date = "14040403",
                        title = "شارژ حساب",
                        type = TransactionType.RECEIVE
                    )
                )
                add(
                    TransactionModel(
                        transactionId = "12112",
                        amount = 123412342134.1,
                        date = "14040403"
                    )
                )
                add(
                    TransactionModel(
                        transactionId = "12112",
                        amount = 134212.1,
                        date = "14040403",
                        title = "انتقال وچه",
                        type = TransactionType.TRANSFER
                    )
                )
                add(
                    TransactionModel(
                        transactionId = "12112",
                        amount = 134212.1,
                        date = "14040403",
                        title = "شارژ حساب",
                        type = TransactionType.RECEIVE
                    )
                )
                add(
                    TransactionModel(
                        transactionId = "12112",
                        amount = 123412342134.1,
                        date = "14040403"
                    )
                )
                add(
                    TransactionModel(
                        transactionId = "12112",
                        amount = 134212.1,
                        date = "14040403",
                        title = "انتقال وچه",
                        type = TransactionType.TRANSFER
                    )
                )
                add(
                    TransactionModel(
                        transactionId = "12112",
                        amount = 134212.1,
                        date = "14040403",
                        title = "شارژ حساب",
                        type = TransactionType.RECEIVE
                    )
                )
            }
            emit(com.pmb.core.platform.Result.Success(list))
        }
    }
}

class PreviewDepositsViewModel(
    initialState: DepositsViewState,
    getDepositsUseCase: GetUserDepositListUseCase,
    getTransactionsUseCase: TransactionsByCountUsaCase
) : DepositsViewModel(initialState, getDepositsUseCase, getTransactionsUseCase)


@Composable
@AppPreview
fun DepositsFullRevealedPreview() {
    val fakeNavManager = rememberFakeNavigationManager()

    CompositionLocalProvider(
        LocalNavigationManager provides fakeNavManager
    ) {
        HamrahBankTheme {
            val depositViewmodel = PreviewDepositsViewModel(
                initialState = DepositsViewState(),
                getDepositsUseCase = GetUserDepositListUseCase(MockDepositRepository()),
                getTransactionsUseCase = TransactionsByCountUsaCase(
                    MockTransactionsByCountRepository()
                )
            )
            DepositsBackDropScreen(
                depositViewmodel,
                rememberBackdropScaffoldState(BackdropValue.Revealed)
            )
        }
    }
}

@Composable
@AppPreview
fun DepositsFullConcealedPreview() {
    val fakeNavManager = rememberFakeNavigationManager()

    CompositionLocalProvider(
        LocalNavigationManager provides fakeNavManager
    ) {
        HamrahBankTheme {
            val depositViewmodel = PreviewDepositsViewModel(
                initialState = DepositsViewState(),
                getDepositsUseCase = GetUserDepositListUseCase(MockDepositRepository()),
                getTransactionsUseCase = TransactionsByCountUsaCase(
                    MockTransactionsByCountRepository()
                )
            )

            DepositsBackDropScreen(
                depositViewmodel,
                rememberBackdropScaffoldState(BackdropValue.Concealed)
            )
        }
    }
}