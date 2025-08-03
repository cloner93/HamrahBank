package com.pmb.account.presentation.transactions

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.outlined.Description
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.paging.compose.LazyPagingItems
import com.pmb.account.R
import com.pmb.account.presentation.account.TransactionLazyList
import com.pmb.account.presentation.transactions.filterScreen.DateType
import com.pmb.account.presentation.transactions.filterScreen.viewmodel.entity.TransactionFilter
import com.pmb.ballon.component.base.ButtonMediumText
import com.pmb.ballon.component.base.ChipWithIcon
import com.pmb.ballon.models.IconStyle
import com.pmb.ballon.ui.theme.AppTheme
import com.pmb.calender.toPersianDateString
import com.pmb.core.utils.toCurrency
import com.pmb.domain.model.TransactionModel

@Composable
internal fun AllTransactionsSection(
    transaction: LazyPagingItems<TransactionModel>,
    transactionFilter: TransactionFilter? = null,
    onFilterClick: () -> Unit = {},
    onFilterItemClick: (TransactionFilter) -> Unit = {},
    onStatementClick: () -> Unit = {},
    onTransactionItemClick: (transaction: TransactionModel) -> Unit = {},
) {
    StatementAndFilters(
        transactionFilter = transactionFilter,
        onFilterClick = {
            onFilterClick()
        },
        onFilterItemClick = {
            onFilterItemClick(it)
        },
        onStatementClick = {
            onStatementClick()
        })

    TransactionLazyList(
        transaction,
        true,
    ) { transaction ->
        onTransactionItemClick(transaction)
    }
}

@Composable
private fun StatementAndFilters(
    transactionFilter: TransactionFilter? = null,
    itemSpacing: Dp = 8.dp,
    onFilterClick: () -> Unit,
    onFilterItemClick: (TransactionFilter) -> Unit,
    onStatementClick: () -> Unit,
) {
    Column {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Row(
                modifier = Modifier
                    .padding(all = 8.dp)
                    .clickable { onFilterClick() },
            ) {
                Icon(
                    painter = painterResource(R.drawable.ic_filter),
                    tint = AppTheme.colorScheme.onBackgroundNeutralCTA,
                    contentDescription = null
                )
                ButtonMediumText(
                    modifier = Modifier.padding(horizontal = 8.dp),
                    text = "فیلترها",
                    color = AppTheme.colorScheme.onBackgroundNeutralCTA
                )
            }
            Row(
                modifier = Modifier
                    .padding(all = 8.dp)
                    .clickable { onStatementClick() },
            ) {
                Icon(
                    imageVector = Icons.Outlined.Description,
                    tint = AppTheme.colorScheme.onBackgroundNeutralCTA,
                    contentDescription = null
                )
                ButtonMediumText(
                    modifier = Modifier.padding(horizontal = 8.dp),
                    text = "صورتحساب",
                    color = AppTheme.colorScheme.onBackgroundNeutralCTA
                )
            }
        }
        if (transactionFilter != null) {
            val chips = mutableListOf<Pair<String, () -> Unit>>()

            transactionFilter.transactionType?.let {
                chips.add(it.string to {
                    onFilterItemClick.invoke(transactionFilter.copy(transactionType = null))
                })
            }

            transactionFilter.fromPrice?.let {
                chips.add("از ${it.toCurrency()} ریال" to {
                    onFilterItemClick.invoke(transactionFilter.copy(fromPrice = null))
                })
            }

            transactionFilter.toPrice?.let {
                chips.add("تا ${it.toCurrency()} ریال" to {
                    onFilterItemClick.invoke(transactionFilter.copy(toPrice = null))
                })
            }

            if (transactionFilter.dateType != null) {
                if (transactionFilter.dateType != DateType.CUSTOM) {
                    chips.add(transactionFilter.dateType.string to {
                        onFilterItemClick.invoke(transactionFilter.copy(dateType = null))
                    })
                } else {
                    transactionFilter.fromDate?.let {
                        chips.add("از ${it.toString().toPersianDateString()}" to {
                            onFilterItemClick.invoke(transactionFilter.copy(fromDate = null))
                        })
                    }
                    transactionFilter.toDate?.let {
                        chips.add("تا ${it.toString().toPersianDateString()}" to {

                            onFilterItemClick.invoke(transactionFilter.copy(toDate = null))
                        })
                    }
                }
            }

            if (chips.isNotEmpty()) LazyRow(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 16.dp),
                contentPadding = PaddingValues(horizontal = itemSpacing),
                horizontalArrangement = Arrangement.spacedBy(itemSpacing)
            ) {
                items(chips) { chip ->
                    ChipWithIcon(
                        modifier = Modifier.border(
                            width = 1.dp,
                            color = AppTheme.colorScheme.strokeNeutral1Default,
                            shape = RoundedCornerShape(16.dp)
                        ),
                        roundedShape = 16.dp,
                        value = chip.first,
                        endIcon = Icons.Default.Close,
                        endIconStyle = IconStyle(tint = AppTheme.colorScheme.onBackgroundNeutralDefault),
                        clickable = chip.second,
                        color = AppTheme.colorScheme.background1Neutral,
                        assetColor = AppTheme.colorScheme.onBackgroundNeutralDefault
                    )
                }
            }
        }
    }
}