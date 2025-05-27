package com.pmb.account.presentation.transactions.search

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
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
import androidx.hilt.navigation.compose.hiltViewModel
import com.pmb.account.R
import com.pmb.account.presentation.transactions.TransactionRow
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
import com.pmb.navigation.manager.LocalNavigationManager
import com.pmb.navigation.moduleScreen.AccountScreens

@Composable
fun TransactionSearchScreen() {
    val viewModel = hiltViewModel<TransactionSearchViewModel>()
    val viewState by viewModel.viewState.collectAsState()
    val navigationManager = LocalNavigationManager.current
    LaunchedEffect(Unit) {
        viewModel.viewEvent.collect { event ->
            when (event) {
                TransactionSearchViewEvents.NavigateBack -> {
                    navigationManager.navigateBack()
                }

                is TransactionSearchViewEvents.NavigateToTransactionInfoScreen -> {
                    navigationManager.navigateWithString(
                        AccountScreens.TransactionReceipt.createRoute(
                            event.depositId,
                            event.transactionId
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
        if (viewState.transactionList.isEmpty()) {
            EmptyList(
                iconType = IconType.Painter(painterResource(R.drawable.empty_list)),
                message = "تراکنشی یافت نشد!"
            )
        } else
            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                contentPadding = PaddingValues(12.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                items(viewState.transactionList.size) { item ->
                    TransactionRow(viewState.transactionList[item]) {
                        viewModel.handle(
                            TransactionSearchViewActions.NavigateToTransactionInfoScreen(
                                viewState.depositId ?: "",
                                viewState.transactionList[item].transactionId
                            )
                        )
                    }
                }
            }
    }
}