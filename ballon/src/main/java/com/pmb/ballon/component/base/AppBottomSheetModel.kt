package com.pmb.ballon.component.base

import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.SheetState
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import kotlinx.coroutines.launch


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppBottomSheet(
    sheetState: SheetState,
    onDismiss: () -> Unit,
    dragHandle: @Composable () -> Unit = {},
    content: @Composable ColumnScope.() -> Unit
) {
    val scope = rememberCoroutineScope()
    ModalBottomSheet(
        onDismissRequest = {
            scope.launch {
                sheetState.hide() // Hide the sheet on dismissal request
                onDismiss() // Trigger onDismiss after hiding
            }
        },
        sheetState = sheetState,
        dragHandle = dragHandle,
        content = content
    )

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppBottomSheet(
    isVisible: Boolean,
    onDismiss: () -> Unit,
    dragHandle: @Composable () -> Unit = {},
    content: @Composable ColumnScope.() -> Unit
) {
    val sheetState =
        rememberModalBottomSheetState(skipPartiallyExpanded = true, confirmValueChange = { false })
    val scope = rememberCoroutineScope()

    LaunchedEffect(isVisible) {
        if (isVisible) {
            sheetState.show() // Show the bottom sheet
        } else {
            sheetState.hide() // Hide the bottom sheet
            onDismiss()
        }
    }

    ModalBottomSheet(
        onDismissRequest = {
            scope.launch {
                sheetState.hide() // Hide the sheet on dismissal request
                onDismiss() // Trigger onDismiss after hiding
            }
        },
        sheetState = sheetState,
        dragHandle = dragHandle,
        content = content
    )
}