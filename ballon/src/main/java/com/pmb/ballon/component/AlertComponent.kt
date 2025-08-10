package com.pmb.ballon.component

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import com.pmb.ballon.component.bottom_sheet.SimpleConfirmBottomSheet
import com.pmb.core.platform.AlertModelState

@Composable
fun AlertComponent(alertState: AlertModelState) {
    var showConfirmDeleteBottomSheet by remember { mutableStateOf(false) }
    when (alertState) {
        is AlertModelState.Dialog -> MessageDialog(
            title = alertState.title,
            message = alertState.description,
            onConfirm = alertState.onPositiveClick,
            onDismiss = alertState.onNegativeClick
        )

        is AlertModelState.SnackBar -> /*SnackBar(
            message = alertState.message,
            actionLabel = alertState.buttonTitle,
            onDismissed = alertState.onDismissed,
            onActionPerformed = alertState.onActionPerformed
        )*/ Unit

        is AlertModelState.BottomSheet ->
            showConfirmDeleteBottomSheet = true
    }

    if (showConfirmDeleteBottomSheet && alertState is AlertModelState.BottomSheet)
        SimpleConfirmBottomSheet(
            message = alertState.message,
            cancelable = alertState.cancelable,
            confirmButton = alertState.positiveButtonTitle,
            rejectButton = alertState.negativeButtonTitle,
            onConfirm = {
                showConfirmDeleteBottomSheet = false
                alertState.onPositiveClick?.invoke()
            },
            onDismiss = {
                showConfirmDeleteBottomSheet = false
                alertState.onNegativeClick?.invoke()
            },
        )
}