package com.pmb.ballon.component.datePicker

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.pmb.ballon.R
import com.pmb.ballon.component.base.AppBottomSheet
import com.pmb.ballon.component.base.AppButton
import com.pmb.ballon.component.base.AppTopBar
import com.pmb.ballon.component.base.ClickableIcon
import com.pmb.ballon.component.base.IconType
import com.pmb.ballon.ui.theme.AppTheme
import com.pmb.calender.Jdn
import com.pmb.calender.utils.Calendar

@Composable
fun ShowPersianDatePickerBottomSheet(
    defaultDate: Jdn = Jdn.today(),
    onChangeValue: (year: String, month: String, day: String) -> Unit,
    onDismiss: () -> Unit,
    title: String
) {
    var isVisible by remember { mutableStateOf(true) }

    var selectedDate by remember { mutableStateOf(defaultDate) }

    AppBottomSheet(
        isVisible = isVisible,
        cancelable = true,
        onDismiss = { onDismiss() },
        content = { nestedConnection ->
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(color = AppTheme.colorScheme.onForegroundNeutralDefault)
                    .padding(24.dp)
                    .nestedScroll(nestedConnection),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                AppTopBar(
                    title = title,
                    requiredHeight = false,
                    startIcon = ClickableIcon(
                        icon = IconType.ImageVector(Icons.Default.Close),
                        onClick = { isVisible = false })
                )
                Spacer(modifier = Modifier.size(24.dp))
                PersianDatePicker(
                    calendar = Calendar.SHAMSI,
                    jdn = selectedDate,
                    setJdn = {
                        selectedDate = it
                    }
                )
                Spacer(modifier = Modifier.size(8.dp))
                AppButton(
                    modifier = Modifier.fillMaxWidth(),
                    title = stringResource(R.string._continue),
                    onClick = {
                        isVisible = false
                        onChangeValue(
                            selectedDate.toPersianDate().year.toString(),
                            selectedDate.toPersianDate().month.toString().padStart(2, '0'),
                            selectedDate.toPersianDate().dayOfMonth.toString().padStart(2, '0')
                        )
                    })
            }
        })
}
