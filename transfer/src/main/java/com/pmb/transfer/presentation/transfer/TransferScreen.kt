package com.pmb.transfer.presentation.transfer

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import com.pmb.ballon.component.EmptyList
import com.pmb.ballon.component.ExtendFAB
import com.pmb.ballon.component.base.AppTopBar
import com.pmb.ballon.component.base.ClickableIcon
import com.pmb.ballon.component.base.IconType
import com.pmb.ballon.models.isScrollingUp
import com.pmb.ballon.ui.theme.AppTheme
import com.pmb.core.presentation.NavigationManager
import com.pmb.transfer.R
import com.pmb.transfer.domain.clientBanks
import com.pmb.transfer.domain.transactionClientBanks
import com.pmb.transfer.presentation.TransferScreens
import com.pmb.transfer.presentation.components.FavoriteContactsView
import com.pmb.transfer.presentation.components.TransactionClientBankList

@Composable
fun TransferScreen(navigationManager: NavigationManager) {
    val lazyListState = rememberLazyListState()
    Box(contentAlignment = Alignment.BottomCenter) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(color = AppTheme.colorScheme.onForegroundNeutralDefault)
        ) {
            AppTopBar(
                title = stringResource(R.string.fund_transfer),
                startIcon = ClickableIcon(
                    icon = IconType.Painter(painterResource(com.pmb.ballon.R.drawable.ic_question_circle)),
                    onClick = {

                    }),
                endIcon = ClickableIcon(
                    icon = IconType.Painter(painterResource(com.pmb.ballon.R.drawable.ic_search)),
                    onClick = {
                        navigationManager.navigate(TransferScreens.TransferDestinationSearch)
                    })
            )

            if (transactionClientBanks.isEmpty()) {
                EmptyList(
                    iconType = IconType.Painter(painterResource(R.drawable.img_bank_card_shrare_money)),
                    message = stringResource(R.string.msg_dont_have_transfer_yet)
                )
            } else {
                FavoriteContactsView(items = clientBanks,
                    onEditClick = {
                        navigationManager.navigate(TransferScreens.TransferEditFavorite )
                }, onClick = {
                    navigationManager.navigate(TransferScreens.TransferDestinationInput)
                })

                TransactionClientBankList(
                    items = transactionClientBanks,
                    state = lazyListState,
                    onEditClick = {
                        navigationManager.navigate(TransferScreens.TransferEditLatestDestination )
                    },
                    onClick = {
                        navigationManager.navigate(TransferScreens.TransferDestinationInput)
                    }
                )
            }

        }

        ExtendFAB(
            extended = lazyListState.isScrollingUp(),
            text = stringResource(R.string.new_transfer),
            icon = IconType.ImageVector(imageVector = Icons.Default.Add),
            onClick = {
                navigationManager.navigate(TransferScreens.TransferDestinationInput)
            }
        )
    }
}