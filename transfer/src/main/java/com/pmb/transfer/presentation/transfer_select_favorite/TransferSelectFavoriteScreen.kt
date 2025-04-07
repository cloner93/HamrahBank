package com.pmb.transfer.presentation.transfer_select_favorite

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.pmb.ballon.component.base.AppContent
import com.pmb.ballon.component.base.AppTopBar
import com.pmb.core.presentation.NavigationManager
import com.pmb.transfer.R
import com.pmb.transfer.domain.TransactionClientBank
import com.pmb.transfer.domain.transactionClientBanks
import com.pmb.transfer.presentation.components.transfer_confirm.ClientBankInfoTypeRow

@Composable
fun TransferSelectFavoriteScreen(navigationManager: NavigationManager) {
    val items =
        remember {
            mutableStateListOf<TransactionClientBank>().apply { addAll(transactionClientBanks) }
        }

    AppContent(
        topBar = {
            AppTopBar(
                title = stringResource(R.string.favorites),
                onBack = { navigationManager.navigateBack() })
        }
    ) {
        Column(modifier = Modifier.fillMaxSize()) {
            LazyColumn(modifier = Modifier.fillMaxSize()) {
                items(items.size) { index ->
                    val item = items[index]
                    ClientBankInfoTypeRow(info = item, onClick = {

                    })
                }
            }
        }
    }
}