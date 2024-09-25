package com.pmb.ballon.component

import androidx.annotation.DrawableRes
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.pmb.ballon.R
import com.pmb.ballon.component.base.AppImage
import com.pmb.ballon.component.base.AppText
import com.pmb.ballon.models.ImageStyle
import com.pmb.ballon.models.Size
import com.pmb.ballon.models.TextStyle


@Composable
fun TextImage(
    modifier: Modifier = Modifier,
    @DrawableRes image: Int,
    text: String,
    imageStyle: ImageStyle = ImageStyle(size = Size.FIX(all = 128.dp)),
    textStyle: TextStyle = TextStyle(
        color = MaterialTheme.colorScheme.primary,
        typography = MaterialTheme.typography.titleLarge,
        textAlign = TextAlign.Center
    )
) {
    Column(modifier = modifier, horizontalAlignment = Alignment.CenterHorizontally) {
        AppImage(image = image, style = imageStyle)
        Spacer(modifier = Modifier.size(24.dp))
        AppText(
            modifier = Modifier
                .wrapContentWidth()
                .align(Alignment.CenterHorizontally),
            title = text,
            style = textStyle
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