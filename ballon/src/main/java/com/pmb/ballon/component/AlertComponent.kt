package com.pmb.ballon.component

import androidx.compose.runtime.Composable
import com.pmb.core.platform.AlertModelState

@Composable
fun AlertComponent(alertState: AlertModelState) {
    when (alertState) {
        is AlertModelState.Dialog ->
            MessageDialog(
                title = alertState.title,
                message = alertState.description,
                onConfirm = alertState.onPositiveClick,
                onDismiss = alertState.onNegativeClick
            )

        is AlertModelState.SnackBar ->
            SnackBar(
                message = alertState.message,
                actionLabel = alertState.buttonTitle,
                onDismissed = alertState.onDismissed,
                onActionPerformed = alertState.onActionPerformed
            )
    }
}