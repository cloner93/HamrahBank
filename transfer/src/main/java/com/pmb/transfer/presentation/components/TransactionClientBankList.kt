package com.pmb.transfer.presentation.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.pmb.ballon.component.Header
import com.pmb.transfer.R
import com.pmb.transfer.domain.TransactionClientBank

@Composable
fun TransactionClientBankList(
    items: List<TransactionClientBank>,
    onClick: (TransactionClientBank) -> Unit
) {
    LazyColumn(modifier = Modifier.fillMaxSize()) {
        item {
            Header(title = stringResource(R.string.favorites))
        }

        items(items.size) { index ->
            ClientBankInfoTypeRow(info = items[index],
                onClick = { onClick(items[index]) })
        }
    }
}