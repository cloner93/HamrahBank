package com.pmb.account.presentation.transactions

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.pmb.account.presentation.component.RowOfMonth
import com.pmb.account.presentation.component.SingleRow
import com.pmb.ballon.component.base.BodySmallText
import com.pmb.ballon.component.base.Headline6Text
import com.pmb.ballon.ui.theme.AppTheme
import com.pmb.calender.currentMonthPair
import com.pmb.core.utils.toCurrency
import com.pmb.domain.model.transaztion.Summarize
import io.github.persiancalendar.calendar.PersianDate

@Composable
internal fun SummarizeSection(
    totalTransaction: Double,
    transactionList: List<Summarize>?,
    onTransactionClick: (Summarize) -> Unit = {},
    currentMonth: Pair<PersianDate, PersianDate>,
    selectedMonth: (Pair<PersianDate, PersianDate>) -> Unit,
    title: String
) {

    // TODO: Fix it
    LaunchedEffect(Unit) {
        selectedMonth(currentMonthPair())
    }

    RowOfMonth(
        modifier = Modifier.padding(bottom = 32.dp),
        currentMonth = currentMonth,
        selectedMonth = selectedMonth,
    )
    transactionList?.let { list ->
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
            shape = RoundedCornerShape(12.dp),
            colors = CardDefaults.cardColors(containerColor = AppTheme.colorScheme.background1Neutral),
            border = BorderStroke(1.dp, Color(0xFFB8B8BC)),
            elevation = CardDefaults.cardElevation(0.dp)
        ) {

            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                BodySmallText(
                    modifier = Modifier.padding(vertical = 8.dp),
                    text = title,
                    color = AppTheme.colorScheme.onBackgroundNeutralSubdued
                )

                Row(
                    modifier = Modifier.padding(bottom = 16.dp)
                ) {
                    Headline6Text(
                        text = totalTransaction.toCurrency(),
                        color = AppTheme.colorScheme.onBackgroundNeutralDefault
                    )

                    BodySmallText(
                        text = "ریال", color = AppTheme.colorScheme.onBackgroundNeutralSubdued
                    )
                }

                LazyColumn(
                    contentPadding = PaddingValues(12.dp),
                    verticalArrangement = Arrangement.spacedBy(24.dp)
                ) {
                    items(list.size) { item ->
                        SingleRow(
                            title = list[item].docDesc,
                            amount = list[item].totalAmount.toDouble(),
                            onClick = { onTransactionClick(list[item]) }
                        )
                    }
                }

            }
        }
    }
}