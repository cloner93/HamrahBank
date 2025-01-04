package com.pmb.home.presentation.home

import androidx.compose.runtime.Composable
import com.pmb.ballon.component.base.AppContent
import com.pmb.ballon.component.base.BodyLargeText
import com.pmb.core.presentation.NavigationManager

@Composable
fun HomeScreen(navigationManager: NavigationManager) {
    AppContent {
        BodyLargeText(text = "Home Screen")
    }
}