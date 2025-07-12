package com.pmb.transfer.presentation.components.transfer_confirm

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import com.pmb.ballon.component.BaseItemColumn
import com.pmb.ballon.component.base.AppButtonIcon
import com.pmb.ballon.component.base.BaseAppText
import com.pmb.ballon.component.base.ClickableIcon
import com.pmb.ballon.models.IconStyle
import com.pmb.ballon.models.TextStyle
import com.pmb.ballon.ui.theme.AppTheme
import com.pmb.transfer.domain.entity.BankIdentifierNumberType
import com.pmb.transfer.domain.entity.TransactionClientBankEntity
import com.pmb.transfer.presentation.components.ProfileAndThumbnail
import com.pmb.transfer.utils.BankUtil

@Composable
fun ClientBankInfoTypeRow(
    info: TransactionClientBankEntity,
    enable: Boolean = true,
    background: Color = Color.Unspecified,
    titleStyle: TextStyle = TextStyle(
        color = AppTheme.colorScheme.foregroundNeutralDefault,
        typography = AppTheme.typography.bodyMedium,
        textAlign = TextAlign.Start
    ),
    subtitleStyle: TextStyle = TextStyle(
        color = AppTheme.colorScheme.onBackgroundNeutralSubdued,
        typography = AppTheme.typography.caption,
        textAlign = TextAlign.Start
    ),
    endIcon: ClickableIcon? = null,
    onClick: ((TransactionClientBankEntity) -> Unit)? = null
) {
    val formattedNumber = when (info.type) {
        BankIdentifierNumberType.ACCOUNT -> info.clientBankEntity.accountNumber
        BankIdentifierNumberType.CARD -> info.clientBankEntity.cardNumberFormated
        BankIdentifierNumberType.IBAN -> info.clientBankEntity.ibanFormated
    }

    // Chain the modifiers directly or reassign
    val modifier = Modifier
        .fillMaxWidth()
        .clip(shape = RoundedCornerShape(16.dp))
        .background(color = if (background != Color.Unspecified) background else Color.Transparent)
        .then(
            if (enable && onClick != null) Modifier.clickable { onClick(info) }
            else Modifier
        )


    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(vertical = 10.dp, horizontal = 16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        ProfileAndThumbnail(
            profileUrl = info.clientBankEntity.profileUrl,
            icon = BankUtil.getLogo(info.clientBankEntity.cardNumber),
            imageSize = 44.dp,
        )
        Spacer(modifier = Modifier.width(12.dp))

        BaseItemColumn(
            modifier = Modifier.weight(1f),
            title = { BaseAppText(title = info.clientBankEntity.name, style = titleStyle) },
            subtitle = {
                CompositionLocalProvider(LocalLayoutDirection provides LayoutDirection.Ltr) {
                    BaseAppText(title = formattedNumber, style = subtitleStyle)
                }
            },
            bottomDivider = false
        )

        if (endIcon != null) {
            AppButtonIcon(
                icon = endIcon.icon,
                style = IconStyle(tint = endIcon.tint),
                onClick = endIcon.onClick
            )
        }
    }
}