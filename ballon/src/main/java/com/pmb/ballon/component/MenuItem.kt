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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import com.pmb.ballon.R
import com.pmb.ballon.component.base.AppIcon
import com.pmb.ballon.component.base.AppImage
import com.pmb.ballon.component.base.BaseAppText
import com.pmb.ballon.component.base.BodySmallText
import com.pmb.ballon.models.IconStyle
import com.pmb.ballon.models.ImageStyle
import com.pmb.ballon.models.TextStyle
import com.pmb.ballon.ui.theme.AppTheme


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
            }

        }
        if (bottomDivider) HorizontalDivider(color = AppTheme.colorScheme.background3Neutral)
    }
}

@Composable
fun ImageItemRow(
    modifier: Modifier = Modifier,
    title: String,
    subtitle: String? = null,
    horizontalPadding: Dp = 4.dp,
    @DrawableRes startImage: Int? = null,
    bottomDivider: Boolean = false,
    titleStyle: TextStyle? = TextStyle(
        color = AppTheme.colorScheme.onBackgroundNeutralDefault,
        typography = AppTheme.typography.bodyMedium,
    ),
    subtitleStyle: TextStyle? = TextStyle(
        typography = AppTheme.typography.caption,
        color = AppTheme.colorScheme.onBackgroundNeutralSubdued
    ),
    titleLayoutDirection: LayoutDirection = LayoutDirection.Rtl,
    textWeight : Float =1f,
    subTitleLayoutDirection: LayoutDirection = LayoutDirection.Rtl,
    startImageStyle: ImageStyle = ImageStyle(),
    endContent: @Composable () -> Unit = {},
    clickable: Boolean = true,
    onItemClick: (() -> Unit)? = null
) {
    Column(
        modifier = modifier,
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

            startImage?.let {
                AppImage(image = it, style = startImageStyle)
                Spacer(modifier = Modifier.size(16.dp))
            }

            Column(modifier = Modifier.weight(textWeight),horizontalAlignment = Alignment.Start) {
                CompositionLocalProvider(LocalLayoutDirection provides titleLayoutDirection) {
                    BaseAppText(title = title, style = titleStyle)
                }
                Spacer(modifier = Modifier.size(4.dp))
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


            endContent?.let {
                endContent.invoke()
            }

        }
        if (bottomDivider) HorizontalDivider()
    }
}

@Preview
@Composable
private fun MenuItemTitlePreview() {
    Column {
        MenuItem(
            title = "my Services",
            startIcon = R.drawable.ic_shopping_bag_star,
            endIcon = R.drawable.ic_arrow_left,
            bottomDivider = true,
            clickable = false,
            onItemClick = {

            })

        MenuItem(
            title = "my Services",
            subtitle = "my service subtitle...new view genarate",
            startIcon = R.drawable.ic_shopping_bag_star,
            endIcon = R.drawable.ic_arrow_left,
            bottomDivider = true,
            subtitleStyle = TextStyle(color = Color.Blue),
            clickable = false,
            onItemClick = {

            })
    }

}