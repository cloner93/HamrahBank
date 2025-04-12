package com.pmb.transfer.presentation.transfer_confirm

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Switch
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
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
import com.pmb.core.utils.toCurrency
import com.pmb.transfer.R
import com.pmb.transfer.domain.entity.TransactionClientBankEntity
import com.pmb.transfer.domain.entity.TransferMethodEntity
import com.pmb.transfer.presentation.components.ClientBankProfileInfo
import com.pmb.transfer.presentation.components.transfer_confirm.ShowInputsByTransferType
import com.pmb.transfer.presentation.transfer_confirm.viewmodel.TransferConfirmViewEvents
import com.pmb.transfer.presentation.transfer_confirm.viewmodel.TransferConfirmViewModel

@Composable
fun TransferConfirmScreen(
    navigationManager: NavigationManager,
    viewModel: TransferConfirmViewModel,
    account: TransactionClientBankEntity?,
    amount: Long?,
    transferMethod: TransferMethodEntity?
) {
    val viewState by viewModel.viewState.collectAsState()
    var showPaymentBottomSheet by remember { mutableStateOf(false) }
    var saveAccountChecked by remember { mutableStateOf(false) }
    var depositId by remember { mutableStateOf("") }

    LaunchedEffect(Unit) {
        viewModel.viewEvent.collect { event ->
            when (event) {
                is TransferConfirmViewEvents.NavigateToOtp -> {

                }
            }
        }
    }

    AppContent(
        topBar = {
            AppTopBar(
                title = stringResource(R.string.transfer_confirm),
                onBack = { navigationManager.navigateBack() })
        },
        horizontalAlignment = Alignment.CenterHorizontally,
        footer = {
            AppButton(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 16.dp, end = 16.dp, top = 16.dp),
                title = stringResource(R.string.confirm),
                onClick = {
                })
        },
    ) {
        Spacer(modifier = Modifier.size(24.dp))
        if (account != null) {
            ClientBankProfileInfo(item = account, profileSize = 64.dp, iconSize = 32.dp)
        }
        Spacer(modifier = Modifier.size(24.dp))
        BodySmallText(text = stringResource(R.string.transfer_confirm))
        amount?.let {
            SentencesWithSuffix(
                sentence = it.toDouble().toCurrency(),
                suffix = stringResource(com.pmb.ballon.R.string.real_carrency)
            )
        }
        Spacer(modifier = Modifier.size(32.dp))
        HorizontalDivider(modifier = Modifier.padding(horizontal = 16.dp))
        Row(
            modifier = Modifier
                .fillMaxWidth()
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
        transferMethod?.let {
            ShowInputsByTransferType(
                transferMethod = it,
                depositId = depositId,
                onDepositIdChange = { depositId = it },
                accountClickable = { showPaymentBottomSheet = true })
        }
    }

//    if (showPaymentBottomSheet)
//        PaymentBottomSheet(
//            title = "۶۰۳۷  ۶۹۱۷  ۸۳۳۶  ۳۷۷۳",
//            result = { pass2, cvv2, year, month ->
//                navigationManager.navigate(TransferScreens.TransferReceipt)
//            },
//            onRetryPass2 = { },
//            onDismiss = {
//                showPaymentBottomSheet = false
//            })
}