package com.pmb.transfer.presentation.components.transfer_confirm

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.VerticalDivider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.pmb.ballon.component.base.AppNumberTextField
import com.pmb.ballon.ui.theme.AppTheme
import com.pmb.transfer.R

@Composable
fun ExpireDateInputField(
    modifier: Modifier = Modifier,
    year: String,
    month: String,
    enabled: Boolean = true,
    error: Boolean = false,
    onValueChange: (year: String, month: String) -> Unit
) {
    var isYearFocused by remember { mutableStateOf(false) }
    var isMonthFocused by remember { mutableStateOf(false) }

    val borderColor = if (error) AppTheme.colorScheme.strokeNeutral2Error
    else if (!enabled) Color.Transparent
    else if (isYearFocused || isMonthFocused) AppTheme.colorScheme.strokeNeutral2Active
    else AppTheme.colorScheme.strokeNeutral1Default

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(height = 56.dp)
            .then(modifier)
            .border(
                border = BorderStroke(
                    width = if (isYearFocused || isMonthFocused) 2.dp else 1.dp,
                    color = borderColor
                ),
                shape = RoundedCornerShape(12.dp)
            )
    ) {
        AppNumberTextField(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f),
            value = month,
            label = stringResource(R.string.month),
            bordered = false,
            enabled = enabled,
            trailingIcon = {},
            onFocused = { isMonthFocused = it },
            onValueChange = {
                if (it.length <= 2 && it.toIntOrNull() in 0..12 || it.isEmpty())
                    onValueChange(year, it)
            }
        )
        VerticalDivider(modifier = Modifier.padding(vertical = 12.dp))
        AppNumberTextField(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f),
            value = year,
            label = stringResource(R.string.year),
            bordered = false,
            enabled = enabled,
            trailingIcon = {},
            onFocused = { isYearFocused = it },
            onValueChange = { if (it.length <= 2) onValueChange(it, month) }
        )
    }
}