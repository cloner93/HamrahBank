package com.pmb.transfer.presentation.transfer_amount

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.core.text.isDigitsOnly
import com.pmb.ballon.component.SentencesWithSuffix
import com.pmb.ballon.component.base.AppButton
import com.pmb.ballon.component.base.AppContent
import com.pmb.ballon.component.base.AppNumberTextField
import com.pmb.ballon.component.base.AppTopBar
import com.pmb.ballon.ui.theme.AppTheme
import com.pmb.core.presentation.NavigationManager
import com.pmb.core.utils.Convert
import com.pmb.transfer.R
import com.pmb.transfer.domain.TransactionClientBank
import com.pmb.transfer.domain.transactionClientBanks
import com.pmb.transfer.presentation.TransferScreens
import com.pmb.transfer.presentation.components.ClientBankProfileInfo

@Composable
fun AmountScreen(navigationManager: NavigationManager) {
    var isValid by remember { mutableStateOf(false) }
    var identifierNumber by remember { mutableStateOf("") }
    val clientBank by remember { mutableStateOf<TransactionClientBank>(transactionClientBanks.first()) }

    Box(modifier = Modifier.background(color = AppTheme.colorScheme.background1Neutral)) {
        AppContent(topBar = {
            AppTopBar(title = stringResource(R.string.destination),
                onBack = { navigationManager.navigateBack() })
        }, footer = {
            AppButton(modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 24.dp),
                title = stringResource(R.string.next),
                enable = isValid,
                onClick = {
                    navigationManager.navigate(TransferScreens.TransferMethod)
                })
        }) {
            ClientBankProfileInfo(clientBank = clientBank.clientBank)
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
}