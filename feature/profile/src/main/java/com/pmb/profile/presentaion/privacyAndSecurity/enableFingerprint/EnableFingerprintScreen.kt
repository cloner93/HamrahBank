package com.pmb.profile.presentaion.privacyAndSecurity.enableFingerprint

import android.util.Log
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Close
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
import com.pmb.ballon.R
import com.pmb.ballon.component.TextImage
import com.pmb.ballon.component.base.AppButtonIcon
import com.pmb.ballon.component.base.AppButtonWithIcon
import com.pmb.ballon.component.base.AppContent
import com.pmb.ballon.component.base.IconType
import com.pmb.ballon.component.text_field.AppPasswordTextField
import com.pmb.ballon.models.IconStyle
import com.pmb.ballon.models.ImageStyle
import com.pmb.ballon.models.Size
import com.pmb.ballon.models.TextStyle
import com.pmb.ballon.ui.theme.AppTheme
import com.pmb.core.BiometricPromptHelper
import com.pmb.navigation.manager.LocalNavigationManager
import com.pmb.navigation.manager.NavigationManager
import com.pmb.profile.presentaion.privacyAndSecurity.enableFingerprint.viewmodel.EnableFingerprintViewActions
import com.pmb.profile.presentaion.privacyAndSecurity.enableFingerprint.viewmodel.EnableFingerprintViewEvents
import com.pmb.profile.presentaion.privacyAndSecurity.enableFingerprint.viewmodel.EnableFingerprintViewModel

@Composable
fun EnableFingerprintScreen(viewModel: EnableFingerprintViewModel) {
    val navigationManager: NavigationManager = LocalNavigationManager.current

    val viewState by viewModel.viewState.collectAsState()

    val activity = LocalContext.current as? FragmentActivity

    LaunchedEffect(Unit) {
        viewModel.viewEvent.collect { event ->
            when (event) {
                is EnableFingerprintViewEvents.EnableFingerprint -> {
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

                EnableFingerprintViewEvents.NavigateBack -> {
                    navigationManager.navigateBack()
                }
            }
        }
    }

    AppContent(
        modifier = Modifier.padding(horizontal = 16.dp),
        topBar = {
            Box(
                modifier = Modifier
                    .fillMaxWidth(),
            ) {
                AppButtonIcon(
                    modifier = Modifier
                        .align(Alignment.Companion.TopStart),
                    icon = Icons.Outlined.Close,
                    style = IconStyle(tint = AppTheme.colorScheme.onBackgroundNeutralDefault),
                    onClick = {
                        navigationManager.navigateBack()
                    })
            }
        },
        footer = {

        },
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.size(32.dp))
        TextImage(
            spacer = 0.dp,
            image = IconType.Painter(
                painterResource(R.drawable.profile_placeholder)
            ),
            text = viewState.userData?.fullName ?: "",
            imageStyle = ImageStyle(size = Size.FIX(all = 56.dp)),
            textStyle = TextStyle(
                color = AppTheme.colorScheme.onBackgroundNeutralDefault,
                typography = AppTheme.typography.headline4,
                textAlign = TextAlign.Center
            )
        )

        Spacer(modifier = Modifier.size(32.dp))
        AppPasswordTextField(
            value = viewState.password,
            label = "رمز عبور",
            conditionMessage = false,
            onValueChange = { viewModel.handle(EnableFingerprintViewActions.PasswordChange(it)) })

        Spacer(modifier = Modifier.size(24.dp))

        AppButtonWithIcon(
            modifier = Modifier
                .fillMaxWidth(),
            title = "فعالسازی",
            enable = viewState.password != ""
        ) {
            viewModel.handle(EnableFingerprintViewActions.EnableFingerprint(viewState.password))
        }
    }
}