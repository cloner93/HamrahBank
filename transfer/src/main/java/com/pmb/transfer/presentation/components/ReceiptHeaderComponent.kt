package com.pmb.transfer.presentation.components

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.pmb.ballon.component.base.ButtonSmallText
import com.pmb.transfer.domain.entity.ReceiptStatus
import com.pmb.transfer.domain.entity.TransferReceiptEntity

@Composable
fun ReceiptHeaderComponent(receipt: TransferReceiptEntity?) {
    Spacer(modifier = Modifier.size(8.dp))
    receipt?.let { ClientBankProfileInfo(it.destination) }
    Spacer(modifier = Modifier.size(12.dp))
    ReceiptStatusBadge(receipt?.status ?: ReceiptStatus.UNKNOWN)
    receipt?.message?.let {
        Spacer(modifier = Modifier.size(12.dp))
        ButtonSmallText(text = it)
    }
}