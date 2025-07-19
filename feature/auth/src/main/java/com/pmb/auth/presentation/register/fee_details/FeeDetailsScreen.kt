package com.pmb.auth.presentation.register.fee_details

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
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.pmb.auth.R
import com.pmb.auth.presentation.register.fee_details.viewModel.FeeDetailsViewModel
import com.pmb.ballon.component.AlertComponent
import com.pmb.ballon.component.base.AppButton
import com.pmb.ballon.component.base.AppContent
import com.pmb.ballon.component.base.AppLoading
import com.pmb.ballon.component.base.AppTopBar
import com.pmb.ballon.component.base.BodyMediumText
import com.pmb.ballon.component.base.CaptionText
import com.pmb.ballon.ui.theme.AppTheme
import com.pmb.navigation.manager.LocalNavigationManager
import com.pmb.navigation.manager.NavigationManager

@Composable
fun FeeDetailsScreen(
    viewModel: FeeDetailsViewModel
) {
    val viewState by viewModel.viewState.collectAsState()
    val navigationManager: NavigationManager = LocalNavigationManager.current
    AppContent(
        modifier = Modifier.padding(horizontal = 16.dp),
        backgroundColor = AppTheme.colorScheme.background2Neutral,
        topBar = {
            AppTopBar(
                title = stringResource(R.string.fee_details),
                onBack = {
                    navigationManager.navigateBack()
                }
            )
        },
        footer = {
            AppButton(modifier = Modifier
                .fillMaxWidth()
                .padding(start = 16.dp, end = 16.dp, top = 16.dp),
                enable = viewState.data?.entityList?.isNotEmpty() == true,
                title = stringResource(com.pmb.ballon.R.string.confirm),
                onClick = {
                    navigationManager.navigateBack()
                })
        }
    ) {
        viewState.data?.let { feeDetailsEntity ->
            Spacer(modifier = Modifier.size(20.dp))
            Column(
                modifier = Modifier
                    .background(AppTheme.colorScheme.background1Neutral, RoundedCornerShape(12.dp))
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
                            color = AppTheme.colorScheme.onBackgroundNeutralDefault
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
                            color = AppTheme.colorScheme.strokeNeutral3Devider
                        )
                    }
                }
            }
            Spacer(modifier = Modifier.size(10.dp))
            Column(
                modifier = Modifier
                    .background(AppTheme.colorScheme.background1Neutral, RoundedCornerShape(12.dp))
                    .padding(horizontal = 10.dp)
            ) {
                Row(
                    modifier = Modifier.heightIn(min = 56.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    BodyMediumText(
                        text = stringResource(R.string.total),
                        color = AppTheme.colorScheme.foregroundNeutralDefault
                    )
                    Spacer(modifier = Modifier.weight(1f))
                    BodyMediumText(
                        text = feeDetailsEntity.getTotalPrice(),
                        color = AppTheme.colorScheme.onBackgroundNeutralDefault
                    )
                    Spacer(modifier = Modifier.size(4.dp))
                    CaptionText(
                        text = stringResource(com.pmb.ballon.R.string.rial),
                        color = AppTheme.colorScheme.onBackgroundNeutralSubdued
                    )
                }
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