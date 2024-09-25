package com.pmb.ballon.models

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import com.pmb.ballon.ui.theme.AppTypography

data class TextStyle(val color: Color, val typography: TextStyle? = AppTypography.bodyMedium, val textAlign: TextAlign = TextAlign.Unspecified)

data class ButtonStyle(
    val containerColor: Color? = null,
    val text: com.pmb.ballon.models.TextStyle = TextStyle(
        color = Color.Unspecified,
        typography = AppTypography.labelLarge
    )
)


data class IconStyle(val tint: Color? = null, val size: Size = Size.DEFAULT)

data class ImageStyle(val size: Size = Size.DEFAULT)
