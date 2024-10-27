package com.pmb.ballon.component

import androidx.annotation.DrawableRes
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.HorizontalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.pmb.ballon.R
import com.pmb.ballon.component.base.AppIcon
import com.pmb.ballon.component.base.BodyMediumText
import com.pmb.ballon.component.base.BodySmallText
import com.pmb.ballon.models.IconStyle
import com.pmb.ballon.models.TextStyle
import com.pmb.ballon.ui.theme.AppTheme

@Composable
fun MenuItem(
    title: String,
    subtitle: String? = null,
    @DrawableRes startIcon: Int? = null,
    @DrawableRes endIcon: Int? = null,
    bottomDivider: Boolean = false,
    titleStyle: TextStyle = TextStyle(
        color = AppTheme.colorScheme.foregroundNeutralDefault,
        typography = AppTheme.typography.bodyLarge,
    ),
    subtitleStyle: TextStyle? = null,
    startIconStyle: IconStyle? = null,
    endIconStyle: IconStyle = IconStyle(tint = AppTheme.colorScheme.onForegroundNeutralDisabled),
    onItemClick: (() -> Unit)? = null
) {
    Column(modifier = Modifier.clickable(enabled = onItemClick != null) {
        onItemClick?.invoke()
    }) {
        Row(
            modifier = Modifier.padding(horizontal = 4.dp, vertical = 14.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {

            startIcon?.let {
                AppIcon(icon = it, style = startIconStyle)
                Spacer(modifier = Modifier.size(16.dp))
            }

            Column(horizontalAlignment = Alignment.Start) {
                BodyMediumText(text = title, color = titleStyle.color)
                subtitle?.let {
                    BodySmallText(
                        text = it,
                        color = subtitleStyle?.color
                            ?: AppTheme.colorScheme.onForegroundNeutralDisabled
                    )
                }
            }

            Spacer(modifier = Modifier.weight(1f))

            endIcon?.let {
                AppIcon(icon = endIcon, style = endIconStyle)
            }

        }
        if (bottomDivider) HorizontalDivider(thickness = 1.dp)
    }
}

@Preview
@Composable
private fun MenuItemTitlePreview() {
    Column {
        MenuItem(title = "my Services",
            startIcon = R.drawable.ic_shopping_bag_star,
            endIcon = R.drawable.ic_arrow_left,
            bottomDivider = true,
            onItemClick = {

            })

        MenuItem(title = "my Services",
            subtitle = "my service subtitle...new view genarate",
            subtitleStyle = TextStyle(color = Color.Blue),
            startIcon = R.drawable.ic_shopping_bag_star,
            endIcon = R.drawable.ic_arrow_left,
            bottomDivider = true,
            onItemClick = {

            })
    }

}