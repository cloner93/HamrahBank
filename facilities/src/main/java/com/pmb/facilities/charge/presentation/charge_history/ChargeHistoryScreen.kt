package com.pmb.facilities.charge.presentation.charge_history

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.pmb.ballon.component.AmountText
import com.pmb.ballon.component.ChipWithIcon
import com.pmb.ballon.component.ImageItemRow
import com.pmb.ballon.component.base.AppContent
import com.pmb.ballon.component.base.AppTopBar
import com.pmb.ballon.models.TextStyle
import com.pmb.ballon.ui.theme.AppTheme
import com.pmb.facilities.R
import com.pmb.facilities.charge.presentation.charge_history.viewModel.ChargeHistoryViewActions
import com.pmb.facilities.charge.presentation.charge_history.viewModel.ChargeHistoryViewEvents
import com.pmb.facilities.charge.presentation.charge_history.viewModel.ChargeHistoryViewModel
import com.pmb.navigation.manager.LocalNavigationManager

@Composable
fun ChargeHistoryScreen(viewModel: ChargeHistoryViewModel) {
    val navigationManager = LocalNavigationManager.current
    val viewState by viewModel.viewState.collectAsStateWithLifecycle()
    LaunchedEffect(Unit) {

        viewModel.viewEvent.collect { event ->
            when (event) {
                is ChargeHistoryViewEvents.SelectedFilterIsSuccess -> {
                    viewModel.handle(ChargeHistoryViewActions.GetChargeHistory(event.filter))
                }
            }
        }
    }
    AppContent(
        scrollState = null,
        horizontalAlignment = Alignment.CenterHorizontally,
        backgroundColor = AppTheme.colorScheme.background3Neutral,
        topBar = {
            AppTopBar(
                title = stringResource(R.string.charge_history),
                onBack = {
                    navigationManager.navigateBack()
                }
            )
        }
    ) {
        viewState.filterItemList?.let {
            LazyRow(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 16.dp),
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                contentPadding = PaddingValues(horizontal = 16.dp)
            ) {
                items(it) { item ->
                    ChipWithIcon(
                        modifier = Modifier,
                        value = item.filterName,
                        clickable = {
                            viewModel.handle(
                                ChargeHistoryViewActions.SetSelectedFilter(
                                    item.filterName
                                )
                            )
                        },
                        color = if (item.isSelected.value) AppTheme.colorScheme.stateLayerNeutralPressed else Color.White,
                        assetColor = Color.Black,
                        borderColor = AppTheme.colorScheme.strokeNeutral1Default,
                        fillMaxWidth = false
                    )
                }
            }
        }
        viewState.dataList?.let {
            LazyColumn(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 20.dp, start = 16.dp, end = 16.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {

                items(it) { item ->
                    ImageItemRow(
                        modifier = Modifier
                            .fillMaxWidth()
                            .heightIn(min = 68.dp)
                            .background(
                                AppTheme.colorScheme.background1Neutral,
                                RoundedCornerShape(12.dp)
                            ),
                        horizontalPadding = 12.dp,
                        title = item.title,
                        startImage = item.imageString,
                        titleStyle = TextStyle(
                            color = AppTheme.colorScheme.foregroundNeutralDefault,
                            typography = AppTheme.typography.bodyMedium
                        ),
                        subtitle = item.subTitle,

                        endContent = {
                            item.price?.let {
                                AmountText(
                                    amount = it.toDouble(),
                                    currency = stringResource(com.pmb.ballon.R.string.rial),
                                    amountStyle = TextStyle(
                                        color = AppTheme.colorScheme.onBackgroundNeutralDefault,
                                        typography = AppTheme.typography.bodyMedium,
                                    ),
                                    currencyStyle = TextStyle(
                                        color = AppTheme.colorScheme.onBackgroundNeutralSubdued,
                                        typography = AppTheme.typography.caption,
                                    )
                                )
                            }
                        }
                    )

                }
            }
        }
    }
}
