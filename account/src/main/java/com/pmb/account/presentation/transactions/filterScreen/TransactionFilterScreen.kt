package com.pmb.account.presentation.transactions.filterScreen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import com.pmb.account.presentation.component.ChipWithIcon
import com.pmb.ballon.component.base.AppButtonIcon
import com.pmb.ballon.component.base.AppClickableReadOnlyTextField
import com.pmb.ballon.component.base.AppTopBar
import com.pmb.ballon.component.base.BodyMediumText
import com.pmb.ballon.component.base.IconType
import com.pmb.ballon.ui.theme.AppTheme


// TODO:
/**
- viewmodel
- event state action
- navigation
- back button
- set clickable chips
- handle numbers in textFields
- handle date picker
*/
@OptIn(ExperimentalLayoutApi::class)
@Composable
fun TransactionFilterScreen() {
    Column(
        modifier = Modifier
            .background(color = AppTheme.colorScheme.background1Neutral)
            .fillMaxSize()
            .padding(all = 12.dp)
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.Start
    ) {

        var showBirthdayPicker by remember { mutableStateOf(false) }

        AppTopBar(
            title = "فیلتر ها",
            onBack = {}
        )
        Spacer(modifier = Modifier.height(24.dp))
        BodyMediumText(
            text = "نوع تراکنش",
            color = AppTheme.colorScheme.onBackgroundNeutralSubdued
        )
        Spacer(modifier = Modifier.height(24.dp))
        Row(
            modifier = Modifier.fillMaxWidth(),
        ) {
            ChipWithIcon(
                modifier = Modifier.weight(1f),
                value = "همه",
                clickable = { },
                color = Color.White,
                assetColor = Color.Black,
                borderColor = AppTheme.colorScheme.strokeNeutral1Default,
                fillMaxWidth = true
            )
            Spacer(modifier = Modifier.width(8.dp))
            ChipWithIcon(
                modifier = Modifier.weight(1f),
                value = "برداشت",
                clickable = { },
                color = Color.White,
                assetColor = Color.Black,
                borderColor = AppTheme.colorScheme.strokeNeutral1Default,
                fillMaxWidth = true
            )
            Spacer(modifier = Modifier.width(8.dp))
            ChipWithIcon(
                modifier = Modifier.weight(1f),
                value = "واریز",
                clickable = { },
                color = Color.White,
                assetColor = Color.Black,
                borderColor = AppTheme.colorScheme.strokeNeutral1Default,
                fillMaxWidth = true
            )

        }

        Spacer(modifier = Modifier.height(44.dp))
        BodyMediumText(
            text = "مبلغ تراکنش",
            color = AppTheme.colorScheme.onBackgroundNeutralSubdued
        )
        Spacer(modifier = Modifier.height(24.dp))
        MBTextField(
            modifier = Modifier.fillMaxWidth(),
            label = "از"
        )
        Spacer(modifier = Modifier.height(24.dp))
        MBTextField(modifier = Modifier.fillMaxWidth(), label = "تا")

        Spacer(modifier = Modifier.height(44.dp))
        BodyMediumText(
            text = "انتخاب تاریخ",
            color = AppTheme.colorScheme.onBackgroundNeutralSubdued
        )
        Spacer(modifier = Modifier.height(24.dp))
        FlowRow(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            maxItemsInEachRow = 3,
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {

            ChipWithIcon(
                modifier = Modifier.weight(1f),
                fillMaxWidth = true,
                value = "امروز",
                clickable = { },
                color = Color.White,
                assetColor = Color.Black,
                borderColor = AppTheme.colorScheme.strokeNeutral1Default,
            )
            ChipWithIcon(
                modifier = Modifier.weight(1f),
                fillMaxWidth = true,
                value = "هفته گذشته",
                clickable = { },
                color = Color.White,
                assetColor = Color.Black,
                borderColor = AppTheme.colorScheme.strokeNeutral1Default,
            )

            ChipWithIcon(
                modifier = Modifier.weight(1f),
                fillMaxWidth = true,
                value = "ماه گذشته",
                clickable = { },
                color = Color.White,
                assetColor = Color.Black,
                borderColor = AppTheme.colorScheme.strokeNeutral1Default,
            )
            ChipWithIcon(
                modifier = Modifier.weight(1f),
                fillMaxWidth = true,
                value = "ماه جاری",
                clickable = { },
                color = Color.White,
                assetColor = Color.Black,
                borderColor = AppTheme.colorScheme.strokeNeutral1Default,
            )
            ChipWithIcon(
                modifier = Modifier.weight(1f),
                fillMaxWidth = true,
                value = "انتخاب بازه دلخواه",
                clickable = { },
                color = Color.White,
                assetColor = Color.Black,
                borderColor = AppTheme.colorScheme.strokeNeutral1Default,
            )
        }

        Spacer(modifier = Modifier.height(32.dp))
        AppClickableReadOnlyTextField(
            modifier = Modifier.height(56.dp),
            value = "",
            label = "از تاریخ",
            trailingIcon = {
                AppButtonIcon(
                    icon = IconType.Painter(painterResource(com.pmb.ballon.R.drawable.ic_calendar_month)),
                    onClick = {}
                )
            },
            onClick = {
                showBirthdayPicker = true
            })

        Spacer(modifier = Modifier.height(24.dp))
        AppClickableReadOnlyTextField(
            modifier = Modifier.height(56.dp),
            value = "",
            label = "تا تاریخ",
            trailingIcon = {
                AppButtonIcon(
                    icon = IconType.Painter(painterResource(com.pmb.ballon.R.drawable.ic_calendar_month)),
                    onClick = {}
                )
            },
            onClick = {
                showBirthdayPicker = true
            })
    }
}

@Composable
fun MBTextField(modifier: Modifier, label: String) {
    var text by remember { mutableStateOf("") }

    OutlinedTextField(
        modifier = modifier,
        value = text,
        onValueChange = { text = it },
        label = {
            BodyMediumText(
                text = label,
                color = AppTheme.colorScheme.onBackgroundNeutralDefault
            )
        },
        colors = OutlinedTextFieldDefaults.colors(
            focusedBorderColor = AppTheme.colorScheme.onBackgroundNeutralDefault,
            unfocusedBorderColor = AppTheme.colorScheme.strokeNeutral1Default,
            focusedLabelColor = AppTheme.colorScheme.onBackgroundNeutralDefault,
            unfocusedLabelColor = AppTheme.colorScheme.strokeNeutral1Default,
            cursorColor = AppTheme.colorScheme.onBackgroundNeutralDefault,
            errorBorderColor = AppTheme.colorScheme.onBackgroundErrorDefault,
        )
    )
}

@Preview
@Composable
private fun TransactionFilterScreenPreview() {
    CompositionLocalProvider(LocalLayoutDirection provides LayoutDirection.Rtl) {
        TransactionFilterScreen()
    }
}