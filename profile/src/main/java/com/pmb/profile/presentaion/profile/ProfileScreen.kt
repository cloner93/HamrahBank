package com.pmb.profile.presentaion.profile

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.HelpOutline
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import com.pmb.ballon.component.AlertComponent
import com.pmb.ballon.component.MenuItem
import com.pmb.ballon.component.TextImage
import com.pmb.ballon.component.annotation.AppPreview
import com.pmb.ballon.component.base.AppButtonIcon
import com.pmb.ballon.component.base.AppContent
import com.pmb.ballon.component.base.AppLoading
import com.pmb.ballon.models.IconStyle
import com.pmb.ballon.ui.theme.AppTheme
import com.pmb.ballon.ui.theme.HamrahBankTheme
import com.pmb.navigation.manager.LocalNavigationManager
import com.pmb.navigation.moduleScreen.ProfileScreens
import com.pmb.profile.R
import com.pmb.profile.presentaion.profile.viewModel.ProfileViewActions
import com.pmb.profile.presentaion.profile.viewModel.ProfileViewEvents
import com.pmb.profile.presentaion.profile.viewModel.ProfileViewModel


@Composable
fun ProfileScreen(viewModel : ProfileViewModel) {
    val viewState by viewModel.viewState.collectAsState()

    val navigationManager = LocalNavigationManager.current

    LaunchedEffect(Unit) {
        viewModel.viewEvent.collect { event ->
            when (event) {
                ProfileViewEvents.LogoutAccountSucceed -> {

                }

                ProfileViewEvents.NavigateToThemeScreen -> {
                    navigationManager.navigate(ProfileScreens.ThemeScreen)
                }
            }
        }
    }

    AppContent(
        modifier = Modifier
            .padding(horizontal = 16.dp),
        backgroundColor = AppTheme.colorScheme.background3Neutral,
        scrollState = rememberScrollState(),
        topBar = {

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
            ) {
                AppButtonIcon(
                    modifier = Modifier
                        .align(Alignment.Companion.TopStart),
                    icon = Icons.Outlined.HelpOutline,
                    style = IconStyle(tint = AppTheme.colorScheme.onBackgroundNeutralDefault),
                    onClick = {

                    })
                Row(
                    modifier = Modifier
                        .align(Alignment.Companion.Center)
                ) {
                    TextImage(
                        image = R.drawable.ic_profile,
                        spacer = 0.dp,
                        text = "userData "
                    )
                }
            }
        },
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
                    title = stringResource(R.string.user_info),
                    startIcon = com.pmb.ballon.R.drawable.ic_user_circle_black,
                    endIcon = com.pmb.ballon.R.drawable.ic_arrow_left,
                    bottomDivider = true,
                    startIconStyle = IconStyle(tint = AppTheme.colorScheme.onBackgroundNeutralCTA),
                    endIconStyle = IconStyle(tint = AppTheme.colorScheme.foregroundNeutralRest),
                    clickable = true,
                    onItemClick = {
                        navigationManager.navigate(ProfileScreens.PersonalInfo.Graph)
                    })

                MenuItem(
                    title = stringResource(R.string.security_privacy),
                    startIcon = com.pmb.ballon.R.drawable.ic_lock,
                    endIcon = com.pmb.ballon.R.drawable.ic_arrow_left,
                    bottomDivider = true,
                    startIconStyle = IconStyle(tint = AppTheme.colorScheme.onBackgroundNeutralCTA),
                    endIconStyle = IconStyle(tint = AppTheme.colorScheme.foregroundNeutralRest),
                    clickable = true,
                    onItemClick = {
                        navigationManager.navigate(ProfileScreens.PrivacyAndSecurity.Graph)
                    })

                MenuItem(
                    title = stringResource(R.string.appearance_of_program),
                    startIcon = R.drawable.ic_theme,
                    endIcon = com.pmb.ballon.R.drawable.ic_arrow_left,
                    bottomDivider = true,
                    startIconStyle = IconStyle(tint = AppTheme.colorScheme.onBackgroundNeutralCTA),
                    endIconStyle = IconStyle(tint = AppTheme.colorScheme.foregroundNeutralRest),
                    clickable = true,
                    onItemClick = {
                        viewModel.handle(ProfileViewActions.NavigateToThemeScreen)
                    })

                MenuItem(
                    title = stringResource(R.string.update),
                    startIcon = R.drawable.ic_update,
                    endIcon = com.pmb.ballon.R.drawable.ic_arrow_left,
                    startIconStyle = IconStyle(tint = AppTheme.colorScheme.onBackgroundNeutralCTA),
                    endIconStyle = IconStyle(tint = AppTheme.colorScheme.foregroundNeutralRest),
                    clickable = true,
                    onItemClick = {

                    })
            }
        }
        Spacer(modifier = Modifier.height(16.dp))
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
                    title = stringResource(R.string.support),
                    startIcon = R.drawable.ic_support,
                    endIcon = com.pmb.ballon.R.drawable.ic_arrow_left,

                    bottomDivider = true,
                    startIconStyle = IconStyle(tint = AppTheme.colorScheme.onBackgroundNeutralCTA),
                    endIconStyle = IconStyle(tint = AppTheme.colorScheme.foregroundNeutralRest),
                    clickable = true,
                    onItemClick = {

                    })

                MenuItem(
                    title = stringResource(R.string.invite_friend),
                    startIcon = R.drawable.ic_invite,
                    bottomDivider = true,
                    endIcon = com.pmb.ballon.R.drawable.ic_arrow_left,
                    startIconStyle = IconStyle(tint = AppTheme.colorScheme.onBackgroundNeutralCTA),
                    endIconStyle = IconStyle(tint = AppTheme.colorScheme.foregroundNeutralRest),
                    clickable = true,
                    onItemClick = {

                    })

                MenuItem(
                    title = stringResource(R.string.about_app),
                    startIcon = R.drawable.ic_info,
                    bottomDivider = true,
                    endIcon = com.pmb.ballon.R.drawable.ic_arrow_left,
                    startIconStyle = IconStyle(tint = AppTheme.colorScheme.onBackgroundNeutralCTA),
                    endIconStyle = IconStyle(tint = AppTheme.colorScheme.foregroundNeutralRest),
                    clickable = true,
                    onItemClick = {

                    })

                MenuItem(
                    title = stringResource(R.string.comments_and_suggestions),
                    startIcon = R.drawable.ic_comment,
                    endIcon = com.pmb.ballon.R.drawable.ic_arrow_left,
                    startIconStyle = IconStyle(tint = AppTheme.colorScheme.onBackgroundNeutralCTA),
                    endIconStyle = IconStyle(tint = AppTheme.colorScheme.foregroundNeutralRest),
                    clickable = true,
                    onItemClick = {

                    })
            }
        }
        Spacer(modifier = Modifier.height(16.dp))
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
                    title = stringResource(R.string.logout_account),
                    startIcon = R.drawable.ic_logout,
                    endIcon = com.pmb.ballon.R.drawable.ic_arrow_left,
                    startIconStyle = IconStyle(tint = AppTheme.colorScheme.onBackgroundNeutralCTA),
                    endIconStyle = IconStyle(tint = AppTheme.colorScheme.foregroundNeutralRest),
                    clickable = true,
                    onItemClick = {
//                        viewModel.handle(ProfileViewActions.LogoutAccount)
                    })
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

@AppPreview
@Composable
private fun ProfileScreenPreview() {
    CompositionLocalProvider(LocalLayoutDirection provides LayoutDirection.Rtl) {
        HamrahBankTheme {
//            ProfileScreen()
        }
    }
}

