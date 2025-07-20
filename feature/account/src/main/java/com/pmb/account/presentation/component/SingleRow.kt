package com.pmb.account.presentation.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ChevronLeft
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.pmb.ballon.component.base.AppIcon
import com.pmb.ballon.component.base.CaptionText
import com.pmb.ballon.component.base.Headline6Text
import com.pmb.ballon.models.IconStyle
import com.pmb.ballon.ui.theme.AppTheme
import com.pmb.core.utils.toCurrency

@Composable
internal fun SingleRow(
    title: String,
    amount: Double,
    onClick: () -> Unit
) {
    Row(
        modifier = Modifier.clickable { onClick() },
        verticalAlignment = Alignment.CenterVertically
    ) {
        Headline6Text(
            text = title,
            color = AppTheme.colorScheme.foregroundNeutralDefault
        )
        Spacer(modifier = Modifier.weight(1f))
        CaptionText(
            text = amount.toCurrency(),
            color = AppTheme.colorScheme.onBackgroundNeutralSubdued
        )
        CaptionText(
            text = "ریال",
            color = AppTheme.colorScheme.onBackgroundNeutralSubdued
        )
        AppIcon(
            icon = Icons.Default.ChevronLeft,
            style = IconStyle(tint = AppTheme.colorScheme.onBackgroundNeutralSubdued)
        )
    }
}
