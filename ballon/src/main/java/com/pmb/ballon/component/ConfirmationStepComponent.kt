package com.pmb.ballon.component

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.pmb.ballon.component.base.BodySmallText
import com.pmb.ballon.ui.theme.AppTheme

@Composable
fun Circle(
    modifier: Modifier = Modifier,
    color: Color = AppTheme.colorScheme.foregroundPrimaryDefault
) {
    Box(
        modifier = modifier.drawBehind {
            drawCircle(color = color)
        }
    )
}

@Composable
fun Line(
    modifier: Modifier = Modifier,
    color: Color = AppTheme.colorScheme.foregroundPrimaryDefault
) {
    Canvas(modifier = modifier) {
        drawLine(
            color = color,
            start = Offset.Zero,
            end = Offset(0f, size.height),
            strokeWidth = 3.dp.toPx()
        )
    }
}

@Composable
fun ConfirmationStepComponent(
    modifier: Modifier = Modifier,
    text: String,
    isStepPassed: Boolean = false,
    isEnabled: Boolean = true,
    isDrawLine: Boolean = true,
    textDisabledColor: Color = AppTheme.colorScheme.onBackgroundPrimaryDisabled,
    textEnabledColor: Color = AppTheme.colorScheme.foregroundNeutralDefault,
    infoEnabledColor: Color = AppTheme.colorScheme.foregroundPrimaryDefault,
    infoDisabledColor: Color = AppTheme.colorScheme.onBackgroundPrimaryDisabled
) {
    Row(modifier = modifier) {
        Column(
            modifier = Modifier.heightIn(min = 50.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Circle(
                modifier = Modifier.size(20.dp),
                color = if (isEnabled) infoEnabledColor else infoDisabledColor
            )
            if (isDrawLine)
                Line(
                    modifier = Modifier.height(30.dp),
                    color = if (isEnabled && isStepPassed) infoEnabledColor else infoDisabledColor
                )
        }
        Spacer(modifier = Modifier.size(10.dp))
        BodySmallText(
            text = text,
            color = if (isEnabled) textEnabledColor else textDisabledColor
        )
    }
}

