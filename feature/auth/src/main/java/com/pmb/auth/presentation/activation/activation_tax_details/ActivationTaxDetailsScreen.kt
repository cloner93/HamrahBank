package com.pmb.auth.presentation.activation.activation_tax_details

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.HorizontalDivider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.pmb.auth.R
import com.pmb.auth.presentation.activation.activation_tax_details.viewModel.ActivationTaxDetailsViewActions
import com.pmb.auth.presentation.activation.activation_tax_details.viewModel.ActivationTaxDetailsViewEvents
import com.pmb.auth.presentation.activation.activation_tax_details.viewModel.ActivationTaxDetailsViewModel
import com.pmb.auth.utils.ComingType
import com.pmb.ballon.component.AlertComponent
import com.pmb.ballon.component.base.AppButton
import com.pmb.ballon.component.base.AppContent
import com.pmb.ballon.component.base.AppLoading
import com.pmb.ballon.component.base.AppNumberTextField
import com.pmb.ballon.component.base.AppTopBar
import com.pmb.ballon.component.base.BodyMediumText
import com.pmb.ballon.component.base.CaptionText
import com.pmb.ballon.ui.theme.AppTheme
import com.pmb.navigation.manager.LocalNavigationManager
import com.pmb.navigation.manager.NavigationManager

@Composable
fun ActivationTaxDetailsScreen(
    viewModel: ActivationTaxDetailsViewModel,
    onAuthenticationCallback: (ComingType) -> Unit
) {
    val navigationManager: NavigationManager = LocalNavigationManager.current
    val viewState by viewModel.viewState.collectAsState()
    var accountNumber by remember {
        mutableStateOf("")
    }
    LaunchedEffect(Unit) {
        viewModel.viewEvent.collect { event ->
            when (event) {
                ActivationTaxDetailsViewEvents.ConfirmTaxDetails -> {
                    onAuthenticationCallback.invoke(ComingType.COMING_ACTIVATION)
                }
            }
        }
    }

    AppContent(
        modifier = Modifier.padding(horizontal = 16.dp),
        backgroundColor = AppTheme.colorScheme.background2Neutral,
        topBar = {
            AppTopBar(
                title = stringResource(R.string.hamrah_activation_tax),
                onBack = {
                    navigationManager.navigateBack()
                }
            )
        },
        footer = {
            AppButton(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 16.dp, end = 16.dp, top = 16.dp),
                enable = viewState.data?.entityList?.isNotEmpty() == true && accountNumber.isNotEmpty(),
                title = stringResource(com.pmb.ballon.R.string.confirm),
                onClick = {
                    viewModel.handle(
                        ActivationTaxDetailsViewActions.SendActivationTaxDetailsData(
                            accountNumber
                        )
                    )
                })
        }
    ) {
        viewState.data?.let { feeDetailsEntity ->
            Spacer(modifier = Modifier.size(20.dp))
            Column(
                modifier = Modifier
                    .background(Color.White, RoundedCornerShape(12.dp))
                    .padding(horizontal = 10.dp)
            ) {
                feeDetailsEntity.entityList.forEachIndexed { index, feeDetail ->
                    Row(
                        modifier = Modifier.heightIn(min = 56.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        CaptionText(
                            text = feeDetail.title,
                            color = AppTheme.colorScheme.onBackgroundNeutralSubdued
                        )
                        Spacer(modifier = Modifier.weight(1f))
                        BodyMediumText(
                            text = feeDetail.getSeparatedPrice(),
                            color = AppTheme.colorScheme.onBackgroundNeutralActive
                        )
                        Spacer(modifier = Modifier.size(4.dp))
                        CaptionText(
                            text = stringResource(com.pmb.ballon.R.string.rial),
                            color = AppTheme.colorScheme.onBackgroundNeutralSubdued
                        )
                    }
                    if (index < feeDetailsEntity.entityList.size - 1) {
                        HorizontalDivider(
                            thickness = 1.dp,
                            color = AppTheme.colorScheme.background3Neutral
                        )
                    }
                }
            }
            Spacer(modifier = Modifier.size(10.dp))
            Column(
                modifier = Modifier
                    .background(Color.White, RoundedCornerShape(12.dp))
                    .padding(horizontal = 10.dp)
            ) {
                Row(
                    modifier = Modifier.heightIn(min = 56.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    BodyMediumText(
                        text = stringResource(R.string.total),
                        color = AppTheme.colorScheme.onBackgroundNeutralSubdued
                    )
                    Spacer(modifier = Modifier.weight(1f))
                    BodyMediumText(
                        text = feeDetailsEntity.getTotalPrice(),
                        color = AppTheme.colorScheme.onBackgroundNeutralActive
                    )
                    Spacer(modifier = Modifier.size(4.dp))
                    CaptionText(
                        text = stringResource(com.pmb.ballon.R.string.rial),
                        color = AppTheme.colorScheme.onBackgroundNeutralSubdued
                    )
                }
            }

            Spacer(modifier = Modifier.size(20.dp))
            AppNumberTextField(
                value = accountNumber,
                label = stringResource(R.string.tax_account_number),
                onValueChange = {
                    accountNumber = it
                })
        }
    }
    if (viewState.isLoading) {
        AppLoading()
    }
    if (viewState.alertModelState != null) {
        AlertComponent(viewState.alertModelState!!)
    }
}