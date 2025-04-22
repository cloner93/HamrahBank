package com.pmb.ballon.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.ProvideTextStyle
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.runtime.remember
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp

object AppTheme {

    val colorScheme: CustomColors
        @Composable
        @ReadOnlyComposable
        get() = LocalColors.current

    val typography: CustomTypography
        @Composable
        @ReadOnlyComposable
        get() = LocalTypography.current

    val spaces: CustomSpaces
        @Composable
        @ReadOnlyComposable
        get() = LocalSpaces.current
}

data class CustomSpaces(
    val small: Dp = 4.dp,
    val medium: Dp = 8.dp,
    val large: Dp = 16.dp,
    val extraLarge: Dp = 40.dp,
)

/* Global variables (application level) */
val LocalSpaces = staticCompositionLocalOf { CustomSpaces() }

val LocalColors = staticCompositionLocalOf { lightColors() }

val LocalTypography = staticCompositionLocalOf { CustomTypography() }

@Composable
fun HamrahBankTheme(
    spaces: CustomSpaces = AppTheme.spaces,
    typography: CustomTypography = AppTheme.typography,
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit,
) {
    val currentColor = if (darkTheme) darkColors() else lightColors()
    val rememberedColors = remember(currentColor) { currentColor.copy() }.apply {
        updateColorsFrom(currentColor)
    }

    CompositionLocalProvider(
        LocalColors provides rememberedColors,
        LocalSpaces provides spaces,
        LocalTypography provides typography,
        LocalLayoutDirection provides LayoutDirection.Rtl
    ) {
        ProvideTextStyle(typography.bodyMedium, content = content)
    }
}