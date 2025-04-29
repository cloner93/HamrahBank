package com.pmb.profile.presentaion.personal_infos.change_address

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.pmb.ballon.component.AlertComponent
import com.pmb.ballon.component.base.AppButton
import com.pmb.ballon.component.base.AppContent
import com.pmb.ballon.component.base.AppLoading
import com.pmb.ballon.component.base.AppNumberTextField
import com.pmb.ballon.component.base.AppTopBar
import com.pmb.ballon.component.base.BodyMediumText
import com.pmb.ballon.models.TextStyle
import com.pmb.ballon.ui.theme.AppTheme
import com.pmb.navigation.manager.LocalNavigationManager
import com.pmb.profile.R
import com.pmb.profile.domain.entity.AddressEntity
import com.pmb.profile.presentaion.personal_infos.PersonalInfoSharedState
import com.pmb.profile.presentaion.personal_infos.change_address.viewmodel.ChangeAddressViewActions
import com.pmb.profile.presentaion.personal_infos.change_address.viewmodel.ChangeAddressViewEvents
import com.pmb.profile.presentaion.personal_infos.change_address.viewmodel.ChangeAddressViewModel


@Composable
fun ChangeAddressScreen(
    viewModel: ChangeAddressViewModel,
    shareState: PersonalInfoSharedState,
    result: (AddressEntity) -> Unit
) {
    val navigationManager = LocalNavigationManager.current
    val viewState by viewModel.viewState.collectAsStateWithLifecycle()

    LaunchedEffect(Unit) {
        viewModel.viewEvent.collect { event ->
            when (event) {
                is ChangeAddressViewEvents.NavigateBackToPersonalInfo -> {
                    result.invoke(event.addressEntity)
                    navigationManager.navigateBack()
                }
            }
        }
    }
    LaunchedEffect(Unit) {
        viewModel.handle(ChangeAddressViewActions.UpdateShareState(shareState))
    }

    AppContent(
        modifier = Modifier
            .padding(horizontal = 16.dp)
            .padding(top = 24.dp),
        topBar = {
            AppTopBar(
                title = stringResource(R.string.change_address),
                onBack = {
                    navigationManager.navigateBack()
                })
        },
        footer = {
            AppButton(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
                enable = viewState.enableSubmitButton,
                title = stringResource(R.string._continue),
                onClick = {
                    viewModel.handle(ChangeAddressViewActions.SubmitAddress)
                })
        },
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        AppNumberTextField(
            modifier = Modifier.fillMaxWidth(),
            value = viewState.postalCode,
            label = stringResource(R.string.postal_code),
            showClearButton = false,
            trailingIcon = {
                AppButton(
                    modifier = Modifier
                        .wrapContentWidth()
                        .padding(all = 8.dp),
                    textStyle = TextStyle.defaultButton()
                        .copy(typography = AppTheme.typography.buttonSmall),
                    enable = viewState.enablePostalCodeButton,
                    title = stringResource(R.string.inquiry),
                    onClick = {
                        viewModel.handle(ChangeAddressViewActions.SubmitPostalCode)
                    })
            },
            onValueChange = {
                viewModel.handle(ChangeAddressViewActions.ChangeAddress(it))
            },
        )
        Spacer(modifier = Modifier.size(32.dp))

        val label = stringResource(
            if (viewState.newInquiry) R.string.new_address_dot else R.string.current_address_dot
        )

        BodyMediumText(
            text = "$label ${viewState.address}",
            color = if (viewState.newInquiry) AppTheme.colorScheme.onBackgroundNeutralCTA
            else AppTheme.colorScheme.onBackgroundNeutralDefault,
            textAlign = TextAlign.Center
        )
    }



    if (viewState.loading) AppLoading()
    viewState.alertState?.let { AlertComponent(it) }
}