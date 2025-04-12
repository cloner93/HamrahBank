package com.pmb.transfer.presentation.transfer_destination_input

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.pmb.ballon.component.AlertComponent
import com.pmb.ballon.component.base.AppBaseTextField
import com.pmb.ballon.component.base.AppButton
import com.pmb.ballon.component.base.AppContent
import com.pmb.ballon.component.base.AppImage
import com.pmb.ballon.component.base.AppLoading
import com.pmb.ballon.component.base.AppTextButton
import com.pmb.ballon.component.base.AppTopBar
import com.pmb.ballon.models.ImageStyle
import com.pmb.ballon.models.Size
import com.pmb.ballon.ui.theme.AppTheme
import com.pmb.core.presentation.NavigationManager
import com.pmb.transfer.R
import com.pmb.transfer.domain.entity.TransactionClientBankEntity
import com.pmb.transfer.presentation.TransferScreens
import com.pmb.transfer.presentation.components.transfer_confirm.ClientBankInfoTypeRow
import com.pmb.transfer.presentation.transfer_destination_input.viewmodel.TransferDestinationInputViewActions
import com.pmb.transfer.presentation.transfer_destination_input.viewmodel.TransferDestinationInputViewEvents
import com.pmb.transfer.presentation.transfer_destination_input.viewmodel.TransferDestinationInputViewModel
import com.pmb.transfer.utils.BankUtil

@Composable
fun DestinationInputScreen(
    navigationManager: NavigationManager,
    viewModel: TransferDestinationInputViewModel,
    selectedAccount: (TransactionClientBankEntity) -> Unit
) {
    val viewState by viewModel.viewState.collectAsState()
    var isValid by remember { mutableStateOf(false) }
    var identifierNumber by remember { mutableStateOf("") }

    LaunchedEffect(Unit) {
        viewModel.viewEvent.collect { event ->
            when (event) {
                is TransferDestinationInputViewEvents.NavigateToDestinationAmount -> {
                    selectedAccount(event.value)
                    navigationManager.navigate(TransferScreens.TransferAmount)
                }
            }
        }
    }

    Box(modifier = Modifier.background(color = AppTheme.colorScheme.background1Neutral))
    {
        AppContent(
            topBar = {
                AppTopBar(
                    title = stringResource(R.string.destination),
                    onBack = { navigationManager.navigateBack() })
            },
            footer = {
                AppButton(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 24.dp),
                    title = stringResource(R.string.next),
                    enable = isValid,
                    onClick = {
                        viewModel.handle(
                            TransferDestinationInputViewActions.CheckAccount(identifierNumber)
                        )
                    })
            }
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(top = 24.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                AppBaseTextField(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp),
                    value = identifierNumber,
                    onValueChange = { newText ->
                        if (newText.all { it.isDigit() }) {
                            identifierNumber = newText
                            isValid = BankUtil.getBankByCardNumber(identifierNumber) != null
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

                Spacer(modifier = Modifier.height(32.dp))

                if (viewState.accountDetail == null)
                    AppTextButton(
                        title = stringResource(R.string.select_from_favorites),
                        colors = com.pmb.ballon.models.AppButton.textButtonRedColors(),
                        onClick = {
                            navigationManager.navigate(TransferScreens.TransferSelectFavorite)
                        })

                viewState.accountDetail?.let {
                    Box(modifier = Modifier.padding(horizontal = 16.dp, vertical = 12.dp)) {
                        ClientBankInfoTypeRow(
                            info = it,
                            background = Color(0xFFF3F3F7),
                            onClick = {
                                navigationManager.apply {
                                    setCurrentScreenData("transferData", null)
                                    navigate(TransferScreens.TransferAmount)
                                }
                            })
                    }
                }
            }
        }
    }

    if (viewState.loading) AppLoading()
    viewState.alertState?.let { AlertComponent(it) }
}
