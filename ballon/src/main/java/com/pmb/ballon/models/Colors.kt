package com.pmb.ballon.models

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import com.pmb.ballon.ui.theme.AppTheme

class Colors constructor(
    val containerColor: Color,
    val contentColor: Color,
    val disabledContainerColor: Color,
    val disabledContentColor: Color,
)

class ObjectColors constructor(
    val contentColor: Color,
    val errorContentColor: Color,
    val disabledContentColor: Color,
)

class ItemColors constructor(
    val titleColor: ObjectColors,
    val subtitleColor: ObjectColors,
    val startIcoColor: ObjectColors,
    val endIconColor: ObjectColors,
    val containerColor: ObjectColors,
    val borderColor: ObjectColors,
)

object MenuItemColors {
    @Composable
    fun default(
        titleColor: ObjectColors = ObjectColors(
            contentColor = AppTheme.colorScheme.foregroundPrimaryDefault,
            errorContentColor = AppTheme.colorScheme.onBackgroundErrorDefault,
            disabledContentColor = AppTheme.colorScheme.onBackgroundNeutralDisabled
        ),
        subtitleColor: ObjectColors = ObjectColors(
            contentColor = AppTheme.colorScheme.onBackgroundNeutralSubdued,
            errorContentColor = AppTheme.colorScheme.onBackgroundErrorDefault,
            disabledContentColor = AppTheme.colorScheme.onBackgroundNeutralDisabled
        ),
        startIconColor: ObjectColors = ObjectColors(
            contentColor = AppTheme.colorScheme.onBackgroundNeutralCTA,
            errorContentColor = AppTheme.colorScheme.onBackgroundNeutralCTA,
            disabledContentColor = AppTheme.colorScheme.onBackgroundNeutralDisabled
        ),
        endIconColor: ObjectColors = ObjectColors(
            contentColor = AppTheme.colorScheme.foregroundNeutralDefault,
            errorContentColor = AppTheme.colorScheme.onBackgroundNeutralSubdued,
            disabledContentColor = AppTheme.colorScheme.onBackgroundNeutralDisabled,
        ),
        containerColor: ObjectColors = ObjectColors(
            contentColor = Color.Transparent,
            errorContentColor = Color.Transparent,
            disabledContentColor = Color.Transparent
        ),
        borderColor: ObjectColors = ObjectColors(
            contentColor = AppTheme.colorScheme.onBackgroundNeutralSubdued,
            errorContentColor = AppTheme.colorScheme.onBackgroundNeutralCTA,
            disabledContentColor = AppTheme.colorScheme.strokeNeutral3Rest
        )
    ): ItemColors = ItemColors(
        titleColor = titleColor,
        subtitleColor = subtitleColor,
        startIcoColor = startIconColor,
        endIconColor = endIconColor,
        containerColor = containerColor,
        borderColor = borderColor
    )
}