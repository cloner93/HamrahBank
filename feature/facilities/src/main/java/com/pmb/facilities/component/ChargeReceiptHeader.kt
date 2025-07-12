package com.pmb.facilities.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.pmb.ballon.component.base.AppImage
import com.pmb.ballon.component.base.BodyMediumText
import com.pmb.ballon.component.base.CaptionText
import com.pmb.ballon.models.ImageStyle
import com.pmb.ballon.models.Size
import com.pmb.ballon.ui.theme.AppTheme


@Composable
fun ChargeReceiptHeader(
    image: Int,
    headerTitle: String,
    headerSubTitle: String,
) {
    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.size(8.dp))
        AppImage(
            image = painterResource(image),
            style = ImageStyle(Size.FIX(48.dp))
        )
        BodyMediumText(
            text = headerTitle,
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.Center,
            color = AppTheme.colorScheme.onBackgroundNeutralDefault
        )
        CaptionText(
            text = headerSubTitle,
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.Center,
            color = AppTheme.colorScheme.onBackgroundNeutralSubdued
        )
    }
}