package com.pmb.ballon.component

import androidx.annotation.DrawableRes
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.pmb.ballon.R
import com.pmb.ballon.component.base.AppImage
import com.pmb.ballon.component.base.BaseAppText
import com.pmb.ballon.component.base.IconType
import com.pmb.ballon.models.ImageStyle
import com.pmb.ballon.models.Size
import com.pmb.ballon.models.TextStyle
import com.pmb.ballon.ui.theme.AppTheme


@Composable
fun TextImage(
    modifier: Modifier = Modifier,
    image: IconType,
    text: String,
    spacer: Dp = 24.dp,
    imageStyle: ImageStyle = ImageStyle(size = Size.FIX(all = 128.dp)),
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
        AppImage(image = image, style = imageStyle)
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
fun TextImage(
    modifier: Modifier = Modifier,
    @DrawableRes image: Int,
    text: String,
    spacer: Dp = 24.dp,
    imageStyle: ImageStyle = ImageStyle(size = Size.FIX(all = 128.dp)),
    textStyle: TextStyle = TextStyle(
        color = AppTheme.colorScheme.onBackgroundNeutralDefault,
        typography = AppTheme.typography.headline4,
        textAlign = TextAlign.Center
    )
) {
    TextImage(
        modifier = modifier,
        image = IconType.Painter(painterResource(id = image)),
        text = text,
        spacer = spacer,
        imageStyle = imageStyle,
        textStyle = textStyle
    )
}


@Preview
@Composable
private fun TextImagePreview() {
    TextImage(
        image = R.drawable.img_key,
        text = "جهت ثبت رمز ورود جدید، باید از طریق یکی روش\u200Cهای زیر اقدام به احراز هویت نمایید."
    )
}