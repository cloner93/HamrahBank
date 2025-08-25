package com.pmb.auth.presentation.register.register_confirm

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.pmb.auth.R
import com.pmb.auth.presentation.register.RegisterSharedViewState
import com.pmb.auth.presentation.register.register_confirm.viewModel.RegisterConfirmStepViewModel
import com.pmb.ballon.component.AlertComponent
import com.pmb.ballon.component.base.AppContent
import com.pmb.ballon.component.base.AppImage
import com.pmb.ballon.component.base.AppLoading
import com.pmb.ballon.component.base.AppTopBar
import com.pmb.ballon.component.base.BodyLargeText
import com.pmb.ballon.component.base.BodyMediumText
import com.pmb.ballon.component.base.ClickableIcon
import com.pmb.ballon.component.base.Headline3Text
import com.pmb.ballon.component.base.IconType
import com.pmb.ballon.ui.theme.AppTheme
import com.pmb.navigation.manager.LocalNavigationManager
import com.pmb.navigation.manager.NavigationManager
import com.pmb.navigation.moduleScreen.AuthScreens

@Composable
fun RegisterConfirmStepScreen(
    viewModel: RegisterConfirmStepViewModel,
    sharedViewState: RegisterSharedViewState
) {
    val navigationManager: NavigationManager = LocalNavigationManager.current
    val viewState by viewModel.viewState.collectAsState()
    AppContent(
        modifier = Modifier.padding(horizontal = 16.dp),
        topBar = {
            AppTopBar(
                title = "",
                requiredHeight = false,
                startIcon = ClickableIcon(
                    icon = IconType.ImageVector(Icons.Default.Close),
                    onClick = { navigationManager.navigateAndClearStack(AuthScreens.Auth) })
            )
        },
        footer = {

        }
    ) {

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
                        text = stringResource(
                            R.string.check_authentication_data
                        ),
                        color = AppTheme.colorScheme.strokeNeutral2Active
                    )
                    Spacer(modifier = Modifier.size(8.dp))
                    BodyMediumText(
                        text = stringResource(
                            R.string.result_authentication_info
                        ),
                        color = AppTheme.colorScheme.onBackgroundPrimarySubdued,
                        textAlign = TextAlign.Center
                    )
                    Spacer(modifier = Modifier.size(32.dp))
                    BodyLargeText(
                        text = stringResource(R.string.interception_number, sharedViewState.refId ?:""),
                        color = AppTheme.colorScheme.onBackgroundNeutralDefault
                    )

                }

    }
    if (viewState.loading) {
        AppLoading()
    }
    if (viewState.alertModelState != null) {
        AlertComponent(viewState.alertModelState!!)
    }
}


