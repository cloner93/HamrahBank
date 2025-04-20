package com.pmb.transfer.presentation.transfer_edit_favorite

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.pmb.ballon.component.AlertComponent
import com.pmb.ballon.component.base.AppLoading
import com.pmb.ballon.component.base.AppTopBar
import com.pmb.ballon.component.base.ClickableIcon
import com.pmb.ballon.component.base.IconType
import com.pmb.ballon.component.bottom_sheet.SimpleConfirmBottomSheet
import com.pmb.ballon.ui.theme.AppTheme
import com.pmb.navigation.manager.LocalNavigationManager
import com.pmb.navigation.moduleScreen.TransferScreens
import com.pmb.transfer.R
import com.pmb.transfer.domain.entity.TransactionClientBankEntity
import com.pmb.transfer.presentation.components.transfer_confirm.ClientBankInfoTypeRow
import com.pmb.transfer.presentation.transfer_edit_favorite.viewmodel.TransferEditFavoriteViewActions
import com.pmb.transfer.presentation.transfer_edit_favorite.viewmodel.TransferEditFavoriteViewEvents
import com.pmb.transfer.presentation.transfer_edit_favorite.viewmodel.TransferEditFavoriteViewModel


@Composable
fun TransferEditFavoriteScreen(
    viewModel: TransferEditFavoriteViewModel,
    selectedAccount: (TransactionClientBankEntity) -> Unit
) {
    val viewState by viewModel.viewState.collectAsState()
    var selectedItem by remember { mutableStateOf<TransactionClientBankEntity?>(null) }
    var showConfirmDeleteBottomSheet by remember { mutableStateOf(false) }
    val navigationManager = LocalNavigationManager.current
    // Handle one-time events such as navigation or showing toasts
    LaunchedEffect(Unit) {
        viewModel.viewEvent.collect { event ->
            when (event) {
                is TransferEditFavoriteViewEvents.NavigateToDestinationAmount -> {
                    selectedAccount.invoke(event.item)
                    navigationManager.navigate(TransferScreens.TransferAmount)
                }
            }
        }
    }

    Column(modifier = Modifier.fillMaxSize()) {
        AppTopBar(
            title = stringResource(R.string.edit_favorites),
            startIcon = ClickableIcon(
                icon = IconType.ImageVector(Icons.Default.Close),
                onClick = { navigationManager.navigateBack() })
        )

        viewState.accountsFavorite?.let { items ->
            LazyColumn(modifier = Modifier.fillMaxSize()) {
                items(items.size) { index ->
                    val item = items[index]
                    ClientBankInfoTypeRow(
                        info = item,
                        endIcon = ClickableIcon(
                            icon = IconType.ImageVector(Icons.Default.Close),
                            tint = AppTheme.colorScheme.onBackgroundNeutralSubdued,
                            onClick = {
                                selectedItem = item
                                showConfirmDeleteBottomSheet = true
                            }),
                    )
                }
            }
        }
    }

    if (showConfirmDeleteBottomSheet && selectedItem != null)
        selectedItem?.let { removeItem ->
            SimpleConfirmBottomSheet(
                message = stringResource(
                    R.string.msg_remove_favorite_destination,
                    removeItem.clientBankEntity.name
                ),
                rejectButton = stringResource(com.pmb.ballon.R.string.cancel),
                confirmButton = stringResource(com.pmb.ballon.R.string.delete),
                onDismiss = {
                    showConfirmDeleteBottomSheet = false
                },
                onConfirm = {
                    viewModel.handle(TransferEditFavoriteViewActions.RemoveAccount(removeItem))
                    showConfirmDeleteBottomSheet = false
                }
            )
        }

    if (viewState.loading) {
        AppLoading()
    }

    viewState.alertState?.let {
        AlertComponent(it)
    }
}