package com.pmb.transfer.presentation.transfer_receipt

import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.res.stringResource
import com.pmb.ballon.component.base.AppContent
import com.pmb.ballon.component.base.AppTopBar
import com.pmb.ballon.ui.theme.AppTheme
import com.pmb.core.presentation.NavigationManager
import com.pmb.transfer.R

@Composable
fun TransferReceiptScreen(navigationManager: NavigationManager) {
    AppContent(
        backgroundColor = AppTheme.colorScheme.background1Neutral,
        topBar = {
            AppTopBar(
                title = stringResource(R.string.mony_transfer_receipt),
                onBack = { navigationManager.navigateBack() })
        },
        horizontalAlignment = Alignment.CenterHorizontally,
        footer = { -> },
        content = { -> })

}