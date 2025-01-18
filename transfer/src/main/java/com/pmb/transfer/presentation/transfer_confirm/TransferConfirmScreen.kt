package com.pmb.transfer.presentation.transfer_confirm

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Switch
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.pmb.ballon.component.SentencesWithSuffix
import com.pmb.ballon.component.base.AppButton
import com.pmb.ballon.component.base.AppContent
import com.pmb.ballon.component.base.AppTopBar
import com.pmb.ballon.component.base.BodyMediumText
import com.pmb.ballon.component.base.BodySmallText
import com.pmb.ballon.ui.theme.AppTheme
import com.pmb.core.presentation.NavigationManager
import com.pmb.transfer.R
import com.pmb.transfer.domain.TransactionClientBank
import com.pmb.transfer.domain.transactionClientBanks
import com.pmb.transfer.presentation.TransferScreens
import com.pmb.transfer.presentation.components.ClientBankProfileInfo
import com.pmb.transfer.presentation.components.transfer_confirm.PaymentBottomSheet
import com.pmb.transfer.presentation.components.transfer_confirm.ShowInputsByTransferType

@Composable
fun TransferConfirmScreen(navigationManager: NavigationManager) {
    val clientBank by remember { mutableStateOf<TransactionClientBank>(transactionClientBanks.first()) }
    var showPaymentBottomSheet by remember { mutableStateOf(false) }
    var saveAccountChecked by remember { mutableStateOf(false) }
    var transferType by remember { mutableStateOf(1) }
    var depositId by remember { mutableStateOf("") }


    AppContent(
        topBar = {
            AppTopBar(title = stringResource(R.string.destination),
                onBack = { navigationManager.navigateBack() })
        },
        horizontalAlignment = Alignment.CenterHorizontally,
        footer = {
            AppButton(modifier = Modifier
                .fillMaxWidth()
                .padding(start = 16.dp, end = 16.dp, top = 16.dp),
                title = stringResource(R.string.confirm),
                onClick = {
                    showPaymentBottomSheet = true
                })
        },
    ) {
        Spacer(modifier = Modifier.size(24.dp))
        ClientBankProfileInfo(
            clientBank = clientBank.clientBank, profileSize = 64.dp, iconSize = 32.dp
        )
        Spacer(modifier = Modifier.size(24.dp))
        BodySmallText(text = stringResource(R.string.transfer_confirm))
        SentencesWithSuffix(
            sentence = "", suffix = stringResource(com.pmb.ballon.R.string.real_carrency)
        )
        Spacer(modifier = Modifier.size(32.dp))
        HorizontalDivider(modifier = Modifier.padding(horizontal = 16.dp))
        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(start = 32.dp, end = 16.dp, top = 12.dp, bottom = 12.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            BodyMediumText(
                text = stringResource(R.string.save_to_destination),
                color = AppTheme.colorScheme.onBackgroundNeutralSubdued
            )
            Switch(checked = saveAccountChecked, onCheckedChange = {
                saveAccountChecked = it
            })
        }
        HorizontalDivider(modifier = Modifier.padding(horizontal = 16.dp))
        Spacer(modifier = Modifier.size(32.dp))
        ShowInputsByTransferType(
            transferType = transferType,
            depositId = depositId,
            onDepositIdChange = { depositId = it },
            accountClickable = { /*showCardsBottomSheet = true*/ })
    }

    if (showPaymentBottomSheet)
        PaymentBottomSheet(
            title = "۶۰۳۷  ۶۹۱۷  ۸۳۳۶  ۳۷۷۳",
            result = { pass2, cvv2, year, month ->
                navigationManager.navigate(TransferScreens.TransferReceipt)
            },
            onRetryPass2 = { },
            onDismiss = {
                showPaymentBottomSheet = false
            })
}