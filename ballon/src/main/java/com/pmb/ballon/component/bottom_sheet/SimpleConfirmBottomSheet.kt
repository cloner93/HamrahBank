package com.pmb.ballon.component.bottom_sheet

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.BottomSheetDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.pmb.ballon.component.base.AppBottomSheet
import com.pmb.ballon.component.base.AppButton
import com.pmb.ballon.component.base.AppOutlineButton
import com.pmb.ballon.component.base.Headline5Text
import com.pmb.ballon.ui.theme.AppTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SimpleConfirmBottomSheet(
    message: String,
    rejectButton: String,
    confirmButton: String,
    onDismiss: () -> Unit,
    onConfirm: () -> Unit
) {
    var isVisible by remember { mutableStateOf(true) }
    AppBottomSheet(
        isVisible = isVisible,
        onDismiss = { onDismiss() },
        dragHandle = { BottomSheetDefaults.DragHandle() },
        content = {
            Column(
                modifier = Modifier.padding(20.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Headline5Text(
                    modifier = Modifier.fillMaxWidth(),
                    text = message,
                    color = AppTheme.colorScheme.onBackgroundNeutralDefault,
                    textAlign = TextAlign.Center
                )
                Spacer(modifier = Modifier.height(24.dp))

                Row {
                    AppButton(
                        modifier = Modifier
                            .fillMaxWidth()
                            .weight(1f),
                        title = confirmButton,
                        colors = com.pmb.ballon.models.AppButton.buttonRedColors(),
                        onClick = {
                            isVisible = false
                            onConfirm()
                        })
                    Spacer(modifier = Modifier.width(16.dp))
                    AppOutlineButton(
                        modifier = Modifier
                            .fillMaxWidth()
                            .weight(1f),
                        title = rejectButton,
                        onClick = {
                            isVisible = false
                        })
                }
            }
        })
}
