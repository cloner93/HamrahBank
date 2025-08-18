package com.pmb.account.presentation.issueCard.selectCardNo

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.Icon
import androidx.compose.material3.MenuAnchorType
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.pmb.account.presentation.issueCard.IssueCardSharedState
import com.pmb.account.presentation.issueCard.selectCardNo.viewmodel.SelectCardNoViewActions
import com.pmb.account.presentation.issueCard.selectCardNo.viewmodel.SelectCardNoViewEvents
import com.pmb.account.presentation.issueCard.selectCardNo.viewmodel.SelectCardNoViewModel
import com.pmb.ballon.component.AlertComponent
import com.pmb.ballon.component.CustomSpinner
import com.pmb.ballon.component.base.AppBaseTextField
import com.pmb.ballon.component.base.AppButton
import com.pmb.ballon.component.base.AppContent
import com.pmb.ballon.component.base.AppTopBar
import com.pmb.ballon.component.base.BodyMediumText
import com.pmb.ballon.component.base.BodySmallText
import com.pmb.ballon.component.base.CaptionText
import com.pmb.ballon.component.base.ClickableIcon
import com.pmb.ballon.component.base.IconType
import com.pmb.ballon.ui.theme.AppTheme
import com.pmb.core.utils.toCurrency
import com.pmb.domain.model.DepositModel
import com.pmb.domain.model.card.FetchCommissionForCreateCardResponse
import com.pmb.navigation.manager.LocalNavigationManager
import com.pmb.navigation.moduleScreen.AccountScreens

@Composable
fun SelectCardNoScreen(
    viewmodel: SelectCardNoViewModel,
    sharedValue: IssueCardSharedState,
    onUpdateOwnerAccount: (DepositModel, DepositModel) -> Unit,
    onUpdateSelectedCard: (String) -> Unit,
    onCommissions: (FetchCommissionForCreateCardResponse) -> Unit,
) {
    val viewState by viewmodel.viewState.collectAsState()
    val navigationManager = LocalNavigationManager.current

    LaunchedEffect(Unit) {
        viewmodel.viewEvent.collect { event ->
            when (event) {
                SelectCardNoViewEvents.NavigateBack -> navigationManager.navigateBack()
            }
        }
    }

    AppContent(
        modifier = Modifier.padding(horizontal = 16.dp),
        backgroundColor = AppTheme.colorScheme.background1Neutral,
        footer = {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                val bool = viewState.selectedFeeDeposit != null && when (viewState.cardType) {
                    CardType.OLD_CARD -> !viewState.selectedOldCard.isNullOrEmpty()
                    CardType.NEW_CARD -> true
                    else -> false
                }

                viewState.commissionFee?.let { commission ->
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable {
                                viewState.commissionFee?.let {
                                    onCommissions(it)
                                    navigationManager.navigate(AccountScreens.IssueCard.IssueCardFeeScreen)
                                }
                            },
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Row {
                            BodySmallText(
                                text = "مجموع\u200C کارمزدها:",
                                color = AppTheme.colorScheme.onBackgroundNeutralSubdued
                            )
                            Spacer(Modifier.width(6.dp))
                            BodySmallText(
                                text = commission.totalAmount.toDouble()
                                    .toCurrency() + " ریال",
                                color = AppTheme.colorScheme.onBackgroundNeutralSubdued
                            )
                        }
                        BodySmallText(
                            text = "مشاهده جزئیات",
                            color = AppTheme.colorScheme.onBackgroundNeutralDefault
                        )
                    }
                }

                AppButton(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 16.dp),
                    title = "تایید و ادامه",
                    enable = bool,
                    onClick = {
                        onUpdateOwnerAccount(
                            viewState.deposits.first { it.depositNumber == sharedValue.accountNumber },
                            viewState.selectedFeeDeposit!!
                        )

                        navigationManager.navigate(AccountScreens.IssueCard.IssueCardConfirmScreen)
                    })
            }
        },
        topBar = {
            AppTopBar(
                title = "صدور کارت برای سپرده",
                startIcon = ClickableIcon(
                    icon = IconType.ImageVector(Icons.Default.ArrowForward),
                    onClick = {
                        navigationManager.navigateBack()
                    })
            )
        }) {

        Spacer(modifier = Modifier.height(24.dp))

        if (!sharedValue.panList.isNullOrEmpty()) {
            CustomSpinner(
                modifier = Modifier.fillMaxWidth(),
                options = listOf(CardType.NEW_CARD.type, CardType.OLD_CARD.type),
                labelString = "نحوه اختصاص شماره کارت",
                displayText = viewState.cardType.type,
                readOnly = true,
                isEnabled = true
            ) { title ->
                var cardType = CardType.UNSPECIFIED

                if (title == CardType.NEW_CARD.type)
                    cardType = CardType.NEW_CARD
                else if (title == CardType.OLD_CARD.type)
                    cardType = CardType.OLD_CARD

                viewmodel.handle(SelectCardNoViewActions.ChangeCardType(cardType))
            }
        } else
            viewmodel.handle(SelectCardNoViewActions.ChangeCardType(CardType.NEW_CARD))

        if (viewState.cardType != CardType.UNSPECIFIED) {
            if (viewState.cardType == CardType.OLD_CARD) {
                Spacer(Modifier.height(12.dp))
                CustomSpinner(
                    modifier = Modifier.fillMaxWidth(),
                    options = sharedValue.panList?.map { it.toString() },
                    labelString = "شماره کارت",
                    displayText = viewState.selectedOldCard ?: "",
                    readOnly = true,
                    isEnabled = true
                ) { title ->
                    onUpdateSelectedCard(title.replace(" ", ""))
                    viewmodel.handle(SelectCardNoViewActions.SelectOldCard(title))
                }
            }

            Spacer(Modifier.height(12.dp))
            BankAccountSpinner(
                modifier = Modifier.fillMaxWidth(),
                options = viewState.deposits,
                labelString = "کسر کارمزد از حساب",
                isEnabled = true,
                readOnly = true,
                isError = !viewState.depositsError.isNullOrEmpty(),
                errorText = viewState.depositsError,
                selectedAccount = viewState.selectedFeeDeposit,
            ) { deposit ->
                viewmodel.handle(SelectCardNoViewActions.SelectFeeDeposit(deposit))
            }
        }
    }

    if (viewState.alertModelState != null) {
        AlertComponent(viewState.alertModelState!!)
    }
}

