package com.pmb.receipt.component

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.pmb.ballon.models.RowType

@Composable
fun ReceiptComponent(
    modifier: Modifier = Modifier,
    rowTypes: List<RowType>,
    captureMode: Boolean = false,
    footerContent: @Composable () -> Unit = @Composable { ReceiptFooterComponent() },
    headerContent: @Composable () -> Unit
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        headerContent.invoke()
        ReceiptRowsComponent(rowTypes = rowTypes, captureMode = captureMode)
        footerContent.invoke()
    }
}