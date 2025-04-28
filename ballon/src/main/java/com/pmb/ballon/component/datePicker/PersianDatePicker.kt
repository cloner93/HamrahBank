package com.pmb.ballon.component.datePicker

import android.view.HapticFeedbackConstants
import android.view.View
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.pmb.ballon.component.base.SubtitleMediumText
import com.pmb.ballon.ui.theme.AppTheme
import com.pmb.calender.Jdn
import com.pmb.calender.utils.Calendar

@Composable
fun PersianDatePicker(calendar: Calendar = Calendar.SHAMSI, jdn: Jdn, setJdn: (Jdn) -> Unit) {
    val date = remember(jdn.value, calendar) { jdn on calendar }
    val daysFormat = remember(calendar, date.year, date.month) {
        Jdn(calendar, date.year, date.month, 1);
        { item: Int ->
            /*if you want show day name use it. (monthStart + item - 1).weekDayName + " / " +*/ item.toString()
        }
    }
    val monthsLength = remember(calendar, date.year, date.month) {
        calendar.getMonthLength(date.year, date.month)
    }
    val yearMonths = remember(calendar, date.year) {
        calendar.getYearMonths(date.year)
    }
    val monthsFormat = remember(calendar, date.year) {
//        val months = monthsyearMonthNameOfDate(date);
        { item: Int ->
            /*if you want show month name use it. months[item - 1] + " / " +*/
            item.toString()
        }
    }
    val todayYear = remember(calendar) { Jdn.today().on(calendar).year }
    val startYear = remember(calendar) { todayYear - 200 }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier
            .padding(horizontal = 8.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 24.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            SubtitleMediumText(
                modifier = Modifier.weight(1f),
                text = stringResource(com.pmb.ballon.R.string.day),
                color = AppTheme.colorScheme.onBackgroundNeutralDefault,
                textAlign = TextAlign.Center
            )
            SubtitleMediumText(
                modifier = Modifier.weight(1f),
                text = stringResource(com.pmb.ballon.R.string.month),
                color = AppTheme.colorScheme.onBackgroundNeutralDefault,
                textAlign = TextAlign.Center
            )
            SubtitleMediumText(
                modifier = Modifier.weight(1f),
                text = stringResource(com.pmb.ballon.R.string.year),
                color = AppTheme.colorScheme.onBackgroundNeutralDefault,
                textAlign = TextAlign.Center
            )
        }
        Box(
            modifier = Modifier,
            contentAlignment = Alignment.Center
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(42.dp)
                    .background(
                        color = AppTheme.colorScheme.backgroundTintNeutralDefault,
                        shape = RoundedCornerShape(size = 100000.dp),
                    ),
            )
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                val view = LocalView.current
                NumberPicker(
                    modifier = Modifier.weight(1f),
                    label = daysFormat,
                    range = 1..monthsLength,
                    value = date.dayOfMonth,
                    onClickLabel = "day",
                ) {
                    setJdn(Jdn(calendar, date.year, date.month, it))
                    view.performHapticFeedbackVirtualKey()
                }
                Spacer(modifier = Modifier.width(8.dp))
                NumberPicker(
                    modifier = Modifier.weight(1f),
                    label = monthsFormat,
                    range = 1..yearMonths,
                    value = date.month,
                    onClickLabel = "month",
                ) { month ->
                    val day =
                        date.dayOfMonth.coerceIn(1, calendar.getMonthLength(date.year, month))
                    setJdn(Jdn(calendar, date.year, month, day))
                    view.performHapticFeedbackVirtualKey()
                }
                Spacer(modifier = Modifier.width(8.dp))
                NumberPicker(
                    modifier = Modifier.weight(1f),
                    range = startYear..startYear + 400,
                    value = date.year,
                    onClickLabel = "year",
                ) { year ->
                    val month = date.month.coerceIn(1, calendar.getYearMonths(year))
                    val day = date.dayOfMonth.coerceIn(1, calendar.getMonthLength(year, month))
                    setJdn(Jdn(calendar, year, month, day))
                    view.performHapticFeedbackVirtualKey()
                }
            }
        }
    }
}

fun View.performHapticFeedbackVirtualKey() {
    performHapticFeedback(HapticFeedbackConstants.VIRTUAL_KEY)
}