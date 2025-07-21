package com.pmb.account.presentation.transactions

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.pmb.account.R
import com.pmb.account.presentation.component.TransactionRow
import com.pmb.account.presentation.transactions.detailedTransactionLIst.DetailedTransactionListViewActions
import com.pmb.account.presentation.transactions.detailedTransactionLIst.DetailedTransactionListViewEvents
import com.pmb.account.presentation.transactions.detailedTransactionLIst.DetailedTransactionListViewModel
import com.pmb.ballon.component.EmptyList
import com.pmb.ballon.component.base.AppContent
import com.pmb.ballon.component.base.AppTopBar
import com.pmb.ballon.component.base.IconType
import com.pmb.ballon.ui.theme.AppTheme
import com.pmb.domain.model.TransactionModel
import com.pmb.navigation.manager.LocalNavigationManager
import com.pmb.navigation.moduleScreen.AccountScreens

@Composable
fun DetailedTransactionList(
    sharedState: TransactionSharedState,
    viewModel: DetailedTransactionListViewModel
) {
    val navigationManager = LocalNavigationManager.current

    LaunchedEffect(Unit) {
        viewModel.viewEvent.collect { event ->
            when (event) {
                DetailedTransactionListViewEvents.NavigateBack -> {
                }

                is DetailedTransactionListViewEvents.NavigateToTransactionDetails -> {
                    navigationManager.navigateWithString(
                        AccountScreens.TransactionReceipt.createRoute(
                            sharedState.selectedDeposit?.depositNumber ?: "",
                            event.transaction
                        )
                    )
                }
            }
        }
    }

    val sharedTransaction = sharedState.summarize
    AppContent(
        modifier = Modifier
            .padding(horizontal = 16.dp),
        backgroundColor = AppTheme.colorScheme.background1Neutral,
        scrollState = null,
        topBar = {
            AppTopBar(
                title = sharedTransaction?.docDesc ?: "لیست تراکنش ها",
                onBack = {
                    navigationManager.navigateBack()
                })
        }
    ) {
        sharedState.summarize?.transactionList?.let {
            TransactionList(
                transaction = it
            ) { transaction ->
                viewModel.handle(DetailedTransactionListViewActions.OpenTransaction(transaction))
            }
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
            message = "خطا در بارگذاری"
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