package com.pmb.transfer.presentation.transfer_confirm_otp

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.pmb.ballon.component.AlertComponent
import com.pmb.ballon.component.DynamicPassCardInputField
import com.pmb.ballon.component.base.AppButton
import com.pmb.ballon.component.base.AppContent
import com.pmb.ballon.component.base.AppLoading
import com.pmb.ballon.component.base.AppNumberTextField
import com.pmb.ballon.component.base.AppTopBar
import com.pmb.ballon.component.base.ButtonMediumText
import com.pmb.core.presentation.NavigationManager
import com.pmb.transfer.R
import com.pmb.transfer.domain.entity.CardBankEntity
import com.pmb.transfer.domain.entity.TransferConfirmEntity
import com.pmb.transfer.domain.entity.TransferReceiptEntity
import com.pmb.transfer.presentation.TransferScreens
import com.pmb.transfer.presentation.transfer_confirm_otp.viewmodel.TransferConfirmOtpViewActions
import com.pmb.transfer.presentation.transfer_confirm_otp.viewmodel.TransferConfirmOtpViewEvents
import com.pmb.transfer.presentation.transfer_confirm_otp.viewmodel.TransferConfirmOtpViewModel
import com.pmb.transfer.utils.BankUtil

@Composable
fun TransferConfirmOtpScreen(
    navigationManager: NavigationManager,
    viewModel: TransferConfirmOtpViewModel,
    transferConfirm: TransferConfirmEntity?,
    cardBank: CardBankEntity?,
    receipt: (TransferReceiptEntity) -> Unit
) {
    val viewState by viewModel.viewState.collectAsState()
    cardBank?.let { viewModel.handle(TransferConfirmOtpViewActions.UpdateCardBank(cardBank)) }

    LaunchedEffect(Unit) {
        viewModel.viewEvent.collect { event ->
            when (event) {
                is TransferConfirmOtpViewEvents.NavigateToReceipt -> {
                    receipt.invoke(event.receipt)
                    navigationManager.navigate(TransferScreens.TransferReceipt)
                }
            }
        }
    }

    LaunchedEffect(Unit) {
        transferConfirm?.let {
            viewModel.handle(TransferConfirmOtpViewActions.TransferConfirm(transferConfirm))
        }
    }

    AppContent(
        topBar = {
            AppTopBar(
                title = stringResource(R.string.cart_info),
                onBack = { navigationManager.navigateBack() })
        },
        footer = {
            AppButton(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
                title = stringResource(R.string.confirm_and_transfer),
                enable = viewState.enableButton,
                onClick = {
                    viewModel.handle(TransferConfirmOtpViewActions.SubmitOtp)
                })
        },
        content = {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Spacer(modifier = Modifier.size(24.dp))
                viewState.cardBank?.let {
                    ButtonMediumText(text = BankUtil.formatCardNumber(it.cardNumber))
                }
                Spacer(modifier = Modifier.size(32.dp))

                AppNumberTextField(
                    value = viewState.cvv2,
                    label = "CVV2",
                    trailingIcon = {},
                    onValueChange = {
                        if (it.length <= 4)
                            viewModel.handle(TransferConfirmOtpViewActions.UpdateCvv2(it))
                    },
                )

                Spacer(modifier = Modifier.size(32.dp))

                DynamicPassCardInputField(
                    dyPass = viewState.password,
                    timer = viewState.timer,
                    retryEnabled = viewState.enableResend,
                    onValueChange = {
                        viewModel.handle(TransferConfirmOtpViewActions.UpdateDyPassword(it))
                    },
                    onRetryDyPass = {
                        viewModel.handle(TransferConfirmOtpViewActions.ResendOtp)
                    })
            }
        })

    if (viewState.loading) AppLoading()
    viewState.alertState?.let { AlertComponent(it) }
}