package com.pmb.account.presentation.cards

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.FabPosition
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.pmb.account.R
import com.pmb.account.presentation.component.CardModel
import com.pmb.account.presentation.component.CardRow
import com.pmb.ballon.component.MenuBottomSheet
import com.pmb.ballon.component.base.AppButtonIcon
import com.pmb.ballon.component.base.AppFAB
import com.pmb.ballon.models.IconStyle
import com.pmb.ballon.models.MenuSheetModel
import com.pmb.ballon.ui.theme.AppTheme
import com.pmb.core.presentation.NavigationManager
import kotlin.random.Random

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun CardsScreen(navigationManager: NavigationManager) {
    var showDetailCardBottomSheet by remember { mutableStateOf(false) }
    var showFabBottomSheet by remember { mutableStateOf(false) }
    var currentCardModel by remember { mutableStateOf<CardModel?>(null) }

    val cards = generateRandomCards()

    Scaffold(
        floatingActionButton = {
            AppFAB(
                icon = com.pmb.ballon.R.drawable.ic_add,
                containerColor = Color(0xffE8E8EB),
                contentColor = AppTheme.colorScheme.iconColor,
                onClick = { showFabBottomSheet = true })
        },
        floatingActionButtonPosition = FabPosition.Start // Optional: position the FAB
    ) {

        Column(modifier = Modifier.fillMaxSize()) {
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
                    style = IconStyle(tint = Color(0xFFAAABAE)),
                    onClick = {

                    })
            }

            LazyColumn(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f),
                verticalArrangement = Arrangement.spacedBy(16.dp), // Space between items
                contentPadding = PaddingValues(20.dp) // Padding around the list
            ) {
                items(cards.size) { item ->
                    CardRow(item = cards[item], onClick = {
                        currentCardModel = it
                        showDetailCardBottomSheet = true
                    })
                }
            }
        }
    }

    val menuItems = listOf(
        MenuSheetModel(
            title = stringResource(R.string.card_reissue),
            icon = com.pmb.ballon.R.drawable.ic_credit_card_refresh_reload
        ),
        MenuSheetModel(
            title = stringResource(R.string.card_connection_disconnection),
            icon = com.pmb.ballon.R.drawable.ic_credit_card_cross_delete_circle
        ),
        MenuSheetModel(
            title = stringResource(R.string.deactivating_the_card),
            icon = com.pmb.ballon.R.drawable.ic_credit_card_lock_circe
        ),
        MenuSheetModel(
            title = stringResource(R.string.password_management),
            icon = com.pmb.ballon.R.drawable.ic_credit_card_lock_circe
        ),
        MenuSheetModel(
            title = stringResource(R.string.cardless_withdrawal),
            icon = com.pmb.ballon.R.drawable.ic_key_password
        ),
        MenuSheetModel(
            title = stringResource(R.string.card_cancellation),
            icon = com.pmb.ballon.R.drawable.ic_money_banknote_hand,
            textColor = { Color(0xFFBA1A1A) },
            iconTint = { Color(0xFFBA1A1A) }
        )
    )

    if (showDetailCardBottomSheet) MenuBottomSheet(
        title = currentCardModel?.cardNumber!!,
        items = menuItems,
        onDismiss = { showDetailCardBottomSheet = false },
        onSelect = {

        })

    val fabItems = listOf(
        MenuSheetModel(
            title = stringResource(R.string.add_a_new_bank_card),
            icon = com.pmb.ballon.R.drawable.ic_credit_card
        ),
        MenuSheetModel(
            title = stringResource(R.string.adding_and_activating_a_bank_card),
            icon = com.pmb.ballon.R.drawable.ic_add,
            iconTint = { AppTheme.colorScheme.iconColor }
        )
    )

    if (showFabBottomSheet) MenuBottomSheet(
        items = fabItems,
        onDismiss = { showFabBottomSheet = false },
        onSelect = {

        })
}

@Composable
fun generateRandomCards(): List<CardModel> {
    return List(5) {
        CardModel(
            cardNumber = (1..16).map { Random.nextInt(0, 10) }
                .joinToString(""), // Generates 16-digit card number
            amount = Random.nextDouble(1000.0, 1_000_000_000.0), // Generates random amount
            currency = stringResource(com.pmb.ballon.R.string.real_currency), // Uses string resource for currency
            placeholder = listOf(
                "پوریا خلج",
                "علی احمدی",
                "زهرا باقری",
                "محمد حسینی",
                "فاطمه رضایی"
            ).random(), // Random name
            expiredDate = "${Random.nextInt(1, 13).toString().padStart(2, '0')}/${
                Random.nextInt(
                    22,
                    30
                )
            }" // Random MM/YY
        )
    }
}