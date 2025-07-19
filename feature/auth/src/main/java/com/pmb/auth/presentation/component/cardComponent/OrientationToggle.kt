package com.pmb.auth.presentation.component.cardComponent

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.pmb.ballon.ui.theme.AppTheme
import com.pmb.ballon.ui.theme.HamrahBankTheme

@Composable
fun OrientationToggle(
    modifier: Modifier = Modifier,
    selected: Orientation,
    onSelect: (Orientation) -> Unit
) {
    Row(
        modifier = modifier
            .clip(RoundedCornerShape(16))
            .background(AppTheme.colorScheme.backgroundTintNeutralDefault)
            .padding(4.dp)
            .fillMaxWidth()
            .height(44.dp),
        horizontalArrangement = Arrangement.SpaceEvenly,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Orientation.entries.forEach { orientation ->
            val isSelected = orientation == selected

            val backgroundColor by animateColorAsState(
                targetValue = if (isSelected) AppTheme.colorScheme.background1Neutral else Color.Transparent,
                animationSpec = tween(durationMillis = 0),
                label = "backgroundColor"
            )

            val contentColor by animateColorAsState(
                targetValue = if (isSelected) AppTheme.colorScheme.onBackgroundNeutralActive else AppTheme.colorScheme.onBackgroundNeutralSubdued,
                animationSpec = tween(durationMillis = 0),
                label = "textColor"
            )
            val animatedElevation by animateDpAsState(
                targetValue = if (isSelected) 4.dp else 0.dp,
                animationSpec = tween(durationMillis = 0),
                label = "elevation"
            )

            Box(
                modifier = Modifier
                    .weight(1 / (Orientation.entries.size.toFloat()))
                    .fillMaxHeight()
                    .shadow(animatedElevation, RoundedCornerShape(12.dp), clip = false)
                    .clip(RoundedCornerShape(12.dp))
                    .background(backgroundColor)
                    .clickable { onSelect(orientation) }
            ) {
                Text(
                    modifier = Modifier.align(Alignment.Center),
                    text = orientation.label,
                    color = contentColor,
                    textAlign = TextAlign.Center,
                    style = AppTheme.typography.buttonSmall,
                )
            }
        }
    }
}


enum class Orientation(val label: String) {
    Horizontal("افقی"),
    Vertical("عمودی")
}

@Preview
@Composable
fun OrientationTogglePreview() {
    var selected by remember { mutableStateOf(Orientation.Horizontal) }

    HamrahBankTheme() {
        OrientationToggle(
            selected = selected,
        ) {
            selected = it
        }
    }
}