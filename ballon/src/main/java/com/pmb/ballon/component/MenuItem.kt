package com.pmb.ballon.component

import androidx.annotation.DrawableRes
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.HorizontalDivider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import com.pmb.ballon.R
import com.pmb.ballon.component.annotation.AppPreview
import com.pmb.ballon.component.base.AppIcon
import com.pmb.ballon.component.base.BaseAppText
import com.pmb.ballon.component.base.BodySmallText
import com.pmb.ballon.models.IconStyle
import com.pmb.ballon.models.TextStyle
import com.pmb.ballon.ui.theme.AppTheme
import com.pmb.ballon.ui.theme.HamrahBankTheme


object MenuItemDefaults {
    val innerPadding: MenuItemPadding = MenuItemPadding.vertical(vertical = 14.dp)
    val startIconPadding: MenuItemPadding = MenuItemPadding.horizontal(16.dp)
    val endIconPadding: MenuItemPadding = MenuItemPadding(end = 12.dp)
    val horizontalDividerPadding: MenuItemPadding = MenuItemPadding.horizontal(horizontal = 4.dp)
}

data class MenuItemPadding(
    val start: Dp = 0.dp,
    val top: Dp = 0.dp,
    val end: Dp = 0.dp,
    val bottom: Dp = 0.dp
) {
    fun toPaddingValues(): PaddingValues {
        return PaddingValues(
            start = start,
            top = top,
            end = end,
            bottom = bottom
        )
    }

    fun copy(vertical: Dp? = null, horizontal: Dp? = null): MenuItemPadding =
        MenuItemPadding(
            start = horizontal ?: start,
            top = vertical ?: top,
            end = horizontal ?: end,
            bottom = vertical ?: bottom
        )

    fun copy(
        start: Dp? = null,
        top: Dp? = null,
        end: Dp? = null,
        bottom: Dp? = null,
    ): MenuItemPadding =
        MenuItemPadding(
            start = start ?: this.start,
            top = top ?: this.top,
            end = end ?: this.end,
            bottom = bottom ?: this.bottom
        )

    companion object {
        // برای اعمال padding افقی
        fun horizontal(horizontal: Dp) = MenuItemPadding(start = horizontal, end = horizontal)

        // برای اعمال padding عمودی
        fun vertical(vertical: Dp) = MenuItemPadding(top = vertical, bottom = vertical)

        // تابع برای اعمال padding از هر چهار جهت
        fun all(horizontal: Dp, vertical: Dp) =
            MenuItemPadding(start = horizontal, end = horizontal, top = vertical, bottom = vertical)
    }
}

@Composable
fun MenuItem(
    modifier: Modifier = Modifier,
    title: String,
    subtitle: String? = null,
    horizontalDividerPadding: MenuItemPadding = MenuItemDefaults.horizontalDividerPadding,
    @DrawableRes startIcon: Int? = null,
    @DrawableRes endIcon: Int? = null,
    bottomDivider: Boolean = false,
    titleStyle: TextStyle = TextStyle(
        color = AppTheme.colorScheme.foregroundNeutralDefault,
        typography = AppTheme.typography.bodyLarge,
    ),
    subtitleStyle: TextStyle? = null,
    titleLayoutDirection: LayoutDirection = LayoutDirection.Rtl,
    subTitleLayoutDirection: LayoutDirection = LayoutDirection.Rtl,
    startIconStyle: IconStyle? = null,
    endIconStyle: IconStyle = IconStyle(tint = AppTheme.colorScheme.onForegroundNeutralDisabled),
    innerPadding: MenuItemPadding = MenuItemDefaults.innerPadding,
    startIconPadding: MenuItemPadding = MenuItemDefaults.startIconPadding,
    endIconPadding: MenuItemPadding = MenuItemDefaults.endIconPadding,
    endContent: @Composable (() -> Unit)? = null,
    clickable: Boolean = true,
    onItemClick: (() -> Unit)? = null
) {
    val _modifier = if (clickable) modifier.clickable { onItemClick?.invoke() } else modifier
    Column(
        modifier = _modifier,
        verticalArrangement = Arrangement.Center
    ) {
        Row(
            modifier = Modifier
                .clickable(enabled = onItemClick != null) {
                    onItemClick?.invoke()
                }
                .padding(innerPadding.toPaddingValues()),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            startIcon?.let {
                AppIcon(
                    modifier = modifier.padding(startIconPadding.toPaddingValues()),
                    icon = it,
                    style = startIconStyle
                )
            }

            Column(horizontalAlignment = Alignment.Start) {
                CompositionLocalProvider(LocalLayoutDirection provides titleLayoutDirection) {
                    BaseAppText(title = title, style = titleStyle)
                }

                subtitle?.let {
                    CompositionLocalProvider(LocalLayoutDirection provides subTitleLayoutDirection) {
                        BodySmallText(
                            text = it,
                            color = subtitleStyle?.color
                                ?: AppTheme.colorScheme.onForegroundNeutralDisabled
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.weight(1f))
            endContent?.invoke()
            endIcon?.let {
                AppIcon(
                    modifier = Modifier.padding(endIconPadding.toPaddingValues()),
                    icon = endIcon,
                    style = endIconStyle
                )
            }

        }
        if (bottomDivider) {
            HorizontalDivider(
                modifier = Modifier.padding(horizontalDividerPadding.toPaddingValues()),
                color = AppTheme.colorScheme.strokeNeutral3Devider
            )
        }
    }
}

@AppPreview
@Composable
private fun MenuItemTitlePreview() {
    HamrahBankTheme {
        MenuItem(
            title = "تست منو",
            startIcon = R.drawable.ic_pin,
            endIcon = R.drawable.ic_arrow_left,
            startIconStyle = IconStyle(tint = AppTheme.colorScheme.onBackgroundNeutralCTA),
            endIconStyle = IconStyle(tint = AppTheme.colorScheme.foregroundNeutralRest),
            clickable = true,
            onItemClick = { }
        )
    }

}