package com.pmb.ballon.component

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun DashedDivider(
    color: Color,
    thickness: Dp = 1.dp,
    dashWidth: Dp = 4.dp,
    gapWidth: Dp = 4.dp,
    modifier: Modifier = Modifier
        .fillMaxWidth()
        .height(thickness)
) {
    val dashWidthPx = with(LocalDensity.current) { dashWidth.toPx() }
    val gapWidthPx = with(LocalDensity.current) { gapWidth.toPx() }

    Canvas(modifier = modifier) {
        val totalWidth = size.width
        var startX = 0f

        while (startX < totalWidth) {
            drawLine(
                color = color,
                start = Offset(x = startX, y = 0f),
                end = Offset(x = startX + dashWidthPx, y = 0f),
                strokeWidth = thickness.toPx()
            )
            startX += dashWidthPx + gapWidthPx
        }
    }
}

