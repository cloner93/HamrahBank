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
import com.pmb.transfer.domain.ClientBank
import com.pmb.transfer.utils.BankUtil

@Composable
fun ContactRow(clientBank: ClientBank, onClick: (ClientBank) -> Unit) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .padding(horizontal = 12.dp)
            .clickable { onClick(clientBank) }
    ) {
        ProfileAndThumbnail(
            profileUrl = clientBank.profileUrl,
            icon = BankUtil.getLogo(clientBank.cardNumber),
        )
        Spacer(modifier = Modifier.height(6.dp))
        CaptionText(text = clientBank.name)
    }
}
