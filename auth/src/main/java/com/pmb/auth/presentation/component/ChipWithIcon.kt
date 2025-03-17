package com.pmb.auth.presentation.component

import androidx.annotation.DrawableRes
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import com.pmb.ballon.component.base.AppIcon
import com.pmb.ballon.component.base.BodySmallText
import com.pmb.ballon.models.IconStyle
import com.pmb.ballon.models.Size
import com.pmb.ballon.ui.theme.AppTheme

@Composable
fun ChipWithIcon(value: String, @DrawableRes icon :Int, clickable: () -> Unit) {
    Box(
        modifier = Modifier
            .wrapContentSize()
            .height(32.dp)
            .clip(shape = RoundedCornerShape(16.dp))
            .border(
                width = 1.dp,
                color = AppTheme.colorScheme.strokeNeutral1Default,
                shape = RoundedCornerShape(16.dp)
            )
            .clickable { clickable.invoke() }
            .padding(horizontal = 10.dp, vertical = 6.dp),
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            AppIcon(
                icon = icon,
                style = IconStyle(
                    tint = AppTheme.colorScheme.onBackgroundNeutralSubdued,
                    size = Size.FIX(18.dp)
                )
            )
            Spacer(modifier = Modifier.size(6.dp))
            BodySmallText(text = value, color = AppTheme.colorScheme.onBackgroundNeutralDefault)
        }
    }
}