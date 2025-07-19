package com.pmb.profile.presentaion.personal_infos.change_phone_number

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.pmb.ballon.component.AlertComponent
import com.pmb.ballon.component.base.AppButton
import com.pmb.ballon.component.base.AppContent
import com.pmb.ballon.component.base.AppLoading
import com.pmb.ballon.component.base.AppMobileTextField
import com.pmb.ballon.component.base.AppTopBar
import com.pmb.ballon.ui.theme.AppTheme
import com.pmb.navigation.manager.LocalNavigationManager
import com.pmb.navigation.moduleScreen.ProfileScreens
import com.pmb.profile.R
import com.pmb.profile.domain.entity.OtpEntity
import com.pmb.profile.presentaion.personal_infos.PersonalInfoSharedState
import com.pmb.profile.presentaion.personal_infos.change_phone_number.viewmodel.ChangePhoneNumberViewActions
import com.pmb.profile.presentaion.personal_infos.change_phone_number.viewmodel.ChangePhoneNumberViewEvents
import com.pmb.profile.presentaion.personal_infos.change_phone_number.viewmodel.ChangePhoneNumberViewModel

@Composable
fun ChangePhoneNumberScreen(
    viewModel: ChangePhoneNumberViewModel,
    sharedState: PersonalInfoSharedState,
    result: (otpEntity: OtpEntity) -> Unit
) {
    val navigationManager = LocalNavigationManager.current
    val viewState by viewModel.viewState.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.handle(ChangePhoneNumberViewActions.UpdateShareState(sharedState))
    }

    LaunchedEffect(Unit) {
        viewModel.viewEvent.collect { event ->
            when (event) {
                is ChangePhoneNumberViewEvents.NavigateToVerifyPhoneNumber -> {
                    result.invoke(event.otpEntity)
                    navigationManager.navigate(ProfileScreens.PersonalInfo.VerifyPhoneNumberOtp)
                }
            }
        }
    }

    AppContent(
        modifier = Modifier
            .padding(horizontal = 16.dp)
            .padding(top = 24.dp),
        backgroundColor = AppTheme.colorScheme.background3Neutral,
        topBar = {
            AppTopBar(
                title = stringResource(R.string.change_phone_number),
                onBack = { navigationManager.navigateBack() })
        },
        footer = {
            AppButton(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
                title = stringResource(R.string.save),
                enable = viewState.enableButton,
                onClick = {
                    viewModel.handle(ChangePhoneNumberViewActions.SubmitPhoneNumber)
                })
        },
        content = {
            AppMobileTextField(
                modifier = Modifier.fillMaxWidth(),
                value = viewState.phoneNumber,
                label = stringResource(R.string.new_phone_number),
                onValueChange = {
                    if (it.length <= viewState.mobileValidation.length) viewModel.handle(
                        ChangePhoneNumberViewActions.ChangePhoneNumber(it)
                    )
                })
        })

    if (viewState.loading) AppLoading()
    viewState.alertState?.let { AlertComponent(it) }
}