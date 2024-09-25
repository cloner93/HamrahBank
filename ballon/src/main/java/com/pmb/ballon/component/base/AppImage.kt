package com.pmb.ballon.component.base

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import com.pmb.ballon.models.ImageStyle

@Composable
fun AppImage(
    modifier: Modifier = Modifier,
    @DrawableRes image: Int,
    style: ImageStyle = ImageStyle()
) {
    Image(
        modifier = modifier,
        painter = painterResource(id = image),
        contentDescription = null
    )
}