package com.pmb.transfer.presentation.transfer_method

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.pmb.ballon.component.ItemVerticalTextIcon
import com.pmb.ballon.component.base.AppContent
import com.pmb.ballon.component.base.AppTopBar
import com.pmb.ballon.ui.theme.AppTheme
import com.pmb.core.presentation.NavigationManager
import com.pmb.transfer.R
import com.pmb.transfer.domain.TransactionClientBank

@Composable
fun TransferMethodScreen(navigationManager: NavigationManager) {
    var isValid by remember { mutableStateOf(false) }
    var identifierNumber by remember { mutableStateOf("") }
    var clientBank by remember { mutableStateOf<TransactionClientBank?>(null) }

    Box(modifier = Modifier.background(color = AppTheme.colorScheme.background1Neutral)) {
        AppContent(
            topBar = {
                AppTopBar(title = stringResource(R.string.transfer_method),
                    onBack = { navigationManager.navigateBack() })
            }
        ) {
            Column(modifier = Modifier.padding(horizontal = 24.dp, vertical = 16.dp)) {
                ItemVerticalTextIcon(title = stringResource(R.string.card_to_card),
                    subtitle = "انتقال در لحظه | کارمزد: ۵٬۰۰۰ ریال",
                    startIcon = com.pmb.ballon.R.drawable.ic_bank_card_swap,
                    onClick = {

                    })
                Spacer(modifier = Modifier.size(16.dp))
                ItemVerticalTextIcon(title = "بین بانکی (ساتنا)",
                    subtitle = "حداقل مبلغ انتقال ۵۰۰.۰۰۰.۰۰۰ ریال است.",
                    startIcon = com.pmb.ballon.R.drawable.ic_university,
                    enable = false,
                    onClick = {

                    })
                Spacer(modifier = Modifier.size(16.dp))
                ItemVerticalTextIcon(title = "بین بانکی (پایا)",
                    subtitle = "انتقال در امروز ۷:۴۵ شب | کارمزد: ۲.۴۰۰ ریال",
                    startIcon = com.pmb.ballon.R.drawable.ic_university,
                    enable = false,
                    onClick = {

                    })
                Spacer(modifier = Modifier.size(16.dp))
                ItemVerticalTextIcon(title = "بین بانکی (پل)",
                    subtitle = "انتقال در لحظه | کارمزد: ۵.۰۰۰ ریال",
                    startIcon = com.pmb.ballon.R.drawable.ic_university,
                    enable = false,
                    onClick = {

                    })
                Spacer(modifier = Modifier.size(16.dp))
                ItemVerticalTextIcon(title = "ملت به ملت",
                    subtitle = "برای انتقال، کارت مقصد باید ملت باشد.",
                    startIcon = com.pmb.ballon.R.drawable.ic_bank_mellat,
                    enable = false,
                    onClick = {

                    })
            }
        }
    }
}