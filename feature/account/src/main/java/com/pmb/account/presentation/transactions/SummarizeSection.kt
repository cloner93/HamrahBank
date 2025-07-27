package com.pmb.account.presentation.transactions

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
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
import com.pmb.account.presentation.component.shimmerLoading
import com.pmb.ballon.component.annotation.AppPreview
import com.pmb.ballon.component.base.BodySmallText
import com.pmb.ballon.component.base.Headline6Text
import com.pmb.ballon.ui.theme.AppTheme
import com.pmb.ballon.ui.theme.HamrahBankTheme
import com.pmb.calender.currentMonthPair
import com.pmb.core.utils.toCurrency
import com.pmb.domain.model.TransactionModel
import com.pmb.domain.model.transaztion.Summarize
import io.github.persiancalendar.calendar.PersianDate

@AppPreview
@Composable
private fun SummarizePreview() {
    HamrahBankTheme {
        Column(
            Modifier.background(color = AppTheme.colorScheme.background1Neutral)
        ) {

            SummarizeSection(
                title = "پرداخت ماهانه",
                totalTransaction = 1.2,
                loading = false,
                transactionList = listOf(
                    Summarize(
                        docDesc = "حواله ملتی",
                        totalAmount = "100000",
                        transactionList = listOf(
                            TransactionModel(
                                transactionId = "2",
                                title = "حواله",
                                amount = 1111.1,
                                date = "14040505"
                            )
                        )
                    )
                ),
                currentMonth = currentMonthPair(),
                { }
            )
        }
    }
}

@AppPreview
@Composable
private fun SummarizeLoadingPreview() {
    HamrahBankTheme {
        Column(
            Modifier.background(color = AppTheme.colorScheme.background1Neutral)
        ) {

            SummarizeSection(
                title = "",
                totalTransaction = 0.0,
                transactionList = emptyList(),
                currentMonth = currentMonthPair(),
                selectedMonth = { }
            )
        }
    }
}

@Composable
internal fun SummarizeSection(
    title: String,
    totalTransaction: Double,
    loading: Boolean = true,
    transactionList: List<Summarize>,
    currentMonth: Pair<PersianDate, PersianDate>?,
    selectedMonth: (Pair<PersianDate, PersianDate>) -> Unit,
    onTransactionClick: (Summarize) -> Unit = {},
) {

    if (currentMonth == null)
        LaunchedEffect(Unit) {
            selectedMonth(currentMonthPair())
        }
    RowOfMonth(
        modifier = Modifier.padding(bottom = 32.dp),
        loading = loading,
        currentMonth = currentMonth ?: currentMonthPair(),
        selectedMonth = selectedMonth
    )

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
            modifier = Modifier
                .fillMaxWidth()
                .then(
                    if (loading) Modifier.shimmerLoading() else Modifier
                ),
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
                items(transactionList.size) { item ->
                    SingleRow(
                        title = transactionList[item].docDesc,
                        amount = transactionList[item].totalAmount.toDouble(),
                        onClick = { onTransactionClick(transactionList[item]) }
                    )
                }
            }
        }
    }
}