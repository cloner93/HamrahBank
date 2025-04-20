package com.pmb.auth.presentation.scan_card_info.card_confirm

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.HorizontalDivider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.pmb.auth.R
import com.pmb.auth.presentation.scan_card_info.card_confirm.viewModel.CardInformationConfirmViewEvents
import com.pmb.auth.presentation.scan_card_info.card_confirm.viewModel.CardInformationConfirmViewModel
import com.pmb.ballon.component.base.AppButton
import com.pmb.ballon.component.base.AppContent
import com.pmb.ballon.component.base.AppTopBar
import com.pmb.ballon.component.base.BodySmallText
import com.pmb.ballon.component.base.CaptionText
import com.pmb.ballon.ui.theme.AppTheme
import com.pmb.navigation.manager.LocalNavigationManager
import com.pmb.navigation.manager.NavigationManager
import com.pmb.navigation.moduleScreen.HomeScreens

@Composable
fun CardInformationConfirmScreen(
    viewModel: CardInformationConfirmViewModel
) {
    val navigationManager: NavigationManager = LocalNavigationManager.current
    val viewState by viewModel.viewState.collectAsState()
    LaunchedEffect(Unit) {
        viewModel.viewEvent.collect { event ->
            when (event) {
                CardInformationConfirmViewEvents.SendCardInformationConfirmation -> {
                    navigationManager.navigate(HomeScreens.Home)
                }
            }
        }
    }
    AppContent(
        modifier = Modifier.padding(horizontal = 16.dp),
        backgroundColor = AppTheme.colorScheme.background3Neutral,
        topBar = {
            AppTopBar(
                title = stringResource(R.string.bank_card_information),
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
                enable = !viewState.isLoading,
                title = stringResource(R.string._continue),
                onClick = {
                })
        }
    ) {
        Spacer(modifier = Modifier.size(16.dp))
        viewState.data?.let {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(
                        AppTheme.colorScheme.onForegroundNeutralDefault,
                        RoundedCornerShape(12.dp)
                    )
                    .padding(4.dp)
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(12.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {

                    CaptionText(
                        text = stringResource(com.pmb.ballon.R.string.name),
                        color = AppTheme.colorScheme.onBackgroundNeutralDefault,
                        modifier = Modifier
                            .weight(1f)
                            .padding(horizontal = 12.dp),
                    )
                    BodySmallText(
                        text = it.cardOwnerName,
                        color = AppTheme.colorScheme.onBackgroundNeutralDefault,
                        modifier = Modifier.padding(horizontal = 12.dp),
                    )
                }
                HorizontalDivider(
                    color = AppTheme.colorScheme.backgroundTintNeutralDefault, thickness = 1.dp,
                    modifier = Modifier.fillMaxWidth()
                )
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(12.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {

                    CaptionText(
                        text = stringResource(com.pmb.ballon.R.string.family),
                        color = AppTheme.colorScheme.onBackgroundNeutralDefault,
                        modifier = Modifier
                            .weight(1f)
                            .padding(horizontal = 12.dp),
                    )
                    BodySmallText(
                        text = it.cardOwnerFamily,
                        color = AppTheme.colorScheme.onBackgroundNeutralDefault,
                        modifier = Modifier.padding(horizontal = 12.dp),
                    )
                }
                HorizontalDivider(
                    color = AppTheme.colorScheme.backgroundTintNeutralDefault, thickness = 1.dp,
                    modifier = Modifier.fillMaxWidth()
                )
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(12.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {

                    CaptionText(
                        text = stringResource(com.pmb.ballon.R.string.card_number),
                        color = AppTheme.colorScheme.onBackgroundNeutralDefault,
                        modifier = Modifier
                            .weight(1f)
                            .padding(horizontal = 12.dp),
                    )
                    BodySmallText(
                        text = it.cardNumber,
                        color = AppTheme.colorScheme.onBackgroundNeutralDefault,
                        modifier = Modifier.padding(horizontal = 12.dp),
                    )
                }
                HorizontalDivider(
                    color = AppTheme.colorScheme.backgroundTintNeutralDefault, thickness = 1.dp,
                    modifier = Modifier.fillMaxWidth()
                )
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(12.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {

                    CaptionText(
                        text = stringResource(com.pmb.ballon.R.string.cvv2),
                        color = AppTheme.colorScheme.onBackgroundNeutralDefault,
                        modifier = Modifier
                            .weight(1f)
                            .padding(horizontal = 12.dp),
                    )
                    BodySmallText(
                        text = it.cvv2,
                        color = AppTheme.colorScheme.onBackgroundNeutralDefault,
                        modifier = Modifier.padding(horizontal = 12.dp),
                    )
                }
                HorizontalDivider(
                    color = AppTheme.colorScheme.backgroundTintNeutralDefault, thickness = 1.dp,
                    modifier = Modifier.fillMaxWidth()
                )
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(12.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {

                    CaptionText(
                        text = stringResource(com.pmb.ballon.R.string.year_month),
                        color = AppTheme.colorScheme.onBackgroundNeutralDefault,
                        modifier = Modifier
                            .weight(1f)
                            .padding(horizontal = 12.dp),
                    )
                    BodySmallText(
                        text = it.expirationDate,
                        color = AppTheme.colorScheme.onBackgroundNeutralDefault,
                        modifier = Modifier.padding(horizontal = 12.dp),
                    )
                }
            }
        }

    }
}