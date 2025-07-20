package com.pmb.account.presentation.transactions.search

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.pmb.account.R
import com.pmb.account.presentation.component.TransactionRow
import com.pmb.account.presentation.transactions.TransactionSharedState
import com.pmb.account.presentation.transactions.search.viewmodel.TransactionSearchViewActions
import com.pmb.account.presentation.transactions.search.viewmodel.TransactionSearchViewEvents
import com.pmb.account.presentation.transactions.search.viewmodel.TransactionSearchViewModel
import com.pmb.ballon.component.EmptyList
import com.pmb.ballon.component.base.AppButtonIcon
import com.pmb.ballon.component.base.AppContent
import com.pmb.ballon.component.base.AppSearchTextField
import com.pmb.ballon.component.base.IconType
import com.pmb.ballon.models.IconStyle
import com.pmb.ballon.ui.theme.AppTheme
import com.pmb.domain.model.TransactionModel
import com.pmb.navigation.manager.LocalNavigationManager
import com.pmb.navigation.moduleScreen.AccountScreens

@Composable
fun TransactionSearchScreen(
    viewModel: TransactionSearchViewModel,
    sharedState: TransactionSharedState
) {
    val viewState by viewModel.viewState.collectAsState()
    val navigationManager = LocalNavigationManager.current

    LaunchedEffect(Unit) {
        viewModel.setListOfTransaction(
            sharedState.transactionList,
            sharedState.selectedDeposit
        )

        viewModel.viewEvent.collect { event ->
            when (event) {
                TransactionSearchViewEvents.NavigateBack -> {
                    navigationManager.navigateBack()
                }

                is TransactionSearchViewEvents.NavigateToTransactionInfoScreen -> {
                    navigationManager.navigateWithString(
                        AccountScreens.TransactionReceipt.createRoute(
                            event.deposit.depositNumber,
                            event.transactionJson
                        )
                    )
                }
            }
        }
    }

    AppContent(
        modifier = Modifier.padding(horizontal = 16.dp),
        scrollState = null,
        topBar = {
            Row(
                modifier = Modifier.height(64.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                AppButtonIcon(
                    icon = IconType.ImageVector(Icons.Filled.ArrowForward),
                    style = IconStyle(tint = AppTheme.colorScheme.onBackgroundNeutralDefault),
                    onClick = {
                        viewModel.handle(TransactionSearchViewActions.NavigateBack)
                    }
                )

                AppSearchTextField(
                    modifier = Modifier.padding(end = 16.dp),
                    hint = "مبلغ، نام، عنوان تراکنش و...",
                    query = viewState.query,
                    onValueChange = {
                        viewModel.handle(TransactionSearchViewActions.SearchTransactions(it))
                    }
                )
            }
        }
    ) {
        val list =
            viewState.filteredTransactionList.ifEmpty { viewState.transactionList }

        TransactionList(list) { transactionModel ->
            viewModel.handle(
                TransactionSearchViewActions.NavigateToTransactionInfoScreen(
                    transactionModel
                )
            )
        }
    }
}

@Composable
fun TransactionList(
    transaction: List<TransactionModel>,
    onItemClick: (TransactionModel) -> Unit
) {
    if (transaction.isEmpty()) {
        EmptyList(
            modifier = Modifier.fillMaxSize(),
            iconType = IconType.Painter(painterResource(R.drawable.empty_list)),
            message = "تراکنش یافت نشد."
        )
    } else {
        LazyColumn(
            modifier = Modifier.fillMaxSize()
        ) {
            items(transaction.size) { index ->
                transaction[index].let { item ->
                    Spacer(modifier = Modifier.height(8.dp))
                    TransactionRow(
                        item = item,
                        isAmountVisible = true,
                        onClick = { onItemClick(item) }
                    )
                }
            }
        }
    }
}