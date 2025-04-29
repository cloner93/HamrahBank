package com.pmb.ballon.models

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.pmb.ballon.component.base.AppIcon
import com.pmb.ballon.ui.theme.AppTheme

object Icons {

    @Composable
    fun arrowLeft() = com.pmb.ballon.R.drawable.ic_arrow_left

    @Composable
    fun ArrowDownIcon(
        modifier: Modifier = Modifier,
        color: Color = AppTheme.colorScheme.onBackgroundNeutralDefault,
        size: Size = Size.DEFAULT
    ) {
        AppIcon(
            modifier = modifier,
            icon = Icons.Default.KeyboardArrowDown,
            style = IconStyle(tint = color, size = size)
        )
    }
}