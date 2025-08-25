package com.pmb.transfer.presentation.transfer_amount

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.pmb.ballon.component.AlertComponent
import com.pmb.ballon.component.SentencesWithSuffix
import com.pmb.ballon.component.base.AppButton
import com.pmb.ballon.component.base.AppContent
import com.pmb.ballon.component.base.AppLoading
import com.pmb.ballon.component.base.AppNumberTextField
import com.pmb.ballon.component.base.AppTopBar
import com.pmb.ballon.component.base.CaptionText
import com.pmb.ballon.component.base.CommaVisualTransformation
import com.pmb.ballon.ui.theme.AppTheme
import com.pmb.core.utils.Convert
import com.pmb.navigation.manager.LocalNavigationManager
import com.pmb.navigation.moduleScreen.TransferScreens
import com.pmb.transfer.R
import com.pmb.transfer.domain.entity.AccountBankEntity
import com.pmb.transfer.domain.entity.TransactionClientBankEntity
import com.pmb.transfer.domain.entity.TransferMethodEntity
import com.pmb.transfer.presentation.components.ClientBankProfileInfo
import com.pmb.transfer.presentation.transfer_amount.viewmodel.TransferAmountViewActions
import com.pmb.transfer.presentation.transfer_amount.viewmodel.TransferAmountViewEvents
import com.pmb.transfer.presentation.transfer_amount.viewmodel.TransferAmountViewModel

@Composable
fun TransferAmountScreen(
    viewModel: TransferAmountViewModel,
    account: TransactionClientBankEntity?,
    amount: (Double, List<TransferMethodEntity>) -> Unit
) {
    val viewState by viewModel.viewState.collectAsState()
    val navigationManager = LocalNavigationManager.current

    LaunchedEffect(Unit) {
        viewModel.viewEvent.collect { event ->
            when (event) {
                is TransferAmountViewEvents.NavigateToDestinationType -> {
                    amount.invoke(event.amount, event.methods)
                    navigationManager.navigate(TransferScreens.TransferMethod)
                }
            }
        }
    }
    LaunchedEffect(Unit) {
        viewModel.handle(TransferAmountViewActions.UpdateDestination(account = account))
    }

    val trailingIcon: (@Composable () -> Unit)? = viewState.amount.takeIf { it > 0 }?.let {
        {
            CaptionText(
                text = stringResource(com.pmb.ballon.R.string.rial),
                color = AppTheme.colorScheme.onBackgroundNeutralSubdued
            )
        }
    }


    Box(modifier = Modifier.background(color = AppTheme.colorScheme.background1Neutral)) {
        AppContent(topBar = {
            AppTopBar(
                title = stringResource(R.string.transfer_amount_title),
                onBack = { navigationManager.navigateBack() })
        }, footer = {
            AppButton(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 24.dp),
                title = stringResource(R.string.next),
                enable = viewState.enableButton,
                onClick = {
                    viewModel.handle(TransferAmountViewActions.SubmitAmount)
                })
        }) {
            Spacer(modifier = Modifier.size(24.dp))
            account?.let { ClientBankProfileInfo(it) }
            Spacer(modifier = Modifier.size(40.dp))
            AppNumberTextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
                value = if (viewState.amount > 0.0) viewState.amount.toLong().toString() else "",
                showClearButton = false,
                trailingIcon = trailingIcon,
                visualTransformation = CommaVisualTransformation(),
                onValueChange = { amount ->
                    viewModel.handle(
                        TransferAmountViewActions.UpdateAmount(
                            amount.toDoubleOrNull()?.takeIf { it >= 0 } ?: 0.0))
                },
                label = stringResource(R.string.amount),
            )

            Spacer(modifier = Modifier.size(16.dp))

            if (viewState.amount >= 10)
                SentencesWithSuffix(sentence = Convert.numberToWords(viewState.amount))
        }
    }

    if (viewState.loading) AppLoading()
    viewState.alertState?.let { AlertComponent(it) }
}