package com.pmb.account.presentation.deposits

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import com.pmb.account.R
import com.pmb.account.presentation.component.DepositCarouselWidget
import com.pmb.account.presentation.component.DepositModel
import com.pmb.account.presentation.component.ShareDepositBottomSheet
import com.pmb.account.presentation.component.TransactionModel
import com.pmb.account.presentation.component.TransactionRow
import com.pmb.account.presentation.component.TransactionType
import com.pmb.ballon.component.DepositBottomSheet
import com.pmb.ballon.component.MenuBottomSheet
import com.pmb.ballon.component.MenuItem
import com.pmb.ballon.component.base.AppButtonIcon
import com.pmb.ballon.component.base.RoundedTopColumn
import com.pmb.ballon.models.DepositBottomSheetModel
import com.pmb.ballon.models.IconStyle
import com.pmb.ballon.models.MenuSheetModel
import com.pmb.ballon.models.TextStyle
import com.pmb.ballon.ui.theme.AppTheme
import com.pmb.ballon.ui.theme.HamrahBankTheme
import com.pmb.core.presentation.NavigationManager


@Composable
fun DepositsScreen(navigationManager: NavigationManager) {
    var showCopyBottomSheet by remember { mutableStateOf(false) }
    var showMoreBottomSheet by remember { mutableStateOf(false) }
    var showDepositList by remember { mutableStateOf(false) }
    var showAmounts by remember { mutableStateOf(false) }
    var currentDepositModel by remember { mutableStateOf<DepositModel?>(null) }
    var selectedDeposit by remember { mutableStateOf<DepositModel?>(null) }

    val depositModels = listOf(
        DepositModel(
            title = "حساب قرض الحسنه",
            depositNumber = "123456",
            amount = 10000023400.0,
            currency = stringResource(com.pmb.ballon.R.string.real_carrency),
            ibanNumber = "IR1234567890098765432112",
            cardNumber = "6219861920241234",
        ),
        DepositModel(
            title = "حساب سرمایه گذاری بلند مدت",
            depositNumber = "97974632",
            amount = 678326023400.0,
            currency = stringResource(com.pmb.ballon.R.string.real_carrency),
            ibanNumber = "IR1234567890098765432112",
            cardNumber = "6219861920241234",
        ),
        DepositModel(
            title = "حساب مشترک",
            depositNumber = "82768947",
            amount = 68392.0,
            currency = stringResource(com.pmb.ballon.R.string.real_carrency),
            ibanNumber = "IR1234567890098765432112",
            cardNumber = "6219861920241234",
        ),
        DepositModel(
            title = "حساب ارزی",
            depositNumber = "23879",
            amount = 9999999999990.0,
            currency = stringResource(com.pmb.ballon.R.string.daller),
            ibanNumber = "IR1234567890098765432112",
            cardNumber = "6219861920241234",
        ),
        DepositModel(
            title = "حساب قرض الحسنه",
            depositNumber = "123",
            amount = 0.0,
            currency = stringResource(com.pmb.ballon.R.string.real_carrency),
            ibanNumber = "IR1234567890098765432112",
            cardNumber = "6219861920241234",
        )
    )

    val transactions = listOf(
        TransactionModel(
            type = TransactionType.RECEIVE,
            title = "برداشت",
            amount = 1000000000.0,
            currency = stringResource(com.pmb.ballon.R.string.real_carrency),
            date = "امروز ساعت ۱۳:۴۵"
        ),
        TransactionModel(
            type = TransactionType.WITHDRAWAL,
            title = "دریافت از سپرده",
            amount = 20000.0,
            currency = stringResource(com.pmb.ballon.R.string.real_carrency),
            date = "امروز ساعت ۱۳:۴۵"
        ),
        TransactionModel(
            type = TransactionType.RECEIVE,
            title = "برداشت",
            amount = 1000000000.0,
            currency = stringResource(com.pmb.ballon.R.string.real_carrency),
            date = "امروز ساعت ۱۳:۴۵"
        ),
        TransactionModel(
            type = TransactionType.WITHDRAWAL,
            title = "دریافت از سپرده",
            amount = 20000.0,
            currency = stringResource(com.pmb.ballon.R.string.real_carrency),
            date = "امروز ساعت ۱۳:۴۵"
        ),
        TransactionModel(
            type = TransactionType.RECEIVE,
            title = "برداشت",
            amount = 1000000000.0,
            currency = stringResource(com.pmb.ballon.R.string.real_carrency),
            date = "امروز ساعت ۱۳:۴۵"
        ),
        TransactionModel(
            type = TransactionType.RECEIVE,
            title = "برداشت",
            amount = 1000000000.0,
            currency = stringResource(com.pmb.ballon.R.string.real_carrency),
            date = "امروز ساعت ۱۳:۴۵"
        ),
        TransactionModel(
            type = TransactionType.WITHDRAWAL,
            title = "دریافت از سپرده",
            amount = 20000.0,
            currency = stringResource(com.pmb.ballon.R.string.real_carrency),
            date = "امروز ساعت ۱۳:۴۵"
        ),
        TransactionModel(
            type = TransactionType.RECEIVE,
            title = "برداشت",
            amount = 1000000000.0,
            currency = stringResource(com.pmb.ballon.R.string.real_carrency),
            date = "امروز ساعت ۱۳:۴۵"
        ),
    )

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(350.dp)
            .background(
                brush = Brush.verticalGradient(
                    colors = listOf(
                        Color(0xFFDF445F), Color(0xFFC11332)
                    )
                )
            )
    ) {
        Image(painter = painterResource(R.drawable.bg_mellat_logo), contentDescription = null)
    }

    Column {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(92.dp)
                .padding(horizontal = 8.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            AppButtonIcon(icon = com.pmb.ballon.R.drawable.ic_help_filled,
                style = IconStyle(tint = Color.White),
                onClick = {

                })


            AppButtonIcon(icon = com.pmb.ballon.R.drawable.ic_coins,
                style = IconStyle(tint = Color.White),
                onClick = {

                })
        }

        DepositCarouselWidget(depositModels = depositModels, onSelected = {
            currentDepositModel = it
        }, onMoreClick = {
            showMoreBottomSheet = true
        }, onAmountVisibilityClick = {
            showAmounts = false
        }, onDepositListChipsClick = {
            showDepositList = true

        })

        Spacer(modifier = Modifier.height(32.dp))

        RoundedTopColumn(
            modifier = Modifier.fillMaxSize()
        ) {

            MenuItem(modifier = Modifier
                .clip(RoundedCornerShape(16.dp))
                .background(color = AppTheme.colorScheme.backgroundTintNeutralDefault),
                title = stringResource(R.string.deposit_card_sheba),
                horizontalPadding = 16.dp,
                startIcon = com.pmb.ballon.R.drawable.ic_racket,
                titleStyle = TextStyle(
                    color = AppTheme.colorScheme.foregroundNeutralDefault,
                    typography = AppTheme.typography.buttonLarge
                ),
                startIconStyle = IconStyle(tint = AppTheme.colorScheme.onBackgroundNeutralSubdued),
                clickable = false,
                onItemClick = {
                    showCopyBottomSheet = true
                })
            Spacer(modifier = Modifier.height(12.dp))
            MenuItem(modifier = Modifier
                .clip(RoundedCornerShape(16.dp))
                .background(
                    color = AppTheme.colorScheme.backgroundTintNeutralDefault,
                    shape = RoundedCornerShape(16.dp)
                ),
                title = stringResource(R.string.transactions),
                horizontalPadding = 16.dp,
                startIcon = com.pmb.ballon.R.drawable.ic_bar_chart_vertical,
                endIcon = com.pmb.ballon.R.drawable.ic_arrow_left,
                titleStyle = TextStyle(
                    color = AppTheme.colorScheme.foregroundNeutralDefault,
                    typography = AppTheme.typography.buttonLarge
                ),//Color/On Background/Neutral/Subdued
                startIconStyle = IconStyle(tint = AppTheme.colorScheme.onBackgroundNeutralSubdued),
                endIconStyle = IconStyle(tint = AppTheme.colorScheme.onBackgroundNeutralSubdued),
                clickable = false,
                onItemClick = {

                })

            Spacer(modifier = Modifier.height(24.dp))

            LazyColumn {
                items(transactions.size) { item ->
                    Spacer(modifier = Modifier.height(12.dp))
                    TransactionRow(transactions[item])
                    Spacer(modifier = Modifier.height(12.dp))
                }
            }
        }
    }

    val menuItems = listOf(
        MenuSheetModel(
            title = stringResource(R.string.select_for_main_deposit),
            icon = com.pmb.ballon.R.drawable.ic_pin
        ), MenuSheetModel(
            title = stringResource(R.string.cards_connected_to_the_deposit),
            icon = com.pmb.ballon.R.drawable.ic_credit_cards
        ), MenuSheetModel(
            title = stringResource(R.string.request_to_issue_a_card_for_deposit),
            icon = com.pmb.ballon.R.drawable.ic_credit_card
        ), MenuSheetModel(
            title = stringResource(R.string.edit_deposit_title),
            icon = com.pmb.ballon.R.drawable.ic_edit
        )
    )

    if (showCopyBottomSheet) ShareDepositBottomSheet(info = currentDepositModel!!,
        onDismiss = { showCopyBottomSheet = false })

    if (showMoreBottomSheet) MenuBottomSheet(title = currentDepositModel?.depositNumber!!,
        items = menuItems,
        onDismiss = { showMoreBottomSheet = false },
        onSelect = {

        })

    if (showDepositList) {
        Log.d("TAG", "DepositsScreen: ")
        DepositBottomSheet(title = "سپرده ها",
            items = depositModels.mapToDepositMenu(),
            onDismiss = { showDepositList = false },
            onSelect = {})
    }

}

private fun List<DepositModel>.mapToDepositMenu(): List<DepositBottomSheetModel> {
    val list = mutableListOf<DepositBottomSheetModel>()
    for (item in this) {
        val deposit = DepositBottomSheetModel(
            title = item.title,
            desc = "اقزودن نام",
            depositNumber = item.depositNumber,
            amount = item.amount,
            currency = item.currency,
            ibanNumber = item.ibanNumber,
            state = 1,
            cardNumber = item.cardNumber
        )
        list.add(deposit)
    }
    return list
}

@Preview
@Composable
private fun DepositsScreenPrev() {
    CompositionLocalProvider(LocalLayoutDirection provides LayoutDirection.Rtl) {
        HamrahBankTheme {
//            DepositsScreen()
        }
    }
}