enum class CardType(val type: String) {
    NEW_CARD("شماره کارت جدید"), OLD_CARD("شماره کارت قبلی"), UNSPECIFIED("")
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun BankAccountSpinner(
    modifier: Modifier = Modifier,
    options: List<DepositModel>?,
    labelString: String,
    selectedAccount: DepositModel?,
    isEnabled: Boolean = true,
    isError: Boolean = false,
    errorText: String? = null,
    readOnly: Boolean = true,
    onOptionSelected: (DepositModel) -> Unit
) {
    var expanded by remember { mutableStateOf(false) }

    ExposedDropdownMenuBox(
        expanded = expanded,
        onExpandedChange = {
            if (isEnabled) expanded = !expanded
        }
    ) {
        AppBaseTextField(
            modifier = modifier
                .menuAnchor(MenuAnchorType.PrimaryNotEditable, true)
                .clickable {
                    if (isEnabled) expanded = !expanded
                },
            value = selectedAccount?.let {
                "${it.title} (${it.depositNumber})" +
                        "\nقابل برداشت: ${it.amount.toCurrency()} ${it.currency}"
            } ?: "",
            onValueChange = {},
            label = labelString,
            isError = isError,
            errorText = errorText,
            enabled = isEnabled,
            readOnly = readOnly,
            hideCursor = true,
            trailingIcon = {
                Icon(
                    imageVector = if (expanded) Icons.Default.KeyboardArrowUp else Icons.Default.KeyboardArrowDown,
                    contentDescription = "Dropdown Icon",
                    tint = AppTheme.colorScheme.onBackgroundNeutralDefault
                )
            },
        )

        ExposedDropdownMenu(
            modifier = Modifier.heightIn(max = 300.dp),
            expanded = expanded,
            containerColor = AppTheme.colorScheme.background1Neutral,
            onDismissRequest = { expanded = false }
        ) {
            options?.forEach { deposit ->
                DropdownMenuItem(
                    text = {
                        Column {
                            Spacer(Modifier.height(8.dp))
                            BodyMediumText(
                                text = deposit.let {
                                    "${it.title} (${it.depositNumber})"
                                },
                                color = AppTheme.colorScheme.foregroundNeutralDefault,
                                textAlign = TextAlign.Start
                            )
                            CaptionText(
                                text = deposit.let {
                                    "\nقابل برداشت: ${it.amount.toCurrency()} ${it.currency}"
                                },
                                color = AppTheme.colorScheme.foregroundNeutralDefault,
                                textAlign = TextAlign.Start
                            )
                        }
                    },
                    onClick = {
                        onOptionSelected(deposit)
                        expanded = false
                    },
                )
            }
        }
    }
}