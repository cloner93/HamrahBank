package com.pmb.ballon.component.base

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import com.pmb.ballon.models.TextStyle
import com.pmb.ballon.ui.theme.AppTheme
import com.pmb.ballon.ui.theme.AppTypography

@Composable
internal fun BaseAppText(modifier: Modifier = Modifier, title: String, style: TextStyle? = null) {
    val textColor = style?.color ?: Color.Unspecified
    val typography = style?.typography ?: AppTypography.bodyLarge
    Text(
        modifier = modifier,
        text = title,
        color = textColor,
        style = typography,
        textAlign = style?.textAlign
    )
}


@Composable
fun CaptionText(
    modifier: Modifier = Modifier,
    text: String,
    color: Color = Color.Unspecified,
    textAlign: TextAlign = TextAlign.Unspecified
) {
    BaseAppText(
        modifier = modifier, title = text, style = TextStyle(
            color = color,
            typography = AppTheme.typography.caption,
            textAlign = textAlign
        )
    )
}

@Composable
fun OverlineText(
    modifier: Modifier = Modifier,
    text: String,
    color: Color = Color.Unspecified,
    textAlign: TextAlign = TextAlign.Unspecified
) {
    BaseAppText(
        modifier = modifier, title = text, style = TextStyle(
            color = color,
            typography = AppTheme.typography.overline,
            textAlign = textAlign
        )
    )
}

@Composable
fun BodySmallText(
    modifier: Modifier = Modifier,
    text: String,
    color: Color = Color.Unspecified,
    textAlign: TextAlign = TextAlign.Unspecified
) {
    BaseAppText(
        modifier = modifier, title = text, style = TextStyle(
            color = color,
            typography = AppTheme.typography.bodySmall,
            textAlign = textAlign
        )
    )
}

@Composable
fun BodyMediumText(
    modifier: Modifier = Modifier,
    text: String,
    color: Color = Color.Unspecified,
    textAlign: TextAlign = TextAlign.Unspecified
) {
    BaseAppText(
        modifier = modifier, title = text, style = TextStyle(
            color = color,
            typography = AppTheme.typography.bodyMedium,
            textAlign = textAlign
        )
    )
}

@Composable
fun BodyLargeText(
    modifier: Modifier = Modifier,
    text: String,
    color: Color = Color.Unspecified,
    textAlign: TextAlign = TextAlign.Unspecified
) {
    BaseAppText(
        modifier = modifier, title = text, style = TextStyle(
            color = color,
            typography = AppTheme.typography.buttonLarge,
            textAlign = textAlign
        )
    )
}

@Composable
fun ButtonXSmallText(
    modifier: Modifier = Modifier,
    text: String,
    color: Color = Color.Unspecified,
    textAlign: TextAlign = TextAlign.Unspecified
) {
    BaseAppText(
        modifier = modifier, title = text, style = TextStyle(
            color = color,
            typography = AppTheme.typography.buttonXSmall,
            textAlign = textAlign
        )
    )
}

@Composable
fun ButtonSmallText(
    modifier: Modifier = Modifier,
    text: String,
    color: Color = Color.Unspecified,
    textAlign: TextAlign = TextAlign.Unspecified
) {
    BaseAppText(
        modifier = modifier, title = text, style = TextStyle(
            color = color,
            typography = AppTheme.typography.buttonSmall,
            textAlign = textAlign
        )
    )
}

@Composable
fun ButtonMediumText(
    modifier: Modifier = Modifier,
    text: String,
    color: Color = Color.Unspecified,
    textAlign: TextAlign = TextAlign.Unspecified
) {
    BaseAppText(
        modifier = modifier, title = text, style = TextStyle(
            color = color,
            typography = AppTheme.typography.buttonMedium,
            textAlign = textAlign
        )
    )
}

@Composable
fun ButtonLargeText(
    modifier: Modifier = Modifier,
    text: String,
    color: Color = Color.Unspecified,
    textAlign: TextAlign = TextAlign.Unspecified
) {
    BaseAppText(
        modifier = modifier, title = text, style = TextStyle(
            color = color,
            typography = AppTheme.typography.buttonLarge,
            textAlign = textAlign
        )
    )
}

@Composable
fun SubtitleLargeText(
    modifier: Modifier = Modifier,
    text: String,
    color: Color = Color.Unspecified,
    textAlign: TextAlign = TextAlign.Unspecified
) {
    BaseAppText(
        modifier = modifier, title = text, style = TextStyle(
            color = color,
            typography = AppTheme.typography.subtitleLarge,
            textAlign = textAlign
        )
    )
}

