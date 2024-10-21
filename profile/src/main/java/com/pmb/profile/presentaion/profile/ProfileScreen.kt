package com.pmb.profile.presentaion.profile

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.pmb.ballon.component.MenuItem
import com.pmb.ballon.component.TextImage
import com.pmb.ballon.component.base.AppContent
import com.pmb.ballon.models.IconStyle
import com.pmb.ballon.models.TextStyle
import com.pmb.ballon.ui.theme.AppTheme
import com.pmb.profile.R

@Composable
fun ProfileScreen(navController: NavController) {
    AppContent {
        Column(modifier = Modifier.padding(16.dp), horizontalAlignment = Alignment.CenterHorizontally) {
            TextImage(
                modifier = Modifier.padding(all = 32.dp),
                image = R.drawable.profile_placeholder,
                text = "مشتاق مودت"
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
                    startIconStyle = IconStyle(tint = AppTheme.colorScheme.onBackgroundPrimaryCTA),
                    titleStyle = TextStyle(color = AppTheme.colorScheme.onBackgroundPrimaryCTA),
                    endIcon = com.pmb.ballon.R.drawable.ic_arrow_left,
                    bottomDivider = true,
                    onItemClick = {

                    })

                MenuItem(
                    title = stringResource(R.string.my_services),
                    startIcon = com.pmb.ballon.R.drawable.ic_my_services,
                    endIcon = com.pmb.ballon.R.drawable.ic_arrow_left,
                    startIconStyle = IconStyle(tint = AppTheme.colorScheme.iconColor),
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
                    startIconStyle = IconStyle(tint = AppTheme.colorScheme.iconColor),
                    bottomDivider = true,
                    onItemClick = {

                    })

                MenuItem(
                    title = stringResource(R.string.security_privacy),
                    startIcon = com.pmb.ballon.R.drawable.ic_lock,
                    endIcon = com.pmb.ballon.R.drawable.ic_arrow_left,
                    startIconStyle = IconStyle(tint = AppTheme.colorScheme.iconColor),
                    bottomDivider = true,
                    onItemClick = {

                    })

                MenuItem(
                    title = stringResource(R.string.appearance_of_program),
                    startIcon = com.pmb.ballon.R.drawable.ic_painting_palette,
                    endIcon = com.pmb.ballon.R.drawable.ic_arrow_left,
                    startIconStyle = IconStyle(tint = AppTheme.colorScheme.iconColor),
                    bottomDivider = true,
                    onItemClick = {

                    })

                MenuItem(
                    title = stringResource(R.string.update),
                    startIcon = com.pmb.ballon.R.drawable.ic_refresh,
                    endIcon = com.pmb.ballon.R.drawable.ic_arrow_left,
                    startIconStyle = IconStyle(tint = AppTheme.colorScheme.iconColor),
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
                    startIconStyle = IconStyle(tint = AppTheme.colorScheme.iconColor),
                    bottomDivider = true,
                    onItemClick = {

                    })

                MenuItem(
                    title = stringResource(R.string.invite_friend),
                    startIcon = com.pmb.ballon.R.drawable.ic_happy_heart,
                    endIcon = com.pmb.ballon.R.drawable.ic_arrow_left,
                    startIconStyle = IconStyle(tint = AppTheme.colorScheme.iconColor),
                    bottomDivider = true,
                    onItemClick = {

                    })

                MenuItem(
                    title = stringResource(R.string.about_app),
                    startIcon = com.pmb.ballon.R.drawable.ic_info_circle,
                    endIcon = com.pmb.ballon.R.drawable.ic_arrow_left,
                    startIconStyle = IconStyle(tint = AppTheme.colorScheme.iconColor),
                    bottomDivider = true,
                    onItemClick = {

                    })

                MenuItem(
                    title = stringResource(R.string.comments_and_suggestions),
                    startIcon = com.pmb.ballon.R.drawable.ic_comment,
                    endIcon = com.pmb.ballon.R.drawable.ic_arrow_left,
                    startIconStyle = IconStyle(tint = AppTheme.colorScheme.iconColor),
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
                    onItemClick = {

                    })
            }

            Spacer(modifier = Modifier.height(16.dp))
        }
    }
}