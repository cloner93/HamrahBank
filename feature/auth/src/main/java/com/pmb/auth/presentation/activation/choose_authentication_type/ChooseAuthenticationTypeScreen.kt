package com.pmb.auth.presentation.activation.choose_authentication_type

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import com.pmb.auth.presentation.component.ShowMellatSignatureBottomSheet
import com.pmb.navigation.manager.LocalNavigationManager
import com.pmb.navigation.manager.NavigationManager

@Composable
fun ActivationAuthenticationTypeScreen() {
    val navigationManager: NavigationManager = LocalNavigationManager.current
    var showMellatSignatureAppBottomSheet by remember { mutableStateOf(false) }
//    AppContent(
//        modifier = Modifier.padding(horizontal = 16.dp),
//        topBar = {
//            AppTopBar(
//                title = stringResource(R.string.authentication),
//                onBack = {
//                    navigationManager.navigateBack()
//                }
//            )
//        },
//        footer = {
//            Spacer(modifier = Modifier.size(100.dp))
//        },
//        horizontalAlignment = Alignment.CenterHorizontally
//    ) {
//        Spacer(modifier = Modifier.size(30.dp))
//        AppImage(
//            image = if (comingType == ComingType.COMING_PASSWORD) painterResource(com.pmb.ballon.R.drawable.img_key) else painterResource(
//                com.pmb.ballon.R.drawable.img_document
//            ),
//            style = ImageStyle(size = Size.FIX(all = 128.dp))
//        )
//        Spacer(modifier = Modifier.size(16.dp))
//        Headline4Text(
//            textAlign = TextAlign.Center,
//            modifier = Modifier.fillMaxWidth(),
//            text = if (comingType == ComingType.COMING_PASSWORD) stringResource(R.string.authentication_type_password_description) else stringResource(
//                R.string.authentication_type_activation_description
//            ),
//            color = AppTheme.colorScheme.onBackgroundPrimaryActive
//        )
//        Spacer(modifier = Modifier.weight(1f))
//        AppButtonWithWeightIcon(
//            modifier = Modifier
//                .padding(vertical = 8.dp)
//                .fillMaxWidth(),
//            title = stringResource(R.string.bank_card),
//            icon = com.pmb.ballon.R.drawable.ic_arrow_left,
//            colors = buttonColors(
//                containerColor = Color.White
//            ),
//            textStyle = TextStyle.defaultButton(
//                color = AppTheme.colorScheme.onBackgroundPrimaryActive,
//                typography = AppTheme.typography.bodyMedium
//            ),
//        ) {
//            navigationManager.navigate(AuthScreens.CardInformation)
//        }
//        AppButtonWithWeightIcon(
//            modifier = Modifier
//                .padding(vertical = 8.dp)
//                .fillMaxWidth(),
//            title = stringResource(R.string.visual_authentication),
//            icon = com.pmb.ballon.R.drawable.ic_arrow_left,
//            colors = buttonColors(
//                containerColor = Color.White
//            ),
//            textStyle = TextStyle.defaultButton(
//                color = AppTheme.colorScheme.onBackgroundPrimaryActive,
//                typography = AppTheme.typography.bodyMedium
//            ),
//        ) {
//            navigationManager.navigate(AuthScreens.RegisterNationalId)
//        }
//        AppButtonWithWeightIcon(
//            modifier = Modifier
//                .padding(vertical = 8.dp)
//                .fillMaxWidth(),
//            title = stringResource(R.string.mellat_signature_app),
//            icon = com.pmb.ballon.R.drawable.ic_arrow_left,
//            colors = buttonColors(
//                containerColor = Color.White
//            ),
//            textStyle = TextStyle.defaultButton(
//                color = AppTheme.colorScheme.onBackgroundPrimaryActive,
//                typography = AppTheme.typography.bodyMedium
//            ),
//        ) {
//            showMellatSignatureAppBottomSheet = true
//        }
//    }
    if (showMellatSignatureAppBottomSheet) {
        ShowMellatSignatureBottomSheet() {
            showMellatSignatureAppBottomSheet = false
        }
    }
}
