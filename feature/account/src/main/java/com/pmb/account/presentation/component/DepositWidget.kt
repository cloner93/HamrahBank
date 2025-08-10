package com.pmb.account.presentation.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.outlined.MoreHoriz
import androidx.compose.material.icons.outlined.Visibility
import androidx.compose.material.icons.outlined.VisibilityOff
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.pmb.account.R
import com.pmb.ballon.component.annotation.AppPreview
import com.pmb.ballon.component.base.AppButtonIcon
import com.pmb.ballon.component.base.AppContent
import com.pmb.ballon.component.base.BodyMediumText
import com.pmb.ballon.component.base.BodySmallText
import com.pmb.ballon.component.base.CaptionText
import com.pmb.ballon.component.base.ChipWithIcon
import com.pmb.ballon.component.base.Headline2Text
import com.pmb.ballon.component.base.Headline4Text
import com.pmb.ballon.models.IconStyle
import com.pmb.ballon.models.Size
import com.pmb.ballon.ui.theme.AppTheme
import com.pmb.ballon.ui.theme.HamrahBankTheme
import com.pmb.core.utils.toCurrency
import com.pmb.domain.model.DepositModel

@Composable
fun DepositWidget(
    modifier: Modifier = Modifier,
    item: DepositModel?,
    isAmountVisible: Boolean,
    isLoading: Boolean,
    moreClick: () -> Unit,
    onAmountVisibilityClick: () -> Unit,
    onDepositListChipsClick: () -> Unit,
    onRefreshClick: () -> Unit,
) {
    Card(
        modifier = modifier
            .fillMaxWidth(),
        shape = RoundedCornerShape(24.dp),
        colors = CardDefaults.cardColors(containerColor = AppTheme.colorScheme.background1Neutral),
        elevation = CardDefaults.cardElevation(8.dp)
    ) {
        Column(
            modifier = Modifier
                .background(color = AppTheme.colorScheme.background1Neutral)
//                .loadingState(isLoading)
                .padding(12.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                AppButtonIcon(
                    icon = Icons.Outlined.MoreHoriz,
                    style = IconStyle(tint = AppTheme.colorScheme.onBackgroundNeutralDefault),
                    onClick = moreClick
                )
                AppButtonIcon(
                    icon = if (isAmountVisible) Icons.Outlined.Visibility else Icons.Outlined.VisibilityOff,
                    style = IconStyle(tint = AppTheme.colorScheme.onBackgroundNeutralDefault),
                    onClick = onAmountVisibilityClick
                )
                Spacer(modifier = Modifier.weight(1f))

                ChipWithIcon(
                    value = "سپرده ها",
                    startIcon = Icons.Default.ArrowDropDown,
                    color = AppTheme.colorScheme.backgroundTintPrimaryDefault,
                    startIconStyle = IconStyle(
                        tint = AppTheme.colorScheme.onBackgroundTintPrimaryDefault,
                        size = Size.FIX(18.dp)
                    ),
                    clickable = onDepositListChipsClick,
                    assetColor = AppTheme.colorScheme.onBackgroundTintPrimaryDefault
                )
            }
            Spacer(modifier = Modifier.height(8.dp))
            BodyMediumText(
                text = item?.depositNumber ?: "",
                color = AppTheme.colorScheme.onBackgroundNeutralDefault
            )
            Spacer(modifier = Modifier.height(8.dp))
            CaptionText(
                modifier = Modifier
                    .padding(horizontal = 8.dp),
                text = item?.title ?: "",
                color = AppTheme.colorScheme.onBackgroundNeutralDefault
            )
            Spacer(modifier = Modifier.height(8.dp))
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                if (isAmountVisible) {
                    AppButtonIcon(
                        icon = painterResource(R.drawable.ic_refresh),
                        style = IconStyle(tint = AppTheme.colorScheme.onBackgroundNeutralDefault),
                        onClick = onRefreshClick
                    )
                    Headline4Text(
                        text = item?.amount?.toCurrency() ?: "",
                        color = AppTheme.colorScheme.onBackgroundNeutralDefault
                    )
                    Spacer(modifier = Modifier.width(6.dp))
                    BodySmallText(
                        text = item?.currency ?: "",
                        color = AppTheme.colorScheme.onBackgroundNeutralDefault
                    )

                } else {
                    Headline2Text(
                        text = "********",
                        color = AppTheme.colorScheme.foregroundNeutralDefault
                    )
                }
            }
        }
    }
}

@AppPreview
@Composable
private fun DepositPrev() {
    val dip = DepositModel(
        title = "حساب قرض الحسنه",
        desc = "تنخواه",
        depositNumber = "1232324-56",
        amount = 10000023400.0,
        currency = stringResource(com.pmb.ballon.R.string.real_carrency),
        ibanNumber = "IR1234567890098765432112",
        cardNumber = "6219861920241234",
        categoryCode = 0,
    )
    HamrahBankTheme {
        AppContent {
            DepositWidget(
                item = dip,
                isAmountVisible = true,
                isLoading = true,
                moreClick = {},
                onAmountVisibilityClick = { },
                onDepositListChipsClick = {}
            ) { }
            DepositWidget(
                item = null,
                isAmountVisible = true,
                isLoading = true,
                moreClick = {},
                onAmountVisibilityClick = { },
                onDepositListChipsClick = {}
            ) { }
            DepositWidget(
                item = null,
                isAmountVisible = true,
                isLoading = false,
                moreClick = {},
                onAmountVisibilityClick = { },
                onDepositListChipsClick = {}
            ) { }
        }
    }
}
