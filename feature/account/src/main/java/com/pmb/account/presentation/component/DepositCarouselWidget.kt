package com.pmb.account.presentation.component

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.pmb.ballon.R
import com.pmb.ballon.component.annotation.AppPreview
import com.pmb.ballon.ui.theme.HamrahBankTheme
import com.pmb.domain.model.DepositModel

@Composable
fun DepositCarouselWidget(
    depositModel: DepositModel?,
    onMoreClick: () -> Unit,
    onAmountVisibilityClick: () -> Unit,
    onDepositListChipsClick: () -> Unit,
    isAmountVisible: Boolean,
    isLoading: Boolean
) {
    Box(
        modifier = Modifier
            .fillMaxWidth(),
        contentAlignment = Alignment.BottomCenter
    ) {
//        if (depositModel != null)
            DepositWidget(
                modifier = Modifier.padding(start = 32.dp, end = 32.dp),
                item = depositModel,
                isAmountVisible = isAmountVisible,
                moreClick = { onMoreClick.invoke() },
                isLoading = isLoading,
                onAmountVisibilityClick = { onAmountVisibilityClick.invoke() }
            ) { onDepositListChipsClick.invoke() }
    }
}

@AppPreview
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
        categoryCode = 0,
    )
    HamrahBankTheme {
        DepositCarouselWidget(
            depositModel = dip,
            onMoreClick = {},
            onAmountVisibilityClick = {},
            onDepositListChipsClick = { },
            isAmountVisible = true,
            isLoading = false
        )
    }
}