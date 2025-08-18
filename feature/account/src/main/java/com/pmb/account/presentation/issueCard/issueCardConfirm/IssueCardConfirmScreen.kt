package com.pmb.account.presentation.issueCard.issueCardConfirm

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.pmb.account.presentation.issueCard.IssueCardSharedState
import com.pmb.account.presentation.issueCard.issueCardConfirm.viewmodel.IssueCardConfirmViewActions
import com.pmb.account.presentation.issueCard.issueCardConfirm.viewmodel.IssueCardConfirmViewEvents
import com.pmb.account.presentation.issueCard.issueCardConfirm.viewmodel.IssueCardConfirmViewModel
import com.pmb.ballon.component.AlertComponent
import com.pmb.ballon.component.MenuItemDefaults.horizontalDividerPadding
import com.pmb.ballon.component.base.AppButton
import com.pmb.ballon.component.base.AppContent
import com.pmb.ballon.component.base.AppLoading
import com.pmb.ballon.component.base.AppTopBar
import com.pmb.ballon.component.base.BodyMediumText
import com.pmb.ballon.component.base.CaptionText
import com.pmb.ballon.component.base.ClickableIcon
import com.pmb.ballon.component.base.IconType
import com.pmb.ballon.ui.theme.AppTheme
import com.pmb.core.utils.toCurrency
import com.pmb.navigation.manager.LocalNavigationManager
import com.pmb.navigation.moduleScreen.AccountScreens

@Composable
fun IssueCardConfirmScreen(
    viewModel: IssueCardConfirmViewModel,
    value: IssueCardSharedState
) {
    val viewState by viewModel.viewState.collectAsState()
    val navigationManager = LocalNavigationManager.current

    LaunchedEffect(Unit) {
        viewModel.viewEvent.collect { event ->
            when (event) {
                IssueCardConfirmViewEvents.NavigateBack -> navigationManager.navigateBack()
                is IssueCardConfirmViewEvents.SuccessAnswer -> navigationManager.navigateWithString(
                    AccountScreens.TransactionReceipt.createRoute(
                        value.cardOwnerAccount?.depositNumber ?: "", event.json
                    )
                )
            }
        }
    }

    val listOfData = listOf(
        "صاحب کارت" to value.userData?.output?.name + " " + value.userData?.output?.family,
        "کد ملی" to value.userData?.output?.nationalCode.toString().padStart(2, '0'),
        "نوع کارت" to "معمولی",
        "نحوه تخصیص شماره کارت" to value.addressType.title,
        "حساب اصلی کارت" to value.accountNumber,
        "نوع حساب اصلی" to (value.cardOwnerAccount?.title ?: ""),
        "تحویل کارت" to "تحویل در آدرس مشتری",
        "آدرس" to value.address,
        "کد پستی" to (value.postalCode ?: value.userData?.output?.postalCode),
        "کسر کارمزد از حساب" to value.commissionFeeAccount?.depositNumber,
        "مجموع کارمزدها" to (value.commissionFee?.totalAmount?.toDouble()
            ?: 0.0).toCurrency() + " ریال"
    )

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
                        viewModel.handle(IssueCardConfirmViewActions.SendData(value))
                    })
            }
        },
        topBar = {
            AppTopBar(
                title = "تایید اطلاعات درخواست",
                startIcon = ClickableIcon(
                    icon = IconType.ImageVector(Icons.Default.ArrowForward),
                    onClick = {
                        navigationManager.navigateBack()
                    }
                )
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
                listOfData.forEachIndexed { index, it ->

                    FeeRow(
                        title = it.first,
                        value = it.second ?: "",
                        bottomDivider = listOfData.size - 1 != index
                    )
                }
            }
        }
    }

    if (viewState.isLoading)
        AppLoading()

    if (viewState.alertModelState != null) {
        AlertComponent(viewState.alertModelState!!)
    }
}

@Composable
private fun FeeRow(
    title: String,
    value: String,
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
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            CaptionText(
                text = title,
                color = AppTheme.colorScheme.onBackgroundNeutralSubdued
            )

            BodyMediumText(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxWidth(),
                text = value,
                textAlign = TextAlign.End,
                color = AppTheme.colorScheme.onBackgroundNeutralDefault
            )

        }
        if (bottomDivider)
            HorizontalDivider(
                modifier = Modifier.padding(horizontalDividerPadding.toPaddingValues()),
                color = AppTheme.colorScheme.strokeNeutral3Devider
            )
    }

}