package com.pmb.ballon.component.base

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.painterResource

@Composable
fun AppImage(@DrawableRes icon: Int) {
    Image(
        painter = painterResource(id = icon),
        contentDescription = null
    )
}