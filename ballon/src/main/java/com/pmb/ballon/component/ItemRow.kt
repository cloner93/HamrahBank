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
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import com.pmb.ballon.component.base.BaseAppText
import com.pmb.ballon.models.RowType


@Composable
fun BaseItemRow(
    modifier: Modifier = Modifier,
    title: @Composable () -> Unit,
    subtitle: @Composable () -> Unit,
    bottomDivider: Boolean = true
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 4.dp)
    ) {
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
        if (bottomDivider) HorizontalDivider()
    }
}

@Composable
fun AppItemRow(
    modifier: Modifier = Modifier,
    rowType: RowType,
    bottomDivider: Boolean = true
) {
    BaseItemRow(
        modifier = modifier,
        title = {
            BaseAppText(title = rowType.title, style = rowType.textStyle)
        },
        subtitle = {
            when (rowType) {
                is RowType.SimpleTextRow -> Unit
                is RowType.TwoTextRow -> {
                    CompositionLocalProvider(LocalLayoutDirection provides rowType.subtitleLayoutDirection) {
                        BaseAppText(
                            title = rowType.subtitle,
                            style = rowType.subtitleStyle
                        )
                    }
                }

                is RowType.PaymentRow -> {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        BaseAppText(title = rowType.amount, style = rowType.amountStyle)
                        Spacer(modifier = Modifier.size(8.dp))
                        BaseAppText(title = rowType.currency, style = rowType.currencyStyle)
                    }
                }
            }
        },
        bottomDivider = bottomDivider
    )
}

@Preview
@Composable
private fun InvoiceItemRowPreview() {

}

