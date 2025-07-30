package com.pmb.ballon.component

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.AlertDialog
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.pmb.ballon.component.base.AppButton
import com.pmb.ballon.component.base.AppTextButton
import com.pmb.ballon.component.base.BodyLargeText
import com.pmb.ballon.component.base.BodyMediumText
import com.pmb.ballon.ui.theme.AppTheme

@Composable
fun MessageDialog(
    title: String,
    message: String? = null,
    positiveButtonTitle: String = "تایید",
    negativeButtonTitle: String = "لغو",
    onConfirm: (() -> Unit)?,
    onDismiss: (() -> Unit)?
) {
    AlertDialog(
        containerColor = AppTheme.colorScheme.background1Neutral,
        onDismissRequest = {
            if (onDismiss != null) {
                onDismiss()
            }
        },
        title = {
            BodyLargeText(
                text = title,
                color = AppTheme.colorScheme.onBackgroundNeutralDefault
            )
        },
        text = {
            message?.let {
                BodyMediumText(
                    text = it,
                    color = AppTheme.colorScheme.onBackgroundNeutralDefault
                )
            }
        },
        confirmButton = {
            onConfirm?.let {
                AppButton(
                    modifier = Modifier,
                    title = positiveButtonTitle, onClick = {
                    onConfirm()
                })
            }
        },
        dismissButton = {
            onDismiss?.let {
                AppTextButton(
                    modifier = Modifier.padding(horizontal = 8.dp),
                    title = negativeButtonTitle,
                    onClick = {
                        onDismiss()
                    })
            }
        })

}
