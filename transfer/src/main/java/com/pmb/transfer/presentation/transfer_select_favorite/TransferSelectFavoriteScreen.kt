package com.pmb.transfer.presentation.transfer_select_favorite

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.pmb.ballon.component.AlertComponent
import com.pmb.ballon.component.base.AppContent
import com.pmb.ballon.component.base.AppLoading
import com.pmb.ballon.component.base.AppTopBar
import com.pmb.ballon.component.base.ClickableIcon
import com.pmb.ballon.component.base.IconType
import com.pmb.core.presentation.NavigationManager
import com.pmb.transfer.R
import com.pmb.transfer.domain.entity.TransactionClientBankEntity
import com.pmb.transfer.presentation.TransferScreens
import com.pmb.transfer.presentation.components.transfer_confirm.ClientBankInfoTypeRow
import com.pmb.transfer.presentation.transfer_select_favorite.viewmodel.TransferSelectFavoriteViewActions
import com.pmb.transfer.presentation.transfer_select_favorite.viewmodel.TransferSelectFavoriteViewEvents
import com.pmb.transfer.presentation.transfer_select_favorite.viewmodel.TransferSelectFavoriteViewModel

@Composable
fun TransferSelectFavoriteScreen(
    navigationManager: NavigationManager,
    viewModel: TransferSelectFavoriteViewModel,
    selectedAccount: (TransactionClientBankEntity) -> Unit
) {
    val viewState by viewModel.viewState.collectAsState()

    // Handle one-time events such as navigation or showing toasts
    LaunchedEffect(Unit) {
        viewModel.viewEvent.collect { event ->
            when (event) {
                is TransferSelectFavoriteViewEvents.TransferDestinationAmount -> {
                    selectedAccount.invoke(event.item)
                    navigationManager.navigate(TransferScreens.TransferAmount)
                }
            }
        }
    }

    AppContent(
        scrollState = null,
        topBar = {
            AppTopBar(
                title = stringResource(R.string.favorites),
                startIcon = ClickableIcon(
                    icon = IconType.ImageVector(Icons.Default.Close),
                    onClick = {
                        navigationManager.navigateBack()
                    })
            )
        }) {
        Column(modifier = Modifier.fillMaxSize()) {
            LazyColumn(modifier = Modifier.fillMaxSize()) {
                items(viewState.accounts.size) { index ->
                    val item = viewState.accounts[index]
                    ClientBankInfoTypeRow(info = item, onClick = {
                        viewModel.handle(TransferSelectFavoriteViewActions.SelectFavorite(it))
                    })
                }
            }
        }
    }

    if (viewState.loading) AppLoading()
    viewState.alertState?.let { AlertComponent(it) }
}