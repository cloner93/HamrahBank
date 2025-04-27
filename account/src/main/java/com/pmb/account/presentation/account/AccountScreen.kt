package com.pmb.account.presentation.account

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.pmb.account.R
import com.pmb.account.presentation.cards.CardsScreen
import com.pmb.account.presentation.deposits.DepositsScreen
import com.pmb.ballon.component.DynamicTabSelector

@Composable
fun AccountScreen() {
    val cards = stringResource(R.string.cards)
    val accounts = stringResource(R.string.accounts)
    val selectedOption = remember { mutableIntStateOf(0) }
    val optionTexts = listOf(accounts, cards)
    when (selectedOption.intValue) {
        0 -> DepositsScreen()
        1 -> CardsScreen()
    }

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 25.dp)
            .height(92.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        Spacer(modifier = Modifier.width(68.dp))

        DynamicTabSelector(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f),
            tabs = optionTexts,
            selectedOption = selectedOption.intValue
        ) {
            selectedOption.intValue = it
        }

        Spacer(modifier = Modifier.width(68.dp))
    }
}