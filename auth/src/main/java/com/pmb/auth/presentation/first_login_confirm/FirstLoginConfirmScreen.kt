package com.pmb.auth.presentation.first_login_confirm

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.pmb.auth.R
import com.pmb.auth.presentation.component.ShowInvalidLoginBottomSheet
import com.pmb.auth.presentation.first_login_confirm.viewModel.FirstLoginConfirmViewActions
import com.pmb.auth.presentation.first_login_confirm.viewModel.FirstLoginConfirmViewEvents
import com.pmb.auth.presentation.first_login_confirm.viewModel.FirstLoginConfirmViewModel
import com.pmb.auth.presentation.first_login_confirm.viewModel.TimerStatus
import com.pmb.auth.presentation.first_login_confirm.viewModel.TimerTypeId
import com.pmb.auth.utils.ComingType
import com.pmb.ballon.component.AlertComponent
import com.pmb.ballon.component.base.AppButton
import com.pmb.ballon.component.base.AppContent
import com.pmb.ballon.component.base.AppLoading
import com.pmb.ballon.component.base.AppNumberTextField
import com.pmb.ballon.component.base.AppTextButton
import com.pmb.ballon.component.base.AppTopBar
import com.pmb.ballon.component.base.BodyMediumText
import com.pmb.ballon.component.base.ChipWithIcon
import com.pmb.ballon.ui.theme.AppTheme
import com.pmb.navigation.manager.LocalNavigationManager
import com.pmb.navigation.manager.NavigationManager
import com.pmb.navigation.moduleScreen.AuthScreens
import com.pmb.navigation.moduleScreen.HomeScreens


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FirstLoginConfirmScreen(
    viewModel: FirstLoginConfirmViewModel,
    comingType: ComingType = ComingType.COMING_LOGIN
) {
    val navigationManager: NavigationManager = LocalNavigationManager.current
    val viewState by viewModel.viewState.collectAsState()
    val sheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true)
    var showBottomSheet by remember { mutableStateOf(viewState.isShowBottomSheet) }

    LaunchedEffect(viewState.isShowBottomSheet) {
        showBottomSheet = viewState.isShowBottomSheet
        if (showBottomSheet) {
            sheetState.show()
        } else {
            sheetState.hide()
        }
    }


    val phonenumber by remember { mutableStateOf(viewModel.getAccountModel().mobileNumber) }
    var otp by remember { mutableStateOf("") }

    val title =
        if (viewState.timerState?.get(TimerTypeId.RESEND_TIMER)?.timerStatus == TimerStatus.IS_RUNNING) {

            stringResource(
                R.string.resend_request,
                viewState.calculateMinute(TimerTypeId.RESEND_TIMER),
                viewState.calculateSecond(TimerTypeId.RESEND_TIMER)
            )


        } else {
            stringResource(R.string.re_send)
        }

    // Handle one-time events such as navigation or showing toasts
    LaunchedEffect(Unit) {
        viewModel.viewEvent.collect { event ->
            when (event) {
                FirstLoginConfirmViewEvents.FirstLoginConfirmSucceed -> {

                    if (comingType === ComingType.COMING_LOGIN)
                        navigationManager.navigate(HomeScreens.Home)
                    else
                        navigationManager.navigate(AuthScreens.AuthenticationInformation)
                }
            }
        }
    }
    AppContent(
        modifier = Modifier.padding(horizontal = 16.dp),
        topBar = {
            AppTopBar(
                title = stringResource(R.string.otp),
                onBack = {
                    navigationManager.navigateBack()
                })
        },
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.size(24.dp))
        BodyMediumText(
            text = stringResource(R.string.msg_first_login_confirm_header),
            color = AppTheme.colorScheme.onBackgroundPrimarySubdued,
            textAlign = TextAlign.Center
        )
        Spacer(modifier = Modifier.size(32.dp))
        ChipWithIcon(
            value = phonenumber,
            startIcon = Icons.Default.Edit,
            clickable = {
                if (comingType === ComingType.COMING_LOGIN)
                    navigationManager.navigateBack()
                else
                    navigationManager.navigateAndClearStack(AuthScreens.Register)
            })
        Spacer(modifier = Modifier.size(32.dp))
        AppNumberTextField(
            modifier = Modifier.fillMaxWidth(),
            value = otp,
            label = stringResource(R.string.otp),
            onValueChange = { otp = it },
        )
        Spacer(modifier = Modifier.size(32.dp))
        AppButton(
            modifier = Modifier.fillMaxWidth(),
            enable = otp.length >= 6,
            title = stringResource(R.string.login),
            onClick = {
                viewModel.handle(
                    FirstLoginConfirmViewActions.ConfirmFirstLogin(
                        mobileNumber = phonenumber, otpCode = otp
                    )
                )
            })
        Spacer(modifier = Modifier.size(8.dp))
        AppTextButton(
            modifier = Modifier.fillMaxWidth(),
            enable = viewState.timerState?.get(TimerTypeId.RESEND_TIMER)?.timerStatus == TimerStatus.IS_FINISHED,
            title = title,
            onClick = {
                viewModel.handle(
                    FirstLoginConfirmViewActions.ResendFirstLoginInfo(
                        mobileNumber = phonenumber,
                        userName = viewModel.getAccountModel().userName,
                        password = viewModel.getAccountModel().passWord
                    )
                )
            })
    }
    if (viewState.loading) {
        AppLoading()
    }
    if (viewState.alertModelState != null) {
        AlertComponent(viewState.alertModelState!!)
    }
    if (showBottomSheet) {
        ModalBottomSheet(
            onDismissRequest = {
                viewModel.handle(FirstLoginConfirmViewActions.ClearBottomSheet)
                showBottomSheet = false
                navigationManager.navigateBack()
            },
            sheetState = sheetState
        ) {
            ShowInvalidLoginBottomSheet(
                expired =
                    "${viewState.calculateHour(TimerTypeId.LOCK_TIMER)}:${
                        viewState.calculateMinute(
                            TimerTypeId.LOCK_TIMER
                        )
                    }:${
                        viewState.calculateSecond(
                            TimerTypeId.LOCK_TIMER
                        )
                    }",
                onDismiss = {
                    viewModel.handle(
                        FirstLoginConfirmViewActions.ClearBottomSheet
                    )
                    navigationManager.navigateBack()
                })
        }
    }
}

