package com.pmb.transfer.presentation.transfer

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import com.pmb.ballon.component.base.AppTopBar
import com.pmb.ballon.component.base.ClickableIcon
import com.pmb.ballon.component.base.IconType
import com.pmb.ballon.ui.theme.AppTheme
import com.pmb.core.presentation.NavigationManager
import com.pmb.transfer.R
import com.pmb.transfer.domain.transactionClientBanks
import com.pmb.transfer.presentation.TransferScreens
import com.pmb.transfer.presentation.components.TransactionClientBankList

@Composable
fun TransferScreen(navigationManager: NavigationManager) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = AppTheme.colorScheme.onForegroundNeutralDefault)
    ) {
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
        TransactionClientBankList(
            items = transactionClientBanks,
            onClick = {
                navigationManager.navigate(TransferScreens.DestinationInput)
            }
        )
    }
}
