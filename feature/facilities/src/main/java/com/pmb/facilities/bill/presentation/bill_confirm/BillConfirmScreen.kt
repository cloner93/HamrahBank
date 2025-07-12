package com.pmb.facilities.bill.presentation.bill_confirm

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.pmb.ballon.component.TextImage
import com.pmb.ballon.component.base.AppButton
import com.pmb.ballon.component.base.AppContent
import com.pmb.ballon.component.base.AppTopBar
import com.pmb.ballon.component.base.CaptionText
import com.pmb.ballon.component.base.Headline5Text
import com.pmb.ballon.models.ImageStyle
import com.pmb.ballon.models.Size
import com.pmb.ballon.models.TextStyle
import com.pmb.ballon.ui.theme.AppTheme
import com.pmb.core.utils.toCurrency
import com.pmb.facilities.R
import com.pmb.facilities.bill.presentation.BillSharedState
import com.pmb.navigation.manager.LocalNavigationManager

@Composable
fun BillConfirmScreen(
    sharedState: State<BillSharedState>
) {
    val navigationManager = LocalNavigationManager.current
    AppContent(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.padding(8.dp),
        topBar = {
            AppTopBar(
                title = stringResource(R.string.bill_confirm),
                onBack = {
                    navigationManager.navigateBack()
                })
        }, footer = {
            AppButton(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(
                        horizontal = 16.dp,
                        vertical = 17.dp
                    ),
                title = stringResource(com.pmb.ballon.R.string.confirm),
                enable = true,
                onClick = {
                })
        }
    ) {
        TextImage(
            image = sharedState.value.billIdEntity?.billImage ?: R.drawable.ic_irancell,
            imageStyle = ImageStyle(size = Size.FIX(48.dp)),
            text = sharedState.value.billIdEntity?.billTitle ?: "",
            spacer = 0.dp,
            textStyle = TextStyle(
                color = AppTheme.colorScheme.onBackgroundNeutralDefault,
                typography = AppTheme.typography.bodyMedium,
                textAlign = TextAlign.Center
            )
        )
        Spacer(Modifier.size(12.dp))
        Headline5Text(
            text = "${sharedState.value.billIdEntity?.billPrice?.toCurrency()} ${stringResource(com.pmb.ballon.R.string.rial)}",
            color = AppTheme.colorScheme.onBackgroundNeutralDefault
        )
        CaptionText(
            text = sharedState.value.billIdEntity?.billPriceTitle ?: "",
            color = AppTheme.colorScheme.onBackgroundNeutralSubdued
        )
        Spacer(Modifier.size(40.dp))

    }
}

