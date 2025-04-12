package com.pmb.transfer.presentation.transfer_edit_destination

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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import com.pmb.ballon.component.AlertComponent
import com.pmb.ballon.component.base.AppLoading
import com.pmb.ballon.component.base.AppTopBar
import com.pmb.ballon.component.base.ClickableIcon
import com.pmb.ballon.component.base.IconType
import com.pmb.ballon.ui.theme.AppTheme
import com.pmb.core.presentation.NavigationManager
import com.pmb.transfer.R
import com.pmb.transfer.domain.entity.TransactionClientBankEntity
import com.pmb.transfer.presentation.TransferScreens
import com.pmb.transfer.presentation.components.transfer_confirm.ClientBankInfoTypeRow
import com.pmb.transfer.presentation.transfer_edit_destination.viewmodel.TransferEditDestinationViewActions
import com.pmb.transfer.presentation.transfer_edit_destination.viewmodel.TransferEditDestinationViewEvents
import com.pmb.transfer.presentation.transfer_edit_destination.viewmodel.TransferEditDestinationViewModel

@Composable
fun TransferEditDestinationScreen(
    navigationManager: NavigationManager,
    viewModel: TransferEditDestinationViewModel,
    selectedAccount: (TransactionClientBankEntity) -> Unit
) {
    val viewState by viewModel.viewState.collectAsState()

    // Handle one-time events such as navigation or showing toasts
    LaunchedEffect(Unit) {
        viewModel.viewEvent.collect { event ->
            when (event) {
                TransferEditDestinationViewEvents.NavigateUp -> {
                    navigationManager.navigateBack()
                }

                is TransferEditDestinationViewEvents.TransferDestinationAmount -> {
                    selectedAccount.invoke(event.item)
                    navigationManager.navigate(TransferScreens.TransferAmount)
                }
            }
        }
    }

    Column(modifier = Modifier.fillMaxSize()) {
        AppTopBar(
            title = stringResource(R.string.edit_recent_destinations),
            startIcon = ClickableIcon(
                icon = IconType.ImageVector(Icons.Default.Close),
                onClick = { navigationManager.navigateBack() })
        )

        LazyColumn(modifier = Modifier.fillMaxSize()) {
            items(viewState.transactionClientBanks.size) { index ->
                val item = viewState.transactionClientBanks[index]
                ClientBankInfoTypeRow(
                    info = item,
                    endIcon = ClickableIcon(
                        icon = IconType.Painter(painterResource(if (item.favorite) com.pmb.ballon.R.drawable.ic_pin_filled else com.pmb.ballon.R.drawable.ic_pin)),
                        tint = if (item.favorite) AppTheme.colorScheme.onBackgroundNeutralCTA else AppTheme.colorScheme.onBackgroundNeutralSubdued,
                        onClick = {
                            viewModel.handle(
                                TransferEditDestinationViewActions.ChangeFavoriteStatus(
                                    newStatus = !item.favorite,
                                    item = item
                                )
                            )
                        }),
                )
            }
        }
    }

    if (viewState.loading) AppLoading()
    viewState.alertState?.let { AlertComponent(it) }
}