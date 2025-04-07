package com.pmb.transfer.presentation.components

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import com.pmb.ballon.component.HeaderList
import com.pmb.ballon.component.base.IconType
import com.pmb.ballon.ui.theme.AppTheme
import com.pmb.transfer.R
import com.pmb.transfer.domain.TransactionClientBank
import com.pmb.transfer.presentation.components.transfer_confirm.ClientBankInfoTypeRow

@Composable
fun TransactionClientBankList(
    items: List<TransactionClientBank>,
    state: LazyListState = rememberLazyListState(),
    isFavorite: Boolean = true,
    onEditClick: () -> Unit,
    onClick: (TransactionClientBank) -> Unit
) {
    LazyColumn(modifier = Modifier.fillMaxSize(), state = state) {
        if (isFavorite)
            item {
                HeaderList(
                    title = stringResource(R.string.recently_destinations),
                    iconType = IconType.Painter(painterResource(com.pmb.ballon.R.drawable.ic_edit)),
                    tint = if (items.isEmpty()) AppTheme.colorScheme.onBackgroundNeutralDisabled else AppTheme.colorScheme.onBackgroundNeutralDefault,
                    clickable = items.isNotEmpty(),
                    onClick = onEditClick
                )
            }

        items(items.size) { index ->
            ClientBankInfoTypeRow(
                info = items[index],
                onClick = { onClick(items[index]) })
        }
    }
}