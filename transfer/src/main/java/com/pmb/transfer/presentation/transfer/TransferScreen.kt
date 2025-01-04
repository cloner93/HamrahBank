package com.pmb.transfer.presentation.transfer

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.pmb.ballon.component.base.AppContent
import com.pmb.ballon.component.base.AppTopBar
import com.pmb.ballon.component.base.ClickableIcon
import com.pmb.ballon.component.base.IconType
import com.pmb.ballon.ui.theme.AppTheme
import com.pmb.core.presentation.NavigationManager
import com.pmb.transfer.R
import com.pmb.transfer.domain.clientBanks
import com.pmb.transfer.domain.transactionClientBanks
import com.pmb.transfer.presentation.TransferScreens
import com.pmb.transfer.presentation.components.ContactsView
import com.pmb.transfer.presentation.components.TransactionClientBankList

@Composable
fun TransferScreen(navigationManager: NavigationManager) {
    AppContent(
        topBar = {
            AppTopBar(
                title = stringResource(R.string.fund_transfer),
                onBack = {
                    navigationManager.navigateBack()
                },
                endIcon = ClickableIcon(
                    icon = IconType.Painter(painterResource(com.pmb.ballon.R.drawable.ic_search)),
                    onClick = {
                        navigationManager.navigate(TransferScreens.Search)
                    })
            )
        },
        footer = {

        },
        content = {

        })
}

@Composable
private fun TransferScreenOld(navigationManager: NavigationManager) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = AppTheme.colorScheme.onForegroundNeutralDefault)
    ) {
        ContactsView(
            items = clientBanks,
            onClick = {
                navigationManager.navigate(TransferScreens.DestinationInput)
            }
        )
        Spacer(modifier = Modifier.height(8.dp))
        TransactionClientBankList(
            items = transactionClientBanks,
            onClick = {
                navigationManager.navigate(TransferScreens.DestinationInput)
            }
        )
    }
}

