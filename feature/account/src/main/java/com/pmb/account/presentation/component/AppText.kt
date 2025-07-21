package com.pmb.account.presentation.component

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.pmb.ballon.models.TextStyle
import com.pmb.ballon.ui.theme.AppTypography


@Composable
internal fun AppText(modifier: Modifier = Modifier, title: String, style: TextStyle? = null) {
    val textColor = style?.color ?: Color.Unspecified
    val typography = style?.typography ?: AppTypography.bodyLarge
    Text(
        modifier = modifier,
        text = title,
        color = textColor,
        style = typography,
        textAlign = style?.textAlign
    )
}