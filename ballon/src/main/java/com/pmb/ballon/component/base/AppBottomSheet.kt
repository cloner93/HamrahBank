package com.pmb.ballon.component.base

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.BottomSheetScaffold
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.material3.rememberBottomSheetScaffoldState
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BottomSheetScaffoldExample() {
    val sheetState = rememberBottomSheetScaffoldState()
    val coroutineScope = rememberCoroutineScope()


    BottomSheetScaffold(
        scaffoldState = sheetState,
        sheetContent = {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            ) {
                Text(
                    text = "This is a persistent bottom sheet",
                    style = MaterialTheme.typography.bodyLarge
                )
                Spacer(modifier = Modifier.height(16.dp))
                Button(onClick = {
                    coroutineScope.launch {
                        sheetState.bottomSheetState.partialExpand() // Collapse the bottom sheet
                    }
                }) {
                    Text("Collapse Sheet")
                }
            }
        },
        sheetPeekHeight = 64.dp // The height the sheet is initially displayed at
    ) {
        // Main content of your screen
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text("Swipe up to see the sheet")
            Spacer(modifier = Modifier.height(16.dp))
            Button(onClick = {
                coroutineScope.launch {
                    sheetState.bottomSheetState.expand() // Expand the bottom sheet
                }
            }) {
                Text("Expand Bottom Sheet")
            }
        }
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppBottomSheet(show: Boolean = false, onDismiss: () -> Unit) {
    val sheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true,
        confirmValueChange = { false })
    val scope = rememberCoroutineScope()

    LaunchedEffect(show) {
        if (show) {
            sheetState.show() // Show the bottom sheet when `show` is true
        } else {
            sheetState.hide() // Hide the bottom sheet when `show` is false
        }
    }

    if (show) {
        ModalBottomSheet(
            onDismissRequest = {
                scope.launch {
                    sheetState.hide() // Hide the sheet on dismissal request
                    onDismiss() // Trigger onDismiss after hiding
                }
            },
            sheetState = sheetState
        ) {
            // Sheet content
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            ) {
                Text(text = "This is a modal bottom sheet")

                Spacer(modifier = Modifier.height(16.dp))

                Button(onClick = {
                    scope.launch {
                        sheetState.hide() // Hide the bottom sheet
                        onDismiss() // Trigger the dismiss callback after hiding
                    }
                }) {
                    Text("Hide Bottom Sheet")
                }
            }
        }
    }
}