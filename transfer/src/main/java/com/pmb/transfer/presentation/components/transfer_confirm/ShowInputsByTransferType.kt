package com.pmb.transfer.presentation.components.transfer_confirm

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.pmb.transfer.domain.entity.PaymentType
import com.pmb.transfer.domain.entity.TransferMethodEntity


@Composable
fun ShowInputsByTransferType(
    transferMethod: TransferMethodEntity,
    depositId: String,
    onDepositIdChange: (String) -> Unit,
    accountClickable: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
    ) {
        when (transferMethod.paymentType) {
            PaymentType.CARD_TO_CARD,
            PaymentType.MELLAT_TO_MELLAT -> ShowInputsCard(
                depositId = depositId,
                onDepositIdChange = onDepositIdChange,
            )

            PaymentType.INTERNAL_SATNA,
            PaymentType.INTERNAL_PAYA,
            PaymentType.INTERNAL_BRIDGE ->
                ShowInputsAccount(
                    depositId = depositId,
                    onDepositIdChange = onDepositIdChange,
                )
        }
    }
}