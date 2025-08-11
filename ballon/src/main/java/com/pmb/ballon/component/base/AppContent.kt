package com.pmb.ballon.component.base

import android.annotation.SuppressLint
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.pmb.ballon.component.AlertComponent
import com.pmb.ballon.ui.theme.AppTheme
import com.pmb.core.platform.AlertModelState
import kotlinx.coroutines.launch

@Deprecated("Use AppContent() instead added alertState to handle alert")
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
            .background(backgroundColor)
            .then(if (footer != null) Modifier.padding(bottom = 16.dp) else Modifier),
    ) {
        topBar?.invoke(this)
        Column(
            modifier = modifier
                .weight(1f)
                .fillMaxWidth()
                .then(if (scrollState != null) Modifier.verticalScroll(scrollState) else Modifier),
            verticalArrangement = verticalArrangement,
            horizontalAlignment = horizontalAlignment
        ) {
            content()
        }

        footer?.invoke(this)
    }
}

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun AppContent(
    modifier: Modifier = Modifier,
    backgroundColor: Color = AppTheme.colorScheme.background1Neutral,
    topBar: (@Composable (ColumnScope.() -> Unit))? = null,
    verticalArrangement: Arrangement.Vertical = Arrangement.Top,
    horizontalAlignment: Alignment.Horizontal = Alignment.Start,
    scrollState: ScrollState? = rememberScrollState(), // if your screen has scrollable item like lazyColumn, pass it null, By default your screen is scrollable.
    footer: (@Composable (ColumnScope.() -> Unit))? = null,
    alertState: AlertModelState? = null,
    content: @Composable (ColumnScope.() -> Unit)
) {
    val wrapperModifier = footer?.let { Modifier.imePadding() } ?: Modifier
    val snackbarHostState = remember { SnackbarHostState() }
    val coroutineScope = rememberCoroutineScope()
    var _alertState by remember { mutableStateOf(alertState) }
    _alertState = alertState
    if (_alertState != null) {
        when (alertState) {
            is AlertModelState.BottomSheet -> Unit
            is AlertModelState.Dialog -> Unit
            is AlertModelState.SnackBar -> {
                coroutineScope.launch {
                    snackbarHostState.showSnackbar(
                        message = alertState.message,
                        actionLabel = alertState.buttonTitle,
                        withDismissAction = alertState.onActionPerformed != null,
                        duration = SnackbarDuration.Short
                    )
                }
                _alertState = null
            }

            null -> Unit
        }
    }

//    LaunchedEffect(Unit) {
//        alertState?.collect {
//            when (it) {
//                is AlertModelState.BottomSheet -> Unit
//                is AlertModelState.Dialog -> Unit
//                is AlertModelState.SnackBar ->
//                    coroutineScope.launch {
//                        snackbarHostState.showSnackbar(
//                            message = it.message,
//                            actionLabel = it.buttonTitle,
//                            withDismissAction = it.onActionPerformed != null,
//                            duration = SnackbarDuration.Short
//                        )
//                    }
//            }
//        }
//    }

    Scaffold(
        snackbarHost = {
            SnackbarHost(
                hostState = snackbarHostState,
                snackbar = { data ->
                    AppSnackBar(data)
                },
            )
        },
    ) { padding ->
        Column(
            modifier = wrapperModifier
                .padding(padding)
                .fillMaxSize()
                .background(backgroundColor)
                .then(if (footer != null) Modifier.padding(bottom = 16.dp) else Modifier),
        ) {
            topBar?.invoke(this)
            Column(
                modifier = modifier
                    .weight(1f)
                    .fillMaxWidth()
                    .then(if (scrollState != null) Modifier.verticalScroll(scrollState) else Modifier),
                verticalArrangement = verticalArrangement,
                horizontalAlignment = horizontalAlignment
            ) {
                content()
            }

            footer?.invoke(this)
        }
    }
    alertState?.let { AlertComponent(it) }
}