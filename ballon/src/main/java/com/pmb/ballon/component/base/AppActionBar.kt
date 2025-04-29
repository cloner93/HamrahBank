package com.pmb.ballon.component.base

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material.icons.filled.CloudQueue
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.pmb.ballon.models.IconStyle
import com.pmb.ballon.models.TextStyle
import com.pmb.ballon.ui.theme.AppTheme

data class ClickableIcon(val icon: IconType, val tint: androidx.compose.ui.graphics.Color? = null, val onClick: () -> Unit)
sealed class IconType {
    data class Painter(val painter: androidx.compose.ui.graphics.painter.Painter) : IconType()
    data class ImageVector(val imageVector: androidx.compose.ui.graphics.vector.ImageVector) :
        IconType()

    data class Bitmap(val imageBitmap: androidx.compose.ui.graphics.ImageBitmap) : IconType()
}

@Composable
fun AppTopBar(
    title: String,
    textStyle: TextStyle = TextStyle.defaultTopBar(),
    requiredHeight: Boolean = true,
    startIcon: ClickableIcon? = null,
    endIcon: ClickableIcon? = null
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .then(if (requiredHeight) Modifier.height(64.dp) else Modifier),
    ) {
        if (startIcon != null) {
            AppButtonIcon(
                modifier = Modifier.align(Alignment.CenterStart),
                icon = startIcon.icon,
                onClick = startIcon.onClick,
                style = IconStyle(tint = startIcon.tint)
            )
        }

        if (endIcon != null) {
            AppButtonIcon(
                modifier = Modifier.align(Alignment.CenterEnd),
                icon = endIcon.icon,
                onClick = endIcon.onClick
            )
        }

        BaseAppText(modifier = Modifier.align(Alignment.Center), title = title, style = textStyle)
    }
}

@Composable
fun AppTopBar(title: String, onBack: (() -> Unit)? = null, endIcon: ClickableIcon? = null) {
    AppTopBar(
        title = title,
        startIcon = onBack?.let {
            ClickableIcon(
                IconType.ImageVector(Icons.AutoMirrored.Filled.ArrowBack),
                tint = AppTheme.colorScheme.onBackgroundNeutralDefault,
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
        endIcon = ClickableIcon(IconType.ImageVector(Icons.AutoMirrored.Filled.ArrowForward), onClick = {})
    )
}


@Preview
@Composable
private fun AppTopBarPreview2() {
    AppTopBar(
        title = "test toolbar",
        requiredHeight = false,
        startIcon = ClickableIcon(IconType.ImageVector(Icons.Default.CloudQueue), onClick = {}),
        endIcon = ClickableIcon(IconType.ImageVector(Icons.AutoMirrored.Filled.ArrowForward), onClick = {})
    )
}