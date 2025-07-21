package com.pmb.account.presentation.account.details

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.pmb.ballon.component.MenuItemDefaults.horizontalDividerPadding
import com.pmb.ballon.component.annotation.AppPreview
import com.pmb.ballon.component.base.AppContent
import com.pmb.ballon.component.base.AppTopBar
import com.pmb.ballon.component.base.BodyLargeText
import com.pmb.ballon.ui.theme.AppTheme
import com.pmb.ballon.ui.theme.HamrahBankTheme
import com.pmb.calender.toPersianDateString
import com.pmb.navigation.manager.LocalNavigationManager

@Composable
fun DepositDetailsScreen(viewModel: DepositDetailsViewModel) {
    val viewState by viewModel.viewState.collectAsState()
    val navigationManager = LocalNavigationManager.current

    AppContent(
        modifier = Modifier
            .padding(horizontal = 16.dp),
        backgroundColor = AppTheme.colorScheme.background3Neutral,
        scrollState = rememberScrollState(),
        topBar = {
            AppTopBar(
                title = "جزییات حساب",
                onBack = {
                    navigationManager.navigateBack()
                })
        },
    ) {
        Card(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(16.dp),
            colors = CardDefaults.cardColors(containerColor = AppTheme.colorScheme.background1Neutral),
            elevation = CardDefaults.cardElevation(0.dp)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                viewState.depositDetail?.let {
                    Content(it)
                }
            }
        }
    }
}

@Composable
private fun Content(
    depositDetail: DepositDetail
) {
    depositDetail.branchName?.let {
        Item(
            "شعبه افتتاح کننده",
            it
        )
        HorizontalDivider(
            modifier = Modifier.padding(horizontalDividerPadding.toPaddingValues()),
            color = AppTheme.colorScheme.strokeNeutral3Devider
        )
    }
    depositDetail.lastTransactionDate?.let {
        Item(
            "تاریخ آخرین گردش",
            it.toPersianDateString()
        )
        HorizontalDivider(
            modifier = Modifier.padding(horizontalDividerPadding.toPaddingValues()),
            color = AppTheme.colorScheme.strokeNeutral3Devider
        )
    }
    depositDetail.ibanCode?.let {
        Item(
            "شماره شبا",
            it
        )
        HorizontalDivider(
            modifier = Modifier.padding(horizontalDividerPadding.toPaddingValues()),
            color = AppTheme.colorScheme.strokeNeutral3Devider
        )
    }
    depositDetail.accountType?.let {
        Item(
            "نوع حساب",
            it
        )
        HorizontalDivider(
            modifier = Modifier.padding(horizontalDividerPadding.toPaddingValues()),
            color = AppTheme.colorScheme.strokeNeutral3Devider
        )
    }
    depositDetail.interestRate?.let {
        Item(
            "نرخ سود",
            it
        )
    }
}

@Composable
fun Item(
    title: String,
    value: String
) {
    Column(
        modifier =
            Modifier
                .fillMaxWidth()
                .padding(16.dp)
    ) {
        BodyLargeText(
            text = title,
            color = AppTheme.colorScheme.onBackgroundNeutralSubdued
        )
        Spacer(modifier = Modifier.height(8.dp))
        BodyLargeText(
            text = value,
            color = AppTheme.colorScheme.onBackgroundNeutralDefault
        )
    }
}

@AppPreview
@Composable
private fun ItemPreview() {
    HamrahBankTheme {
        Column {
            Item(
                "شعبه افتتاح کننده",
                "شادمان"
            )
        }
    }
}