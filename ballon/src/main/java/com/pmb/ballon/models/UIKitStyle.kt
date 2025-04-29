package com.pmb.ballon.models

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import com.pmb.ballon.ui.theme.AppTheme
import com.pmb.ballon.ui.theme.AppTypography

data class TextStyle(
    val color: Color,
    val typography: TextStyle? = AppTypography.bodyMedium,
    val textAlign: TextAlign = TextAlign.Unspecified,
    val overflow: TextOverflow = TextOverflow.Clip
) {

    companion object {
        @Composable
        fun defaultButton(
            color: Color = Color.Unspecified,
            typography: TextStyle = AppTheme.typography.buttonLarge,
            textAlign: TextAlign = TextAlign.Unspecified
        ) = TextStyle(
            color = color,
            typography = typography,
            textAlign = textAlign
        )

        @Composable
        fun defaultTopBar(
            color: Color = AppTheme.colorScheme.onBackgroundNeutralDefault,
            typography: TextStyle = AppTheme.typography.headline5,
            textAlign: TextAlign = TextAlign.Center,
        ) = TextStyle(
            color = color,
            typography = typography,
            textAlign = textAlign
        )
    }
}


data class ButtonStyle(
    val containerColor: Color? = null,
    val text: com.pmb.ballon.models.TextStyle = TextStyle(
        color = Color.Unspecified,
        typography = AppTypography.buttonLarge
    )
)


data class IconStyle(val tint: Color? = null, val size: Size = Size.DEFAULT)

data class ImageStyle(val size: Size = Size.DEFAULT)
