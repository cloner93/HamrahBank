package com.pmb.account.presentation.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import com.pmb.ballon.component.annotation.AppPreview
import com.pmb.ballon.models.TextStyle
import com.pmb.ballon.ui.theme.AppTheme
import com.pmb.ballon.ui.theme.HamrahBankTheme
import com.pmb.calender.generateShiftedMonthList
import com.pmb.calender.monthName

@Composable
internal fun RowOfMonth(modifier: Modifier = Modifier) {
    val list = generateShiftedMonthList()

    var selectedMonth by remember { mutableIntStateOf(list[10].first.month) }
    val listState = rememberLazyListState()

    LaunchedEffect(selectedMonth) {
        val index = list.indexOfFirst { it.first.month == selectedMonth }
        if (index != -1) {
            listState.animateScrollToItem(index, scrollOffset = (list.size / 2) * -70)
        }
    }
    LazyRow(
        modifier = modifier.fillMaxWidth(),
        state = listState,
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        contentPadding = PaddingValues(horizontal = 16.dp)
    ) {
        itemsIndexed(list) { _, month ->
            val isSelected = month.first.month == selectedMonth
            MonthItem(
                month = month.first.monthName(),
                isSelected = isSelected,
                onClick = { selectedMonth = month.first.month },
                year = month.first.year.toString(),
                isEnabled = list.last() != month
            )
        }
    }
}


@AppPreview
@Composable
fun RowOfMonthPreview() {
    HamrahBankTheme {
        Row(
            modifier = Modifier.background(color = AppTheme.colorScheme.background1Neutral),
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            RowOfMonth()
        }
    }
}

@Composable
internal fun MonthItem(
    modifier: Modifier = Modifier,
    year: String,
    month: String,
    isSelected: Boolean,
    isEnabled: Boolean,
    onClick: () -> Unit
) {
    Box(
        modifier = modifier
            .clip(RoundedCornerShape(8.dp))
            .background(
                if (isSelected) AppTheme.colorScheme.foregroundPrimaryDefault
                else AppTheme.colorScheme.backgroundTintNeutralDefault
            )
            .clickable { if (isEnabled) onClick() }
            .padding(
                horizontal = 16.dp,
                vertical = 8.dp
            )) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            AppText(
                title = year,
                style = TextStyle(
                    color = if (isSelected) AppTheme.colorScheme.onForegroundNeutralDefault
                    else if (!isEnabled) AppTheme.colorScheme.foregroundNeutralRest
                    else AppTheme.colorScheme.onBackgroundNeutralSubdued,
                    typography = AppTheme.typography.caption,
                ),
            )
            Spacer(modifier = Modifier.height(8.dp))
            AppText(
                title = month,
                style = TextStyle(
                    color = if (isSelected) AppTheme.colorScheme.onForegroundNeutralDefault
                    else if (!isEnabled) AppTheme.colorScheme.foregroundNeutralRest
                    else AppTheme.colorScheme.onBackgroundNeutralDefault,
                    typography = if (isSelected) AppTheme.typography.buttonLarge
                    else AppTheme.typography.buttonSmall,
                ),
            )

        }
    }
}

@AppPreview
@Composable
fun MonthItemPreview() {
    HamrahBankTheme {
        Row(
            modifier = Modifier.background(color = AppTheme.colorScheme.background1Neutral),
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            MonthItem(
                year = "1404",
                month = "تیر",
                isSelected = true,
                isEnabled = true,
            ) { }
            MonthItem(
                year = "1404",
                month = "تیر",
                isSelected = false,
                isEnabled = true,
            ) { }
            MonthItem(
                year = "1404",
                month = "تیر",
                isSelected = false,
                isEnabled = false,
            ) { }
        }
    }
}