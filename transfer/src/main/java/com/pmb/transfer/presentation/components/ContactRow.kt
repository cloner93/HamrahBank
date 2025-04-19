package com.pmb.transfer.presentation.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.pmb.ballon.component.base.CaptionText
import com.pmb.transfer.domain.entity.TransactionClientBankEntity
import com.pmb.transfer.utils.BankUtil

@Composable
fun ContactRow(item: TransactionClientBankEntity, onClick: (TransactionClientBankEntity) -> Unit) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .padding(horizontal = 12.dp)
            .clickable { onClick(item) }
    ) {
        ProfileAndThumbnail(
            profileUrl = item.clientBankEntity.profileUrl,
            icon = BankUtil.getLogo(item.clientBankEntity.cardNumber),
        )
        Spacer(modifier = Modifier.height(6.dp))
        CaptionText(text = item.clientBankEntity.name)
    }
}
