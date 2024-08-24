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
import com.pmb.ballon.component.base.AppText
import com.pmb.ballon.models.IconStyle
import com.pmb.ballon.models.TextStyle

@Composable
fun MenuItem(
    title: String,
    subtitle: String? = null,
    @DrawableRes startIcon: Int,
    @DrawableRes endIcon: Int? = null,
    bottomDivider: Boolean = false,
    titleStyle: TextStyle? = null,
    subtitleStyle: TextStyle? = null,
    startIconStyle: IconStyle? = null,
    endIconStyle: IconStyle? = null,
    onItemClick: (() -> Unit)? = null
) {
    Column(modifier = Modifier.clickable(enabled = onItemClick != null) {
        onItemClick?.invoke()
    }) {
        Row(
            modifier = Modifier.padding(horizontal = 12.dp, vertical = 14.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {

            endIcon?.let {
                AppIcon(icon = endIcon, style = endIconStyle)
            }

            Spacer(modifier = Modifier.weight(1f))

            Column(horizontalAlignment = Alignment.Start) {
                AppText(title = title, textStyle = titleStyle)
                subtitle?.let { AppText(title = it, textStyle = subtitleStyle) }
            }

            Spacer(modifier = Modifier.size(16.dp))

            AppIcon(icon = startIcon, style = startIconStyle)
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