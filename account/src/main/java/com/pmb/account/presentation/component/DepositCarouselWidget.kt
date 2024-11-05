package com.pmb.account.presentation.component

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun DepositCarouselWidget(
    depositModels: List<DepositModel>,
    onSelected: (DepositModel) -> Unit,
    onMoreClick: (DepositModel) -> Unit,
    onCopyClick: (DepositModel) -> Unit
) {
    val pagerState = rememberPagerState(pageCount = { depositModels.size })

    // Call onSelected only when the page changes
    LaunchedEffect(pagerState.currentPage) {
        onSelected(depositModels[pagerState.currentPage])
    }

    Box(
        modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.BottomCenter
    ) {
        HorizontalPager(
            state = pagerState,
            contentPadding = PaddingValues(horizontal = 16.dp),
            pageSpacing = 8.dp,
            verticalAlignment = Alignment.Top,
        ) { page ->
            DepositWidget(
                item = depositModels[page],
                moreClick = { onMoreClick.invoke(depositModels[page]) },
                copyDepositNumberClick = { onCopyClick.invoke(depositModels[page]) }
            )
        }
    }
}