package com.pmb.ballon.component.base

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.pmb.ballon.models.ButtonStyle

@Composable
fun AppButton(
    modifier: Modifier = Modifier,
    title: String,
    style: ButtonStyle? = ButtonStyle(),
    enable: Boolean = true,
    onClick: () -> Unit
) {
    Button(
        modifier = modifier,
        shape = RoundedCornerShape(12.dp),
        enabled = enable,
        onClick = onClick
    ) {
        AppText(title = title, style = style?.text)
    }
}


@Composable
fun AppOutlineButton(
    modifier: Modifier = Modifier,
    title: String,
    style: ButtonStyle? = ButtonStyle(),
    onClick: () -> Unit
) {
    OutlinedButton(
        modifier = modifier,
        shape = RoundedCornerShape(12.dp),
        onClick = onClick
    ) {
        AppText(title = title, style = style?.text)
    }
}

@Composable
fun AppTextButton(
    modifier: Modifier = Modifier,
    title: String,
    style: ButtonStyle? = ButtonStyle(),
    onClick: () -> Unit
) {
    TextButton(
        modifier = modifier,
        shape = RoundedCornerShape(12.dp),
        onClick = onClick
    ) {
        AppText(title = title, style = style?.text)
    }
}


@Preview
@Composable
private fun AppButtonPreview() {
    AppButton(title = "Test") {

    }
}