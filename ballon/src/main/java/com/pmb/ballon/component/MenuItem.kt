package com.pmb.ballon.component

import androidx.annotation.DrawableRes
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
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

@Composable
fun MenuItem(
    modifier: Modifier = Modifier,
    title: String,
    subtitle: String? = null,
    horizontalPadding: Dp = 4.dp,
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
                .padding(horizontal = horizontalPadding, vertical = 14.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {

            startIcon?.let {
                Spacer(modifier = Modifier.size(16.dp))
                AppIcon(icon = it, style = startIconStyle)
                Spacer(modifier = Modifier.size(16.dp))
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
                AppIcon(icon = endIcon, style = endIconStyle)
                Spacer(modifier = Modifier.size(8.dp))
            }

        }
        if (bottomDivider) {
            HorizontalDivider(
                modifier = Modifier.padding(horizontal = 8.dp),
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