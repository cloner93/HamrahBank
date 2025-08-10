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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.pmb.ballon.R
import com.pmb.ballon.component.base.AppBottomSheet
import com.pmb.ballon.component.base.Headline6Text
import com.pmb.ballon.models.AppBottomSheetDefaults
import com.pmb.ballon.models.IconStyle
import com.pmb.ballon.models.MenuSheetModel
import com.pmb.ballon.models.TextStyle
import com.pmb.ballon.ui.theme.AppTheme


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GuideBottomSheet(
    title: String? = null, onDismiss: () -> Unit
) {
    var isVisible by remember { mutableStateOf(true) }
    val items = listOf(
        MenuSheetModel(
            title = stringResource(R.string.call),
            icon = R.drawable.ic_phone_calling,
            showEndIcon = false,
            onClicked = {

            }), MenuSheetModel(
            title = stringResource(R.string.common_questions),
            icon = R.drawable.ic_quiz,
            showEndIcon = false,

            onClicked = {

            }), MenuSheetModel(
            title = stringResource(R.string.feature_usage_guide),
            icon = R.drawable.ic_user_checkmark,
            onClicked = {

            })
    )
    AppBottomSheet(
        isVisible = isVisible,
        cancelable = true,
        dragHandle = { AppBottomSheetDefaults.DragHandle() },
        onDismiss = { onDismiss() },
        content = {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                title?.let {
                    Headline6Text(
                        text = it, color = AppTheme.colorScheme.onBackgroundNeutralSubdued
                    )
                    Spacer(modifier = Modifier.height(32.dp))
                }
                LazyColumn {
                    items(items.size) { item ->
                        ItemRow(
                            item = items[item], onClick = {
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
        horizontalDividerPadding = MenuItemDefaults.horizontalDividerPadding.copy(end = 16.dp),
        startIconStyle = IconStyle(tint = item.iconTint()),
        endIconStyle = IconStyle(tint = AppTheme.colorScheme.foregroundNeutralRest),
        startIcon = item.icon,
        endIcon = if (item.showEndIcon) R.drawable.ic_arrow_left else null,
        titleStyle = TextStyle(
            color = item.textColor(), typography = AppTheme.typography.headline6
        ),
        clickable = false,
        onItemClick = onClick
    )
}