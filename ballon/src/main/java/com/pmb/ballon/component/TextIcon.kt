package com.pmb.ballon.component

import androidx.annotation.DrawableRes
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.pmb.ballon.component.base.AppIcon
import com.pmb.ballon.component.base.AppImage
import com.pmb.ballon.component.base.BaseAppText
import com.pmb.ballon.component.base.BodyMediumText
import com.pmb.ballon.component.base.Headline6Text
import com.pmb.ballon.component.base.IconType
import com.pmb.ballon.models.IconStyle
import com.pmb.ballon.models.TextStyle
import com.pmb.ballon.ui.theme.AppTheme

@Composable
fun TextIcon(
    title: String,
    @DrawableRes icon: Int,
    color: Color = AppTheme.colorScheme.onBackgroundNeutralSubdued
) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        AppImage(modifier = Modifier.size(18.dp), image = icon)
        Spacer(modifier = Modifier.width(8.dp))
        BodyMediumText(
            modifier = Modifier.padding(vertical = 4.dp),
            text = title,
            color = color
        )
    }
}

@Composable
fun TextIconVertical(
    modifier: Modifier = Modifier,
    icon: IconType,
    text: String,
    spacer: Dp = 8.dp,
    iconStyle: IconStyle = IconStyle(
        tint = AppTheme.colorScheme.onBackgroundNeutralDefault,
    ),
    textStyle: TextStyle = TextStyle(
        color = AppTheme.colorScheme.onBackgroundNeutralDefault,
        typography = AppTheme.typography.headline4,
        textAlign = TextAlign.Center
    )
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        AppIcon(icon = icon, style = iconStyle)
        Spacer(modifier = Modifier.size(spacer))
        BaseAppText(
            modifier = Modifier
                .wrapContentWidth()
                .align(Alignment.CenterHorizontally),
            title = text,
            style = textStyle
        )
    }
}


@Composable
fun HeadlineTextIcon(
    title: String,
    iconSize: Dp = 18.dp,
    @DrawableRes icon: Int,
    color: Color = AppTheme.colorScheme.onBackgroundNeutralSubdued
) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        AppImage(modifier = Modifier.size(iconSize), image = icon)
        Spacer(modifier = Modifier.width(8.dp))
        Headline6Text(
            modifier = Modifier.padding(vertical = 4.dp),
            text = title,
            color = color
        )
    }
}

@Preview
@Composable
fun TextRowPreview() {
    TextIcon(title = "Title", icon = com.pmb.ballon.R.drawable.ic_key)
}