package com.pmb.transfer.presentation.components.transfer_confirm

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp


@Composable
fun ShowInputsByTransferType(
    transferType: Int,
    depositId: String,
    onDepositIdChange: (String) -> Unit,
    accountClickable: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
    ) {
        if (transferType == 1) ShowInputsCard(
            depositId = depositId,
            onDepositIdChange = onDepositIdChange,
        )
        else if (transferType == 2) ShowInputsAccount(
            depositId = depositId,
            onDepositIdChange = onDepositIdChange,
        )
    }
}