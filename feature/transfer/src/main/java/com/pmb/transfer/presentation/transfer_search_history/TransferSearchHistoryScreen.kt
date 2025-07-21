package com.pmb.transfer.presentation.transfer_search_history

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.pmb.ballon.component.AlertComponent
import com.pmb.ballon.component.EmptyList
import com.pmb.ballon.component.base.AppButtonIcon
import com.pmb.ballon.component.base.AppLoading
import com.pmb.ballon.component.base.AppSearchTextField
import com.pmb.ballon.component.base.IconType
import com.pmb.ballon.ui.theme.AppTheme
import com.pmb.navigation.manager.LocalNavigationManager
import com.pmb.navigation.moduleScreen.TransferScreens
import com.pmb.transfer.R
import com.pmb.transfer.domain.entity.TransactionClientBankEntity
import com.pmb.transfer.presentation.components.TransactionClientBankList
import com.pmb.transfer.presentation.transfer_search_history.viewmodel.TransferSearchHistoryViewActions
import com.pmb.transfer.presentation.transfer_search_history.viewmodel.TransferSearchHistoryViewEvents
import com.pmb.transfer.presentation.transfer_search_history.viewmodel.TransferSearchHistoryViewModel

@Composable
fun TransferSearchHistoryScreen(
    viewModel: TransferSearchHistoryViewModel,
    selectedAccount: (TransactionClientBankEntity) -> Unit
) {
    val viewState by viewModel.viewState.collectAsState()
    val navigationManager = LocalNavigationManager.current
    // Handle one-time events such as navigation or showing toasts
    LaunchedEffect(Unit) {
        viewModel.viewEvent.collect { event ->
            when (event) {
                is TransferSearchHistoryViewEvents.TransferDestinationAmount -> {
                    selectedAccount(event.item)
                    navigationManager.navigate(TransferScreens.TransferAmount)
                }
            }
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = AppTheme.colorScheme.onForegroundNeutralDefault)
    ) {
        Row(
            modifier = Modifier.height(64.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            AppButtonIcon(
                icon = IconType.ImageVector(Icons.Filled.ArrowForward),
                onClick = { navigationManager.navigateBack() }
            )

            AppSearchTextField(
                modifier = Modifier.padding(end = 16.dp),
                hint = stringResource(R.string.hint_transfer_account_search),
                query = viewState.query,
                onValueChange = {
                    viewModel.handle(TransferSearchHistoryViewActions.SearchAccounts(it))
                }
            )
        }

        if (viewState.accounts.isEmpty() && !viewState.loading) {
            EmptyList(
                modifier = Modifier.fillMaxSize(),
                iconType = IconType.Painter(painterResource(R.drawable.img_bank_card_shrare_money)),
                message = stringResource(R.string.msg_dont_have_transfer_yet)
            )
        } else {
            TransactionClientBankList(
                items = viewState.accounts,
                isFavorite = false,
                onEditClick = {
                    navigationManager.navigate(TransferScreens.TransferEditLatestDestination)
                },
                onClick = {
                    viewModel.handle(TransferSearchHistoryViewActions.SelectAccount(it))
                }
            )
        }
    }

    if (viewState.loading) AppLoading()
    viewState.alertState?.let { AlertComponent(it) }
}