package com.pmb.ballon.component

import androidx.annotation.DrawableRes
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.pmb.ballon.component.base.AppIcon
import com.pmb.ballon.component.base.BodyMediumText
import com.pmb.ballon.models.IconStyle
import com.pmb.ballon.models.Icons
import com.pmb.ballon.models.ItemColors
import com.pmb.ballon.models.MenuItemColors
import com.pmb.ballon.ui.theme.AppTheme

@Composable
fun ItemVerticalTextIcon(
    modifier: Modifier = Modifier,
    title: String,
    subtitle: String? = null,
    @DrawableRes startIcon: Int? = null,
    @DrawableRes endIcon: Int? = Icons.arrowLeft(),
    colors: ItemColors = MenuItemColors.default(),
    containerPadding: PaddingValues = PaddingValues(horizontal = 4.dp, vertical = 8.dp),
    textPadding: PaddingValues = PaddingValues(horizontal = 12.dp),
    startIconPadding: PaddingValues = PaddingValues(all = 12.dp),
    enable: Boolean = true,
    onClick: (() -> Unit)? = null
) {
    val _modifier = modifier
        .fillMaxWidth()
        .clip(shape = RoundedCornerShape(16.dp))
        .background(color = if (enable) colors.containerColor.contentColor else colors.containerColor.disabledContentColor)
        .then(
            Modifier.border(
                width = 1.dp,
                color = if (enable) colors.borderColor.contentColor else colors.borderColor.disabledContentColor,
                shape = RoundedCornerShape(16.dp)
            )
        )
        .then(
            if (enable && onClick != null) Modifier.clickable { onClick() }
            else modifier
        )

    Row(
        modifier = _modifier.padding(containerPadding),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        startIcon?.let {
            Box(modifier = Modifier.padding(startIconPadding)) {
                AppIcon(
                    icon = it,
                    style = IconStyle(
                        tint = if (enable) colors.startIcoColor.contentColor else colors.startIcoColor.disabledContentColor,
                    )
                )
            }
        }

        Column(
            modifier = Modifier
                .padding(textPadding)
                .weight(1f),
            horizontalAlignment = Alignment.Start
        ) {
            BodyMediumText(
                text = title,
                color = if (enable) colors.titleColor.contentColor else colors.titleColor.disabledContentColor
            )
            subtitle?.let {
                Text(
                    text = it,
                    color = if (enable) colors.subtitleColor.contentColor else colors.subtitleColor.disabledContentColor,
                    maxLines = 1,                  // Restrict to a single line
                    style = AppTheme.typography.bodySmall,
                    overflow = TextOverflow.Ellipsis // Show ellipsis if text is too long
                )
            }
        }

        endIcon?.let {
            AppIcon(
                icon = endIcon,
                style = IconStyle(tint = if (enable) colors.endIconColor.contentColor else colors.endIconColor.disabledContentColor)
            )
        }
        Spacer(modifier = Modifier.size(8.dp))
    }
}

@Preview
@Composable
private fun ItemVerticalTextIconPreview() {
    ItemVerticalTextIcon(
        title = "Title",
        subtitle = "Subtitle",
        startIcon = com.pmb.ballon.R.drawable.ic_add,
        endIcon = com.pmb.ballon.R.drawable.ic_arrow_left,
    )
}