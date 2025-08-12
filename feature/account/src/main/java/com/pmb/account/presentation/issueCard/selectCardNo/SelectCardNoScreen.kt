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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.pmb.ballon.component.CustomSpinner
import com.pmb.ballon.component.annotation.AppPreview
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
import com.pmb.ballon.ui.theme.HamrahBankTheme
import com.pmb.core.utils.toCurrency
import com.pmb.domain.model.DepositModel

@Composable
fun SelectCardNoScreen() {
    var spinnerText by remember { mutableStateOf("") }
    var selectedOldCard by remember { mutableStateOf("") }
    var cardType by remember { mutableStateOf(CardType.UNSPECIFIED) }

    val list = listOf(
        DepositModel(
            title = "حساب قرض الحسنه",
            desc = "",
            depositNumber = "523452345234",
            categoryCode = 1,
            amount = 234523.0,
            currency = "ریال",
            ibanNumber = "2345234523542345245",
            cardNumber = "2345234523452345234",
        ),
        DepositModel(
            title = "حساب بلند مدت ",
            desc = "",
            depositNumber = "3456345",
            categoryCode = 1,
            amount = 666666666.0,
            currency = "ریال",
            ibanNumber = "234532452345",
            cardNumber = "2343452345452345234",
        )
    )

    var selectedFeeDeposit by remember { mutableStateOf(list.firstOrNull()) }
    val listOfOldCards = listOf("5029 4321 7654 9876")

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

                val bool = selectedFeeDeposit != null && when (cardType) {
                    CardType.OLD_CARD -> selectedOldCard.isNotEmpty()
                    CardType.NEW_CARD -> true
                    else -> false
                }

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Row {
                        BodySmallText(
                            text = "مجموع\u200C کارمزدها:",
                            color = AppTheme.colorScheme.onBackgroundNeutralSubdued
                        )
                        Spacer(Modifier.width(6.dp))
                        BodySmallText(
                            text = 5000000.0.toCurrency() + " ریال",
                            color = AppTheme.colorScheme.onBackgroundNeutralSubdued
                        )
                    }
                    BodySmallText(
                        text = "مشاهده جزئیات",
                        color = AppTheme.colorScheme.onBackgroundNeutralDefault
                    )
                }

                AppButton(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 16.dp),
                    title = "تایید و ادامه",
                    enable = bool,
                    onClick = { })
            }
        },
        topBar = {
            AppTopBar(
                title = "صدور کارت برای سپرده",
                startIcon = ClickableIcon(
                    icon = IconType.ImageVector(Icons.Default.ArrowForward),
                    onClick = {
//                        navigationManager.navigateBack()
                    })
            )
        }) {

        Spacer(modifier = Modifier.height(24.dp))
        CustomSpinner(
            modifier = Modifier.fillMaxWidth(),
            options = listOf("شماره کارت جدید", "شماره کارت قبلی"),
            labelString = "نحوه اختصاص شماره کارت",
            displayText = spinnerText,
            readOnly = true,
            isEnabled = true
        ) { title ->
            spinnerText = title
            if (title == "شماره کارت جدید")
                cardType = CardType.NEW_CARD
            else if (title == "شماره کارت قبلی")
                cardType = CardType.OLD_CARD
        }

        if (cardType != CardType.UNSPECIFIED) {
            if (cardType == CardType.OLD_CARD) {
                Spacer(Modifier.height(12.dp))
                CustomSpinner(
                    modifier = Modifier.fillMaxWidth(),
                    options = listOfOldCards,
                    labelString = "شماره کارت",
                    displayText = selectedOldCard,
                    readOnly = true,
                    isEnabled = true
                ) { title ->
                    selectedOldCard = title
                }
            }

            Spacer(Modifier.height(12.dp))
            BankAccountSpinner(
                modifier = Modifier.fillMaxWidth(),
                options = list,
                labelString = "کسر کارمزد از حساب",
                isEnabled = true,
                readOnly = true,
                selectedAccount = selectedFeeDeposit,
            ) { deposit ->
                selectedFeeDeposit = deposit
            }
        }
    }
}

private enum class CardType {
    NEW_CARD, OLD_CARD, UNSPECIFIED
}

@AppPreview
@Composable
private fun SelectCardNoScreenPreview() {
    HamrahBankTheme {
        SelectCardNoScreen()
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun BankAccountSpinner(
    modifier: Modifier = Modifier,
    options: List<DepositModel>?,
    labelString: String,
    selectedAccount: DepositModel?,
    isEnabled: Boolean = true,
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
                            )
                            CaptionText(
                                text = deposit.let {
                                    "\nقابل برداشت: ${it.amount.toCurrency()} ${it.currency}"
                                },
                                color = AppTheme.colorScheme.foregroundNeutralDefault,
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