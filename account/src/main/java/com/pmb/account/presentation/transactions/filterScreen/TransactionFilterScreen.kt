package com.pmb.account.presentation.transactions.filterScreen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import com.pmb.account.presentation.component.ChipWithIcon
import com.pmb.ballon.component.base.AppTopBar
import com.pmb.ballon.component.base.BodyMediumText
import com.pmb.ballon.ui.theme.AppTheme

@Composable
fun TransactionFilterScreen() {
    Column(
        modifier = Modifier
            .background(color = AppTheme.colorScheme.background1Neutral)
            .fillMaxSize()
            .padding(all = 12.dp), horizontalAlignment = Alignment.Start
    ) {

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
        // todo

        BodyMediumText(
            text = "انتخاب تاریخ",
            color = AppTheme.colorScheme.onBackgroundNeutralSubdued
        )
    }
}

@Preview
@Composable
private fun TransactionFilterScreenPreview() {
    CompositionLocalProvider(LocalLayoutDirection provides LayoutDirection.Rtl) {
        TransactionFilterScreen()
    }
}