package com.pmb.account.presentation.deposits

import android.annotation.SuppressLint
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
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.rememberNavController
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
import com.pmb.account.presentation.deposits.viewmodel.DepositsViewState
import com.pmb.account.utils.mapToDepositMenu
import com.pmb.account.utils.mapToDepositModel
import com.pmb.ballon.component.DepositBottomSheet
import com.pmb.ballon.component.MenuBottomSheet
import com.pmb.ballon.component.MenuItem
import com.pmb.ballon.component.MenuItemDefaults
import com.pmb.ballon.component.annotation.AppPreview
import com.pmb.ballon.component.base.AppButtonIcon
import com.pmb.ballon.component.base.RoundedTopColumn
import com.pmb.ballon.models.IconStyle
import com.pmb.ballon.models.MenuSheetModel
import com.pmb.ballon.models.TextStyle
import com.pmb.ballon.ui.theme.AppTheme
import com.pmb.ballon.ui.theme.HamrahBankTheme
import com.pmb.core.platform.Result
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


@SuppressLint("RememberReturnType", "StateFlowValueCalledInComposition")
@Composable
fun DepositsScreen(viewModel: DepositsViewModel) {
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
//                .padding(top = 25.dp)
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

            TransactionLazyList(
                viewState.transactionFlow.value.collectAsLazyPagingItems(),
                viewState.isAmountVisible,
            ) { transaction ->
                viewModel.handle(
                    DepositsViewActions.NavigateToTransactionDetailScreen(
                        transaction
                    )
                )
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

@Composable
fun TransactionLazyList(
    transaction: LazyPagingItems<TransactionModel>,
    amountVisible: Boolean,
    function: (transaction: TransactionModel) -> Unit
) {
    LazyColumn {
        items(transaction.itemCount) { item ->
            Row(
                modifier = Modifier.padding(2.dp),
            ) {
                transaction[item]?.let {
                    TransactionRow(
                        it,
                        amountVisible,
                        { transaction ->
                            function(transaction)
                        }
                    )
                }
            }
        }

        when (transaction.loadState.append) {
            is LoadState.Error -> {
                item {
                    Column(
                        modifier = Modifier
                            .fillMaxSize(),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center,
                    ) {
                        AppButtonIcon(
                            icon = painterResource(com.pmb.ballon.R.drawable.ic_help),
                            onClick = {
                                transaction.retry()
                            }
                        )
                    }
                }
            }

            LoadState.Loading -> {
                item {
                    Column(
                        modifier = Modifier
                            .fillMaxSize(),
                        horizontalAlignment = Alignment.CenterHorizontally,
                    ) {
                        CircularProgressIndicator(color = Color.Red)
                    }
                }
            }

            is LoadState.NotLoading -> {}
        }
        when (transaction.loadState.refresh) {
            is LoadState.Error -> {
                item {
                    Column(
                        modifier = Modifier
                            .fillMaxSize(),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center,
                    ) {
                        Text(
                            text = "We couldn't fetch popular movies. Please try again.",
                            textAlign = TextAlign.Center
                        )
                        TextButton(
                            onClick = { transaction.retry() }
                        ) {
                            Text(
                                text = "Retry again",
                            )
                        }
                    }
                }
            }

            is LoadState.Loading -> {
                item {
                    Column(
                        modifier = Modifier
                            .fillMaxSize(),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center,
                    ) {
                        Text(
                            modifier = Modifier
                                .padding(8.dp),
                            text = "Loading Popular Movies"
                        )

                        CircularProgressIndicator(color = Color.Red)
                    }
                }
            }

            else -> {}
        }

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
    override fun getDepositList(): Flow<Result<List<DepositModel>>> {
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
            emit(Result.Success(list))
        }
    }
}

class MockDepositEmptyRepository : DepositsRepository {
    override fun getDepositList(): Flow<Result<List<DepositModel>>> {
        return flow {
            emit(Result.Loading)
        }
    }
}

class MockTransactionsByCountRepository : TransactionsByCountRepository {
    override fun getTransactionsByCount(transactionRequest: TransactionRequest): Flow<Result<List<TransactionModel>>> {
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
            }
            emit(Result.Success(list))
        }
    }
}

class MockTransactionsByCountEmptyRepository : TransactionsByCountRepository {
    override fun getTransactionsByCount(transactionRequest: TransactionRequest): Flow<Result<List<TransactionModel>>> {
        return flow {

            emit(Result.Loading)
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
@Preview(name = "Phone mini", widthDp = 360, heightDp = 540)
@Preview(name = "Tablet", widthDp = 800, heightDp = 1280)
fun DepositsEmptyPreview() {
    val fakeNavManager = rememberFakeNavigationManager()

    CompositionLocalProvider(
        LocalNavigationManager provides fakeNavManager
    ) {
        HamrahBankTheme {
            val depositViewmodel = PreviewDepositsViewModel(
                initialState = DepositsViewState(),
                getDepositsUseCase = GetUserDepositListUseCase(
                    MockDepositEmptyRepository()
                ),
                getTransactionsUseCase = TransactionsByCountUsaCase(
                    MockTransactionsByCountEmptyRepository()
                )
            )
            DepositsScreen(depositViewmodel)
        }
    }
}

@Composable
@AppPreview
@Preview(name = "Phone mini", widthDp = 360, heightDp = 540)
@Preview(name = "Tablet", widthDp = 800, heightDp = 1280)
fun DepositsFullPreview() {
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
            DepositsScreen(depositViewmodel)
        }
    }
}