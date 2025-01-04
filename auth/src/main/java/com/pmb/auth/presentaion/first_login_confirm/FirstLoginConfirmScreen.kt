package com.pmb.auth.presentaion.first_login_confirm

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.pmb.auth.R
import com.pmb.auth.presentaion.component.ShowInvalidLoginBottomSheet
import com.pmb.auth.presentaion.first_login_confirm.viewModel.FirstLoginConfirmViewActions
import com.pmb.auth.presentaion.first_login_confirm.viewModel.FirstLoginConfirmViewEvents
import com.pmb.auth.presentaion.first_login_confirm.viewModel.FirstLoginConfirmViewModel
import com.pmb.ballon.component.AlertComponent
import com.pmb.ballon.component.base.AppButton
import com.pmb.ballon.component.base.AppContent
import com.pmb.ballon.component.base.AppIcon
import com.pmb.ballon.component.base.AppLoading
import com.pmb.ballon.component.base.AppNumberTextField
import com.pmb.ballon.component.base.AppTextButton
import com.pmb.ballon.component.base.AppTopBar
import com.pmb.ballon.component.base.BodyMediumText
import com.pmb.ballon.component.base.BodySmallText
import com.pmb.ballon.models.IconStyle
import com.pmb.ballon.models.Size
import com.pmb.ballon.ui.theme.AppTheme
import com.pmb.core.presentation.NavigationManager
import com.pmb.home.presentation.HomeScreens


@Composable
fun FirstLoginConfirmScreen(
    navigationManager: NavigationManager,
    viewModel: FirstLoginConfirmViewModel
) {
    var showBottomSheet by remember { mutableStateOf(false) }
    val phonenumber by remember { mutableStateOf("09128353268") }
    var otp by remember { mutableStateOf("") }
    val viewState by viewModel.viewState.collectAsState()

    // Handle one-time events such as navigation or showing toasts
    LaunchedEffect(Unit) {
        viewModel.viewEvent.collect { event ->
            when (event) {
                FirstLoginConfirmViewEvents.FirstLoginConfirmSucceed -> {
                    navigationManager.navigate(HomeScreens.Home)
                }

                FirstLoginConfirmViewEvents.FirstLoginConfirmResend -> {

                }

                FirstLoginConfirmViewEvents.FirstLoginStartTimer -> {

                }

                FirstLoginConfirmViewEvents.FirstLoginFinishTimer -> {

                }
            }
        }
    }
    AppContent(
        modifier = Modifier.padding(horizontal = 16.dp),
        topBar = {
            AppTopBar(
                title = stringResource(R.string.login),
                onBack = { navigationManager.navigateBack() })
        },
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.size(24.dp))
        BodyMediumText(
            text = stringResource(R.string.msg_first_login_confirm_header),
            textAlign = TextAlign.Center
        )
        Spacer(modifier = Modifier.size(32.dp))
        ChipWithIcon(value = phonenumber, clickable = { navigationManager.navigateBack() })
        Spacer(modifier = Modifier.size(32.dp))
        AppNumberTextField(
            modifier = Modifier.fillMaxWidth(),
            value = otp,
            label = stringResource(R.string.otp),
            onValueChange = { otp = it },
        )
        Spacer(modifier = Modifier.size(32.dp))
        AppButton(modifier = Modifier.fillMaxWidth(),
            enable = otp == "123456",
            title = stringResource(R.string.login),
            onClick = {
                viewModel.handle(
                    FirstLoginConfirmViewActions.ConfirmFirstLogin(
                        mobileNumber = phonenumber, otpCode = otp
                    )
                )
            })
        Spacer(modifier = Modifier.size(8.dp))
        AppTextButton(modifier = Modifier.fillMaxWidth(),
            title = stringResource(R.string.re_send),
            onClick = {
                showBottomSheet = true
            })
    }
    if (viewState.loading) {
        AppLoading()
    }
    if (viewState.alertModelState != null) {
        AlertComponent(viewState.alertModelState!!)
    }
    if (showBottomSheet) ShowInvalidLoginBottomSheet(
        expired = "23:59:59",
        onDismiss = { showBottomSheet = false })
}

@Composable
fun ChipWithIcon(value: String, clickable: () -> Unit) {
    Box(
        modifier = Modifier
            .wrapContentSize()
            .height(32.dp)
            .clip(shape = RoundedCornerShape(16.dp))
            .border(
                width = 1.dp,
                color = AppTheme.colorScheme.strokeNeutral1Default,
                shape = RoundedCornerShape(16.dp)
            )
            .clickable { clickable.invoke() }
            .padding(horizontal = 10.dp, vertical = 6.dp),
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            AppIcon(
                icon = Icons.Default.Edit, // Edit icon
                style = IconStyle(
                    tint = AppTheme.colorScheme.onBackgroundNeutralSubdued,
                    size = Size.FIX(18.dp)
                )
            )
            Spacer(modifier = Modifier.size(6.dp))
            BodySmallText(text = value, color = AppTheme.colorScheme.onBackgroundNeutralDefault)
        }
    }
}