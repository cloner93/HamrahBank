package com.pmb.transfer.presentation.destination_input

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.pmb.ballon.component.base.AppBaseTextField
import com.pmb.ballon.component.base.AppButton
import com.pmb.ballon.component.base.AppContent
import com.pmb.ballon.component.base.AppImage
import com.pmb.ballon.component.base.TopBar
import com.pmb.ballon.models.ImageStyle
import com.pmb.ballon.models.Size
import com.pmb.ballon.ui.theme.AppTheme
import com.pmb.core.presentation.NavigationManager
import com.pmb.transfer.R
import com.pmb.transfer.domain.BankIdentifierNumberType
import com.pmb.transfer.domain.ClientBank
import com.pmb.transfer.domain.TransactionClientBank
import com.pmb.transfer.presentation.components.ClientBankInfoTypeRow
import com.pmb.transfer.utils.BankUtil

@Composable
fun DestinationInputScreen(navigationManager: NavigationManager) {
    var isValid by remember { mutableStateOf(false) }
    var identifierNumber by remember { mutableStateOf("") }
    var clientBank by remember { mutableStateOf<TransactionClientBank?>(null) }

    Box(modifier = Modifier.background(color = AppTheme.colorScheme.background1Neutral))
    {
        AppContent(
            topBar = TopBar(
                title = stringResource(R.string.destination),
                onBack = { navigationManager.navigateBack() }),
            footer = {
                AppButton(modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 24.dp),
                    title = stringResource(R.string.next),
                    enable = isValid,
                    onClick = {

                    })
            }
        ) {
            AppBaseTextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
                value = identifierNumber,
                onValueChange = {
                    identifierNumber = it
                    isValid = BankUtil.getBankByCardNumber(identifierNumber) != null
                    if (isValid) {
                        clientBank = TransactionClientBank(
                            clientBank = ClientBank(
                                name = "محمد صادقی",
                                phoneNumber = "09123456789",
                                profileUrl = "https://randomuser.me/api/portraits/men/1.jpg",
                                cardNumber = 6037991234567890,
                                accountNumber = "1234567890123456",
                                iban = "IR820540102680020817909002"
                            ),
                            type = BankIdentifierNumberType.CARD
                        )
                    }
                },
                label = stringResource(R.string.card_account_iban_number),
                trailingIcon = {
                    BankUtil.getBankPainter(identifierNumber)?.let {
                        AppImage(
                            modifier = Modifier.padding(horizontal = 16.dp),
                            image = it,
                            style = ImageStyle(size = Size.FIX(24.dp))
                        )
                    }
                },
            )

            clientBank?.let {
                Box(modifier = Modifier.padding(horizontal = 16.dp, vertical = 12.dp)) {
                    ClientBankInfoTypeRow(info = it,
                        background = Color(0xFFF3F3F7),
                        onClick = {

                        })
                }
            }

        }
    }
}
