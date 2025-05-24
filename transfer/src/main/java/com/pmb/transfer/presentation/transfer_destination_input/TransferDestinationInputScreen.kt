package com.pmb.transfer.presentation.transfer_destination_input

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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import com.pmb.ballon.component.AlertComponent
import com.pmb.ballon.component.ReadClipboardOnFirstLoad
import com.pmb.ballon.component.base.AppButton
import com.pmb.ballon.component.base.AppContent
import com.pmb.ballon.component.base.AppImage
import com.pmb.ballon.component.base.AppLoading
import com.pmb.ballon.component.base.AppNumberTextField
import com.pmb.ballon.component.base.AppTextButton
import com.pmb.ballon.component.base.AppTopBar
import com.pmb.ballon.component.base.BodySmallText
import com.pmb.ballon.models.AppButton
import com.pmb.ballon.models.ImageStyle
import com.pmb.ballon.models.Size
import com.pmb.ballon.ui.theme.AppTheme
import com.pmb.navigation.manager.LocalNavigationManager
import com.pmb.navigation.moduleScreen.TransferScreens
import com.pmb.transfer.R
import com.pmb.transfer.domain.entity.BankIdentifierNumberType
import com.pmb.transfer.domain.entity.TransactionClientBankEntity
import com.pmb.transfer.presentation.components.transfer_confirm.ClientBankInfoTypeRow
import com.pmb.transfer.presentation.transfer_destination_input.viewmodel.TransferDestinationInputViewActions
import com.pmb.transfer.presentation.transfer_destination_input.viewmodel.TransferDestinationInputViewEvents
import com.pmb.transfer.presentation.transfer_destination_input.viewmodel.TransferDestinationInputViewModel
import com.pmb.transfer.utils.BankUtil

@Composable
fun DestinationInputScreen(
    viewModel: TransferDestinationInputViewModel,
    selectedAccount: (TransactionClientBankEntity) -> Unit
) {
    val viewState by viewModel.viewState.collectAsState()
    val navigationManager = LocalNavigationManager.current
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

    ReadClipboardOnFirstLoad {
        viewModel.handle(TransferDestinationInputViewActions.ReadTextFromClipboard(it))
    }

    AppContent(
        modifier = Modifier.padding(horizontal = 16.dp),
        backgroundColor = AppTheme.colorScheme.background1Neutral,
        topBar = {
            AppTopBar(
                title = stringResource(R.string.destination),
                onBack = { navigationManager.navigateBack() })
        },
        footer = {
            AppButton(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
                title = stringResource(R.string.next),
                enable = viewState.enableButton,
                onClick = {
                    viewModel.handle(TransferDestinationInputViewActions.CheckAccount)
                })
        }
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 24.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            AppNumberTextField(
                modifier = Modifier
                    .fillMaxWidth(),
                onValueChange = { newText ->
                    newText
                        .takeIf { it.all { ch -> ch.isDigit() } && it.length <= viewState.identifierNumberMaxLength }
                        ?.let {
                            viewModel.handle(
                                TransferDestinationInputViewActions.UpdateIdentifierNumber(
                                    it
                                )
                            )
                        }
                },
                value = viewState.identifierNumber,
                label = stringResource(R.string.card_account_iban_number),
                showClearButton = false,
                trailingIcon = BankUtil.getBankPainter(viewState.identifierNumber)?.let {
                    {
                        AppImage(
                            modifier = Modifier.padding(horizontal = 16.dp),
                            image = it,
                            style = ImageStyle(size = Size.FIX(24.dp))
                        )
                    }
                },
            )

            Spacer(modifier = Modifier.height(32.dp))

            if (viewState.showFavorite)
                AppTextButton(
                    title = stringResource(R.string.select_from_favorites),
                    colors = AppButton.textButtonRedColors(),
                    onClick = {
                        navigationManager.navigate(TransferScreens.TransferSelectFavorite)
                    })

            if (viewState.showAccount)
                viewState.accountDetail?.let {
                    Box(modifier = Modifier.padding(vertical = 12.dp)) {
                        ClientBankInfoTypeRow(
                            info = it,
                            background = Color(0xFFF3F3F7),
                            onClick = {
                                selectedAccount.invoke(it)
                                navigationManager.navigate(TransferScreens.TransferAmount)
                            })
                    }
                }
            if (viewState.showClipboard) {
                Spacer(modifier = Modifier.height(8.dp))
                viewState.clipboardTextType?.let { type ->
                    BodySmallText(
                        text = stringResource(
                            R.string.clipboard_title_message, stringResource(
                                when (type) {
                                    BankIdentifierNumberType.CARD -> R.string.card
                                    BankIdentifierNumberType.IBAN -> R.string.iban
                                    BankIdentifierNumberType.ACCOUNT -> R.string.account
                                }
                            )
                        ),
                        color = AppTheme.colorScheme.onBackgroundNeutralSubdued
                    )
                    Spacer(modifier = Modifier.height(12.dp))
                }

                AppButton(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 12.dp),
                    title = viewState.identifierNumberClipboard,
                    colors = AppButton.buttonColors().copy(
                        containerColor = AppTheme.colorScheme.stateLayerNeutralHovered,
                        contentColor = AppTheme.colorScheme.foregroundNeutralDefault
                    ),
                    layoutDirection = LayoutDirection.Ltr,
                    onClick = {
                        viewModel.handle(TransferDestinationInputViewActions.ClickedOnClipboard)
                    })
            }
        }
    }
    if (viewState.loading) AppLoading()
    viewState.alertState?.let { AlertComponent(it) }
}
