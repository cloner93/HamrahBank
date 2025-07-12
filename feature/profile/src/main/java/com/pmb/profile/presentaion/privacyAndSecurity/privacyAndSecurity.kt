package com.pmb.profile.presentaion.privacyAndSecurity

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.pmb.ballon.component.MenuItem
import com.pmb.ballon.component.annotation.AppPreview
import com.pmb.ballon.component.base.AppContent
import com.pmb.ballon.component.base.AppTopBar
import com.pmb.ballon.component.base.CaptionText
import com.pmb.ballon.models.IconStyle
import com.pmb.ballon.ui.theme.AppTheme
import com.pmb.ballon.ui.theme.HamrahBankTheme
import com.pmb.navigation.manager.LocalNavigationManager
import com.pmb.navigation.moduleScreen.ProfileScreens
import com.pmb.profile.R

@Composable
fun PrivacySecurityScreen() {

    val navigationManager = LocalNavigationManager.current

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
                            text = "غیرفعال",
                            color = AppTheme.colorScheme.onBackgroundNeutralSubdued
                        )
                    },
                    clickable = true,
                    onItemClick = {
                    })
            }
        }
    }
}

@AppPreview
@Composable
private fun PrivacySecurityScreenPreview() {
    HamrahBankTheme {
        PrivacySecurityScreen()
    }
}