package com.pmb.auth.presentation.fingerprint

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.fragment.app.FragmentActivity
import com.pmb.auth.presentation.fingerprint.viewmodel.EnableFingerprintViewActions
import com.pmb.auth.presentation.fingerprint.viewmodel.EnableFingerprintViewEvents
import com.pmb.auth.presentation.fingerprint.viewmodel.EnableFingerprintViewModel
import com.pmb.ballon.component.base.AppButton
import com.pmb.ballon.component.base.AppContent
import com.pmb.ballon.component.base.AppImage
import com.pmb.ballon.component.base.AppOutlineButton
import com.pmb.ballon.component.base.BodyMediumText
import com.pmb.ballon.component.base.Headline3Text
import com.pmb.ballon.ui.theme.AppTheme
import com.pmb.core.BiometricPromptHelper
import com.pmb.navigation.manager.LocalNavigationManager
import com.pmb.navigation.manager.NavigationManager
import com.pmb.navigation.moduleScreen.HomeScreens


@Composable
fun EnableFingerprintScreen(viewModel: EnableFingerprintViewModel) {
    val navigationManager: NavigationManager = LocalNavigationManager.current

    val activity = LocalContext.current as? FragmentActivity

    LaunchedEffect(Unit) {
        viewModel.viewEvent.collect { event ->
            when (event) {
                EnableFingerprintViewEvents.NavigateForward -> {
                    navigationManager.navigate(HomeScreens.Home)
                }
            }
        }
    }

    AppContent(
        modifier = Modifier.padding(horizontal = 16.dp),
        footer = {
            Row(Modifier.padding(horizontal = 16.dp)) {
                AppButton(
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1f),
                    title = "فعالسازی",
                    onClick = {
                        activity?.let { it ->
                            BiometricPromptHelper.showBiometricPrompt(
                                it,
                                onSuccess = {
                                    viewModel.handle(
                                        EnableFingerprintViewActions.Enable
                                    )
                                },
                                onError = {
                                    Log.d("TAG", "LoginScreen: error $it")
                                },
                                onCancel = {
                                    Log.d("TAG", "LoginScreen: cancel")
                                }
                            )
                        }
                    }
                )

                Spacer(modifier = Modifier.width(16.dp))
                AppOutlineButton(
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1f),
                    title = "بعدا",
                    onClick = {
                        navigationManager.navigate(HomeScreens.Home)
                    }
                )
            }
        },
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.weight(1f)
        ) {
            AppImage(
                modifier = Modifier.size(64.dp),
                image = painterResource(com.pmb.ballon.R.drawable.ic_fingerprint),
            )
            Spacer(modifier = Modifier.size(32.dp))
            Headline3Text(
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Center,
                text = "فعالسازی ورود با اثرانگشت",
                color = AppTheme.colorScheme.strokeNeutral2Active
            )
            Spacer(modifier = Modifier.size(8.dp))
            BodyMediumText(
                text = "در صورت تمایل می توانید احراز هویت پیومتریک خود را فعال نمایید.",
                color = AppTheme.colorScheme.onBackgroundPrimarySubdued,
                textAlign = TextAlign.Center
            )

        }
    }
}