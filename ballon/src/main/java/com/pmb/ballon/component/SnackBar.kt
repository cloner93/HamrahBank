package com.pmb.ballon.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.SnackbarResult
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp


@Composable
fun SnackBar(
    message: String,
    actionLabel: String? = null,
    onDismissed: () -> Unit,
    onActionPerformed: () -> Unit
) {
    val snackbarHostState = remember { SnackbarHostState() }

    Scaffold(snackbarHost = { SnackbarHost(snackbarHostState) }) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            LaunchedEffect(true) {
                val result = snackbarHostState.showSnackbar(
                    message = message, actionLabel = actionLabel
                )
                when (result) {
                    SnackbarResult.Dismissed -> onDismissed.invoke()
                    SnackbarResult.ActionPerformed -> onActionPerformed.invoke()
                }
            }
        }
    }
}
