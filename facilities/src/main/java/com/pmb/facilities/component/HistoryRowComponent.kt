package com.pmb.facilities.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.pmb.ballon.component.base.AppImage
import com.pmb.ballon.component.base.BodyMediumText
import com.pmb.ballon.component.base.CaptionText
import com.pmb.ballon.models.ImageStyle
import com.pmb.ballon.models.Size
import com.pmb.ballon.ui.theme.AppTheme
import com.pmb.facilities.charge.presentation.charge.ChargeData

@Composable
fun HistoryRowComponent(
    modifier: Modifier = Modifier,
    data: ChargeData
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 12.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        AppImage(
            image = data.imageString,
            style = ImageStyle(size = Size.FIX(48.dp))
        )
        Spacer(modifier = Modifier.size(12.dp))
        Column(modifier = Modifier
            .fillMaxHeight()
            .weight(1f)) {
            BodyMediumText(
                text = data.phoneNumber,
                color = AppTheme.colorScheme.foregroundNeutralDefault
            )
            CaptionText(
                text = data.operator,
                color = AppTheme.colorScheme.onBackgroundNeutralSubdued
            )
        }


    }
}