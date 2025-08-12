package com.pmb.account.presentation.issueCard.selectAddress

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.pmb.ballon.component.annotation.AppPreview
import com.pmb.ballon.component.base.AppBaseTextField
import com.pmb.ballon.component.base.AppButton
import com.pmb.ballon.component.base.AppContent
import com.pmb.ballon.component.base.AppNumberTextField
import com.pmb.ballon.component.base.AppTopBar
import com.pmb.ballon.component.base.BodyMediumText
import com.pmb.ballon.component.base.CaptionText
import com.pmb.ballon.component.base.ClickableIcon
import com.pmb.ballon.component.base.IconType
import com.pmb.ballon.ui.theme.AppTheme
import com.pmb.ballon.ui.theme.HamrahBankTheme

@Composable
fun SelectAddressScreen() {
    val oldAddressList = listOf(
        "تهران، کوی نصر، خیابان ۲۷، پلاک ۱۵، واحد ۳"
    )
    val allOptions = buildList {
        add(
            AddressOption(
                "آدرس ثبت شده قبلی",
                isHeader = true,
                isClickable = false
            )
        )
        oldAddressList.forEach { address ->
            add(
                AddressOption(
                    address,
                    isHeader = false,
                    isClickable = true
                )
            )
        }
        add(AddressOption("آدرس جدید", isHeader = true, isClickable = true))
    }

    var spinnerText by remember { mutableStateOf("") }
    var selectedAddress by remember { mutableStateOf("") }
    var addressType by remember { mutableStateOf(AddressType.UNSPECIFIED) }
    var postalCode by remember { mutableStateOf("") }

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
                val bool =
                    if (addressType == AddressType.OLD_ADDRESS) true
                    else if (addressType == AddressType.NEW_ADDRESS && (postalCode.isNotEmpty() && postalCode.length == 10)) true
                    else false

                AppButton(
                    modifier = Modifier.fillMaxWidth(),
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
            options = allOptions,
            labelString = "انتخاب آدرس",
            displayText = spinnerText,
            isEnabled = true
        ) { selectedOption ->
            spinnerText = selectedOption.text
            if (!selectedOption.isHeader) {
                addressType = AddressType.OLD_ADDRESS
                selectedAddress = selectedOption.text
            } else {
                addressType = AddressType.NEW_ADDRESS
            }
        }

        when (addressType) {
            AddressType.OLD_ADDRESS -> {

                Spacer(Modifier.height(32.dp))
                BodyMediumText(
                    modifier = Modifier.fillMaxWidth(),
                    text = selectedAddress,
                    textAlign = TextAlign.Center,
                    color = AppTheme.colorScheme.onBackgroundNeutralDefault
                )
            }

            AddressType.NEW_ADDRESS -> {
                Spacer(Modifier.height(24.dp))
                AppNumberTextField(
                    modifier = Modifier.fillMaxWidth(),
                    value = postalCode,
                    label = "کد پستی",
                    onValueChange = {
                        if (it.length <= 10) postalCode = it
                    },
                    trailingIcon = {
                        AppButton(
                            modifier = Modifier.padding(end = 6.dp),
                            title = "استعلام",
                            enable = postalCode.isNotEmpty() && postalCode.length == 10
                        ) {
                        }
                    }
                )

                Spacer(Modifier.height(32.dp))
                BodyMediumText(
                    modifier = Modifier.fillMaxWidth(),
                    text = "",
                    textAlign = TextAlign.Center,
                    color = AppTheme.colorScheme.onBackgroundNeutralCTA
                )
            }

            AddressType.UNSPECIFIED -> {}
        }
    }
}

@AppPreview
@Composable
private fun SelectAddressScreenPreview() {
    HamrahBankTheme {
        SelectAddressScreen()
    }
}

private enum class AddressType {
    OLD_ADDRESS, NEW_ADDRESS, UNSPECIFIED
}

private data class AddressOption(
    val text: String,
    val isHeader: Boolean = false,
    val isClickable: Boolean = true
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun CustomSpinner(
    modifier: Modifier = Modifier,
    options: List<AddressOption>,
    labelString: String,
    displayText: String,
    isEnabled: Boolean = false,
    onOptionSelected: (AddressOption) -> Unit
) {
    var expanded by remember { mutableStateOf(false) }

    ExposedDropdownMenuBox(
        expanded = expanded,
        onExpandedChange = {
            if (isEnabled) expanded = !expanded
        }) {
        AppBaseTextField(
            value = displayText,
            onValueChange = {},
            label = labelString,
            enabled = isEnabled,
            hideCursor = true,
            trailingIcon = {
                Icon(
                    imageVector = if (expanded) Icons.Default.KeyboardArrowUp else Icons.Default.KeyboardArrowDown,
                    contentDescription = "Dropdown Icon",
                    tint = AppTheme.colorScheme.onBackgroundNeutralDefault
                )
            },
            modifier = modifier
                .menuAnchor(MenuAnchorType.PrimaryNotEditable, true)
                .clickable {
                    if (isEnabled) expanded = !expanded
                })

        ExposedDropdownMenu(
            modifier = Modifier.heightIn(max = 300.dp),
            expanded = expanded,
            containerColor = AppTheme.colorScheme.background1Neutral,
            onDismissRequest = { expanded = false }
        ) {
            options.forEach { option ->
                DropdownMenuItem(
                    text = {
                        if (option.isHeader)
                            BodyMediumText(
                                modifier = Modifier.padding(bottom = 6.dp),
                                text = option.text,
                                color = AppTheme.colorScheme.foregroundNeutralDefault
                            )
                        else
                            CaptionText(
                                modifier = Modifier.padding(bottom = 6.dp),
                                text = option.text,
                                color = AppTheme.colorScheme.onBackgroundNeutralSubdued
                            )
                    },
                    onClick = {
                        if (option.isClickable) {
                            onOptionSelected(option)
                            expanded = false
                        }
                    },
                    enabled = option.isClickable,
                    modifier = Modifier.then(
                        if (!option.isClickable) {
                            Modifier.background(Color.Transparent)
                        } else {
                            Modifier
                        }
                    )
                )
            }
        }
    }
}