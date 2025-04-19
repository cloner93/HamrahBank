package com.pmb.transfer.presentation.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import com.pmb.ballon.component.base.BodyMediumText
import com.pmb.ballon.component.base.Headline6Text
import com.pmb.ballon.ui.theme.AppTheme
import com.pmb.transfer.domain.entity.BankIdentifierNumberType
import com.pmb.transfer.domain.entity.TransactionClientBankEntity
import com.pmb.transfer.utils.BankUtil


@Composable
fun ClientBankProfileInfo(
    item: TransactionClientBankEntity,
    profileSize: Dp = 44.dp,
    iconSize: Dp = 22.dp
) {
    Column(
        modifier = Modifier
            .fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        ProfileAndThumbnail(
            profileUrl = item.clientBankEntity.profileUrl,
            icon = BankUtil.getLogo(item.clientBankEntity.cardNumber),
            imageSize = profileSize,
            iconSize = iconSize
        )
        Spacer(modifier = Modifier.size(12.dp))
        Headline6Text(
            text = item.clientBankEntity.name,
            color = AppTheme.colorScheme.onBackgroundNeutralDefault
        )
        Spacer(modifier = Modifier.size(4.dp))
        CompositionLocalProvider(LocalLayoutDirection provides LayoutDirection.Ltr) {
            BodyMediumText(
                text = when (item.type) {
                    BankIdentifierNumberType.ACCOUNT -> item.clientBankEntity.accountNumber
                    BankIdentifierNumberType.CARD -> item.clientBankEntity.cardNumberFormated
                    BankIdentifierNumberType.IBAN -> item.clientBankEntity.ibanFormated
                },
                color = AppTheme.colorScheme.onBackgroundNeutralSubdued
            )
        }
    }
}