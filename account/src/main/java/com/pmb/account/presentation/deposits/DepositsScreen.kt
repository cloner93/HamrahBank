package com.pmb.account.presentation.deposits

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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.pmb.account.R
import com.pmb.account.presentation.component.DepositCarouselWidget
import com.pmb.account.presentation.component.DepositModel
import com.pmb.account.presentation.component.ShareDepositBottomSheet
import com.pmb.account.presentation.component.TransactionModel
import com.pmb.account.presentation.component.TransactionRow
import com.pmb.account.presentation.component.TransactionType
import com.pmb.ballon.component.MenuBottomSheet
import com.pmb.ballon.component.MenuItem
import com.pmb.ballon.component.base.AppButtonIcon
import com.pmb.ballon.component.base.RoundedTopColumn
import com.pmb.ballon.models.IconStyle
import com.pmb.ballon.models.MenuSheetModel
import com.pmb.ballon.models.TextStyle
import com.pmb.ballon.ui.theme.AppTheme
import com.pmb.core.presentation.NavigationManager


@Composable
fun DepositsScreen(navigationManager: NavigationManager) {
    var showCopyBottomSheet by remember { mutableStateOf(false) }
    var showMoreBottomSheet by remember { mutableStateOf(false) }
    var currentDepositModel by remember { mutableStateOf<DepositModel?>(null) }

    val depositModels = listOf(
        DepositModel(
            title = "حساب قرض الحسنه آقای مشتاق مودت",
            depositNumber = "123456",
            amount = 10000023400.0,
            currency = stringResource(com.pmb.ballon.R.string.real_currency),
            ibanNumber = "IR1234567890098765432112",
            cardNumber = "6219861920241234",
        ),
        DepositModel(
            title = "حساب قرض الحسنه آقای مشتاق مودت",
            depositNumber = "97974632",
            amount = 678326023400.0,
            currency = stringResource(com.pmb.ballon.R.string.real_currency),
            ibanNumber = "IR1234567890098765432112",
            cardNumber = "6219861920241234",
        ),
        DepositModel(
            title = "حساب قرض الحسنه آقای مشتاق مودت",
            depositNumber = "82768947",
            amount = 68392.0,
            currency = stringResource(com.pmb.ballon.R.string.real_currency),
            ibanNumber = "IR1234567890098765432112",
            cardNumber = "6219861920241234",
        ),
        DepositModel(
            title = "حساب قرض الحسنه آقای مشتاق مودت",
            depositNumber = "23879",
            amount = 0.0,
            currency = stringResource(com.pmb.ballon.R.string.real_currency),
            ibanNumber = "IR1234567890098765432112",
            cardNumber = "6219861920241234",
        ),
    )

    val transactions = listOf(
        TransactionModel(
            type = TransactionType.RECEIVE,
            title = "برداشت",
            amount = 1000000000.0,
            currency = stringResource(com.pmb.ballon.R.string.real_currency),
            date = "امروز ساعت ۱۳:۴۵"
        ),
        TransactionModel(
            type = TransactionType.WITHDRAWAL,
            title = "دریافت از سپرده",
            amount = 20000.0,
            currency = stringResource(com.pmb.ballon.R.string.real_currency),
            date = "امروز ساعت ۱۳:۴۵"
        ),
        TransactionModel(
            type = TransactionType.RECEIVE,
            title = "برداشت",
            amount = 1000000000.0,
            currency = stringResource(com.pmb.ballon.R.string.real_currency),
            date = "امروز ساعت ۱۳:۴۵"
        ),
        TransactionModel(
            type = TransactionType.WITHDRAWAL,
            title = "دریافت از سپرده",
            amount = 20000.0,
            currency = stringResource(com.pmb.ballon.R.string.real_currency),
            date = "امروز ساعت ۱۳:۴۵"
        ),
        TransactionModel(
            type = TransactionType.RECEIVE,
            title = "برداشت",
            amount = 1000000000.0,
            currency = stringResource(com.pmb.ballon.R.string.real_currency),
            date = "امروز ساعت ۱۳:۴۵"
        ),
        TransactionModel(
            type = TransactionType.RECEIVE,
            title = "برداشت",
            amount = 1000000000.0,
            currency = stringResource(com.pmb.ballon.R.string.real_currency),
            date = "امروز ساعت ۱۳:۴۵"
        ),
        TransactionModel(
            type = TransactionType.WITHDRAWAL,
            title = "دریافت از سپرده",
            amount = 20000.0,
            currency = stringResource(com.pmb.ballon.R.string.real_currency),
            date = "امروز ساعت ۱۳:۴۵"
        ),
        TransactionModel(
            type = TransactionType.RECEIVE,
            title = "برداشت",
            amount = 1000000000.0,
            currency = stringResource(com.pmb.ballon.R.string.real_currency),
            date = "امروز ساعت ۱۳:۴۵"
        ),
    )

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(350.dp)
            .background(color = AppTheme.colorScheme.iconColor)
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
            AppButtonIcon(
                icon = com.pmb.ballon.R.drawable.ic_help_filled,
                style = IconStyle(tint = Color.White),
                onClick = {

                })


            AppButtonIcon(
                icon = com.pmb.ballon.R.drawable.ic_coins,
                style = IconStyle(tint = Color.White),
                onClick = {

                })
        }

        DepositCarouselWidget(
            depositModels = depositModels,
            onSelected = {
                currentDepositModel = it
            },
            onMoreClick = {
                showMoreBottomSheet = true
            },
            onCopyClick = {

            })

        Spacer(modifier = Modifier.height(32.dp))

        RoundedTopColumn(
            modifier = Modifier.fillMaxSize()
        ) {

            MenuItem(modifier = Modifier
                .clip(RoundedCornerShape(16.dp))
                .background(color = AppTheme.colorScheme.iconBackgroundColor),
                title = stringResource(R.string.deposit_card_sheba),
                horizontalPadding = 16.dp,
                startIcon = com.pmb.ballon.R.drawable.ic_racket,
                titleStyle = TextStyle(
                    color = AppTheme.colorScheme.iconColor,
                    typography = AppTheme.typography.buttonLarge
                ),
                startIconStyle = IconStyle(tint = AppTheme.colorScheme.iconColor),
                clickable = false,
                onItemClick = {
                    showCopyBottomSheet = true
                })
            Spacer(modifier = Modifier.height(12.dp))
            MenuItem(modifier = Modifier
                .clip(RoundedCornerShape(16.dp))
                .background(
                    color = AppTheme.colorScheme.iconBackgroundColor,
                    shape = RoundedCornerShape(16.dp)
                ),
                title = stringResource(R.string.transactions),
                horizontalPadding = 16.dp,
                startIcon = com.pmb.ballon.R.drawable.ic_bar_chart_vertical,
                endIcon = com.pmb.ballon.R.drawable.ic_arrow_left,
                titleStyle = TextStyle(
                    color = AppTheme.colorScheme.iconColor,
                    typography = AppTheme.typography.buttonLarge
                ),
                startIconStyle = IconStyle(tint = AppTheme.colorScheme.iconColor),
                endIconStyle = IconStyle(tint = AppTheme.colorScheme.iconColor),
                clickable = false,
                onItemClick = {

                })

            Spacer(modifier = Modifier.height(14.dp))

            LazyColumn {
                items(transactions.size) { item ->
                    Spacer(modifier = Modifier.height(10.dp))
                    TransactionRow(transactions[item])
                    if (item < transactions.size - 1) Spacer(modifier = Modifier.height(10.dp))
                }
            }
        }
    }
    val menuItems = listOf(
        MenuSheetModel(
            title = stringResource(R.string.select_for_main_deposit),
            icon = com.pmb.ballon.R.drawable.ic_pin
        ),
        MenuSheetModel(
            title = stringResource(R.string.cards_connected_to_the_deposit),
            icon = com.pmb.ballon.R.drawable.ic_credit_cards
        ),
        MenuSheetModel(
            title = stringResource(R.string.request_to_issue_a_card_for_deposit),
            icon = com.pmb.ballon.R.drawable.ic_credit_card
        ),
        MenuSheetModel(
            title = stringResource(R.string.edit_deposit_title),
            icon = com.pmb.ballon.R.drawable.ic_edit
        )
    )

    if (showCopyBottomSheet) ShareDepositBottomSheet(
        info = currentDepositModel!!,
        onDismiss = { showCopyBottomSheet = false })

    if (showMoreBottomSheet) MenuBottomSheet(
        title = currentDepositModel?.depositNumber!!,
        items = menuItems,
        onDismiss = { showMoreBottomSheet = false },
        onSelect = {

        })
}