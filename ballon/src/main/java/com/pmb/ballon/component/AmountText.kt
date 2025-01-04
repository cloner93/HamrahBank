package com.pmb.ballon.component

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.pmb.ballon.component.base.BaseAppText
import com.pmb.ballon.models.TextStyle
import com.pmb.ballon.ui.theme.AppTheme
import com.pmb.core.utils.toCurrency


@Composable
fun AmountText(
    amount: Double,
    currency: String,
    amountStyle: TextStyle = TextStyle(
        color = AppTheme.colorScheme.onForegroundNeutralDefault,
        typography = AppTheme.typography.headline4,
    ),

    currencyStyle: TextStyle = TextStyle(
        color = AppTheme.colorScheme.onForegroundNeutralDefault,
        typography = AppTheme.typography.caption,
    ),
) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        BaseAppText(title = amount.toCurrency(), style = amountStyle)
        Spacer(modifier = Modifier.width(6.dp))
        BaseAppText(title = currency, style = currencyStyle)
    }
}