package com.pmb.transfer.presentation.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.pmb.ballon.component.HeaderList
import com.pmb.ballon.component.base.BodySmallText
import com.pmb.ballon.component.base.IconType
import com.pmb.ballon.ui.theme.AppTheme
import com.pmb.transfer.R
import com.pmb.transfer.domain.entity.ClientBankEntity
import com.pmb.transfer.domain.entity.TransactionClientBankEntity

@Composable
fun FavoriteContactsView(
    items: List<TransactionClientBankEntity>,
    onEditClick: () -> Unit,
    onClick: (TransactionClientBankEntity) -> Unit
) {
    Column(
        modifier = Modifier.fillMaxWidth()
    ) {
        HeaderList(
            title = stringResource(R.string.favorites),
            iconType = IconType.Painter(painterResource(com.pmb.ballon.R.drawable.ic_edit)),
            tint = if (items.isEmpty()) AppTheme.colorScheme.onBackgroundNeutralDisabled else AppTheme.colorScheme.onBackgroundNeutralDefault,
            clickable = items.isNotEmpty(),
            onClick = onEditClick)

        if (items.isEmpty())
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 40.dp),
                contentAlignment = Alignment.Center
            ) {
                BodySmallText(text = stringResource(R.string.msg_favorite_destination_empty))
            }
        else
            LazyRow {
                items(items.size) { index ->
                    ContactRow(item = items[index], onClick = onClick)
                }
            }
    }
}