package com.pmb.ballon.component.base

import androidx.annotation.DrawableRes
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import com.pmb.ballon.models.AppButton
import com.pmb.ballon.models.IconStyle
import com.pmb.ballon.models.TextStyle
import com.pmb.ballon.ui.theme.AppTheme


@Composable
fun AppButton(
    modifier: Modifier = Modifier,
    title: String,
    colors: ButtonColors = AppButton.buttonColors(),
    textStyle: TextStyle = TextStyle.defaultButton(),
    enable: Boolean = true,
    layoutDirection: LayoutDirection = LocalLayoutDirection.current,
    onClick: () -> Unit
) {
    Button(
        modifier = modifier.heightIn(min = 48.dp),
        shape = RoundedCornerShape(12.dp),
        colors = colors,
        enabled = enable,
        onClick = onClick
    ) {
        CompositionLocalProvider(LocalLayoutDirection provides layoutDirection) {
            BaseAppText(title = title, style = textStyle)
        }
    }
}

@Composable
fun AppButtonWithIcon(
    modifier: Modifier = Modifier,
    title: String,
    colors: ButtonColors = AppButton.buttonColors(),
    textStyle: TextStyle = TextStyle.defaultButton(),
    enable: Boolean = true,
    @DrawableRes icon: Int? = null,
    isLeftIcon: Boolean = false,
    spacer: Dp = 10.dp,
    onClick: () -> Unit
) {
    Button(
        modifier = modifier.heightIn(48.dp),
        shape = RoundedCornerShape(12.dp),
        colors = colors,
        enabled = enable,
        onClick = onClick
    ) {
        if (!isLeftIcon)
            icon?.let {
                AppIcon(icon = icon)
                Spacer(modifier = Modifier.size(spacer))
            }
        BaseAppText(title = title, style = textStyle)
        if (isLeftIcon)
            icon?.let {

                Spacer(modifier = Modifier.size(spacer))
                AppIcon(icon = icon)
            }
    }
}

@Composable
fun AppButtonWithWeightIcon(
    modifier: Modifier = Modifier,
    title: String,
    colors: ButtonColors = AppButton.buttonColors(),
    textStyle: TextStyle = TextStyle.defaultButton(),
    enable: Boolean = true,
    iconStyle: IconStyle = IconStyle(tint = AppTheme.colorScheme.foregroundNeutralDefault),
    @DrawableRes icon: Int? = null,
    onClick: () -> Unit
) {
    Button(
        modifier = modifier,
        shape = RoundedCornerShape(12.dp),
        colors = colors,
        enabled = enable,
        onClick = onClick
    ) {
        BaseAppText(title = title, style = textStyle)
        icon?.let {
            Spacer(modifier = Modifier.weight(1f))
            AppIcon(icon = icon, style = iconStyle)
        }
    }
}

@Composable
fun AppOutlineButton(
    modifier: Modifier = Modifier,
    title: String,
    colors: ButtonColors = AppButton.outlinedButtonColors(),
    enable: Boolean = true,
    onClick: () -> Unit
) {
    OutlinedButton(
        modifier = modifier.heightIn(min = 46.dp),
        shape = RoundedCornerShape(12.dp),
        colors = colors,
        enabled = enable,
        onClick = onClick
    ) {
        ButtonLargeText(
            text = title,
            color = AppTheme.colorScheme.onBackgroundNeutralDefault,
        )
    }
}

@Composable
fun AppTextButton(
    modifier: Modifier = Modifier,
    title: String,
    colors: ButtonColors = AppButton.textButtonColors(),
    enable: Boolean = true,
    onClick: () -> Unit
) {
    TextButton(
        modifier = modifier,
        shape = RoundedCornerShape(12.dp),
        colors = colors,
        enabled = enable,
        onClick = onClick
    ) {
        ButtonLargeText(text = title)
    }
}


@Preview
@Composable
private fun AppButtonPreview() {
    AppButtonWithIcon(title = "Test") {

    }
}