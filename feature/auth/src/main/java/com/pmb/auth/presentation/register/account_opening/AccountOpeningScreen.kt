package com.pmb.auth.presentation.register.account_opening

import androidx.compose.foundation.Image
import androidx.compose.foundation.focusable
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.core.text.isDigitsOnly
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.pmb.auth.R
import com.pmb.auth.presentation.register.RegisterSharedViewState
import com.pmb.auth.presentation.register.account_opening.viewModel.OpeningAccountViewActions
import com.pmb.auth.presentation.register.account_opening.viewModel.OpeningAccountViewEvents
import com.pmb.auth.presentation.register.account_opening.viewModel.OpeningAccountViewModel
import com.pmb.auth.presentation.register.account_opening.viewModel.OpeningAccountViewState
import com.pmb.ballon.component.AlertComponent
import com.pmb.ballon.component.base.AppButton
import com.pmb.ballon.component.base.AppButtonIcon
import com.pmb.ballon.component.base.AppClickableReadOnlyTextField
import com.pmb.ballon.component.base.AppContent
import com.pmb.ballon.component.base.AppMobileTextField
import com.pmb.ballon.component.base.AppNationalIdTextField
import com.pmb.ballon.component.base.AppTopBar
import com.pmb.ballon.component.base.IconType
import com.pmb.ballon.component.datePicker.ShowPersianDatePickerBottomSheet
import com.pmb.calender.Jdn
import com.pmb.calender.utils.Calendar
import com.pmb.core.utils.validatePhone
import com.pmb.navigation.manager.LocalNavigationManager
import com.pmb.navigation.manager.NavigationManager
import com.pmb.navigation.moduleScreen.RegisterScreens

@Composable
fun AccountOpeningScreen(
    viewModel: OpeningAccountViewModel,
    sharedState: State<RegisterSharedViewState>,
    updateState: (OpeningAccountViewState) -> Unit
) {
    val viewState by viewModel.viewState.collectAsStateWithLifecycle()
    val navigationManager: NavigationManager = LocalNavigationManager.current
//    sharedState.value.mobileNo?.let { viewModel.handle(OpeningAccountViewActions.SetPhoneNumber(it)) }
//    val focusManager = LocalFocusManager.current
//    val buttonFocusRequester = remember { FocusRequester() }
    var isError by remember { mutableStateOf(false) }
    LaunchedEffect(sharedState.value.mobileNo) {
        sharedState.value.mobileNo?.let {
            viewModel.handle(
                OpeningAccountViewActions.SetPhoneNumber(
                    it
                )
            )
        }
        sharedState.value.nationalCode?.let {
            viewModel.handle(
                OpeningAccountViewActions.SetNationalId(
                    it
                )
            )
        }
        sharedState.value.birthDate?.let { input ->
            viewModel.handle(
                OpeningAccountViewActions.SetBirthday(
                    input.substring(0, 4),
                    input.substring(4, 6),
                    input.substring(6, 8)
                )
            )
        }
    }
    LaunchedEffect(Unit) {
        viewModel.viewEvent.collect { event ->
            when (event) {
                OpeningAccountViewEvents.SendOpeningAccountViewSucceed -> {
                    updateState.invoke(viewState)
                    navigationManager.navigate(RegisterScreens.RegisterNationalId)
                }
            }
        }
    }

    AppContent(
        modifier = Modifier.padding(horizontal = 16.dp),
        topBar = {
            AppTopBar(
                title = stringResource(R.string.account_opening),
                onBack = { navigationManager.navigateBack() }
            )
        },
        footer = {
            AppButton(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 16.dp, end = 16.dp, top = 16.dp)
//                    .focusRequester(buttonFocusRequester)
                    .focusable(),
                enable = !isError && !viewState.phoneNumber.isNullOrEmpty() && !viewState.nationalId.isNullOrEmpty() && viewState.birthDateDay != null,
                title = stringResource(R.string._continue),
                onClick = {
                    viewModel.handle(
                        OpeningAccountViewActions.SendOpeningAccountData
                    )
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
        AppMobileTextField(
            value = viewState.phoneNumber ?: "",
            label = stringResource(R.string.mobile_number),
            onFocused = { focused ->
                isError = if (!focused && !viewState.phoneNumber.isNullOrEmpty()) {
                    !viewState.phoneNumber.validatePhone()
                } else {
                    false
                }
            },
            isError = isError,
            errorText = stringResource(R.string.validate_phone_number),
            onValueChange = { phone ->
                if (phone.isDigitsOnly() || phone.isEmpty())
                    viewModel.handle(OpeningAccountViewActions.SetPhoneNumber(phone))
            })
        Spacer(modifier = Modifier.size(24.dp))
        AppNationalIdTextField(
            value = viewState.nationalId ?: "",
            label = stringResource(R.string.national_id),
            onValidate = { },
            onValueChange = { nationalId ->
                if (nationalId.isDigitsOnly() || nationalId.isEmpty())
                    viewModel.handle(OpeningAccountViewActions.SetNationalId(nationalId))
            })
        Spacer(modifier = Modifier.size(24.dp))
        AppClickableReadOnlyTextField(
            value = viewState.birthDateYear?.let { "${it}/${viewState.birthDateMonth}/${viewState.birthDateDay}" }
                ?: run { "" },
            label = stringResource(R.string.birthday),
            trailingIcon = {
                AppButtonIcon(
                    icon = IconType.Painter(painterResource(com.pmb.ballon.R.drawable.ic_calendar_month)),
                    onClick = {
                        viewModel.handle(OpeningAccountViewActions.ShowBottomSheet(true))
                    }
                )
            },
            onClick = {
                viewModel.handle(OpeningAccountViewActions.ShowBottomSheet(true))
            })
    }

    if (viewState.isShowingBottomSheet) {
        ShowPersianDatePickerBottomSheet(
            title = stringResource(R.string.birthday),
            defaultDate = viewState.birthDateYear?.let {
                Jdn(
                    Calendar.SHAMSI,
                    it.toInt(),
                    viewState.birthDateMonth?.toInt() ?: 1,
                    viewState.birthDateDay?.toInt() ?: 1
                )
            } ?: run { Jdn.today() },
            onDismiss = { viewModel.handle(OpeningAccountViewActions.ShowBottomSheet(false)) },
            onChangeValue = { year, month, day ->
//                focusManager.clearFocus(force = true)
//                buttonFocusRequester.requestFocus()
                viewModel.handle(
                    OpeningAccountViewActions.SetBirthday(
                        year,
                        month,
                        day
                    )
                )
                viewModel.handle(OpeningAccountViewActions.ShowBottomSheet(false))
            },
        )
    }
    viewState.alertModelState?.let { AlertComponent(it) }
}

