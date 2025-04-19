package com.pmb.ballon.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.RadioButton
import androidx.compose.material3.RadioButtonDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import com.pmb.ballon.component.base.BodyMediumText
import com.pmb.ballon.component.base.CaptionText
import com.pmb.ballon.ui.theme.AppTheme

@Composable
fun ItemCheckRow(
    title: String,
    titleMore: String? = null,
    subtitle: String? = null,
    subtitleMore: String? = null,
    titleLayoutDirection: LayoutDirection = LayoutDirection.Rtl,
    checked: Boolean,
    enabled: Boolean = true,
    onCheckedChange: (Boolean) -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .then(
                if (enabled) Modifier.clickable { onCheckedChange.invoke(true) }
                else Modifier
            ),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        RadioButton(
            selected = checked,
            colors = RadioButtonDefaults.colors()
                .copy(selectedColor = AppTheme.colorScheme.onBackgroundNeutralCTA),
            enabled = enabled,
            onClick = { onCheckedChange.invoke(checked) })
        Column(
            modifier = Modifier
                .weight(1f)
                .padding(end = 16.dp, top = 8.dp, bottom = 8.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {

                CompositionLocalProvider(LocalLayoutDirection provides titleLayoutDirection) {
                    BodyMediumText(
                        text = title,
                        color = if (enabled) AppTheme.colorScheme.foregroundNeutralDefault
                        else AppTheme.colorScheme.onBackgroundNeutralDisabled
                    )
                }
                titleMore?.let {
                    CaptionText(
                        text = it,
                        color = if (enabled) AppTheme.colorScheme.onBackgroundNeutralSubdued
                        else AppTheme.colorScheme.onBackgroundNeutralDisabled
                    )
                }
            }
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                if (subtitle != null) {
                    CaptionText(
                        text = subtitle,
                        color = if (enabled) AppTheme.colorScheme.onBackgroundNeutralSubdued
                        else AppTheme.colorScheme.onBackgroundNeutralDisabled
                    )
                }
                if (subtitleMore != null) {
                    CaptionText(
                        text = subtitleMore,
                        color = if (enabled) AppTheme.colorScheme.onBackgroundNeutralSubdued
                        else AppTheme.colorScheme.onBackgroundNeutralDisabled
                    )
                }
            }
        }
    }
}