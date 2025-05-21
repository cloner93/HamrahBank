package com.pmb.receipt.component

import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.pmb.ballon.component.DashedDivider
import com.pmb.ballon.models.Render
import com.pmb.ballon.models.RowType
import com.pmb.ballon.ui.theme.AppTheme

@Composable
internal fun ColumnScope.ReceiptRowsComponent(
    rowTypes: List<RowType>,
    captureMode: Boolean = false
) {
    Spacer(modifier = Modifier.size(24.dp))
    DashedDivider(color = AppTheme.colorScheme.strokeNeutral3Rest, thickness = 1.dp)
    val content: @Composable (RowType) -> Unit = {
        it.Render(modifier = Modifier.padding(vertical = 8.dp))
        DashedDivider(color = AppTheme.colorScheme.strokeNeutral3Rest, thickness = 1.dp)
    }

    if (captureMode) {
        rowTypes.forEach { content(it) }
    } else {
        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
        ) {
            items(rowTypes.size) {
                content(
                    rowTypes[it]
                )
            }
        }
    }
}