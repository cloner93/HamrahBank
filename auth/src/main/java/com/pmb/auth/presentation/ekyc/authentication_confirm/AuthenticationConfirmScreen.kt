package com.pmb.auth.presentation.ekyc.authentication_confirm

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
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
import com.pmb.auth.presentation.AuthScreens
import com.pmb.auth.presentation.ekyc.authentication_confirm.viewModel.AuthenticationConfirmStepViewModel
import com.pmb.auth.utils.ComingType
import com.pmb.ballon.component.AlertComponent
import com.pmb.ballon.component.base.AppButton
import com.pmb.ballon.component.base.AppContent
import com.pmb.ballon.component.base.AppImage
import com.pmb.ballon.component.base.AppLoading
import com.pmb.ballon.component.base.BodyMediumText
import com.pmb.ballon.component.base.Headline3Text
import com.pmb.ballon.ui.theme.AppTheme
import com.pmb.core.presentation.NavigationManager
import com.pmb.core.utils.CollectAsEffect

@Composable
fun AuthenticationConfirmScreen(
    navigationManager: NavigationManager,
    viewModel: AuthenticationConfirmStepViewModel,
) {
    var comingType by remember { mutableStateOf<ComingType>(ComingType.COMING_PASSWORD) }
    val viewState by viewModel.viewState.collectAsState()
    navigationManager.getCurrentScreenFlowData<ComingType?>(
        "authentication",
        null
    )?.CollectAsEffect {
        it.takeIf {
            it != null
        }?.also {
            comingType = it
        }
    }
    AppContent(
        modifier = Modifier.padding(horizontal = 16.dp),
        topBar = {
        },
        footer = {
            AppButton(modifier = Modifier
                .fillMaxWidth()
                .padding(start = 16.dp, end = 16.dp, top = 16.dp),
                enable = viewState.data?.authenticationStepConfirmList?.isNotEmpty() == true,
                title = stringResource(com.pmb.ballon.R.string.confirm),
                onClick = {
                    navigationManager.navigate(AuthScreens.Auth)
                })
        }
    ) {
        viewState.data?.authenticationStepConfirmList?.takeIf { it.isNotEmpty() }
            ?.let { authenticationObject ->
                Column(
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier.weight(1f)
                ) {
                    AppImage(
                        image = painterResource(com.pmb.ballon.R.drawable.ic_open_account),
                    )
                    Spacer(modifier = Modifier.size(16.dp))
                    Headline3Text(
                        modifier = Modifier.fillMaxWidth(),
                        textAlign = TextAlign.Center,
                        text = if (comingType == ComingType.COMING_ACTIVATION) stringResource(R.string.check_activation_data) else stringResource(
                            R.string.check_authentication_data
                        ),
                        color = AppTheme.colorScheme.strokeNeutral2Active
                    )
                    Spacer(modifier = Modifier.size(8.dp))
                    BodyMediumText(
                        text = if (comingType == ComingType.COMING_ACTIVATION) stringResource(R.string.result_activation_info) else stringResource(
                            R.string.result_authentication_info
                        ),
                        color = AppTheme.colorScheme.onBackgroundPrimarySubdued,
                        textAlign = TextAlign.Center
                    )
                    Spacer(modifier = Modifier.size(16.dp))

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


