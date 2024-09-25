package com.pmb.ballon.component.base

import androidx.annotation.DrawableRes
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.FloatingActionButtonDefaults
import androidx.compose.material3.contentColorFor
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import com.pmb.ballon.R

@Composable
fun AppFAB(
    @DrawableRes icon: Int,
    containerColor: Color = FloatingActionButtonDefaults.containerColor,
    contentColor: Color = contentColorFor(containerColor),
    onClick: () -> Unit
) {
    FloatingActionButton(
        onClick = { onClick() },
        containerColor = containerColor,
        contentColor = contentColor,
    ) {
        AppIcon(icon = icon)
    }
}

@Preview
@Composable
private fun AppFABPreview() {
    AppFAB(
        icon = R.drawable.ic_add,
        containerColor = Color(0xffE8E8EB),
        contentColor = Color(0xff00629D),
    ) {}
}