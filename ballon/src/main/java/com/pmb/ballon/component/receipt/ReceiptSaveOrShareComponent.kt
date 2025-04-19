package com.pmb.ballon.component.receipt

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.pmb.ballon.R
import com.pmb.ballon.component.TextImage
import com.pmb.ballon.models.ImageStyle
import com.pmb.ballon.models.Size
import com.pmb.ballon.models.TextStyle
import com.pmb.ballon.ui.theme.AppTheme

@Composable
fun ReceiptSaveOrShareComponent(shareReceipt: () -> Unit, downloadReceipt: () -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        TextImage(
            modifier = Modifier
                .aspectRatio(156f / 76f)
                .clip(shape = RoundedCornerShape(16.dp))
                .weight(1f)
                .background(color = AppTheme.colorScheme.backgroundTintNeutralDefault)
                .clickable { shareReceipt.invoke() },
            spacer = 8.dp,
            imageStyle = ImageStyle(size = Size.FIX(24.dp)),
            image = R.drawable.ic_send,
            text = stringResource(R.string.share),
            textStyle = TextStyle(
                color = AppTheme.colorScheme.onBackgroundTintNeutralDefault,
                typography = AppTheme.typography.buttonSmall,
                textAlign = TextAlign.Center
            )
        )
        Spacer(modifier = Modifier.size(16.dp))
        TextImage(
            modifier = Modifier
                .aspectRatio(156f / 76f)
                .clip(shape = RoundedCornerShape(16.dp))
                .weight(1f)
                .background(color = AppTheme.colorScheme.backgroundTintNeutralDefault)
                .clickable { downloadReceipt.invoke() },
            spacer = 8.dp,
            imageStyle = ImageStyle(size = Size.FIX(24.dp)),
            image = R.drawable.ic_download,
            text = stringResource(R.string.save_to_gallery),
            textStyle = TextStyle(
                color = AppTheme.colorScheme.onBackgroundTintNeutralDefault,
                typography = AppTheme.typography.buttonSmall,
                textAlign = TextAlign.Center
            )
        )
    }
}