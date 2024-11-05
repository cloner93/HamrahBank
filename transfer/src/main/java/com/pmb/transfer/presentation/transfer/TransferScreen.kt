package com.pmb.transfer.presentation.transfer

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.pmb.ballon.ui.theme.AppTheme
import com.pmb.core.presentation.NavigationManager
import com.pmb.transfer.domain.clientBanks
import com.pmb.transfer.domain.transactionClientBanks
import com.pmb.transfer.presentation.components.ContactsView
import com.pmb.transfer.presentation.components.TransactionClientBankList

@Composable
fun TransferScreen(navigationManager: NavigationManager) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = AppTheme.colorScheme.onForegroundNeutralDefault)
    ) {
        ContactsView(
            items = clientBanks,
            onClick = {

            }
        )
        Spacer(modifier = Modifier.height(8.dp))
        TransactionClientBankList(
            items = transactionClientBanks,
            onClick = {

            }
        )
    }
}
