package com.pmb.ballon.component.base

import androidx.annotation.DrawableRes
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.pmb.ballon.models.AppButton
import com.pmb.ballon.models.TextStyle


@Composable
fun AppButton(
    modifier: Modifier = Modifier,
    title: String,
    colors: ButtonColors = AppButton.buttonColors(),
    textStyle: TextStyle = TextStyle.defaultButton(),
    enable: Boolean = true,
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
    spacer: Dp = 10.dp,
    onClick: () -> Unit
) {
    Button(
        modifier = modifier,
        shape = RoundedCornerShape(12.dp),
        colors = colors,
        enabled = enable,
        onClick = onClick
    ) {
        icon?.let {
            AppIcon(icon = icon)
            Spacer(modifier = Modifier.size(spacer))
        }
        BaseAppText(title = title, style = textStyle)
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
        modifier = modifier,
        shape = RoundedCornerShape(12.dp),
        colors = colors,
        enabled = enable,
        onClick = onClick
    ) {
        ButtonLargeText(text = title)
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