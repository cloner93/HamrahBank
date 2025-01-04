package com.pmb.ballon.component.base

import androidx.annotation.DrawableRes
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LocalContentColor
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

@Composable
fun AppIcon(@DrawableRes icon: Int, style: IconStyle? = null) {
    val modifier = style?.let {
        when (style.size) {
            is Size.FIX -> Modifier.size(style.size.all)
            is Size.Rectangle -> Modifier.size(width = style.size.width, height = style.size.height)
            Size.DEFAULT -> Modifier.size(24.dp)
        }
    } ?: Modifier.size(24.dp)
    val tint = style?.tint ?: LocalContentColor.current

    Icon(
        modifier = modifier,
        painter = painterResource(id = icon),
        contentDescription = null,
        tint = tint
    )
}

@Composable
fun AppIcon(icon: ImageVector, style: IconStyle? = null) {
    val modifier = style?.let {
        when (style.size) {
            is Size.FIX -> Modifier.size(style.size.all)
            is Size.Rectangle -> Modifier.size(width = style.size.width, height = style.size.height)
            Size.DEFAULT -> Modifier.size(24.dp)
        }
    } ?: Modifier.size(24.dp)
    val tint = style?.tint ?: LocalContentColor.current

    Icon(
        modifier = modifier,
        imageVector = icon,
        contentDescription = null,
        tint = tint
    )
}

@Composable
fun AppIcon(icon: Painter, style: IconStyle? = null) {
    val modifier = style?.let {
        when (style.size) {
            is Size.FIX -> Modifier.size(style.size.all)
            is Size.Rectangle -> Modifier.size(width = style.size.width, height = style.size.height)
            Size.DEFAULT -> Modifier.size(24.dp)
        }
    } ?: Modifier.size(24.dp)
    val tint = style?.tint ?: LocalContentColor.current

    Icon(
        modifier = modifier,
        painter = icon,
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
            icon = icon.icon,
            style = style,
            onClick = onClick
        )

        is IconType.ImageVector -> AppButtonIcon(
            modifier = modifier,
            icon = icon.icon,
            style = style,
            onClick = onClick
        )

        is IconType.Painter -> AppButtonIcon(
            modifier = modifier,
            icon = icon.icon,
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
    val tint = style?.tint ?: LocalContentColor.current

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
    val tint = style?.tint ?: LocalContentColor.current

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
    val tint = style?.tint ?: LocalContentColor.current

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