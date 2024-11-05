package com.pmb.transfer.presentation.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.pmb.ballon.component.HeaderList
import com.pmb.transfer.R
import com.pmb.transfer.domain.ClientBank

@Composable
fun ContactsView(items: List<ClientBank>, onClick: (ClientBank) -> Unit) {
    Column(
        modifier = Modifier.fillMaxWidth()
    ) {
        HeaderList(
            title = stringResource(R.string.contacts),
            buttonText = stringResource(R.string.see_all),
            onClick = {

            })

        LazyRow {
            items(items.size) { index ->
                ContactRow(clientBank = items[index], onClick = onClick)
            }
        }
    }
}