package com.pmb.profile.presentaion.personal_infos.personal_info

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.pmb.ballon.component.AlertComponent
import com.pmb.ballon.component.MenuItem
import com.pmb.ballon.component.base.AppContent
import com.pmb.ballon.component.base.AppLoading
import com.pmb.ballon.component.base.AppTopBar
import com.pmb.ballon.component.base.CaptionText
import com.pmb.ballon.models.IconStyle
import com.pmb.ballon.models.TextStyle
import com.pmb.ballon.ui.theme.AppTheme
import com.pmb.navigation.manager.LocalNavigationManager
import com.pmb.navigation.moduleScreen.ProfileScreens
import com.pmb.profile.R
import com.pmb.profile.domain.entity.PersonalInfoEntity
import com.pmb.profile.presentaion.personal_infos.PersonalInfoSharedState
import com.pmb.profile.presentaion.personal_infos.personal_info.viewmodel.PersonalInfoViewActions
import com.pmb.profile.presentaion.personal_infos.personal_info.viewmodel.PersonalInfoViewEvents
import com.pmb.profile.presentaion.personal_infos.personal_info.viewmodel.PersonalInfoViewModel

@Composable
fun PersonalInfoScreen(
    viewModel: PersonalInfoViewModel,
    sharedState: PersonalInfoSharedState,
    result: (PersonalInfoEntity) -> Unit
) {
    val navigationManager = LocalNavigationManager.current
    val viewState by viewModel.viewState.collectAsState()
    LaunchedEffect(Unit) {
        viewModel.handle(PersonalInfoViewActions.UpdateShareState(sharedState))
    }

    LaunchedEffect(Unit) {
        viewModel.viewEvent.collect { event ->
            when (event) {
                PersonalInfoViewEvents.NavigateToChangeUsername -> {
                    result.invoke(viewState.personalInfo)
                    navigationManager.navigate(ProfileScreens.PersonalInfo.ChangeUsername)
                }

                PersonalInfoViewEvents.NavigateToChangePhoneNumber -> {
                    result.invoke(viewState.personalInfo)
                    navigationManager.navigate(ProfileScreens.PersonalInfo.ChangePhoneNumber)
                }

                PersonalInfoViewEvents.NavigateToChangeAddress -> {
                    result.invoke(viewState.personalInfo)
                    navigationManager.navigate(ProfileScreens.PersonalInfo.ChangeAddress)
                }

                PersonalInfoViewEvents.NavigateToChangeEducation -> {
                    result.invoke(viewState.personalInfo)
                    navigationManager.navigate(ProfileScreens.PersonalInfo.ChangeEducation)
                }


                PersonalInfoViewEvents.NavigateToChangeJob -> {
                    result.invoke(viewState.personalInfo)
                    navigationManager.navigate(ProfileScreens.PersonalInfo.ChangeJob)
                }
            }
        }
    }

    AppContent(
        modifier = Modifier.padding(horizontal = 16.dp),
        backgroundColor = AppTheme.colorScheme.background3Neutral,
        topBar = {
            AppTopBar(
                title = stringResource(R.string.user_info),
                onBack = { navigationManager.navigateBack() })
        },
        content = {
            if (viewState.showPersonalInfo) ProfileInfoItemsComponent(viewModel)
        }
    )

    if (viewState.loading) AppLoading()
    viewState.alertState?.let { AlertComponent(it) }
}

@Composable
private fun ProfileInfoItemsComponent(viewModel: PersonalInfoViewModel) {
    val viewState by viewModel.viewState.collectAsState()
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 24.dp),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = AppTheme.colorScheme.background1Neutral),
        content = {
            MenuItem(
                horizontalPadding = 16.dp,
                title = stringResource(R.string.change_username),
                startIcon = com.pmb.ballon.R.drawable.ic_edit_username,
                endIcon = com.pmb.ballon.R.drawable.ic_arrow_left,
                bottomDivider = true,
                titleStyle = TextStyle(color = AppTheme.colorScheme.foregroundNeutralDefault),
                startIconStyle = IconStyle(tint = AppTheme.colorScheme.onBackgroundPrimaryCTA),
                endContent = {
                    CaptionText(
                        text = viewState.personalInfo.safeUsername,
                        color = AppTheme.colorScheme.onBackgroundNeutralSubdued
                    )
                },
                onItemClick = {
                    viewModel.handle(PersonalInfoViewActions.ChangeUsername)
                })

            MenuItem(
                horizontalPadding = 16.dp,
                title = stringResource(R.string.change_phone_number),
                startIcon = com.pmb.ballon.R.drawable.ic_mobile,
                endIcon = com.pmb.ballon.R.drawable.ic_arrow_left,
                bottomDivider = true,
                titleStyle = TextStyle(color = AppTheme.colorScheme.foregroundNeutralDefault),
                startIconStyle = IconStyle(tint = AppTheme.colorScheme.onBackgroundPrimaryCTA),
                endContent = {
                    CaptionText(
                        text = viewState.personalInfo.safePhoneNumber,
                        color = AppTheme.colorScheme.onBackgroundNeutralSubdued
                    )
                },
                onItemClick = {
                    viewModel.handle(PersonalInfoViewActions.ChangePhoneNumber)
                })

            MenuItem(
                horizontalPadding = 16.dp,
                title = stringResource(R.string.change_address),
                startIcon = com.pmb.ballon.R.drawable.ic_address,
                endIcon = com.pmb.ballon.R.drawable.ic_arrow_left,
                bottomDivider = true,
                titleStyle = TextStyle(color = AppTheme.colorScheme.foregroundNeutralDefault),
                startIconStyle = IconStyle(tint = AppTheme.colorScheme.onBackgroundPrimaryCTA),
                endContent = {
                    CaptionText(
                        text = viewState.personalInfo.safeAddress,
                        color = AppTheme.colorScheme.onBackgroundNeutralSubdued
                    )
                },
                onItemClick = {
                    viewModel.handle(PersonalInfoViewActions.ChangeAddress)
                })

            MenuItem(
                horizontalPadding = 16.dp,
                title = stringResource(R.string.change_job),
                startIcon = com.pmb.ballon.R.drawable.ic_job,
                endIcon = com.pmb.ballon.R.drawable.ic_arrow_left,
                bottomDivider = true,
                titleStyle = TextStyle(color = AppTheme.colorScheme.foregroundNeutralDefault),
                startIconStyle = IconStyle(tint = AppTheme.colorScheme.onBackgroundPrimaryCTA),
                endContent = {
                    CaptionText(
                        text = viewState.personalInfo.safeJob,
                        color = AppTheme.colorScheme.onBackgroundNeutralSubdued
                    )
                },
                onItemClick = {
                    viewModel.handle(PersonalInfoViewActions.ChangeJob)
                })
            MenuItem(
                horizontalPadding = 16.dp,
                title = stringResource(R.string.change_education),
                startIcon = com.pmb.ballon.R.drawable.ic_education,
                endIcon = com.pmb.ballon.R.drawable.ic_arrow_left,
                bottomDivider = false,
                titleStyle = TextStyle(color = AppTheme.colorScheme.foregroundNeutralDefault),
                startIconStyle = IconStyle(tint = AppTheme.colorScheme.onBackgroundPrimaryCTA),
                endContent = {
                    CaptionText(
                        text = viewState.personalInfo.safeEducation,
                        color = AppTheme.colorScheme.onBackgroundNeutralSubdued
                    )
                },
                onItemClick = {
                    viewModel.handle(PersonalInfoViewActions.ChangeEducation)
                })
        }
    )
}