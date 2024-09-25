package com.pmb.ballon.component.base

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier


data class TopBar(val title: String, val onBack: (() -> Unit)? = null)

@Composable
fun AppContent(
    modifier: Modifier = Modifier,
    topBar: TopBar? = null,
    footer: (@Composable (ColumnScope.() -> Unit))? = null,
    content: @Composable (ColumnScope.() -> Unit)
) {
    val wrapperModifier = footer?.let { Modifier.imePadding() } ?: Modifier
    Column(
        modifier = wrapperModifier.fillMaxSize()
    ) {
        topBar?.let {
            AppTopBar(
                title = it.title,
                onBack = it.onBack
            )

        }

        Column(
            modifier = modifier
                .weight(1f)
                .fillMaxWidth()
                .verticalScroll(rememberScrollState())
        ) {
            content()
        }

        footer?.invoke(this)
    }
}