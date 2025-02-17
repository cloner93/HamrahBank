package com.pmb.auth.presentaion.ekyc.authentication_confirm

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.pmb.auth.R
import com.pmb.auth.presentaion.AuthScreens
import com.pmb.auth.presentaion.ekyc.authentication_confirm.viewModel.AuthenticationConfirmStepViewModel
import com.pmb.ballon.component.AlertComponent
import com.pmb.ballon.component.ConfirmationStepComponent
import com.pmb.ballon.component.base.AppButton
import com.pmb.ballon.component.base.AppContent
import com.pmb.ballon.component.base.AppLoading
import com.pmb.ballon.component.base.AppTopBar
import com.pmb.ballon.component.base.BodyMediumText
import com.pmb.ballon.component.base.Headline6Text
import com.pmb.ballon.ui.theme.AppTheme
import com.pmb.core.presentation.NavigationManager

@Composable
fun AuthenticationConfirmScreen(
    navigationManager: NavigationManager,
    viewModel: AuthenticationConfirmStepViewModel
) {
    val viewState by viewModel.viewState.collectAsState()
    AppContent(
        modifier = Modifier.padding(horizontal = 16.dp),
        topBar = {
            AppTopBar(
                title = stringResource(R.string.authentication),
                onBack = {
                    navigationManager.navigateBack()
                }
            )
        },
        footer = {
            AppButton(modifier = Modifier
                .fillMaxWidth()
                .padding(start = 16.dp, end = 16.dp, top = 16.dp),
                enable = viewState.data?.authenticationStepConfirmList?.isNotEmpty() == true,
                title = stringResource(com.pmb.ballon.R.string.confirm),
                onClick = {
                    navigationManager.navigate(AuthScreens.AuthenticationSelectServices)
                })
        }
    ) {
        viewState.data?.authenticationStepConfirmList?.takeIf { it.isNotEmpty() }
            ?.let { authenticationObject ->
                Spacer(modifier = Modifier.size(16.dp))
                Headline6Text(
                    modifier = Modifier.fillMaxWidth(),
                    textAlign = TextAlign.Center,
                    text = stringResource(R.string.check_authentication_data),
                    color = AppTheme.colorScheme.strokeNeutral2Active
                )
                Spacer(modifier = Modifier.size(8.dp))
                BodyMediumText(
                    text = stringResource(R.string.result_authentication_info),
                    color = AppTheme.colorScheme.onBackgroundPrimarySubdued,
                    textAlign = TextAlign.Center
                )
                Spacer(modifier = Modifier.size(16.dp))
                Column(modifier = Modifier.align(Alignment.CenterHorizontally)) {
                    authenticationObject.forEachIndexed { index, authenticationStepConfirmObject ->
                        ConfirmationStepComponent(
                            modifier = Modifier,
                            text = authenticationStepConfirmObject.title,
                            isStepPassed = if (index + 1 < authenticationObject.size) {
                                authenticationObject[index + 1].isEnabled
                            } else false,
                            isEnabled = authenticationStepConfirmObject.isEnabled,
                            isDrawLine = index < authenticationObject.size - 1
                        )
                    }
                }
            }
    }
    if (viewState.loading) {
        AppLoading()
    }
    if (viewState.alertModelState != null) {
        AlertComponent(viewState.alertModelState!!)
    }
}


