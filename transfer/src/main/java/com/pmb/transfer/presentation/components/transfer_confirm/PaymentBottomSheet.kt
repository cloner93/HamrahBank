package com.pmb.transfer.presentation.components.transfer_confirm

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
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
import androidx.compose.ui.unit.dp
import com.pmb.ballon.component.base.AppBottomSheet
import com.pmb.ballon.component.base.AppButton
import com.pmb.ballon.component.base.AppNumberTextField
import com.pmb.ballon.component.base.AppTopBar
import com.pmb.ballon.component.base.ClickableIcon
import com.pmb.ballon.component.base.IconType
import com.pmb.ballon.models.TextStyle
import com.pmb.ballon.ui.theme.AppTheme
import com.pmb.transfer.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PaymentBottomSheet(
    title: String,
    result: (pass2: String, cvv2: String, year: String, month: String) -> Unit,
    onRetryPass2: () -> Unit,
    onDismiss: () -> Unit
) {
    var isVisible by remember { mutableStateOf(true) }
    var pass2 by remember { mutableStateOf("") }
    var cvv2 by remember { mutableStateOf("") }
    var year by remember { mutableStateOf("") }
    var month by remember { mutableStateOf("") }

    var retryEnabled by remember { mutableStateOf(true) }
    var isPass2 by remember { mutableStateOf(false) }
    var isCvv2 by remember { mutableStateOf(false) }
    var isYear by remember { mutableStateOf(false) }
    var isMonth by remember { mutableStateOf(false) }


    AppBottomSheet(
        isVisible = isVisible,
        onDismiss = onDismiss,
        dragHandle = { BottomSheetDefaults.DragHandle() },
        content = {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                AppTopBar(
                    title = title,
                    textStyle = TextStyle.defaultTopBar()
                        .copy(
                            color = AppTheme.colorScheme.onBackgroundNeutralSubdued,
                            typography = AppTheme.typography.buttonMedium
                        ),
                    requiredHeight = false,
                    startIcon = ClickableIcon(
                        icon = IconType.ImageVector(imageVector = Icons.Default.Close),
                        onClick = { isVisible = false })
                )
                Spacer(modifier = Modifier.size(32.dp))
                DynamicPassCardInputField(
                    dyPass = pass2,
                    retryEnabled = retryEnabled,
                    onValueChange = {
                        pass2 = it
                        isPass2 = it.length >= 4
                    },
                    onRetryDyPass = {
                        retryEnabled = !retryEnabled
                        onRetryPass2()
                    })
                Spacer(modifier = Modifier.size(32.dp))

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    AppNumberTextField(
                        modifier = Modifier.weight(1f),
                        value = cvv2,
                        label = "CVV2",
                        trailingIcon = {},
                        onValueChange = {
                            if (it.length <= 4) cvv2 = it
                            isCvv2 = cvv2.length in 3..4
                        },
                    )
                    Spacer(modifier = Modifier.size(16.dp))
                    ExpireDateInputField(
                        modifier = Modifier.weight(1f),
                        year = year,
                        month = month,
                        onValueChange = { y, m ->
                            year = y
                            month = m
                            isYear = year.length == 2
                            isMonth = month.length == 2
                        })
                }


                Spacer(modifier = Modifier.size(48.dp))
                AppButton(modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
                    title = stringResource(R.string.confirm_and_transfer),
                    enable = isPass2 && isCvv2 && isYear && isMonth,
                    onClick = {
                        isVisible = false
                        result(pass2, cvv2, year, month)
                    })
            }
        }, cancelable = false
    )
}