package com.pmb.auth.presentation.register.authentication_information

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.pmb.auth.R
import com.pmb.auth.presentation.register.RegisterSharedViewState
import com.pmb.auth.presentation.register.authentication_information.viewModel.AuthenticationInformationViewActions
import com.pmb.auth.presentation.register.authentication_information.viewModel.AuthenticationInformationViewEvents
import com.pmb.auth.presentation.register.authentication_information.viewModel.AuthenticationInformationViewModel
import com.pmb.auth.presentation.register.authentication_information.viewModel.AuthenticationInformationViewState
import com.pmb.ballon.component.AlertComponent
import com.pmb.ballon.component.CustomSearchSpinner
import com.pmb.ballon.component.base.AppButton
import com.pmb.ballon.component.base.AppButtonIcon
import com.pmb.ballon.component.base.AppClickableReadOnlyTextField
import com.pmb.ballon.component.base.AppContent
import com.pmb.ballon.component.base.AppLoading
import com.pmb.ballon.component.base.AppMobileTextField
import com.pmb.ballon.component.base.AppNumberTextField
import com.pmb.ballon.component.base.AppTopBar
import com.pmb.ballon.component.base.IconType
import com.pmb.ballon.component.datePicker.ShowPersianDatePickerBottomSheet
import com.pmb.ballon.ui.theme.AppTheme
import com.pmb.calender.Jdn
import com.pmb.calender.longToString
import com.pmb.calender.utils.Calendar
import com.pmb.navigation.manager.LocalNavigationManager
import com.pmb.navigation.manager.NavigationManager
import com.pmb.navigation.moduleScreen.RegisterScreens
import kotlinx.coroutines.launch

