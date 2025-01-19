package com.pmb.ballon.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.pmb.ballon.R
import com.pmb.ballon.component.base.SubtitleMediumText
import com.pmb.ballon.ui.theme.AppTheme

@Composable
fun PersianDatePicker(
    value: String = "", // Ex. 1371/08/28
    onChangeValue: (year: String, month: String, day: String) -> Unit = { _, _, _ -> },
) {
    val days = remember { (1..31).map { it.toString() } }
    val months = remember {
        listOf(
            "فروردین",
            "اردیبهشت",
            "خرداد",
            "تیر",
            "مرداد",
            "شهریور",
            "مهر",
            "آبان",
            "آذر",
            "دی",
            "بهمن",
            "اسفند"
        )
    }
    val years = remember { (1300..1500).map { it.toString() } }

    val dayPickerState = rememberPickerState()
    val monthPickerState = rememberPickerState()
    val yearPickerState = rememberPickerState()

    onChangeValue.invoke(
        yearPickerState.selectedItem,
        monthPickerState.selectedItem,
        dayPickerState.selectedItem
    )

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
                text = stringResource(R.string.day),
                color = AppTheme.colorScheme.onBackgroundNeutralDefault,
                textAlign = TextAlign.Center
            )
            SubtitleMediumText(
                modifier = Modifier.weight(1f),
                text = stringResource(R.string.month),
                color = AppTheme.colorScheme.onBackgroundNeutralDefault,
                textAlign = TextAlign.Center
            )
            SubtitleMediumText(
                modifier = Modifier.weight(1f),
                text = stringResource(R.string.year),
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
                Picker(
                    state = dayPickerState,
                    items = days,
                    modifier = Modifier.weight(1f),
                    textModifier = Modifier.padding(8.dp),
                )
                Picker(
                    state = monthPickerState,
                    items = months,
                    modifier = Modifier.weight(1f),
                    textModifier = Modifier.padding(8.dp),
                )
                Picker(
                    state = yearPickerState,
                    items = years,
                    modifier = Modifier.weight(1f),
                    textModifier = Modifier.padding(8.dp),
                )
            }
        }
    }
}