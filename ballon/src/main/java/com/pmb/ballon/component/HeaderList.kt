package com.pmb.ballon.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.pmb.ballon.component.base.AppTextButton
import com.pmb.ballon.component.base.Headline6Text
import com.pmb.ballon.models.AppButton

@Composable
fun HeaderList(title: String, buttonText: String, onClick: () -> Unit) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Headline6Text(
            modifier = Modifier.padding(16.dp),
            text = title
        )
        AppTextButton(
            modifier = Modifier.padding(4.dp),
            title = buttonText,
            colors = AppButton.textButtonBlueColors(),
            onClick = onClick
        )
    }
}


@Composable
fun Header(title: String) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Headline6Text(
            modifier = Modifier.padding(16.dp),
            text = title
        )
    }
}