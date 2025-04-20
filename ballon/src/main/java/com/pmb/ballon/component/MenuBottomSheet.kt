package com.pmb.ballon.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.BottomSheetDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.pmb.ballon.R
import com.pmb.ballon.component.base.AppBottomSheet
import com.pmb.ballon.component.base.Headline6Text
import com.pmb.ballon.models.IconStyle
import com.pmb.ballon.models.MenuSheetModel
import com.pmb.ballon.models.TextStyle
import com.pmb.ballon.ui.theme.AppTheme


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MenuBottomSheet(
    title: String? = null,
    items: List<MenuSheetModel> = listOf(),
    onDismiss: () -> Unit
) {
    var isVisible by remember { mutableStateOf(true) }
    AppBottomSheet(
        isVisible = isVisible,
        cancelable = true,
        dragHandle = { BottomSheetDefaults.DragHandle() },
        onDismiss = { onDismiss() },
        content = {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                title?.let {
                    Headline6Text(text = it)
                    Spacer(modifier = Modifier.height(32.dp))
                }
                LazyColumn {
                    items(items.size) { item ->
                        ItemRow(
                            item = items[item],
                            onClick = {
                                isVisible = false
                                items[item].onClicked()
                                onDismiss()
                            })
                    }
                }
            }
        })
}

@Composable
private fun ItemRow(item: MenuSheetModel, onClick: () -> Unit) {
    MenuItem(
        modifier = Modifier.height(52.dp),
        title = item.title,
        horizontalPadding = 16.dp,
        startIcon = item.icon,
        endIcon = if (item.showEndIcon) R.drawable.ic_arrow_left else null,
        titleStyle = TextStyle(
            color = item.textColor(),
            typography = AppTheme.typography.headline6
        ),
        startIconStyle = IconStyle(tint = item.iconTint()),
        endIconStyle = IconStyle(tint = Color(0xFFC2C7CF)),
        clickable = false,
        onItemClick = onClick
    )
}