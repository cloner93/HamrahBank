package com.pmb.profile.presentaion.privacyAndSecurity

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.BottomSheetDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
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
import androidx.hilt.navigation.compose.hiltViewModel
import com.pmb.ballon.component.MenuItem
import com.pmb.ballon.component.TextImage
import com.pmb.ballon.component.base.AppBottomSheet
import com.pmb.ballon.component.base.AppButton
import com.pmb.ballon.component.base.AppContent
import com.pmb.ballon.component.base.AppOutlineButton
import com.pmb.ballon.component.base.AppTopBar
import com.pmb.ballon.component.base.BodyMediumText
import com.pmb.ballon.component.base.CaptionText
import com.pmb.ballon.component.base.Headline5Text
import com.pmb.ballon.component.base.IconType
import com.pmb.ballon.models.AppButton
import com.pmb.ballon.models.IconStyle
import com.pmb.ballon.models.ImageStyle
import com.pmb.ballon.models.Size
import com.pmb.ballon.models.TextStyle
import com.pmb.ballon.ui.theme.AppTheme
import com.pmb.core.BiometricPromptHelper
import com.pmb.core.BiometricStatus
import com.pmb.navigation.manager.LocalNavigationManager
import com.pmb.navigation.moduleScreen.ProfileScreens
import com.pmb.profile.R
import com.pmb.profile.presentaion.privacyAndSecurity.viewmodel.PrivacySecurityViewActions
import com.pmb.profile.presentaion.privacyAndSecurity.viewmodel.PrivacySecurityViewModel

@Composable
fun PrivacySecurityScreen() {
    val viewModel = hiltViewModel<PrivacySecurityViewModel>()
    val viewState by viewModel.viewState.collectAsState()

    val navigationManager = LocalNavigationManager.current
    val context = LocalContext.current


    LaunchedEffect(navigationManager) {
        viewModel.checkBimetric()
        Log.d("TAG", "PrivacySecurityScreen: ")
    }

    val biometricStatus =
        BiometricPromptHelper.getBiometricStatus(context) == BiometricStatus.AVAILABLE

    AppContent(
        modifier = Modifier
            .padding(horizontal = 16.dp),
        backgroundColor = AppTheme.colorScheme.background3Neutral,
        topBar = {
            AppTopBar(
                title = "امنیت و حریم خصوصی",
                onBack = {
                    navigationManager.navigateBack()
                })
        }
    ) {
        Card(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(16.dp),
            colors = CardDefaults.cardColors(containerColor = AppTheme.colorScheme.background1Neutral),
            elevation = CardDefaults.cardElevation(0.dp)
        ) {
            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                MenuItem(
                    title = "تغییر رمز عبور",
                    startIcon = R.drawable.ic_change_pass,
                    endIcon = com.pmb.ballon.R.drawable.ic_arrow_left,
                    bottomDivider = true,
                    startIconStyle = IconStyle(tint = AppTheme.colorScheme.onBackgroundNeutralCTA),
                    endIconStyle = IconStyle(tint = AppTheme.colorScheme.foregroundNeutralRest),
                    clickable = true,
                    onItemClick = {
                        navigationManager.navigate(ProfileScreens.PrivacyAndSecurity.ChangePasswordScreen)
                    })

                MenuItem(
                    title = "احراز هویت بیومتریک",
                    startIcon = R.drawable.ic_finger,
                    endIcon = com.pmb.ballon.R.drawable.ic_arrow_left,
                    startIconStyle = IconStyle(tint = AppTheme.colorScheme.onBackgroundNeutralCTA),
                    endIconStyle = IconStyle(tint = AppTheme.colorScheme.foregroundNeutralRest),
                    endContent = {
                        CaptionText(
                            text = if (!biometricStatus)
                                "در دسترس نمی باشد"
                            else {
                                if (viewState.fingerPrintIsEnable)
                                    "فعال"
                                else
                                    "غیر فعال"
                            },
                            color = AppTheme.colorScheme.onBackgroundNeutralSubdued
                        )
                    },
                    clickable = BiometricPromptHelper.getBiometricStatus(context) == BiometricStatus.AVAILABLE,
                    onItemClick = {
                        if (viewState.fingerPrintIsEnable)
                            viewModel.handle(
                                PrivacySecurityViewActions.ShowDiableFingerprintBottomSheet(true)

                            )
                        else
                            navigationManager.navigate(ProfileScreens.PrivacyAndSecurity.EnableFingerprintScreen)
                    })
            }
        }
    }

    if (viewState.disableFingerprintBottomSheetState) {
        DisableFingerprintBottomSheet(
            title = "در حال حاضر این گزینه برای شما فعال است.",
            caption = "آیا مایل به غیرفعالسازی احراز هویت بیومتریک هستید؟",
            isVisible = true,
            rejectButton = "انصراف",
            confirmButton = "غیرفعالسازی",
            onDismiss = {
                viewModel.handle(
                    PrivacySecurityViewActions.ShowDiableFingerprintBottomSheet(false)
                )
            },
            onConfirm = {
                viewModel.handle(
                    PrivacySecurityViewActions.DiableFingerprint
                )
            },
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DisableFingerprintBottomSheet(
    title: String,
    caption: String,
    isVisible: Boolean,
    rejectButton: String,
    confirmButton: String,
    onDismiss: () -> Unit,
    onConfirm: () -> Unit,
) {
    AppBottomSheet(
        isVisible = isVisible,
        onDismiss = { onDismiss() },
        dragHandle = { BottomSheetDefaults.DragHandle() },
        content = {
            Column(
                modifier = Modifier.padding(20.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                TextImage(
                    spacer = 0.dp,
                    image = IconType.Painter(
                        painterResource(R.drawable.ic_warning)
                    ),
                    text = "",
                    imageStyle = ImageStyle(size = Size.FIX(all = 56.dp)),
                    textStyle = TextStyle(
                        color = AppTheme.colorScheme.onBackgroundNeutralDefault,
                        typography = AppTheme.typography.headline4,
                        textAlign = TextAlign.Center
                    )
                )
                Headline5Text(
                    modifier = Modifier.fillMaxWidth(),
                    text = title,
                    color = AppTheme.colorScheme.onBackgroundNeutralDefault,
                    textAlign = TextAlign.Center
                )
                Spacer(modifier = Modifier.height(24.dp))

                BodyMediumText(
                    modifier = Modifier.fillMaxWidth(),
                    text = caption,
                    color = AppTheme.colorScheme.onBackgroundNeutralDefault,
                    textAlign = TextAlign.Center
                )

                Spacer(modifier = Modifier.size(16.dp))

                Row {
                    AppButton(
                        modifier = Modifier
                            .fillMaxWidth()
                            .weight(1f),
                        title = confirmButton,
                        colors = AppButton.buttonRedColors(),
                        onClick = {
                            onConfirm()
                        })
                    Spacer(modifier = Modifier.width(16.dp))
                    AppOutlineButton(
                        modifier = Modifier
                            .fillMaxWidth()
                            .weight(1f),
                        title = rejectButton,
                        onClick = {
                            onDismiss()
                        })
                }
            }
        }
    )
}