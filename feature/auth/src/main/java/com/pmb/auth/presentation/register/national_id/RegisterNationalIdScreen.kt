package com.pmb.auth.presentation.register.national_id

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.pmb.auth.R
import com.pmb.auth.presentation.register.RegisterSharedViewState
import com.pmb.auth.presentation.register.national_id.viewModel.RegisterNationalIdViewActions
import com.pmb.auth.presentation.register.national_id.viewModel.RegisterNationalIdViewEvents
import com.pmb.auth.presentation.register.national_id.viewModel.RegisterNationalIdViewModel
import com.pmb.auth.presentation.register.national_id.viewModel.RegisterNationalIdViewState
import com.pmb.ballon.component.AlertComponent
import com.pmb.ballon.component.base.AppButton
import com.pmb.ballon.component.base.AppContent
import com.pmb.ballon.component.base.AppImage
import com.pmb.ballon.component.base.AppLoading
import com.pmb.ballon.component.base.AppSingleTextField
import com.pmb.ballon.component.base.AppTopBar
import com.pmb.ballon.component.base.BodyMediumText
import com.pmb.ballon.ui.theme.AppTheme
import com.pmb.core.utils.allowOnlyEnglishLettersAndDigits
import com.pmb.core.utils.isValidCustomInput
import com.pmb.navigation.manager.LocalNavigationManager
import com.pmb.navigation.manager.NavigationManager
import com.pmb.navigation.moduleScreen.RegisterScreens


@Composable
fun RegisterNationalIdScreen(
    viewModel: RegisterNationalIdViewModel,
    sharedState: RegisterSharedViewState,
    updateState: (RegisterNationalIdViewState) -> Unit
) {
    val navigationManager: NavigationManager = LocalNavigationManager.current
    val viewState by viewModel.viewState.collectAsState()
    var isError by remember { mutableStateOf(false) }
    LaunchedEffect(Unit) {
        viewModel.viewEvent.collect { event ->
            when (event) {
                RegisterNationalIdViewEvents.RegisterNationalSucceed -> {
                    updateState(viewState)
                    navigationManager.navigate(RegisterScreens.RegisterConfirm)
                }
            }
        }
    }
    AppContent(
        modifier = Modifier.padding(horizontal = 24.dp),
        topBar = {
            AppTopBar(
                title = stringResource(R.string.national_id_serial),
                onBack = { navigationManager.navigateBack() })
        },
        horizontalAlignment = Alignment.CenterHorizontally,
        footer = {
            AppButton(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 16.dp, end = 16.dp, top = 16.dp),
                enable = !viewState.nationalSerialId.isNullOrEmpty() && !viewState.isLoading,
                title = stringResource(R.string._continue),
                onClick = {
                    if (viewState.nationalSerialId?.isValidCustomInput() == true) {
                        isError = false
                        viewModel.handle(
                            RegisterNationalIdViewActions.RegisterNationalIdSerialServices(
                                sharedState.nationalCode ?: "",
                                sharedState.mobileNo ?: "",
                                sharedState.birthDate ?: ""
                            )
                        )
                    }else
                        isError = true
                })
        }
    ) {
        Spacer(modifier = Modifier.size(24.dp))
        BodyMediumText(
            text = stringResource(R.string.national_id_information),
            textAlign = TextAlign.Center,
            color = AppTheme.colorScheme.onBackgroundPrimarySubdued
        )
        Spacer(modifier = Modifier.size(32.dp))
        AppSingleTextField(
            modifier = Modifier.fillMaxWidth(),
            value = viewState.nationalSerialId ?: "",
            label = stringResource(R.string.national_id_serial_interception),
            isError = isError,
            errorText = "الگوی وارد شده صحیح نمی باشد",
            onFocused = {
                if (it)
                    isError = false
            },
            onValueChange = {
                if (it.length <= 10 && it.allowOnlyEnglishLettersAndDigits())
                    viewModel.handle(RegisterNationalIdViewActions.SetNationalIdSerial(it))
            },
        )
        Spacer(modifier = Modifier.size(32.dp))
        AppImage(
            modifier = Modifier
                .fillMaxWidth()
                .aspectRatio(16f / 9f),
            image = painterResource(com.pmb.ballon.R.drawable.ic_national_id),
        )
    }
    if (viewState.isLoading) {
        AppLoading()
    }
    if (viewState.alertModelState != null) {
        AlertComponent(viewState.alertModelState!!)
    }
}