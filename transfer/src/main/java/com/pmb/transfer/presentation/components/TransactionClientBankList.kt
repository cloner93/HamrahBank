package com.pmb.transfer.presentation.components

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.pmb.ballon.component.Header
import com.pmb.transfer.R
import com.pmb.transfer.domain.TransactionClientBank

@Composable
fun TransactionClientBankList(
    items: List<TransactionClientBank>,
    state: LazyListState = rememberLazyListState(),
    isFavorite: Boolean = true,
    onClick: (TransactionClientBank) -> Unit
) {
    LazyColumn(modifier = Modifier.fillMaxSize(), state = state) {
        if (isFavorite)
            item {
                Header(title = stringResource(R.string.favorites))
            }

        items(items.size) { index ->
            ClientBankInfoTypeRow(info = items[index],
                onClick = { onClick(items[index]) })
        }
    }
}