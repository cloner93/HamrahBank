package com.pmb.ballon.component.base

import androidx.annotation.DrawableRes
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.pmb.ballon.R
import com.pmb.ballon.models.IconStyle
import com.pmb.ballon.models.Size
import com.pmb.ballon.ui.theme.AppTheme

@Composable
fun AppIcon(modifier: Modifier = Modifier, icon: IconType, style: IconStyle? = null) {
    when (icon) {
        is IconType.Bitmap -> AppIcon(modifier = modifier, icon = icon.imageBitmap, style = style)
        is IconType.ImageVector -> AppIcon(
            modifier = modifier,
            icon = icon.imageVector,
            style = style
        )

        is IconType.Painter -> AppIcon(modifier = modifier, icon = icon.painter, style = style)
    }
}

@Composable
fun AppIcon(modifier: Modifier = Modifier, @DrawableRes icon: Int, style: IconStyle? = null) {
    val _modifier = style?.let {
        when (style.size) {
            is Size.FIX -> modifier.size(style.size.all)
            is Size.Rectangle -> modifier.size(width = style.size.width, height = style.size.height)
            Size.DEFAULT -> modifier.size(24.dp)
        }
    } ?: modifier.size(24.dp)
    val tint = style?.tint ?: AppTheme.colorScheme.onForegroundNeutralDefault

    Icon(
        modifier = _modifier,
        painter = painterResource(id = icon),
        contentDescription = null,
        tint = tint
    )
}

@Composable
fun AppIcon(modifier: Modifier = Modifier, icon: ImageVector, style: IconStyle? = null) {
    val _modifier = style?.let {
        when (style.size) {
            is Size.FIX -> modifier.size(style.size.all)
            is Size.Rectangle -> modifier.size(width = style.size.width, height = style.size.height)
            Size.DEFAULT -> modifier.size(24.dp)
        }
    } ?: modifier.size(24.dp)
    val tint = style?.tint ?: AppTheme.colorScheme.onForegroundNeutralDefault

    Icon(
        modifier = _modifier,
        imageVector = icon,
        contentDescription = null,
        tint = tint
    )
}

@Composable
fun AppIcon(
    modifier: Modifier = Modifier,
    icon: Painter,
    style: IconStyle? = null
) {
    val iconModifier = style?.let {
        when (style.size) {
            is Size.FIX -> modifier.size(style.size.all)
            is Size.Rectangle -> modifier.size(width = style.size.width, height = style.size.height)
            Size.DEFAULT -> modifier.size(24.dp)
        }
    } ?: modifier.size(24.dp)
    val tint = style?.tint ?: AppTheme.colorScheme.onForegroundNeutralDefault

    Icon(
        modifier = iconModifier,
        painter = icon,
        contentDescription = null,
        tint = tint
    )
}

@Composable
fun AppIcon(modifier: Modifier = Modifier, icon: ImageBitmap, style: IconStyle? = null) {
    val _modifier = style?.let {
        when (style.size) {
            is Size.FIX -> modifier.size(style.size.all)
            is Size.Rectangle -> Modifier.size(width = style.size.width, height = style.size.height)
            Size.DEFAULT -> modifier.size(24.dp)
        }
    } ?: modifier.size(24.dp)
    val tint = style?.tint ?: AppTheme.colorScheme.onForegroundNeutralDefault

    Icon(
        modifier = _modifier,
        bitmap = icon,
        contentDescription = null,
        tint = tint
    )
}

@Composable
fun AppButtonIcon(
    modifier: Modifier = Modifier,
    icon: IconType,
    style: IconStyle? = null,
    onClick: () -> Unit
) {
    when (icon) {
        is IconType.Bitmap -> AppButtonIcon(
            modifier = modifier,
            icon = icon.imageBitmap,
            style = style,
            onClick = onClick
        )

        is IconType.ImageVector -> AppButtonIcon(
            modifier = modifier,
            icon = icon.imageVector,
            style = style,
            onClick = onClick
        )

        is IconType.Painter -> AppButtonIcon(
            modifier = modifier,
            icon = icon.painter,
            style = style,
            onClick = onClick
        )
    }
}

@Composable
fun AppButtonIcon(
    modifier: Modifier = Modifier,
    icon: ImageVector,
    style: IconStyle? = null,
    onClick: () -> Unit
) {
    val tint = style?.tint ?: AppTheme.colorScheme.onBackgroundNeutralDefault

    IconButton(
        onClick = onClick,
        modifier = modifier
    ) {
        Icon(
            imageVector = icon,
            contentDescription = "Description Icon",
            tint = tint
        )
    }
}

@Composable
fun AppButtonIcon(
    modifier: Modifier = Modifier,
    icon: ImageBitmap,
    style: IconStyle? = null,
    onClick: () -> Unit
) {
    val tint = style?.tint ?: AppTheme.colorScheme.onBackgroundNeutralDefault

    IconButton(
        onClick = onClick,
        modifier = modifier
    ) {
        Icon(
            bitmap = icon,
            contentDescription = "Description Icon",
            tint = tint
        )
    }
}

@Composable
fun AppButtonIcon(
    modifier: Modifier = Modifier,
    icon: Painter,
    style: IconStyle? = null,
    onClick: () -> Unit
) {
    val tint = style?.tint ?: AppTheme.colorScheme.onBackgroundNeutralDefault

    IconButton(
        onClick = onClick,
        modifier = modifier
    ) {
        Icon(
            painter = icon,
            contentDescription = "Description Icon",
            tint = tint
        )
    }
}

@Composable
fun AppButtonIcon(
    modifier: Modifier = Modifier,
    @DrawableRes icon: Int,
    style: IconStyle? = null,
    onClick: () -> Unit
) {
    AppButtonIcon(
        modifier = modifier,
        icon = painterResource(id = icon),
        style = style,
        onClick = onClick
    )
}

@Preview
@Composable
private fun AppIconPreview() {
    Column {
        AppIcon(icon = R.drawable.ic_key)
        AppIcon(icon = R.drawable.ic_key, style = IconStyle(tint = Color.Red))
    }
}