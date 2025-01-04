package com.pmb.ballon.component.base

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier


@Composable
fun AppContent(
    modifier: Modifier = Modifier,
    topBar: (@Composable (ColumnScope.() -> Unit))? = null,
    verticalArrangement: Arrangement.Vertical = Arrangement.Top,
    horizontalAlignment: Alignment.Horizontal = Alignment.Start,
    footer: (@Composable (ColumnScope.() -> Unit))? = null,
    content: @Composable (ColumnScope.() -> Unit)
) {
    val wrapperModifier = footer?.let { Modifier.imePadding() } ?: Modifier
    Column(
        modifier = wrapperModifier.fillMaxSize()
    ) {
        topBar?.invoke(this)
        Column(
            modifier = modifier
                .weight(1f)
                .fillMaxWidth()
                .verticalScroll(rememberScrollState()),
            verticalArrangement = verticalArrangement,
            horizontalAlignment = horizontalAlignment
        ) {
            content()
        }

        footer?.invoke(this)
    }
}