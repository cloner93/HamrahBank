package com.pmb.ballon.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable

private val DarkColorScheme = darkColorScheme(
    primary = Color.Dark.OnBackgroundNeutralDefault,
    secondary = Color.Dark.ForegroundNeutralDefault,
    onSecondary = Color.Dark.LOnForegroundNeutralDefault,
    tertiary = Pink80,
    background = Color.Dark.Background2Neutral,
    onBackground = Color.Dark.OnBackgroundPrimarySubdued,
    error = Color.Dark.StrokeNeutral2Error,
    onError = Color.Dark.OnBackgroundErrorDefault,
)

//
//internal val ColorScheme.defaultButtonColors: ButtonColors
//    get() {
//        return defaultButtonColorsCached
//            ?: ButtonColors(
//                containerColor = fromToken(FilledButtonTokens.ContainerColor),
//                contentColor = fromToken(FilledButtonTokens.LabelTextColor),
//                disabledContainerColor =
//                fromToken(FilledButtonTokens.DisabledContainerColor)
//                    .copy(alpha = FilledButtonTokens.DisabledContainerOpacity),
//                disabledContentColor =
//                fromToken(FilledButtonTokens.DisabledLabelTextColor)
//                    .copy(alpha = FilledButtonTokens.DisabledLabelTextOpacity)
//            )
//                .also { defaultButtonColorsCached = it }
//    }

private val LightColorScheme = lightColorScheme(
    primary = Color.Light.OnBackgroundNeutralDefault,
    secondary = Color.Light.ForegroundNeutralDefault,
    onSecondary = Color.Light.LOnForegroundNeutralDefault,
    tertiary = Pink40,
    background = Color.Light.Background2Neutral,
    onBackground = Color.Light.OnBackgroundPrimarySubdued,
    error = Color.Light.StrokeNeutral2Error,
    onError = Color.Light.OnBackgroundErrorDefault,


    /* Other default colors to override
    onPrimary =,
    primaryContainer =,
    onPrimaryContainer =,
    inversePrimary =,
    secondaryContainer =,
    onSecondaryContainer =,
    onTertiary =,
    tertiaryContainer =,
    onTertiaryContainer =,
    onBackground =,
    surface =,
    onSurface =,
    surfaceVariant =,
    onSurfaceVariant =,
    surfaceTint =,
    inverseSurface =,
    inverseOnSurface =,

    errorContainer =,
    onErrorContainer =,
    outline =,
    outlineVariant =,
    scrim =,
    surfaceBright =,
    surfaceContainer =,
    surfaceContainerHigh =,
    surfaceContainerHighest =,
    surfaceContainerLow =,
    surfaceContainerLowest =,
    surfaceDim =,
     */
)

@Composable
fun HamrahBankTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    // Dynamic color is available on Android 12+
    dynamicColor: Boolean = true,
    content: @Composable () -> Unit
) {
    val colorScheme = when {
//        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
//            val context = LocalContext.current
//            if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
//            dynamicLightColorScheme(context)
//        }

//        darkTheme -> DarkColorScheme
        else -> LightColorScheme
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = AppTypography,
        content = content
    )
}