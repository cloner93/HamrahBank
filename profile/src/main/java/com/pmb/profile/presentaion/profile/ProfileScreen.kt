package com.pmb.profile.presentaion.profile

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.pmb.ballon.component.AlertComponent
import com.pmb.ballon.component.MenuItem
import com.pmb.ballon.component.TextImage
import com.pmb.ballon.component.base.AppContent
import com.pmb.ballon.component.base.AppLoading
import com.pmb.ballon.models.IconStyle
import com.pmb.ballon.models.TextStyle
import com.pmb.ballon.ui.theme.AppTheme
import com.pmb.core.presentation.NavigationManager
import com.pmb.profile.R
import com.pmb.profile.presentaion.profile.viewModel.ProfileViewActions
import com.pmb.profile.presentaion.profile.viewModel.ProfileViewEvents
import com.pmb.profile.presentaion.profile.viewModel.ProfileViewModel


@Composable
fun ProfileScreen(navigationManager: NavigationManager, viewModel: ProfileViewModel) {
    val viewState by viewModel.viewState.collectAsState()
    LaunchedEffect(Unit) {
        viewModel.viewEvent.collect { event ->
            when (event) {
                ProfileViewEvents.LogoutAccountSucceed -> {
                    // in the future based on our logic and business can we call it and change some thing else  or navigate other screen
                    Unit
                }
            }
        }
    }
    AppContent {
        Column(
            modifier = Modifier.padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            TextImage(
                modifier = Modifier.padding(all = 32.dp),
                image = R.drawable.profile_placeholder,
                text = viewState.userData?.userName ?: ""
            )

            Column(
                modifier = Modifier
                    .clip(RoundedCornerShape(12.dp))
                    .background(color = AppTheme.colorScheme.onForegroundNeutralDefault)
            ) {
                MenuItem(
                    title = stringResource(R.string.buy_subscription),
                    subtitle = stringResource(R.string.buy_subscription_subtitle),
                    startIcon = com.pmb.ballon.R.drawable.ic_star_magic,
                    endIcon = com.pmb.ballon.R.drawable.ic_arrow_left,
                    bottomDivider = true,
                    titleStyle = TextStyle(color = AppTheme.colorScheme.onBackgroundPrimaryCTA),
                    startIconStyle = IconStyle(tint = AppTheme.colorScheme.onBackgroundPrimaryCTA),
                    clickable = false,
                    onItemClick = {

                    })

                MenuItem(
                    title = stringResource(R.string.my_services),
                    startIcon = com.pmb.ballon.R.drawable.ic_my_services,
                    endIcon = com.pmb.ballon.R.drawable.ic_arrow_left,
                    startIconStyle = IconStyle(tint = AppTheme.colorScheme.iconColor),
                    clickable = false,
                    onItemClick = {

                    })
            }

            Spacer(modifier = Modifier.height(16.dp))

            Column(
                modifier = Modifier
                    .clip(RoundedCornerShape(12.dp))
                    .background(color = AppTheme.colorScheme.onForegroundNeutralDefault)
            ) {
                MenuItem(
                    title = stringResource(R.string.user_info),
                    startIcon = com.pmb.ballon.R.drawable.ic_user_circle_black,
                    endIcon = com.pmb.ballon.R.drawable.ic_arrow_left,
                    bottomDivider = true,
                    startIconStyle = IconStyle(tint = AppTheme.colorScheme.iconColor),
                    clickable = false,
                    onItemClick = {

                    })

                MenuItem(
                    title = stringResource(R.string.security_privacy),
                    startIcon = com.pmb.ballon.R.drawable.ic_lock,
                    endIcon = com.pmb.ballon.R.drawable.ic_arrow_left,
                    bottomDivider = true,
                    startIconStyle = IconStyle(tint = AppTheme.colorScheme.iconColor),
                    clickable = false,
                    onItemClick = {

                    })

                MenuItem(
                    title = stringResource(R.string.appearance_of_program),
                    startIcon = com.pmb.ballon.R.drawable.ic_painting_palette,
                    endIcon = com.pmb.ballon.R.drawable.ic_arrow_left,
                    bottomDivider = true,
                    startIconStyle = IconStyle(tint = AppTheme.colorScheme.iconColor),
                    clickable = false,
                    onItemClick = {

                    })

                MenuItem(
                    title = stringResource(R.string.update),
                    startIcon = com.pmb.ballon.R.drawable.ic_refresh,
                    endIcon = com.pmb.ballon.R.drawable.ic_arrow_left,
                    startIconStyle = IconStyle(tint = AppTheme.colorScheme.iconColor),
                    clickable = false,
                    onItemClick = {

                    })
            }

            Spacer(modifier = Modifier.height(16.dp))

            Column(
                modifier = Modifier
                    .clip(RoundedCornerShape(12.dp))
                    .background(color = AppTheme.colorScheme.onForegroundNeutralDefault)
            ) {
                MenuItem(
                    title = stringResource(R.string.login_to_internet_bank),
                    startIcon = com.pmb.ballon.R.drawable.ic_qr_scan,
                    endIcon = com.pmb.ballon.R.drawable.ic_arrow_left,
                    startIconStyle = IconStyle(tint = AppTheme.colorScheme.iconColor),
                    clickable = false,
                    onItemClick = {

                    })
            }

            Spacer(modifier = Modifier.height(16.dp))

            Column(
                modifier = Modifier
                    .clip(RoundedCornerShape(12.dp))
                    .background(color = AppTheme.colorScheme.onForegroundNeutralDefault)
            ) {
                MenuItem(
                    title = stringResource(R.string.support),
                    startIcon = com.pmb.ballon.R.drawable.ic_support,
                    endIcon = com.pmb.ballon.R.drawable.ic_arrow_left,
                    bottomDivider = true,
                    startIconStyle = IconStyle(tint = AppTheme.colorScheme.iconColor),
                    clickable = false,
                    onItemClick = {

                    })

                MenuItem(
                    title = stringResource(R.string.invite_friend),
                    startIcon = com.pmb.ballon.R.drawable.ic_happy_heart,
                    endIcon = com.pmb.ballon.R.drawable.ic_arrow_left,
                    bottomDivider = true,
                    startIconStyle = IconStyle(tint = AppTheme.colorScheme.iconColor),
                    clickable = false,
                    onItemClick = {

                    })

                MenuItem(
                    title = stringResource(R.string.about_app),
                    startIcon = com.pmb.ballon.R.drawable.ic_info_circle,
                    endIcon = com.pmb.ballon.R.drawable.ic_arrow_left,
                    bottomDivider = true,
                    startIconStyle = IconStyle(tint = AppTheme.colorScheme.iconColor),
                    clickable = false,
                    onItemClick = {

                    })

                MenuItem(
                    title = stringResource(R.string.comments_and_suggestions),
                    startIcon = com.pmb.ballon.R.drawable.ic_comment,
                    endIcon = com.pmb.ballon.R.drawable.ic_arrow_left,
                    startIconStyle = IconStyle(tint = AppTheme.colorScheme.iconColor),
                    clickable = false,
                    onItemClick = {

                    })
            }

            Spacer(modifier = Modifier.height(16.dp))

            Column(
                modifier = Modifier
                    .clip(RoundedCornerShape(12.dp))
                    .background(color = AppTheme.colorScheme.onForegroundNeutralDefault)
            ) {
                MenuItem(
                    title = stringResource(R.string.logout_account),
                    startIcon = com.pmb.ballon.R.drawable.ic_logout,
                    endIcon = com.pmb.ballon.R.drawable.ic_arrow_left,
                    clickable = false,
                    onItemClick = {
                        viewModel.handle(ProfileViewActions.LogoutAccount)
                    })
            }
            Spacer(modifier = Modifier.height(16.dp))
        }
    }
    if (viewState.loading) {
        AppLoading()
    }
    if (viewState.alertModelState != null) {
        AlertComponent(viewState.alertModelState!!)
    }
}