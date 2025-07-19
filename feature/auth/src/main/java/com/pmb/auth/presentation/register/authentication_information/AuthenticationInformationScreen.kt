package com.pmb.auth.presentation.register.authentication_information

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
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
import com.pmb.ballon.component.AlertComponent
import com.pmb.ballon.component.CustomSearchSpinner
import com.pmb.ballon.component.base.AppButton
import com.pmb.ballon.component.base.AppButtonIcon
import com.pmb.ballon.component.base.AppClickableReadOnlyTextField
import com.pmb.ballon.component.base.AppContent
import com.pmb.ballon.component.base.AppLoading
import com.pmb.ballon.component.base.AppMobileTextField
import com.pmb.ballon.component.base.AppSingleTextField
import com.pmb.ballon.component.base.AppTopBar
import com.pmb.ballon.component.base.IconType
import com.pmb.ballon.component.datePicker.ShowPersianDatePickerBottomSheet
import com.pmb.calender.Jdn
import com.pmb.navigation.manager.LocalNavigationManager
import com.pmb.navigation.manager.NavigationManager
import com.pmb.navigation.moduleScreen.RegisterScreens

@Composable
fun AuthenticationInformationScreen(
    viewModel: AuthenticationInformationViewModel,
    sharedState: State<RegisterSharedViewState>,
) {
    val navigationManager: NavigationManager = LocalNavigationManager.current
    val viewState by viewModel.viewState.collectAsState()
    var city by remember { mutableStateOf<String>("") }
    var identifyPlace by remember { mutableStateOf<String>("") }
    var showBirthdayPicker by remember { mutableStateOf(false) }
    var identifyArea by remember { mutableStateOf("") }
    var mobileNumber by remember { mutableStateOf("") }
    var education by remember { mutableStateOf("") }
    LaunchedEffect(Unit) {
        viewModel.viewEvent.collect { event ->
            when (event) {
                AuthenticationInformationViewEvents.SendAuthenticationInformationViewSucceed -> {
                    navigationManager.navigate(RegisterScreens.JobInformation)
                }
            }
        }
    }
    AppContent(
        modifier = Modifier.padding(horizontal = 16.dp),
        topBar = {
            AppTopBar(
                title = stringResource(R.string.authentication_information),
                onBack = {
                    navigationManager.navigateBack()
                }
            )
        }, footer = {

            AppButton(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 16.dp, end = 16.dp, top = 16.dp),
                enable = true,
                title = stringResource(R.string._continue),
                onClick = {
                    viewModel.handle(
                        AuthenticationInformationViewActions.SendAuthenticationParams(
                            cityId = viewState.sendAuthenticationInformationParam?.cityId ?: -1,
                            identifyId = viewState.sendAuthenticationInformationParam?.identifyId
                                ?: -1,
                            birthDate = viewState.sendAuthenticationInformationParam?.birthDate
                                ?: "",
                            identifyArea = identifyArea,
                            phoneNumber = mobileNumber,
                            educationId = viewState.sendAuthenticationInformationParam?.educationId
                                ?: -1
                        )
                    )
                })
        }) {
        Spacer(modifier = Modifier.size(24.dp))
        viewState.authenticationInformation?.let {
            CustomSearchSpinner(
                modifier = Modifier
                    .fillMaxWidth(),
                options = viewState.authenticationInformation?.cities?.map { it.city },
                labelString = stringResource(R.string.birthday_place),
                displayText = city,
                isEnabled = true,
                onSearchValue = {
                    city = it
                }
            ) { type ->
                viewModel.handle(AuthenticationInformationViewActions.SetCityId(city))
            }
            Spacer(modifier = Modifier.size(24.dp))
            CustomSearchSpinner(
                modifier = Modifier
                    .fillMaxWidth(),
                options = viewState.authenticationInformation?.identifyPlace?.map { it.city },
                labelString = stringResource(R.string.identify_place),
                displayText = identifyPlace,
                isEnabled = true,
                onSearchValue = {
                    identifyPlace = it
                }
            ) { type ->
                viewModel.handle(
                    AuthenticationInformationViewActions.SetIdentifyPlaceId(
                        identifyPlace
                    )
                )
            }
            Spacer(modifier = Modifier.size(24.dp))
            AppClickableReadOnlyTextField(
                value = viewState.sendAuthenticationInformationParam?.birthDate ?: "",
                label = stringResource(R.string.identify_day),
                trailingIcon = {
                    AppButtonIcon(
                        icon = IconType.Painter(painterResource(com.pmb.ballon.R.drawable.ic_calendar_month)),
                        onClick = {}
                    )
                },
                onClick = {
                    println("clicked on birthday")
                    showBirthdayPicker = true
                })
            Spacer(modifier = Modifier.size(24.dp))
            AppSingleTextField(
                modifier = Modifier.fillMaxWidth(),
                value = identifyArea,
                label = stringResource(R.string.username),
                onValueChange = { identifyArea = it },
            )
            Spacer(modifier = Modifier.size(24.dp))
            AppMobileTextField(
                value = mobileNumber,
                label = stringResource(R.string.phone_number),
                onValueChange = {
                    mobileNumber = it
                })
            Spacer(modifier = Modifier.size(24.dp))
            CustomSearchSpinner(
                modifier = Modifier
                    .fillMaxWidth(),
                options = viewState.authenticationInformation?.educations?.map { it.education },
                labelString = stringResource(R.string.education),
                displayText = education,
                isEnabled = true,
                onSearchValue = {
                    education = it
                }
            ) { type ->
                viewModel.handle(AuthenticationInformationViewActions.SetEducation(education))
            }
        }
        if (showBirthdayPicker) {
            ShowPersianDatePickerBottomSheet(
                title = stringResource(R.string.birthday),
                defaultDate = Jdn.today(), // TODO: fix it.
                onDismiss = { showBirthdayPicker = false },
                onChangeValue = { year, month, day ->
                    viewModel.handle(AuthenticationInformationViewActions.SetIdentifyDay("$year/$month/$day"))
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

