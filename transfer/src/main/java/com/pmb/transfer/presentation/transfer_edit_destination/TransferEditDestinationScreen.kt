package com.pmb.transfer.presentation.transfer_edit_destination

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import com.pmb.ballon.component.base.AppTopBar
import com.pmb.ballon.component.base.ClickableIcon
import com.pmb.ballon.component.base.IconType
import com.pmb.ballon.ui.theme.AppTheme
import com.pmb.core.presentation.NavigationManager
import com.pmb.transfer.R
import com.pmb.transfer.domain.TransactionClientBank
import com.pmb.transfer.domain.transactionClientBanks
import com.pmb.transfer.presentation.components.transfer_confirm.ClientBankInfoTypeRow

@Composable
fun TransferEditDestinationScreen(navigationManager: NavigationManager) {
    val items =
        remember { mutableStateListOf<TransactionClientBank>().apply { addAll(transactionClientBanks) } }

    Column(modifier = Modifier.fillMaxSize()) {
        AppTopBar(
            title = stringResource(R.string.edit_recent_destinations),
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
                        icon = IconType.Painter( painterResource(if (item.favorite) com.pmb.ballon.R.drawable.ic_pin_filled else com.pmb.ballon.R.drawable.ic_pin)),
                        tint = if (item.favorite) AppTheme.colorScheme.onBackgroundNeutralCTA else AppTheme.colorScheme.onBackgroundNeutralSubdued,
                        onClick = {
                            items[index] = item.copy(favorite = !item.favorite)
                        }),
                )
            }
        }
    }
}