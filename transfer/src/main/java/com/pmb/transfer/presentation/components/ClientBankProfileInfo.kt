package com.pmb.transfer.presentation.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.pmb.ballon.component.base.BodyMediumText
import com.pmb.ballon.component.base.Headline6Text
import com.pmb.ballon.ui.theme.AppTheme
import com.pmb.transfer.domain.ClientBank
import com.pmb.transfer.utils.BankUtil


@Composable
fun ClientBankProfileInfo(clientBank: ClientBank, profileSize: Dp = 44.dp, iconSize: Dp = 22.dp) {
    Column(
        modifier = Modifier
            .fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        ProfileAndThumbnail(
            profileUrl = clientBank.profileUrl,
            icon = BankUtil.getLogo(clientBank.cardNumber),
            imageSize = profileSize,
            iconSize = iconSize
        )
        Spacer(modifier = Modifier.size(12.dp))
        Headline6Text(
            text = clientBank.name,
            color = AppTheme.colorScheme.onBackgroundNeutralDefault
        )
        Spacer(modifier = Modifier.size(4.dp))
        BodyMediumText(
            text = clientBank.accountNumber,
            color = AppTheme.colorScheme.onBackgroundNeutralSubdued
        )
    }
}