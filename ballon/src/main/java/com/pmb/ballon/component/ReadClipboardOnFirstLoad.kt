package com.pmb.ballon.component

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.platform.LocalClipboardManager

@Composable
fun ReadClipboardOnFirstLoad(clipboardText: (String) -> Unit) {
    val clipboardManager = LocalClipboardManager.current
    LaunchedEffect(Unit) { clipboardManager.getText()?.let { clipboardText.invoke(it.text) } }
}