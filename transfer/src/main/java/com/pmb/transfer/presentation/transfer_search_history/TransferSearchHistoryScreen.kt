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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.pmb.ballon.component.EmptyList
import com.pmb.ballon.component.base.AppButtonIcon
import com.pmb.ballon.component.base.AppSearchTextField
import com.pmb.ballon.component.base.IconType
import com.pmb.ballon.ui.theme.AppTheme
import com.pmb.core.presentation.NavigationManager
import com.pmb.transfer.R
import com.pmb.transfer.domain.transactionClientBanks
import com.pmb.transfer.presentation.TransferScreens
import com.pmb.transfer.presentation.components.TransactionClientBankList

@Composable
fun TransferSearchHistoryScreen(navigationManager: NavigationManager) {
    var query by remember { mutableStateOf("") }

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
                query = query,
                onValueChange = { query = it }
            )
        }

        if (transactionClientBanks.isEmpty()) {
            EmptyList(
                iconType = IconType.Painter(painterResource(R.drawable.img_bank_card_shrare_money)),
                message = stringResource(R.string.msg_dont_have_transfer_yet)
            )
        } else {
            TransactionClientBankList(
                items = transactionClientBanks,
                isFavorite = false,
                onClick = {
                    navigationManager.navigate(TransferScreens.TransferDestinationInput)
                }
            )
        }
    }
}