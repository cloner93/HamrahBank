package com.pmb.ballon.component.base

import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.SheetState
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.nestedscroll.NestedScrollConnection
import androidx.compose.ui.input.nestedscroll.nestedScroll
import com.pmb.ballon.ui.theme.AppTheme
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
    cancelable: Boolean = false,
    onDismiss: () -> Unit,
    containerColor: Color = AppTheme.colorScheme.background1Neutral,
    dragHandle: @Composable () -> Unit = {},
    content: @Composable ColumnScope.(NestedScrollConnection) -> Unit
) {
    val sheetState =
        if (cancelable) rememberModalBottomSheetState(skipPartiallyExpanded = true)
        else rememberModalBottomSheetState(
            skipPartiallyExpanded = true,
            confirmValueChange = { false })
    val scope = rememberCoroutineScope()

    // Nested Scroll Connection
    val nestedScrollConnection = remember { object : NestedScrollConnection {} }

    LaunchedEffect(isVisible) {
        if (isVisible) {
            sheetState.show() // Show the bottom sheet
        } else {
            if (sheetState.isVisible)
                sheetState.hide() // Hide the bottom sheet
            onDismiss()
        }
    }

    ModalBottomSheet(
        modifier = Modifier.nestedScroll(nestedScrollConnection), // اتصال Scroll
        onDismissRequest = {
            scope.launch {
                if (sheetState.isVisible)
                    sheetState.hide() // Hide the sheet on dismissal request
                onDismiss() // Trigger onDismiss after hiding
            }
        },
        containerColor = containerColor,
        sheetState = sheetState,
        dragHandle = dragHandle,
        content = { content(nestedScrollConnection) }
    )
}