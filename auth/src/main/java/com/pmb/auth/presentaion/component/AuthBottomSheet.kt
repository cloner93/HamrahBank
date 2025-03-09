package com.pmb.auth.presentaion.component

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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.pmb.auth.R
import com.pmb.ballon.component.PersianDatePicker
import com.pmb.ballon.component.TextImage
import com.pmb.ballon.component.base.AppBottomSheet
import com.pmb.ballon.component.base.AppButton
import com.pmb.ballon.component.base.AppTextButton
import com.pmb.ballon.component.base.AppTopBar
import com.pmb.ballon.component.base.BodyMediumText
import com.pmb.ballon.component.base.ClickableIcon
import com.pmb.ballon.component.base.IconType
import com.pmb.ballon.models.ImageStyle
import com.pmb.ballon.models.Size
import com.pmb.ballon.ui.theme.AppTheme

@Composable
fun ShowChangedNewPasswordBottomSheet(onDismiss: () -> Unit) {
    var isVisible by remember { mutableStateOf(true) }
    AppBottomSheet(
        isVisible = isVisible,
        onDismiss = { onDismiss() },
        content = {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(24.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                TextImage(
                    image = com.pmb.ballon.R.drawable.img_check_circle,
                    text = stringResource(R.string.msg_changed_new_password),
                    imageStyle = ImageStyle(size = Size.FIX(80.dp)),
                )
                Spacer(modifier = Modifier.size(24.dp))
                AppButton(modifier = Modifier.fillMaxWidth(),
                    title = stringResource(R.string.complete),
                    onClick = {
                        isVisible = false
                    })
            }
        })
}

@Composable
fun ShowInvalidLoginBottomSheet(expired: String, onDismiss: () -> Unit) {
    var isVisible by remember { mutableStateOf(true) }
    AppBottomSheet(
        isVisible = isVisible,
        onDismiss = { onDismiss() },
        content = {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(24.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                TextImage(
                    image = com.pmb.ballon.R.drawable.img_info_circle,
                    text = stringResource(R.string.msg_could_not_login),
                    imageStyle = ImageStyle(size = Size.FIX(80.dp)),
                )
                Spacer(modifier = Modifier.size(12.dp))
                BodyMediumText(
                    text = stringResource(R.string.msg_invalid_login_credentials),
                    color = AppTheme.colorScheme.onBackgroundNeutralSubdued,
                    textAlign = TextAlign.Center
                )
                Spacer(modifier = Modifier.size(24.dp))
                AppTextButton(
                    modifier = Modifier.fillMaxWidth(),
                    title = "امکان  ورود مجدد تا ${expired}",
                    enable = false,
                    onClick = { isVisible = false })
                Spacer(modifier = Modifier.size(8.dp))
                AppButton(modifier = Modifier.fillMaxWidth(),
                    title = stringResource(R.string.i_understand),
                    onClick = { isVisible = false })
            }
        })
}


@Preview
@Composable
private fun ShowChangedNewPasswordBottomSheetPreview() {
    ShowChangedNewPasswordBottomSheet {

    }
}


@Composable
fun ShowPersianDatePickerBottomSheet(
    defaultDate: String,
    onChangeValue: (year: String, month: String, day: String) -> Unit,
    onDismiss: () -> Unit
) {
    var isVisible by remember { mutableStateOf(true) }
    var _year by remember { mutableStateOf("") }
    var _month by remember { mutableStateOf("") }
    var _day by remember { mutableStateOf("") }

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
                    title = stringResource(R.string.birthday),
                    requiredHeight = false,
                    startIcon = ClickableIcon(
                        icon = IconType.ImageVector(Icons.Default.Close),
                        onClick = { isVisible = false })
                )
                Spacer(modifier = Modifier.size(24.dp))
                PersianDatePicker(
                    value = defaultDate,
                    onChangeValue = { year, month, day ->
                        _year = year
                        _month = month
                        _day = day
                    }
                )
                Spacer(modifier = Modifier.size(8.dp))
                AppButton(modifier = Modifier.fillMaxWidth(),
                    title = stringResource(R.string._continue),
                    onClick = {
                        isVisible = false
                        onChangeValue(_year, _month, _day)
                    })
            }
        })
}