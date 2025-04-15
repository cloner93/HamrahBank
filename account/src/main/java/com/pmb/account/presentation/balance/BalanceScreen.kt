package com.pmb.account.presentation.balance

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.pmb.account.presentation.balance.viewmodel.BalanceViewEvents
import com.pmb.account.presentation.balance.viewmodel.BalanceViewModel
import com.pmb.account.presentation.component.PieChart
import com.pmb.ballon.component.base.AppContent
import com.pmb.ballon.component.base.AppTopBar
import com.pmb.ballon.component.base.BodyMediumText
import com.pmb.ballon.component.base.BodySmallText
import com.pmb.ballon.component.base.CaptionText
import com.pmb.ballon.component.base.Headline6Text
import com.pmb.ballon.ui.theme.AppTheme
import com.pmb.core.presentation.NavigationManager
import com.pmb.core.utils.toCurrency


@Composable
fun BalanceScreen(navigationManager: NavigationManager) {
    val viewModel = hiltViewModel<BalanceViewModel>()
    val viewState by viewModel.viewState.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.viewEvent.collect { event ->
            when (event) {
                is BalanceViewEvents.ShowError -> {
                    // TODO:
                }
            }
        }
    }

    AppContent(
        modifier = Modifier.padding(horizontal = 16.dp),
        topBar = {
            AppTopBar(
                title = "دارایی ها",
                onBack = {
                    navigationManager.navigateBack()
                })
        }) {

        Card(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(16.dp),
            colors = CardDefaults.cardColors(containerColor = AppTheme.colorScheme.background1Neutral),
            elevation = CardDefaults.cardElevation(0.dp)
        ) {

            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                PieChart(
                    modifier = Modifier,
                    charts = viewState.deposits
                )

                BodySmallText(
                    modifier = Modifier.padding(vertical = 8.dp),
                    text = "مجموع دارایی ها"
                )
                Row(
                    modifier = Modifier.padding(bottom = 16.dp)
                ) {
                    Headline6Text(text = viewState.totalBalance.toCurrency())

                    BodySmallText(text = "ریال")
                }
            }
        }
        Spacer(modifier = Modifier.height(12.dp))

        Card(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(16.dp),
            colors = CardDefaults.cardColors(containerColor = AppTheme.colorScheme.background1Neutral),
            elevation = CardDefaults.cardElevation(0.dp)
        ) {

            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                viewState.deposits.forEach {
                    DepositRow(it) { }
                }
            }
        }
    }
}

@Composable
fun DepositRow(deposit: DepositsChartModel, onClick: () -> Unit) {

    Row(
        modifier = Modifier
            .padding(end = 12.dp, top = 12.dp, bottom = 12.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            modifier = Modifier
                .padding(all = 4.dp)
                .size(24.dp)
                .padding(all = 4.dp)
                .clip(CircleShape)
                .background(deposit.color)
        )

        Column(modifier = Modifier.fillMaxWidth()) {
            Headline6Text(text = deposit.title)
            Spacer(modifier = Modifier.height(4.dp))
            Row {
                BodyMediumText(
                    modifier = Modifier.weight(1f),
                    text = deposit.depositNumber,
                    color = AppTheme.colorScheme.onBackgroundNeutralDefault
                )
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Headline6Text(text = deposit.amount.toCurrency())
                    Spacer(modifier = Modifier.width(4.dp))
                    CaptionText(text = deposit.currency)
                }
            }
        }
    }
}

data class DepositsChartModel(
    val value: Float,
    val color: Color,
    val title: String,
    val depositNumber: String,
    val amount: Double,
    val currency: String,
)

