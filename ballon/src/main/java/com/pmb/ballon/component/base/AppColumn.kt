package com.pmb.ballon.component.base

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.dp
import com.pmb.ballon.ui.theme.AppTheme


@Composable
fun RoundedTopColumn(
    modifier: Modifier = Modifier,
    shape: Shape = RoundedCornerShape(topStart = 20.dp, topEnd = 20.dp),
    backgroundColor: Color = AppTheme.colorScheme.background1Neutral,
    content: @Composable ColumnScope.() -> Unit
) {
    Column(
        modifier = modifier
            .clip(shape)
            .background(backgroundColor)
            .padding(16.dp),
        content = content
    )
}