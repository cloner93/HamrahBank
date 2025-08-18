package com.pmb.account.presentation.issueCard.issueCardFee

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.pmb.ballon.component.MenuItemDefaults.horizontalDividerPadding
import com.pmb.ballon.component.base.AppButton
import com.pmb.ballon.component.base.AppContent
import com.pmb.ballon.component.base.AppTopBar
import com.pmb.ballon.component.base.BodyMediumText
import com.pmb.ballon.component.base.CaptionText
import com.pmb.ballon.component.base.ClickableIcon
import com.pmb.ballon.component.base.IconType
import com.pmb.ballon.ui.theme.AppTheme
import com.pmb.core.utils.toCurrency
import com.pmb.domain.model.card.FetchCommissionForCreateCardResponse
import com.pmb.navigation.manager.LocalNavigationManager

@Composable
fun IssueCardFeeScreen(commissionFee: FetchCommissionForCreateCardResponse?) {

    val navigationManager = LocalNavigationManager.current

    AppContent(
        modifier = Modifier.padding(horizontal = 16.dp),
        backgroundColor = AppTheme.colorScheme.background3Neutral,
        footer = {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                AppButton(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 16.dp),
                    title = "تایید",
                    enable = true,
                    onClick = {
                        navigationManager.navigateBack()
                    })
            }
        },
        topBar = {
            AppTopBar(
                title = "مشاهده کارمزدها",
                startIcon = ClickableIcon(
                    icon = IconType.ImageVector(Icons.Default.ArrowForward),
                    onClick = {
                        navigationManager.navigateBack()
                    })
            )
        }) {

        Spacer(modifier = Modifier.padding(24.dp))
        Card(
            modifier = Modifier
                .fillMaxWidth(),
            shape = RoundedCornerShape(16.dp),
            colors = CardDefaults.cardColors(containerColor = AppTheme.colorScheme.background1Neutral),
            elevation = CardDefaults.cardElevation(0.dp)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                FeeRow(
                    "کارمزد اول",
                    bottomDivider = true,
                    amount = commissionFee?.commissionAmount?.toDouble() ?: 0.0
                )

            }
        }
        Spacer(modifier = Modifier.padding(16.dp))
        Card(
            modifier = Modifier
                .fillMaxWidth(),
            shape = RoundedCornerShape(16.dp),
            colors = CardDefaults.cardColors(containerColor = AppTheme.colorScheme.background1Neutral),
            elevation = CardDefaults.cardElevation(0.dp)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        BodyMediumText(
                            text = "مبلغ کل",
                            color = AppTheme.colorScheme.onBackgroundNeutralDefault
                        )

                        Row(
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            val amount = commissionFee?.totalAmount?.toDouble() ?: 0.0
                            BodyMediumText(
                                text = amount.toCurrency(),
                                color = AppTheme.colorScheme.onBackgroundNeutralDefault
                            )
                            Spacer(Modifier.width(6.dp))
                            CaptionText(
                                text = "ریال",
                                color = AppTheme.colorScheme.onBackgroundNeutralSubdued
                            )
                        }
                    }
                }
            }
        }
    }
}

@Composable
private fun FeeRow(
    title: String,
    amount: Double,
    bottomDivider: Boolean,
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            CaptionText(
                text = title,
                color = AppTheme.colorScheme.onBackgroundNeutralSubdued
            )

            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                BodyMediumText(
                    text = amount.toCurrency(),
                    color = AppTheme.colorScheme.onBackgroundNeutralDefault
                )
                Spacer(Modifier.width(6.dp))
                CaptionText(
                    text = "ریال",
                    color = AppTheme.colorScheme.onBackgroundNeutralSubdued
                )
            }
        }
        if (bottomDivider)
            HorizontalDivider(
                modifier = Modifier.padding(horizontalDividerPadding.toPaddingValues()),
                color = AppTheme.colorScheme.strokeNeutral3Devider
            )
    }

}