package com.pmb.transfer.presentation.transfer_edit_favorite

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.pmb.ballon.component.base.AppTopBar
import com.pmb.ballon.component.base.ClickableIcon
import com.pmb.ballon.component.base.IconType
import com.pmb.ballon.component.bottom_sheet.SimpleConfirmBottomSheet
import com.pmb.ballon.ui.theme.AppTheme
import com.pmb.core.presentation.NavigationManager
import com.pmb.transfer.R
import com.pmb.transfer.domain.TransactionClientBank
import com.pmb.transfer.domain.transactionClientBanks
import com.pmb.transfer.presentation.components.transfer_confirm.ClientBankInfoTypeRow


@Composable
fun TransferEditFavoriteScreen(navigationManager: NavigationManager) {
    val items =
        remember { mutableStateListOf<TransactionClientBank>().apply { addAll(transactionClientBanks) } }
    var selectedItem by remember { mutableStateOf<TransactionClientBank?>(null) }
    var showConfirmDeleteBottomSheet by remember { mutableStateOf(false) }
    Column(modifier = Modifier.fillMaxSize()) {
        AppTopBar(
            title = stringResource(R.string.edit_favorites),
            startIcon = ClickableIcon(
                icon = IconType.ImageVector(Icons.Default.Close),
                onClick = { navigationManager.navigateBack() })
        )

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

    if (showConfirmDeleteBottomSheet && selectedItem != null)
        SimpleConfirmBottomSheet(
            message = stringResource(R.string.msg_remove_favorite_destination, selectedItem!!.clientBank.name),
            rejectButton = stringResource(com.pmb.ballon.R.string.cancel),
            confirmButton =stringResource(com.pmb.ballon.R.string.delete),
            onDismiss ={
                showConfirmDeleteBottomSheet = false
            },
            onConfirm = {
                items.remove(selectedItem!!)
                showConfirmDeleteBottomSheet = false
            }
        )
}