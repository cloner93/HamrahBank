package com.pmb.profile.presentaion.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.BottomSheetDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.pmb.ballon.R
import com.pmb.ballon.component.base.AppBottomSheet
import com.pmb.ballon.component.base.AppButtonWithIcon
import com.pmb.ballon.component.base.Headline5Text
import com.pmb.ballon.ui.theme.AppTheme
import com.pmb.core.utils.actionCall

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ShowSupportBottomSheet(onDismiss: () -> Unit) {
    var isVisible by remember { mutableStateOf(true) }
    val context = LocalContext.current
    AppBottomSheet(
        isVisible = isVisible,
        cancelable = true,
        onDismiss = { onDismiss() },
        dragHandle = { BottomSheetDefaults.DragHandle() },
        content = {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Headline5Text(
                    text = "1556",
                    color = AppTheme.colorScheme.onBackgroundNeutralDefault
                )
                Spacer(modifier = Modifier.size(56.dp))
                AppButtonWithIcon(
                    modifier = Modifier.fillMaxWidth(),
                    title = "تماس",
                    icon = R.drawable.ic_phone_call,
                    spacer = 8.dp,
                    onClick = {
                        context.actionCall("1556")
                    }
                )
            }
        })
}