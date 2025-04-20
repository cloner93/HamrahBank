package com.pmb.transfer.presentation.transfer_verify_card_info

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import com.pmb.ballon.component.AlertComponent
import com.pmb.ballon.component.DynamicPassCardInputField
import com.pmb.ballon.component.base.AppButton
import com.pmb.ballon.component.base.AppContent
import com.pmb.ballon.component.base.AppLoading
import com.pmb.ballon.component.base.AppNumberTextField
import com.pmb.ballon.component.base.AppTopBar
import com.pmb.ballon.component.base.ButtonMediumText
import com.pmb.navigation.manager.LocalNavigationManager
import com.pmb.navigation.moduleScreen.TransferScreens
import com.pmb.transfer.R
import com.pmb.transfer.domain.entity.CardBankEntity
import com.pmb.transfer.domain.entity.CardVerificationEntity
import com.pmb.transfer.domain.entity.TransferReceiptEntity
import com.pmb.transfer.presentation.transfer_verify_card_info.viewmodel.TransferVerifyCardInfoViewActions
import com.pmb.transfer.presentation.transfer_verify_card_info.viewmodel.TransferVerifyCardInfoViewEvents
import com.pmb.transfer.presentation.transfer_verify_card_info.viewmodel.TransferVerifyCardInfoViewModel

@Composable
fun TransferVerifyCardInfoScreen(
    viewModel: TransferVerifyCardInfoViewModel,
    verificationInfo: CardVerificationEntity?,
    cardBank: CardBankEntity?,
    receipt: (TransferReceiptEntity) -> Unit
) {
    val viewState by viewModel.viewState.collectAsState()
    cardBank?.let { viewModel.handle(TransferVerifyCardInfoViewActions.UpdateCardInfo(cardBank)) }
    val navigationManager = LocalNavigationManager.current
    LaunchedEffect(Unit) {
        viewModel.viewEvent.collect { event ->
            when (event) {
                is TransferVerifyCardInfoViewEvents.NavigateToReceipt -> {
                    receipt.invoke(event.receipt)
                    navigationManager.navigate(TransferScreens.TransferReceipt)
                }
            }
        }
    }

    LaunchedEffect(Unit) {
        verificationInfo?.let {
            viewModel.handle(TransferVerifyCardInfoViewActions.TransferVerify(verificationInfo))
        }
    }

    AppContent(
        topBar = {
            AppTopBar(
                title = stringResource(R.string.cart_info),
                onBack = {
                    viewModel.handle(TransferVerifyCardInfoViewActions.CancelTimer)
                    navigationManager.navigateBack()
                })
        },
        footer = {
            AppButton(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
                title = stringResource(R.string.confirm_and_transfer),
                enable = viewState.enableButton,
                onClick = {
                    viewModel.handle(TransferVerifyCardInfoViewActions.SubmitCardInfo)
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
                    CompositionLocalProvider(LocalLayoutDirection provides LayoutDirection.Ltr) {
                        ButtonMediumText(text = it.cardNumberFormated)
                    }
                }
                Spacer(modifier = Modifier.size(32.dp))

                AppNumberTextField(
                    value = viewState.cvv2,
                    label = stringResource(R.string.cvv2),
                    showClearButton = false,
                    onValueChange = {
                        if (it.length <= 4)
                            viewModel.handle(TransferVerifyCardInfoViewActions.UpdateCvv2(it))
                    },
                )

                Spacer(modifier = Modifier.size(32.dp))

                DynamicPassCardInputField(
                    dyPass = viewState.password,
                    timer = viewState.timer,
                    retryEnabled = viewState.enableResend,
                    onValueChange = {
                        viewModel.handle(TransferVerifyCardInfoViewActions.UpdateDyPassword(it))
                    },
                    onRetryDyPass = {
                        viewModel.handle(TransferVerifyCardInfoViewActions.ResendCardInfo)
                    })
            }
        })

    if (viewState.loading) AppLoading()
    viewState.alertState?.let { AlertComponent(it) }
}