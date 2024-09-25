package com.pmb.ballon.component.base

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.pmb.ballon.models.TextStyle

@Composable
fun AppTopBar(title: String, onBack: (() -> Unit)? = null) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(64.dp),
    ) {

        onBack?.let {
            AppButtonIcon(
                modifier = Modifier.align(Alignment.CenterStart),
                icon = Icons.Filled.ArrowForward,
                onClick = it
            )
        }

        AppText(
            modifier = Modifier.align(Alignment.Center),
            title = title,
            style = TextStyle(
                color = MaterialTheme.colorScheme.primary,
                typography = MaterialTheme.typography.titleLarge
            )
        )
    }
}

@Preview
@Composable
private fun AppTopBarPreview() {
    AppTopBar("test toolbar") { }
}