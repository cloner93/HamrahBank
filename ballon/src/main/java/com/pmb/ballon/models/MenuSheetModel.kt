package com.pmb.ballon.models

import androidx.annotation.DrawableRes
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import com.pmb.ballon.ui.theme.AppTheme

data class MenuSheetModel(
    val title: String,
    @DrawableRes val icon: Int,
    val textColor: @Composable () -> Color = { Color(0xFF42474E) },
    val iconTint: @Composable () -> Color = { AppTheme.colorScheme.iconColor }
)