@Composable
fun AuthenticationInformationScreen(
    viewModel: AuthenticationInformationViewModel,
    sharedState: State<RegisterSharedViewState>,
    updateState: (AuthenticationInformationViewState) -> Unit
) {
    val navigationManager: NavigationManager = LocalNavigationManager.current
    val viewState by viewModel.viewState.collectAsState()
    val coroutineContext = rememberCoroutineScope()
    var city by remember {
        mutableStateOf(
            sharedState.value.birthCityCode?.takeIf { it > 0 }
                ?.let { birthCityCode ->
                    sharedState.value.verifyCodeResponse?.cityOfBirthInfoDTOList?.find { it.cityCode == birthCityCode }?.cityName
                        ?: sharedState.value.verifyCodeResponse?.birthCityName ?: ""
                }
                ?: run {
                    sharedState.value.verifyCodeResponse?.birthCityName ?: ""
                }

        )
    }
    var identifyPlace by remember {
        mutableStateOf(
            sharedState.value.issueCityCode?.takeIf { it > 0 }?.let { issueCityCode ->
                sharedState.value.verifyCodeResponse?.cityOfBirthInfoDTOList?.find { it.cityCode == issueCityCode }?.cityName
                    ?: sharedState.value.verifyCodeResponse?.birthCityName ?: ""
            } ?: run {
                sharedState.value.verifyCodeResponse?.issueCityName ?: ""
            }
        )
    }
    var showBirthdayPicker by remember { mutableStateOf(false) }
    var identifyArea by remember {
        mutableStateOf(
            sharedState.value.issueRgnCode?.takeIf { it > 0 }?.toString() ?: run {
                sharedState.value.verifyCodeResponse?.issueReginCode?.takeIf { it > 0 }?.toString()
                    ?: ""
            }
        )
    }
    var tel by remember {
        mutableStateOf(sharedState.value.tel?.takeIf { it.isNotEmpty() }
            ?: run { sharedState.value.verifyCodeResponse?.tel?.takeIf { it.isNotEmpty() } ?: "" })
    }
    var education by remember {
        mutableStateOf(viewModel.getEducation(sharedState.value.education?.takeIf { it > 0 }
            ?: 0)?.education ?: run {
            viewModel.getEducation(sharedState.value.verifyCodeResponse?.education?.takeIf { it > 0 }
                ?: 0)?.education ?: ""
        })
    }
    LaunchedEffect(Unit) {
        viewModel.viewEvent.collect { event ->
            when (event) {
                AuthenticationInformationViewEvents.SendAuthenticationInformationViewSucceed -> {
                    navigationManager.navigate(RegisterScreens.JobInformation)
                }
            }
        }
    }


    LaunchedEffect(sharedState.value.issuePlaceCity) {
        if (sharedState.value.issuePlaceCity != null) {
            identifyPlace = sharedState.value.issuePlaceCity?.cityName ?: ""
            viewModel.handle(AuthenticationInformationViewActions.SetIdentifyPlaceId(sharedState.value.issuePlaceCity!!))
        }
    }
    LaunchedEffect(sharedState.value.birthDatePlaceCity) {
        if (sharedState.value.birthDatePlaceCity != null) {
            city = sharedState.value.birthDatePlaceCity?.cityName ?: ""
            viewModel.handle(AuthenticationInformationViewActions.SetIdentifyPlaceId(sharedState.value.birthDatePlaceCity!!))
        }
    }

    AppContent(modifier = Modifier.padding(horizontal = 16.dp), topBar = {
        AppTopBar(
            title = stringResource(R.string.authentication_information), onBack = {
                navigationManager.navigateBack()
            })
    }, footer = {

        AppButton(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 16.dp, end = 16.dp, top = 16.dp),
            enable = identifyArea.isNotEmpty() && tel.isNotEmpty() && education.isNotEmpty(),
            title = stringResource(R.string._continue),
            onClick = {
                viewModel.handle(
                    AuthenticationInformationViewActions.SetAuthenticationData(
                        sharedState.value
                    )
                )
                coroutineContext.launch {
                    updateState(viewState)
                    navigationManager.navigate(RegisterScreens.JobInformation)
                }
            })
    }) {
        Spacer(modifier = Modifier.size(24.dp))
        AppClickableReadOnlyTextField(
            onClick = {
                navigationManager.navigate(RegisterScreens.SelectBirthDatePlace)
            },
            value = city,
            label = stringResource(R.string.birthday_place),
            trailingIcon = {
                Icon(
                    imageVector = Icons.Default.KeyboardArrowDown,
                    contentDescription = "down Icon",
                    tint = AppTheme.colorScheme.onBackgroundNeutralDefault
                )
            },
        )
//        CustomSearchSpinner(
//            modifier = Modifier.fillMaxWidth(),
//            options = sharedState.value.verifyCodeResponse?.cityOfBirthInfoDTOList?.map {
//                it.cityName ?: ""
//            },
//            labelString = stringResource(R.string.birthday_place),
//            displayText = city,
//            isEnabled = true,
//            onSearchValue = {
//                city = it
//            }) { type ->
//            val city =
//                sharedState.value.verifyCodeResponse?.cityOfBirthInfoDTOList?.find { it.cityName == city }
//            city?.let { viewModel.handle(AuthenticationInformationViewActions.SetCityId(it)) }
//        }
        Spacer(modifier = Modifier.size(24.dp))
        AppClickableReadOnlyTextField(
            onClick = {
                navigationManager.navigate(RegisterScreens.SelectIssuePlace)
            },
            value = identifyPlace,
            label = stringResource(R.string.identify_place),
            trailingIcon = {
                Icon(
                    imageVector = Icons.Default.KeyboardArrowDown,
                    contentDescription = "down Icon",
                    tint = AppTheme.colorScheme.onBackgroundNeutralDefault
                )
            },
        )
//        CustomSearchSpinner(
//            modifier = Modifier.fillMaxWidth(),
//            options = sharedState.value.verifyCodeResponse?.cityOfBirthInfoDTOList?.map {
//                it.cityName ?: ""
//            },
//            labelString = stringResource(R.string.identify_place),
//            displayText = identifyPlace,
//            isEnabled = true,
//            onSearchValue = {
//                identifyPlace = it
//            }) { type ->
//            val city =
//                sharedState.value.verifyCodeResponse?.cityOfBirthInfoDTOList?.find { it.cityName == city }
//            city?.let {
//                viewModel.handle(
//                    AuthenticationInformationViewActions.SetIdentifyPlaceId(
//                        it
//                    )
//                )
//            }
//        }
        Spacer(modifier = Modifier.size(24.dp))
        AppClickableReadOnlyTextField(
            value = viewState.issueDateYear?.let { "${it}/${viewState.issueDateMonth}/${viewState.issueDateDay}" }
                ?: run { sharedState.value.verifyCodeResponse?.issueDate?.takeIf { it > 0 } }?.toLong()
                    ?.longToString()?.let { "${it.first}/${it.second}/${it.third}" } ?: "",
            label = stringResource(R.string.identify_day),
            trailingIcon = {
                AppButtonIcon(
                    icon = IconType.Painter(painterResource(com.pmb.ballon.R.drawable.ic_calendar_month)),
                    onClick = {})
            },
            onClick = {
                println("clicked on birthday")
                showBirthdayPicker = true
            })
        Spacer(modifier = Modifier.size(24.dp))
        AppNumberTextField(
            modifier = Modifier.fillMaxWidth(),
            value = identifyArea,
            label = stringResource(R.string.issue_region),
            onValueChange = { identifyArea = it },
        )
        Spacer(modifier = Modifier.size(24.dp))
        AppMobileTextField(value = tel, label = stringResource(R.string.tel), onValueChange = {
            tel = it
            viewModel.handle(AuthenticationInformationViewActions.SetPhoneNumber(it))
        })
        Spacer(modifier = Modifier.size(24.dp))
        CustomSearchSpinner(
            modifier = Modifier.fillMaxWidth(),
            options = viewModel.getEducationList().map { it.education },
            labelString = stringResource(R.string.education),
            displayText = education,
            isEnabled = true,
            onSearchValue = {
                education = it
            }) { type ->
            val education = viewModel.getEducationList().find { it.education == type }
            education?.let { viewModel.handle(AuthenticationInformationViewActions.SetEducation(it)) }
        }
        if (showBirthdayPicker) {
            ShowPersianDatePickerBottomSheet(
                title = stringResource(R.string.birthday),
                defaultDate = sharedState.value.verifyCodeResponse?.issueDate?.toLong()
                    ?.longToString()?.let {
                        Jdn(
                            Calendar.SHAMSI,
                            it.first.toInt(),
                            it.second.toInt() ?: 1,
                            it.third.toInt() ?: 1
                        )
                    } ?: run { Jdn.today() }, // TODO: fix it.
                onDismiss = { showBirthdayPicker = false },
                onChangeValue = { year, month, day ->
                    viewModel.handle(
                        AuthenticationInformationViewActions.SetIssueDate(
                            year, month, day
                        )
                    )
                    showBirthdayPicker = false
                },
            )
        }
    }
    if (viewState.isLoading) {
        AppLoading()
    }
    if (viewState.alertModelState != null) {
        AlertComponent(viewState.alertModelState!!)
    }

}

