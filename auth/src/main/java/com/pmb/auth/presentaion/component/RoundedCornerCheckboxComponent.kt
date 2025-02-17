package com.pmb.auth.presentaion.component

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.tween
import androidx.compose.animation.expandHorizontally
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInHorizontally
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.selection.toggleable
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.unit.dp
import com.pmb.ballon.component.base.BodyMediumText
import com.pmb.ballon.component.base.CaptionText
import com.pmb.ballon.ui.theme.AppTheme
@Composable
fun RoundedCornerCheckboxComponent(
    modifier: Modifier = Modifier,
    title: String,
    caption: String? = null,
    isChecked: Boolean,
    size: Float = 18f,
    checkedColor: Color = AppTheme.colorScheme.foregroundPrimaryDefault,
    uncheckedBorderColor: Color = AppTheme.colorScheme.onBackgroundPrimarySubdued,
    uncheckedColor: Color = Color.White,
    onValueChange: (Boolean) -> Unit
) {
    val checkboxColor: Color by animateColorAsState(if (isChecked) checkedColor else uncheckedColor)
    val checkBorderColor: Color by animateColorAsState(if (isChecked) checkedColor else uncheckedBorderColor)
    val density = LocalDensity.current
    val duration = 100

    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier


    ) {
        Box(
            modifier = Modifier
                .size(size.dp)
                .background(color = checkboxColor, shape = RoundedCornerShape(6.dp))
                .border(width = 1.5.dp, color = checkBorderColor, shape = RoundedCornerShape(6.dp))
                .toggleable(
                    value = isChecked,
                    role = Role.Checkbox,
                    onValueChange = onValueChange
                ),
            contentAlignment = Alignment.Center
        ) {
            androidx.compose.animation.AnimatedVisibility(
                visible = isChecked,
                enter = slideInHorizontally(animationSpec = tween(duration)) {
                    with(density) { (size * -0.5).dp.roundToPx() }
                } + expandHorizontally(
                    expandFrom = Alignment.Start,
                    animationSpec = tween(duration)
                ),
                exit = fadeOut()
            ) {
                Icon(
                    Icons.Default.Check,
                    contentDescription = null,
                    tint = uncheckedColor
                )
            }
        }
        Spacer(modifier = Modifier.size(20.dp))
        Column {
            BodyMediumText(
                modifier = Modifier,
                text = title,
                color = AppTheme.colorScheme.onBackgroundNeutralDefault

            )
            caption?.takeIf { it.isNotEmpty() }?.let {
                CaptionText(
                    modifier = Modifier,
                    text = it,
                    color = AppTheme.colorScheme.onBackgroundNeutralSubdued
                )
            }
        }
    }
}


@Composable
fun RoundedCornerCheckboxComponent(
    modifier: Modifier = Modifier,
    title: AnnotatedString,
    caption: String? = null,
    isChecked: Boolean,
    size: Float = 18f,
    checkedColor: Color = AppTheme.colorScheme.foregroundPrimaryDefault,
    uncheckedBorderColor: Color = AppTheme.colorScheme.onBackgroundPrimarySubdued,
    uncheckedColor: Color = Color.White,
    onValueChange: (Boolean) -> Unit
) {
    val checkboxColor: Color by animateColorAsState(if (isChecked) checkedColor else uncheckedColor)
    val checkBorderColor: Color by animateColorAsState(if (isChecked) checkedColor else uncheckedBorderColor)
    val density = LocalDensity.current
    val duration = 100

    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier


    ) {
        Box(
            modifier = Modifier
                .size(size.dp)
                .background(color = checkboxColor, shape = RoundedCornerShape(6.dp))
                .border(width = 1.5.dp, color = checkBorderColor, shape = RoundedCornerShape(6.dp))
                .toggleable(
                    value = isChecked,
                    role = Role.Checkbox,
                    onValueChange = onValueChange
                ),
            contentAlignment = Alignment.Center
        ) {
            androidx.compose.animation.AnimatedVisibility(
                visible = isChecked,
                enter = slideInHorizontally(animationSpec = tween(duration)) {
                    with(density) { (size * -0.5).dp.roundToPx() }
                } + expandHorizontally(
                    expandFrom = Alignment.Start,
                    animationSpec = tween(duration)
                ),
                exit = fadeOut()
            ) {
                Icon(
                    Icons.Default.Check,
                    contentDescription = null,
                    tint = uncheckedColor
                )
            }
        }
        Spacer(modifier = Modifier.size(20.dp))
        Column {

            BodyMediumText(
                modifier = Modifier,
                text = title,
                color = AppTheme.colorScheme.onBackgroundNeutralDefault

            )
            caption?.takeIf { it.isNotEmpty() }?.let {
                CaptionText(
                    modifier = Modifier,
                    text = it,
                    color = AppTheme.colorScheme.onBackgroundNeutralSubdued
                )
            }
        }
    }
}