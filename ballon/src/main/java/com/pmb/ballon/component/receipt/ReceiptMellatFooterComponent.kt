package com.pmb.ballon.component.receipt

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.pmb.ballon.component.base.AppImage
import com.pmb.ballon.models.ImageStyle
import com.pmb.ballon.models.Size

@Composable
fun ReceiptFooterComponent() {
    Spacer(modifier = Modifier.size(16.dp))
    Row(verticalAlignment = Alignment.CenterVertically) {
        AppImage(
            image = painterResource(com.pmb.ballon.R.drawable.img_mellat_logo),
            style = ImageStyle(size = Size.Rectangle(width = 36.dp, height = 42.dp))
        )
        Spacer(modifier = Modifier.size(8.dp))
        AppImage(image = painterResource(com.pmb.ballon.R.drawable.ic_mellat_bank))
    }
    Spacer(modifier = Modifier.size(22.dp))
}