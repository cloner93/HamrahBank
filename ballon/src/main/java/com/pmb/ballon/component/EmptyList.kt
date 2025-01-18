package com.pmb.ballon.component

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.pmb.ballon.component.base.IconType
import com.pmb.ballon.models.ImageStyle
import com.pmb.ballon.models.Size
import com.pmb.ballon.models.TextStyle
import com.pmb.ballon.ui.theme.AppTheme

@Composable
fun EmptyList(iconType: IconType, message: String) {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        TextImage(
            image = iconType,
            text = message,
            spacer = 16.dp,
            imageStyle = ImageStyle(size = Size.DEFAULT),
            textStyle = TextStyle(
                color = AppTheme.colorScheme.onBackgroundNeutralSubdued,
                typography = AppTheme.typography.bodySmall,
                textAlign = TextAlign.Center
            )
        )
    }
}