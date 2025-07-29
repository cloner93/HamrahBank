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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.pmb.ballon.component.AlertComponent
import com.pmb.ballon.component.SentencesWithSuffix
import com.pmb.ballon.component.base.AppButton
import com.pmb.ballon.component.base.AppContent
import com.pmb.ballon.component.base.AppLoading
import com.pmb.ballon.component.base.AppTopBar
import com.pmb.ballon.component.base.BodyMediumText
import com.pmb.ballon.component.base.BodySmallText
import com.pmb.ballon.ui.theme.AppTheme
import com.pmb.core.utils.toCurrency
import com.pmb.navigation.manager.LocalNavigationManager
import com.pmb.navigation.moduleScreen.TransferScreens
import com.pmb.transfer.R
import com.pmb.transfer.domain.entity.CardVerificationEntity
import com.pmb.transfer.domain.entity.ReasonEntity
import com.pmb.transfer.domain.entity.TransactionClientBankEntity
import com.pmb.transfer.domain.entity.TransferMethodEntity
import com.pmb.transfer.domain.entity.TransferReceiptEntity
import com.pmb.transfer.domain.entity.TransferSourceEntity
import com.pmb.transfer.presentation.components.ClientBankProfileInfo
import com.pmb.transfer.presentation.components.transfer_confirm.ShowInputsByTransferType
import com.pmb.transfer.presentation.transfer_confirm.viewmodel.TransferConfirmViewActions
import com.pmb.transfer.presentation.transfer_confirm.viewmodel.TransferConfirmViewEvents
import com.pmb.transfer.presentation.transfer_confirm.viewmodel.TransferConfirmViewModel

@Composable
fun TransferConfirmScreen(
    viewModel: TransferConfirmViewModel,
    account: TransactionClientBankEntity?,
    amount: Double?,
    transferMethod: TransferMethodEntity?,
    reason: ReasonEntity?,
    clear: () -> Unit,
    result: (
        source: TransferSourceEntity?,
        receipt: TransferReceiptEntity?,
        verificationInfo: CardVerificationEntity?
    ) -> Unit
) {
    val viewState by viewModel.viewState.collectAsState()
    val navigationManager = LocalNavigationManager.current
    LaunchedEffect(Unit) {
        viewModel.viewEvent.collect { event ->
            when (event) {
                is TransferConfirmViewEvents.NavigateToCardVerification -> {
                    result.invoke(
                        event.source,
                        null,
                        event.verificationInfo
                    )
                    navigationManager.navigate(TransferScreens.TransferCardVerification)
                }

                is TransferConfirmViewEvents.NavigateToReceipt -> {
                    result.invoke(
                        event.receipt.source,
                        event.receipt,
                        null
                    )
                    navigationManager.navigate(TransferScreens.TransferReceipt)
                }
            }
        }
    }

    LaunchedEffect(Unit) {
        viewModel.handle(
            TransferConfirmViewActions.UpdateData(
                account,
                amount,
                transferMethod,
                reason
            )
        )
    }

    AppContent(
        topBar = {
            AppTopBar(
                title = stringResource(R.string.transfer_confirm),
                onBack = {
                    clear.invoke()
                    navigationManager.navigateBack()
                })
        },
        horizontalAlignment = Alignment.CenterHorizontally,
        footer = {
            AppButton(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 16.dp, end = 16.dp, top = 16.dp),
                enable = viewState.enableConfirmButton,
                title = stringResource(R.string.confirm),
                onClick = { viewModel.handle(TransferConfirmViewActions.SubmitTransferData) })
        },
    ) {
        Spacer(modifier = Modifier.size(24.dp))
        if (account != null) {
            ClientBankProfileInfo(item = account)
        }
        Spacer(modifier = Modifier.size(24.dp))
        BodySmallText(
            text = stringResource(R.string.transfer_price),
            color = AppTheme.colorScheme.onBackgroundNeutralSubdued
        )
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
            Switch(checked = viewState.favoriteDestination, onCheckedChange = {
                viewModel.handle(TransferConfirmViewActions.UpdateFavoriteDestination(it))
            })
        }
        HorizontalDivider(modifier = Modifier.padding(horizontal = 16.dp))
        Spacer(modifier = Modifier.size(32.dp))
        transferMethod?.let {
            ShowInputsByTransferType(
                transferMethod = it,
                depositId = viewState.depositId,
                defaultSource = viewState.defaultSource,
                defaultReason = viewState.defaultReason,
                sourceCardBanks = viewState.sourceCardBanks,
                sourceAccountBanks = viewState.sourceAccountBanks,
                onDepositIdChange = { depositId ->
                    viewModel.handle(TransferConfirmViewActions.UpdateDepositId(depositId))
                },
                selectedCardBank = { cardBank ->
                    viewModel.handle(TransferConfirmViewActions.SelectCardBank(cardBank))
                },
                selectedAccountBank = { accountBank ->
                    viewModel.handle(TransferConfirmViewActions.SelectAccountBank(accountBank))
                },
                selectedTransferReason = {
                    navigationManager.navigate(TransferScreens.TransferReason)
                }
            )
        }
    }

    if (viewState.loading) AppLoading()
    viewState.alertState?.let { AlertComponent(it) }
}