package com.pmb.auth.presentation.register.account_opening

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.pmb.auth.R
import com.pmb.auth.presentation.component.ShowPersianDatePickerBottomSheet
import com.pmb.auth.utils.ComingType
import com.pmb.ballon.component.base.AppButton
import com.pmb.ballon.component.base.AppButtonIcon
import com.pmb.ballon.component.base.AppClickableReadOnlyTextField
import com.pmb.ballon.component.base.AppContent
import com.pmb.ballon.component.base.AppMobileTextField
import com.pmb.ballon.component.base.AppNationalIdTextField
import com.pmb.ballon.component.base.AppTopBar
import com.pmb.ballon.component.base.IconType
import com.pmb.core.presentation.NavigationManager

@Composable
fun AccountOpeningScreen(
    navigationManager: NavigationManager,
    onNavigationCallBack: (ComingType) -> Unit
) {
    var phoneNumber by remember { mutableStateOf("09128353268") }
    var nationalId by remember { mutableStateOf("0012345678") }
    var birthday by remember { mutableStateOf("1371/08/28") }

    var showBirthdayPicker by remember { mutableStateOf(false) }
    var isMobile by remember { mutableStateOf(false) }
    var isNationalId by remember { mutableStateOf(false) }
    var isBirthday by remember { mutableStateOf(false) }


    AppContent(
        modifier = Modifier.padding(horizontal = 16.dp),
        topBar = {
            AppTopBar(
                title = stringResource(R.string.account_opening),
                onBack = { navigationManager.navigateBack() }
            )
        },
        footer = {
            AppButton(modifier = Modifier
                .fillMaxWidth()
                .padding(start = 16.dp, end = 16.dp, top = 16.dp),
                enable = true,
                title = stringResource(R.string._continue),
                onClick = {
                    onNavigationCallBack(ComingType.COMING_REGISTER)

                })
        },
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.size(24.dp))
        Image(
            modifier = Modifier.size(56.dp),
            painter = painterResource(com.pmb.ballon.R.drawable.img_mellat_logo),
            contentDescription = "mellat logo"
        )
        Spacer(modifier = Modifier.size(32.dp))
        AppMobileTextField(value = phoneNumber,
            label = stringResource(R.string.mobile_number),
            onValidate = { isMobile = it },
            onValueChange = { phoneNumber = it })
        Spacer(modifier = Modifier.size(24.dp))
        AppNationalIdTextField(value = nationalId,
            label = stringResource(R.string.national_id),
            onValidate = { isNationalId = it },
            onValueChange = { nationalId = it })
        Spacer(modifier = Modifier.size(24.dp))
        AppClickableReadOnlyTextField(
            value = birthday,
            label = stringResource(R.string.birthday),
            trailingIcon = {
                AppButtonIcon(
                    icon = IconType.Painter(painterResource(com.pmb.ballon.R.drawable.ic_calendar_month)),
                    onClick = {
                        showBirthdayPicker = true
                    }
                )
            },
            onClick = {
                println("clicked on birthday")
                showBirthdayPicker = true
            })
    }

    if (showBirthdayPicker) {
        ShowPersianDatePickerBottomSheet(
            defaultDate = birthday,
            onDismiss = { showBirthdayPicker = false },
            onChangeValue = { year, month, day ->
                birthday = "$day/$month/$year"
                showBirthdayPicker = false
            }
        )
    }
}

