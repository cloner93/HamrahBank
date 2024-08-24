package com.pmb.ballon.component.base

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.pmb.ballon.models.TextStyle

@Composable
fun AppText(modifier: Modifier = Modifier, title: String, textStyle: TextStyle? = null) {
    val textColor = textStyle?.color ?: Color.Unspecified
    Text(modifier = modifier, text = title, color = textColor)
}
