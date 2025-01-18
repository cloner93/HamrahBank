package com.pmb.ballon.component.base

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import com.pmb.ballon.models.ImageStyle
import com.pmb.ballon.models.Size

@Composable
fun AppImage(
    modifier: Modifier = Modifier,
    image: IconType,
    style: ImageStyle = ImageStyle()
) {
    when (image) {
        is IconType.Painter -> AppImage(modifier = modifier, image = image.painter, style = style)
        is IconType.ImageVector -> AppImage(modifier = modifier, image = image.imageVector, style = style)
        is IconType.Bitmap -> AppImage(modifier = modifier, image = image.imageBitmap, style = style)
    }
}

@Composable
fun AppImage(
    modifier: Modifier = Modifier,
    @DrawableRes image: Int,
    style: ImageStyle = ImageStyle()
) {
    val _modifier = style.let {
        when (style.size) {
            is Size.FIX -> modifier.size(style.size.all)
            is Size.Rectangle -> modifier.size(width = style.size.width, height = style.size.height)
            Size.DEFAULT -> modifier
        }
    }
    Image(
        modifier = _modifier,
        painter = painterResource(id = image),
        contentDescription = null
    )
}

@Composable
fun AppImage(
    modifier: Modifier = Modifier,
    image: Painter,
    style: ImageStyle = ImageStyle()
) {
    val _modifier = style.let {
        when (style.size) {
            is Size.FIX -> modifier.size(style.size.all)
            is Size.Rectangle -> modifier.size(width = style.size.width, height = style.size.height)
            Size.DEFAULT -> modifier
        }
    }
    Image(
        modifier = _modifier,
        painter = image,
        contentDescription = null
    )
}

@Composable
fun AppImage(
    modifier: Modifier = Modifier,
    image: ImageVector,
    style: ImageStyle = ImageStyle()
) {
    val _modifier = style.let {
        when (style.size) {
            is Size.FIX -> modifier.size(style.size.all)
            is Size.Rectangle -> modifier.size(width = style.size.width, height = style.size.height)
            Size.DEFAULT -> modifier
        }
    }
    Image(
        modifier = _modifier,
        imageVector = image,
        contentDescription = null
    )
}

@Composable
fun AppImage(
    modifier: Modifier = Modifier,
    image: ImageBitmap,
    style: ImageStyle = ImageStyle()
) {
    val _modifier = style.let {
        when (style.size) {
            is Size.FIX -> modifier.size(style.size.all)
            is Size.Rectangle -> modifier.size(width = style.size.width, height = style.size.height)
            Size.DEFAULT -> modifier
        }
    }
    Image(
        modifier = _modifier,
        bitmap = image,
        contentDescription = null
    )
}