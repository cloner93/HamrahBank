package com.pmb.account.presentation.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ChevronLeft
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.pmb.account.R
import com.pmb.ballon.component.base.AppIcon
import com.pmb.ballon.component.base.AppImage
import com.pmb.ballon.component.base.CaptionText
import com.pmb.ballon.component.base.Headline6Text
import com.pmb.ballon.models.IconStyle
import com.pmb.ballon.models.ImageStyle
import com.pmb.ballon.models.Size
import com.pmb.ballon.ui.theme.AppTheme
import com.pmb.core.utils.toCurrency
import com.pmb.domain.model.TransactionModel
import com.pmb.domain.model.TransactionType

@Composable
internal fun TransactionRow(item: TransactionModel, onClick: () -> Unit = {}) {
    Row(
        modifier = Modifier
            .height(48.dp)
            .clickable {
                onClick()
            }, verticalAlignment = Alignment.CenterVertically
    ) {
        AppImage(
            image = when (item.type) {
                TransactionType.DEPOSIT -> R.drawable.ic_transfer
                TransactionType.WITHDRAWAL -> R.drawable.ic_transfer
                TransactionType.TRANSFER -> R.drawable.ic_transfer
                TransactionType.RECEIVE -> R.drawable.ic_receive
                TransactionType.FEE -> R.drawable.ic_transfer
                TransactionType.UNKNOWN -> R.drawable.ic_unknown
            }, style = ImageStyle(size = Size.FIX(42.dp))
        )
        Spacer(modifier = Modifier.width(13.dp))
        Column(
            modifier = Modifier.fillMaxHeight(), verticalArrangement = Arrangement.SpaceBetween
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Headline6Text(
                    text = item.title, color = AppTheme.colorScheme.foregroundNeutralDefault
                )
                Spacer(modifier = Modifier.weight(1f))//Color/On Background/Neutral/Subdued
                CaptionText(
                    text = item.amount.toCurrency(),
                    color = AppTheme.colorScheme.onBackgroundNeutralSubdued
                )
                CaptionText(
                    text = item.currency, color = AppTheme.colorScheme.onBackgroundNeutralSubdued
                )
                AppIcon(
                    icon = Icons.Default.ChevronLeft,
                    style = IconStyle(tint = AppTheme.colorScheme.foregroundNeutralRest)
                )
            }
            CaptionText(
                text = item.date,
                color = AppTheme.colorScheme.onBackgroundNeutralSubdued
            )
        }
    }
}