package com.pmb.account.presentation.component

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun DepositCarouselWidget(
    depositModels: List<DepositModel>,
    onSelected: (DepositModel) -> Unit,
    onMoreClick: (DepositModel) -> Unit,
    onAmountVisibilityClick: (DepositModel) -> Unit,
    onDepositListChipsClick: (DepositModel) -> Unit
) {
    val pagerState = rememberPagerState(pageCount = { depositModels.size })

    // Call onSelected only when the page changes
    LaunchedEffect(pagerState.currentPage) {
        onSelected(depositModels[pagerState.currentPage])
    }

    Box(
        modifier = Modifier
            .fillMaxWidth(),
        contentAlignment = Alignment.BottomCenter
    ) {
        DepositWidget(
            modifier = Modifier.padding(start = 32.dp, end = 32.dp),
            item = depositModels[0],
            moreClick = { onMoreClick.invoke(depositModels[0]) },
            onAmountVisibilityClick = { onAmountVisibilityClick.invoke(depositModels[0]) },
            onDepositListChipsClick = { onDepositListChipsClick.invoke(depositModels[0]) }
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun DepositCardPrev() {
    val dip = DepositModel(
        title = "حساب قرض الحسنه آقای مشتاق مودت",
        depositNumber = "123456",
        amount = 10000023400.0,
        currency = stringResource(com.pmb.ballon.R.string.real_carrency),
        ibanNumber = "IR1234567890098765432112",
        cardNumber = "6219861920241234",
    )
    DepositCarouselWidget(listOf(dip), {}, {}, { }, { })
}