package com.pmb.ballon.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.HorizontalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.pmb.ballon.R
import com.pmb.ballon.component.base.BaseAppText
import com.pmb.ballon.models.TextStyle
import com.pmb.ballon.ui.theme.AppTheme


@Composable
fun BaseItemRow(
    modifier: Modifier = Modifier,
    title: @Composable () -> Unit,
    subtitle: @Composable () -> Unit,
    bottomDivider: Boolean = true
) {
    Column(modifier = modifier
        .fillMaxWidth()
        .padding(horizontal = 4.dp)) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            title()
            subtitle()
        }
        if (bottomDivider) HorizontalDivider(thickness = 1.dp)
    }
}

@Composable
fun InvoiceItemRow(
    modifier: Modifier = Modifier,
    title: String,
    amount: String,
    titleStyle: TextStyle = TextStyle(
        color = AppTheme.colorScheme.onBackgroundPrimarySubdued,
        typography = AppTheme.typography.bodySmall
    ),
    amountStyle: TextStyle = TextStyle(
        color = AppTheme.colorScheme.onBackgroundNeutralDefault,
        typography = AppTheme.typography.bodyMedium
    ),
    preAmountStyle: TextStyle = TextStyle(
        color = AppTheme.colorScheme.onBackgroundPrimarySubdued,
        typography = AppTheme.typography.caption
    ),
    bottomDivider: Boolean = true
) {
    BaseItemRow(
        modifier = modifier,
        title = {
            BaseAppText(title = title, style = titleStyle)
        },
        subtitle = {
            Row(verticalAlignment = Alignment.CenterVertically) {
                BaseAppText(title = amount, style = amountStyle)
                Spacer(modifier = Modifier.size(8.dp))
                BaseAppText(
                    title = stringResource(R.string.real_carrency),
                    style = preAmountStyle
                )
            }
        },
        bottomDivider = bottomDivider
    )
}

@Preview
@Composable
private fun InvoiceItemRowPreview() {
    InvoiceItemRow(title = "کارمزد ۱", amount = "200,000")
}

