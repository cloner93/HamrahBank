package com.pmb.transfer.presentation.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.pmb.ballon.component.HeaderList
import com.pmb.transfer.R
import com.pmb.transfer.domain.TransactionClientBank

@Composable
fun TransactionClientBankList(
    items: List<TransactionClientBank>,
    onClick: (TransactionClientBank) -> Unit
) {
    Column(modifier = Modifier) {
        HeaderList(
            title = stringResource(R.string.favorites),
            buttonText = stringResource(R.string.see_all),
            onClick = {

            })
        LazyColumn {
            items(items.size) { index ->
                ClientBankInfoTypeRow(info = items[index],
                    onClick = { onClick(items[index]) })
            }
        }
    }
}