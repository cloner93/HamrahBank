package com.pmb.ballon.component.base

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material.icons.filled.CloudQueue
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.pmb.ballon.ui.theme.AppTheme

data class ClickableIcon(val icon: IconType, val onClick: () -> Unit)
sealed class IconType {
    data class Painter(val painter: androidx.compose.ui.graphics.painter.Painter) : IconType()
    data class ImageVector(val imageVector: androidx.compose.ui.graphics.vector.ImageVector) : IconType()
    data class Bitmap(val imageBitmap: androidx.compose.ui.graphics.ImageBitmap) : IconType()
}

@Composable
fun AppTopBar(title: String, startIcon: ClickableIcon? = null, endIcon: ClickableIcon? = null) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(64.dp),
    ) {
        if (startIcon != null) {
            AppButtonIcon(
                modifier = Modifier.align(Alignment.CenterStart),
                icon = startIcon.icon,
                onClick = startIcon.onClick
            )
        }

        if (endIcon != null) {
            AppButtonIcon(
                modifier = Modifier.align(Alignment.CenterEnd),
                icon = endIcon.icon,
                onClick = endIcon.onClick
            )
        }

        Headline5Text(
            modifier = Modifier.align(Alignment.Center),
            text = title,
            color = AppTheme.colorScheme.onBackgroundNeutralDefault,
        )
    }
}

@Composable
fun AppTopBar(title: String, onBack: (() -> Unit)? = null, endIcon: ClickableIcon? = null) {
    AppTopBar(
        title = title,
        startIcon = onBack?.let {
            ClickableIcon(
                IconType.ImageVector(Icons.Filled.ArrowForward),
                onClick = onBack
            )
        },
        endIcon = endIcon
    )
}

@Preview
@Composable
private fun AppTopBarPreview() {
    AppTopBar(
        title = "test toolbar",
        startIcon = ClickableIcon(IconType.ImageVector(Icons.Default.CloudQueue), onClick = {}),
        endIcon = ClickableIcon(IconType.ImageVector(Icons.Filled.ArrowForward), onClick = {})
    )
}