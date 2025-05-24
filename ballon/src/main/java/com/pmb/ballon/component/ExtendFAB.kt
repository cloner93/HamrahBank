package com.pmb.ballon.component

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.pmb.ballon.component.base.AppIcon
import com.pmb.ballon.component.base.ButtonMediumText
import com.pmb.ballon.component.base.IconType
import com.pmb.ballon.models.IconStyle
import com.pmb.ballon.ui.theme.AppTheme

@Composable
fun ExtendFAB(
    modifier: Modifier = Modifier,
    extended: Boolean,
    text: String,
    icon: IconType,
    onClick: () -> Unit
) {
    // Use `FloatingActionButton` rather than `ExtendedFloatingActionButton` for full control on
    // how it should animate.
    AnimatedVisibility(visible = extended) {
        ExtendedFloatingActionButton(
            modifier = modifier,
            contentColor = AppTheme.colorScheme.onForegroundNeutralDefault,
            containerColor = AppTheme.colorScheme.foregroundNeutralDefault,
            onClick = onClick
        ) {
            Row {
                AppIcon(
                    icon = icon,
                    style = IconStyle(tint = AppTheme.colorScheme.onForegroundNeutralDefault)
                )
                // Toggle the visibility of the content with animation.
                ButtonMediumText(
                    modifier = Modifier.padding(start = 8.dp, top = 2.dp),
                    color = AppTheme.colorScheme.onForegroundNeutralDefault,
                    text = text
                )
            }
        }
    }
}