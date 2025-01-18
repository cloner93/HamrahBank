package com.pmb.transfer.presentation.components.transfer_confirm

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.BottomSheetDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.pmb.ballon.component.ItemCheckRow
import com.pmb.ballon.component.base.AppBottomSheet
import com.pmb.ballon.component.base.AppTopBar
import com.pmb.ballon.component.base.ClickableIcon
import com.pmb.ballon.component.base.IconType
import com.pmb.transfer.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CardListBottomSheet(
    items: List<String>, onItemSelected: (String) -> Unit, onDismiss: () -> Unit
) {
    var isVisible by remember { mutableStateOf(true) }
    AppBottomSheet(
        isVisible = isVisible,
        cancelable = true,
        dragHandle = { BottomSheetDefaults.DragHandle() },
        onDismiss = { onDismiss() },
        content = {
            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                AppTopBar(
                    title = stringResource(R.string.cards), startIcon = ClickableIcon(
                        icon = IconType.ImageVector(Icons.Filled.Close), onClick = onDismiss
                    )
                )
                LazyColumn {
                    items(items.size) { index ->
                        ItemCheckRow(title = /*items[index]*/ "کارت ۶۱۰۴۳۳۷۸۷۸۹۸۶۵۴۷",
                            titleMore = "فعال",
                            checked = index == 0,
                            enabled = index > items.size - 2,
                            onCheckedChange = {
                                isVisible = false
                                onItemSelected.invoke(items[index])
                            })
                    }
                }
            }
        })
}