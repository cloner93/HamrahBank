package com.pmb.ballon.component

import androidx.compose.material3.AlertDialog
import androidx.compose.runtime.Composable
import com.pmb.ballon.component.base.AppButton
import com.pmb.ballon.component.base.BodyLargeText
import com.pmb.ballon.component.base.BodyMediumText

@Composable
fun MessageDialog(
    title: String, message: String? = null, onConfirm: (() -> Unit)?, onDismiss: (() -> Unit)?
) {
    AlertDialog(onDismissRequest = {
        if (onDismiss != null) {
            onDismiss()
        }
    },
        title = { BodyLargeText(text = title) },
        text = { message?.let { BodyMediumText(text = it) } },
        confirmButton = {
            onConfirm?.let {
                AppButton(title = "OK", onClick = {
                        onConfirm()
                })
            }
        },
        dismissButton = {
            onDismiss?.let {
                AppButton(title = "Cancel", onClick = {
                        onDismiss()
                })
            }
        })
}
