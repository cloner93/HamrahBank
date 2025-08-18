package com.pmb.ballon.component.base

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import com.pmb.ballon.ui.theme.AppTheme

@Composable
fun AppLoading() {
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0x22000000))
            .pointerInput(Unit) { }
    ) {
        CircularProgressIndicator(
            color = AppTheme.colorScheme.onBackgroundPrimaryCTA
        )
    }
}