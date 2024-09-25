package com.pmb.ballon.component

import androidx.annotation.DrawableRes
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.pmb.ballon.component.base.AppImage
import com.pmb.ballon.component.base.AppText
import com.pmb.ballon.models.TextStyle

@Composable
fun TextRow(title: String, @DrawableRes icon: Int) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        AppImage(modifier = Modifier.size(18.dp), image = icon)
        Spacer(modifier = Modifier.width(8.dp))
        AppText(
            modifier = Modifier.padding(vertical = 4.dp), title = title, style = TextStyle(
                color = Color.Unspecified,
                typography = MaterialTheme.typography.bodyMedium
            )
        )
    }
}

@Preview
@Composable
fun TextRowPreview() {
    TextRow(title = "Title", icon = com.pmb.ballon.R.drawable.ic_key)
}