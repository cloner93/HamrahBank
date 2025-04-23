package com.pmb.profile.presentaion.personal_infos.change_phone_number_otp

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.pmb.ballon.component.AlertComponent
import com.pmb.ballon.component.base.AppButton
import com.pmb.ballon.component.base.AppContent
import com.pmb.ballon.component.base.AppLoading
import com.pmb.ballon.component.base.AppNumberTextField
import com.pmb.ballon.component.base.AppTextButton
import com.pmb.ballon.component.base.AppTopBar
import com.pmb.ballon.component.base.BodyMediumText
import com.pmb.core.utils.toTimeFormat
import com.pmb.navigation.manager.LocalNavigationManager
import com.pmb.navigation.moduleScreen.ProfileScreens
import com.pmb.profile.R
import com.pmb.profile.domain.entity.PersonalInfoEntity
import com.pmb.profile.presentaion.personal_infos.PersonalInfoSharedState
import com.pmb.profile.presentaion.personal_infos.change_phone_number_otp.viewmodel.ChangePhoneNumberOtpViewActions
import com.pmb.profile.presentaion.personal_infos.change_phone_number_otp.viewmodel.ChangePhoneNumberOtpViewEvents
import com.pmb.profile.presentaion.personal_infos.change_phone_number_otp.viewmodel.ChangePhoneNumberOtpViewModel

@Composable
fun ChangePhoneNumberOtpScreen(
    viewModel: ChangePhoneNumberOtpViewModel,
    shareState: PersonalInfoSharedState,
    result: (PersonalInfoEntity) -> Unit
) {
    val navigationManager = LocalNavigationManager.current
    val viewState by viewModel.viewState.collectAsStateWithLifecycle()

    LaunchedEffect(Unit) {
        viewModel.viewEvent.collect { event ->
            when (event) {
                is ChangePhoneNumberOtpViewEvents.NavigateBackToPersonalInfo -> {
                    result.invoke(event.personalInfo)
                    navigationManager.navigateAndClearStack(
                        screen = ProfileScreens.PersonalInfo.Info,
                        popUpTo = ProfileScreens.PersonalInfo.ChangePhoneNumber,
                        launchSingleTop = true
                    )
                }
            }
        }
    }


    LaunchedEffect(Unit) {
        viewModel.handle(ChangePhoneNumberOtpViewActions.UpdateShareState(shareState))
    }

    AppContent(
        modifier = Modifier.padding(horizontal = 16.dp), topBar = {
            AppTopBar(
                title = stringResource(R.string.activate_code), onBack = {
                    viewModel.handle(ChangePhoneNumberOtpViewActions.CancelTimer)
                    navigationManager.navigateBack()
                })
        }, horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.size(24.dp))
        BodyMediumText(
            text = stringResource(
                R.string.msg_activate_code_for_phone_number, viewState.phoneNumber
            ), textAlign = TextAlign.Center
        )
        Spacer(modifier = Modifier.size(32.dp))
        AppNumberTextField(
            modifier = Modifier.fillMaxWidth(),
            value = viewState.otp,
            label = stringResource(R.string.activate_code),
            showClearButton = false,
            onValueChange = {
                viewModel.handle(ChangePhoneNumberOtpViewActions.UpdateOtp(it))
            },
        )
        Spacer(modifier = Modifier.size(32.dp))
        AppButton(
            modifier = Modifier.fillMaxWidth(),
            enable = viewState.enableSubmit,
            title = stringResource(R.string._continue),
            onClick = {
                viewModel.handle(ChangePhoneNumberOtpViewActions.SubmitOtp)
            })
        Spacer(modifier = Modifier.size(8.dp))
        AppTextButton(
            modifier = Modifier.fillMaxWidth(),
            enable = viewState.enableResend,
            title = if (viewState.showTimer) stringResource(
                R.string.resend_code_hint,
                viewState.timer.toTimeFormat()
            )
            else stringResource(R.string.re_send),
            onClick = {
                viewModel.handle(ChangePhoneNumberOtpViewActions.ResendOtp)
            })
    }

    if (viewState.loading) AppLoading()
    viewState.alertState?.let { AlertComponent(it) }
}