package com.pmb.account.presentation.component

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.pmb.ballon.R

@Composable
fun DepositCarouselWidget(
    depositModel: DepositModel?,
    onMoreClick: () -> Unit,
    onAmountVisibilityClick: () -> Unit,
    onDepositListChipsClick: () -> Unit,
    isAmountVisible: Boolean
) {
    Box(
        modifier = Modifier
            .fillMaxWidth(),
        contentAlignment = Alignment.BottomCenter
    ) {
        if (depositModel != null)
            DepositWidget(
                modifier = Modifier.padding(start = 32.dp, end = 32.dp),
                item = depositModel,
                isAmountVisible = isAmountVisible,
                moreClick = { onMoreClick.invoke() },
                onAmountVisibilityClick = { onAmountVisibilityClick.invoke() }
            ) { onDepositListChipsClick.invoke() }
    }
}

@Preview(showBackground = true)
@Composable
private fun DepositCardPrev() {
    val dip = DepositModel(
        title = "حساب قرض الحسنه آقای مشتاق مودت",
        desc = "تنخواه",
        depositNumber = "123456",
        amount = 10000023400.0,
        currency = stringResource(R.string.real_carrency),
        ibanNumber = "IR1234567890098765432112",
        cardNumber = "6219861920241234",
    )
    DepositCarouselWidget(dip, {}, {}, { }, true)
}