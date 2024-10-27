package com.pmb.ballon.component

import androidx.annotation.DrawableRes
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.pmb.ballon.R
import com.pmb.ballon.component.base.AppImage
import com.pmb.ballon.component.base.Headline4Text
import com.pmb.ballon.models.ImageStyle
import com.pmb.ballon.models.Size
import com.pmb.ballon.ui.theme.AppTheme


@Composable
fun TextImage(
    modifier: Modifier = Modifier,
    @DrawableRes image: Int,
    text: String,
    imageStyle: ImageStyle = ImageStyle(size = Size.FIX(all = 128.dp))
) {
    Column(modifier = modifier, horizontalAlignment = Alignment.CenterHorizontally) {
        AppImage(image = image, style = imageStyle)
        Spacer(modifier = Modifier.size(24.dp))
        Headline4Text(
            modifier = Modifier
                .wrapContentWidth()
                .align(Alignment.CenterHorizontally),
            text = text,
            color = AppTheme.colorScheme.onBackgroundNeutralDefault,
            textAlign = TextAlign.Center
        )
    }
}


@Preview
@Composable
private fun TextImagePreview() {
    TextImage(
        image = R.drawable.img_key,
        text = "جهت ثبت رمز عبور جدید، باید از طریق یکی روش\u200Cهای زیر اقدام به احراز هویت نمایید."
    )
}