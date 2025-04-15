package com.pmb.ballon.component.base

import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.background
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
import androidx.compose.ui.graphics.Color
import com.pmb.ballon.ui.theme.AppTheme


@Composable
fun AppContent(
    modifier: Modifier = Modifier,
    backgroundColor: Color = AppTheme.colorScheme.background1Neutral,
    topBar: (@Composable (ColumnScope.() -> Unit))? = null,
    verticalArrangement: Arrangement.Vertical = Arrangement.Top,
    horizontalAlignment: Alignment.Horizontal = Alignment.Start,
    scrollState: ScrollState? = rememberScrollState(), // if your screen has scrollable item like lazyColumn, pass it null, By default your screen is scrollable.
    footer: (@Composable (ColumnScope.() -> Unit))? = null,
    content: @Composable (ColumnScope.() -> Unit)
) {
    val wrapperModifier = footer?.let { Modifier.imePadding() } ?: Modifier
    Column(
        modifier = wrapperModifier
            .fillMaxSize()
            .background(backgroundColor),
    ) {
        topBar?.invoke(this)
        Column(
            modifier = modifier
                .weight(1f)
                .fillMaxWidth()
                .then(
                    if (scrollState != null)
                        Modifier.verticalScroll(scrollState)
                    else
                        Modifier
                ),
            verticalArrangement = verticalArrangement,
            horizontalAlignment = horizontalAlignment
        ) {
            content()
        }

        footer?.invoke(this)
    }
}