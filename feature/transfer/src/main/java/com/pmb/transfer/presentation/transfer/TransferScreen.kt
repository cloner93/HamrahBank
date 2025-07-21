package com.pmb.transfer.presentation.transfer

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.pmb.ballon.component.AlertComponent
import com.pmb.ballon.component.EmptyList
import com.pmb.ballon.component.ExtendFAB
import com.pmb.ballon.component.base.AppContent
import com.pmb.ballon.component.base.AppLoading
import com.pmb.ballon.component.base.AppTopBar
import com.pmb.ballon.component.base.ClickableIcon
import com.pmb.ballon.component.base.IconType
import com.pmb.ballon.models.isScrollingUp
import com.pmb.ballon.ui.theme.AppTheme
import com.pmb.navigation.manager.LocalNavigationManager
import com.pmb.navigation.moduleScreen.TransferScreens
import com.pmb.transfer.R
import com.pmb.transfer.domain.entity.TransactionClientBankEntity
import com.pmb.transfer.presentation.components.FavoriteContactsView
import com.pmb.transfer.presentation.components.TransactionClientBankList
import com.pmb.transfer.presentation.transfer.viewmodel.TransferViewActions
import com.pmb.transfer.presentation.transfer.viewmodel.TransferViewEvents
import com.pmb.transfer.presentation.transfer.viewmodel.TransferViewModel

@Composable
fun TransferScreen(
    viewModel: TransferViewModel,
    selectedItem: (TransactionClientBankEntity) -> Unit
) {
    val navigationManager = LocalNavigationManager.current
    val lazyListState = rememberLazyListState()
    val viewState by viewModel.viewState.collectAsState()

    // Handle one-time events such as navigation or showing toasts
    LaunchedEffect(Unit) {
        viewModel.viewEvent.collect { event ->
            when (event) {
                TransferViewEvents.TransferDestination -> {
                    navigationManager.navigate(TransferScreens.TransferDestinationInput)
                }

                is TransferViewEvents.TransferDestinationAmount -> {
                    selectedItem.invoke(event.item)
                    navigationManager.navigate(TransferScreens.TransferAmount)
                }
            }
        }
    }

    AppContent(
        modifier = Modifier
            .fillMaxSize(),
        scrollState = null,
        backgroundColor = AppTheme.colorScheme.onForegroundNeutralDefault,
        topBar = {
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
        }
    ) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.BottomCenter
        ) {
            Column {
                if (!viewState.loading) {
                    if (viewState.accounts.isEmpty() && viewState.favoriteAccounts.isEmpty()) {
                        EmptyList(
                            modifier = Modifier.fillMaxSize(),
                            iconType = IconType.Painter(painterResource(R.drawable.img_bank_card_shrare_money)),
                            message = stringResource(R.string.msg_dont_have_transfer_yet)
                        )
                    } else {
                        FavoriteContactsView(
                            items = viewState.favoriteAccounts,
                            onEditClick = {
                                navigationManager.navigate(TransferScreens.TransferEditFavorite)
                            }, onClick = {
                                viewModel.handle(TransferViewActions.SelectAccount(it))
                            })

                        if (viewState.accounts.isNotEmpty())
                            TransactionClientBankList(
                                items = viewState.accounts,
                                state = lazyListState,
                                onEditClick = {
                                    navigationManager.navigate(TransferScreens.TransferEditLatestDestination)
                                },
                                onClick = {
                                    viewModel.handle(TransferViewActions.SelectAccount(it))
                                }
                            )
                    }
                }
            }

            ExtendFAB(
                modifier = Modifier.padding(16.dp),
                extended = lazyListState.isScrollingUp(),
                text = stringResource(R.string.new_transfer),
                icon = IconType.ImageVector(imageVector = Icons.Default.Add),
                onClick = {
                    viewModel.handle(TransferViewActions.NavigateToDestinationInput)
                }
            )
        }
    }

    if (viewState.loading) AppLoading()
    viewState.alertState?.let { AlertComponent(it) }
}