@Composable
fun SubtitleMediumText(
    modifier: Modifier = Modifier,
    text: String,
    color: Color = Color.Unspecified,
    textAlign: TextAlign = TextAlign.Unspecified
) {
    BaseAppText(
        modifier = modifier, title = text, style = TextStyle(
            color = color,
            typography = AppTheme.typography.subtitleMedium,
            textAlign = textAlign
        )
    )
}

@Composable
fun SubtitleSmallText(
    modifier: Modifier = Modifier,
    text: String,
    color: Color = Color.Unspecified,
    textAlign: TextAlign = TextAlign.Unspecified
) {
    BaseAppText(
        modifier = modifier, title = text, style = TextStyle(
            color = color,
            typography = AppTheme.typography.subtitleSmall,
            textAlign = textAlign
        )
    )
}

@Composable
fun DisplayLargeText(
    modifier: Modifier = Modifier,
    text: String,
    color: Color = Color.Unspecified,
    textAlign: TextAlign = TextAlign.Unspecified
) {
    BaseAppText(
        modifier = modifier, title = text, style = TextStyle(
            color = color,
            typography = AppTheme.typography.displayLarge,
            textAlign = textAlign
        )
    )
}

@Composable
fun DisplayMediumText(
    modifier: Modifier = Modifier,
    text: String,
    color: Color = Color.Unspecified,
    textAlign: TextAlign = TextAlign.Unspecified
) {
    BaseAppText(
        modifier = modifier, title = text, style = TextStyle(
            color = color,
            typography = AppTheme.typography.displayMedium,
            textAlign = textAlign
        )
    )
}

@Composable
fun DisplaySmallText(
    modifier: Modifier = Modifier,
    text: String,
    color: Color = Color.Unspecified,
    textAlign: TextAlign = TextAlign.Unspecified
) {
    BaseAppText(
        modifier = modifier, title = text, style = TextStyle(
            color = color,
            typography = AppTheme.typography.displaySmall,
            textAlign = textAlign
        )
    )
}

@Composable
fun Headline1Text(
    modifier: Modifier = Modifier,
    text: String,
    color: Color = Color.Unspecified,
    textAlign: TextAlign = TextAlign.Unspecified
) {
    BaseAppText(
        modifier = modifier, title = text, style = TextStyle(
            color = color,
            typography = AppTheme.typography.headline1,
            textAlign = textAlign
        )
    )
}

@Composable
fun Headline2Text(
    modifier: Modifier = Modifier,
    text: String,
    color: Color = Color.Unspecified,
    textAlign: TextAlign = TextAlign.Unspecified
) {
    BaseAppText(
        modifier = modifier, title = text, style = TextStyle(
            color = color,
            typography = AppTheme.typography.headline2,
            textAlign = textAlign
        )
    )
}

@Composable
fun Headline3Text(
    modifier: Modifier = Modifier,
    text: String,
    color: Color = Color.Unspecified,
    textAlign: TextAlign = TextAlign.Unspecified
) {
    BaseAppText(
        modifier = modifier, title = text, style = TextStyle(
            color = color,
            typography = AppTheme.typography.headline3,
            textAlign = textAlign
        )
    )
}


@Composable
fun Headline4Text(
    modifier: Modifier = Modifier,
    text: String,
    color: Color = Color.Unspecified,
    textAlign: TextAlign = TextAlign.Unspecified
) {
    BaseAppText(
        modifier = modifier, title = text, style = TextStyle(
            color = color,
            typography = AppTheme.typography.headline4,
            textAlign = textAlign
        )
    )
}

@Composable
fun Headline5Text(
    modifier: Modifier = Modifier,
    text: String,
    color: Color = Color.Unspecified,
    textAlign: TextAlign = TextAlign.Unspecified
) {
    BaseAppText(
        modifier = modifier, title = text, style = TextStyle(
            color = color,
            typography = AppTheme.typography.headline5,
            textAlign = textAlign
        )
    )
}

@Composable
fun Headline6Text(
    modifier: Modifier = Modifier,
    text: String,
    color: Color = Color.Unspecified,
    textAlign: TextAlign = TextAlign.Unspecified
) {
    BaseAppText(
        modifier = modifier, title = text, style = TextStyle(
            color = color,
            typography = AppTheme.typography.headline6,
            textAlign = textAlign
        )
    )
}