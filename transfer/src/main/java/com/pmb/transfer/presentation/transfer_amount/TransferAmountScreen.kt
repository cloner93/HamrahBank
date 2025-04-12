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
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.core.text.isDigitsOnly
import com.pmb.ballon.component.AlertComponent
import com.pmb.ballon.component.SentencesWithSuffix
import com.pmb.ballon.component.base.AppButton
import com.pmb.ballon.component.base.AppContent
import com.pmb.ballon.component.base.AppLoading
import com.pmb.ballon.component.base.AppNumberTextField
import com.pmb.ballon.component.base.AppTopBar
import com.pmb.ballon.ui.theme.AppTheme
import com.pmb.core.presentation.NavigationManager
import com.pmb.core.utils.Convert
import com.pmb.transfer.R
import com.pmb.transfer.domain.entity.TransactionClientBankEntity
import com.pmb.transfer.presentation.TransferScreens
import com.pmb.transfer.presentation.components.ClientBankProfileInfo
import com.pmb.transfer.presentation.transfer_amount.viewmodel.TransferAmountViewActions
import com.pmb.transfer.presentation.transfer_amount.viewmodel.TransferAmountViewEvents
import com.pmb.transfer.presentation.transfer_amount.viewmodel.TransferAmountViewModel

@Composable
fun TransferAmountScreen(
    navigationManager: NavigationManager,
    viewModel: TransferAmountViewModel,
    account: TransactionClientBankEntity?,
    amount: (Long) -> Unit
) {
    val viewState by viewModel.viewState.collectAsState()

    var isValid by remember { mutableStateOf(false) }
    var identifierNumber by remember { mutableStateOf("") }

    LaunchedEffect(Unit) {
        viewModel.viewEvent.collect { event ->
            when (event) {
                is TransferAmountViewEvents.NavigateToDestinationType -> {
                    amount.invoke(event.amount)
                    navigationManager.navigate(TransferScreens.TransferMethod)
                }
            }
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
                enable = isValid,
                onClick = {
                    viewModel.handle(TransferAmountViewActions.SubmitAmount(identifierNumber.toLong()))
                })
        }) {
            Spacer(modifier = Modifier.size(24.dp))
            account?.let { ClientBankProfileInfo(it) }
            Spacer(modifier = Modifier.size(40.dp))
            AppNumberTextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
                value = identifierNumber,
                onValueChange = {
                    identifierNumber = it
                    isValid = if (it.isNotEmpty() && it.isDigitsOnly())
                        it.toBigInteger() >= 10.toBigInteger() else false
                },
                label = stringResource(R.string.amount),
            )

            Spacer(modifier = Modifier.size(16.dp))

            if (isValid)
                SentencesWithSuffix(
                    sentence = Convert.numberToWords(identifierNumber.toBigInteger()),
                )
        }
    }

    if (viewState.loading) AppLoading()
    viewState.alertState?.let { AlertComponent(it) }
}