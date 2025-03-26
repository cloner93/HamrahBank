package com.pmb.account.presentation.transactions

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material.icons.filled.ChevronLeft
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.outlined.Description
import androidx.compose.material.icons.outlined.Tune
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.pmb.account.R
import com.pmb.account.presentation.AccountScreens
import com.pmb.account.presentation.component.ChipWithIcon
import com.pmb.account.presentation.component.DepositModel
import com.pmb.account.presentation.component.TransactionModel
import com.pmb.account.presentation.component.TransactionType
import com.pmb.account.presentation.transactions.viewmodel.TransactionsViewActions
import com.pmb.account.presentation.transactions.viewmodel.TransactionsViewEvents
import com.pmb.account.presentation.transactions.viewmodel.TransactionsViewModel
import com.pmb.account.presentation.transactions.viewmodel.TransactionsViewState
import com.pmb.account.utils.mapToDepositMenu
import com.pmb.account.utils.mapToDepositModel
import com.pmb.ballon.component.DepositBottomSheet
import com.pmb.ballon.component.DynamicTabSelector
import com.pmb.ballon.component.base.AppButtonIcon
import com.pmb.ballon.component.base.AppIcon
import com.pmb.ballon.component.base.AppImage
import com.pmb.ballon.component.base.BodySmallText
import com.pmb.ballon.component.base.ButtonMediumText
import com.pmb.ballon.component.base.CaptionText
import com.pmb.ballon.component.base.ClickableIcon
import com.pmb.ballon.component.base.Headline6Text
import com.pmb.ballon.component.base.IconType
import com.pmb.ballon.models.IconStyle
import com.pmb.ballon.models.ImageStyle
import com.pmb.ballon.models.Size
import com.pmb.ballon.models.TextStyle
import com.pmb.ballon.ui.theme.AppTheme
import com.pmb.ballon.ui.theme.AppTypography
import com.pmb.core.presentation.NavigationManager
import com.pmb.core.utils.toCurrency

@Composable
fun TransactionsScreen(navigationManager: NavigationManager) {
    val viewModel = hiltViewModel<TransactionsViewModel>()
    val viewState by viewModel.viewState.collectAsState()

    val selectedOption = remember { mutableIntStateOf(0) }
    val optionTexts = listOf("همه", "برداشت", "واریز")

    LaunchedEffect(Unit) {
        viewModel.viewEvent.collect { event ->
            when (event) {
                is TransactionsViewEvents.ShowError -> {

                }

                is TransactionsViewEvents.ShowToast -> {

                }

                is TransactionsViewEvents.DepositSelectionChanged -> {

                }

                TransactionsViewEvents.NavigateBack -> {
                    navigationManager.navigateBack()
                }

                TransactionsViewEvents.NavigateToDepositStatementScreen -> {

                }

                TransactionsViewEvents.NavigateToTransactionFilterScreen -> {
                    navigationManager.navigate(AccountScreens.TransactionsFilter)
                }

                TransactionsViewEvents.NavigateToTransactionInfoScreen -> {

                }

                TransactionsViewEvents.NavigateToTransactionSearchScreen -> {

                }
            }
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = AppTheme.colorScheme.background1Neutral)
    ) {
        AppTopBar(
            model = viewState.selectedDeposit,
            startIcon = ClickableIcon(
                IconType.ImageVector(Icons.Filled.ArrowForward),
                onClick = {
                    viewModel.handle(TransactionsViewActions.NavigateBack)
                }
            ),
            endIcon = ClickableIcon(
                IconType.ImageVector(Icons.Filled.Search),
                onClick = {
                    viewModel.handle(TransactionsViewActions.NavigateToTransactionSearchScreen)
                }
            ),
            onClick = {
                viewModel.handle(TransactionsViewActions.ShowDepositListBottomSheet)
            }
        )
        DynamicTabSelector(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 16.dp, end = 16.dp, top = 16.dp, bottom = 32.dp),
            containerColor = AppTheme.colorScheme.backgroundTintNeutralDefault,
            unselectedTextColor = AppTheme.colorScheme.onBackgroundNeutralSubdued,
            tabs = optionTexts,
            selectedOption = selectedOption.intValue
        ) {
            selectedOption.intValue = it
        }

        when (selectedOption.intValue) {
            0 -> {
                AllTransactionsSection(viewModel, viewState)
            }

            1 -> {
                SendTransactionsSection(
                    viewState.totalSendTransactions,
                ) { }
            }

            2 -> {
                SendReceiveTransactionsSection(viewState.totalReceiveTransactions) {}
            }
        }

        if (viewState.showDepositListBottomSheet)
            DepositBottomSheet(
                title = "سپرده ها",
                items = viewState.deposits.mapToDepositMenu(),
                onDismiss = {
                    viewModel.handle(TransactionsViewActions.CloseDepositListBottomSheet(null))
                }
            ) {
                viewModel.handle(
                    TransactionsViewActions.CloseDepositListBottomSheet(
                        it.mapToDepositModel()
                    )
                )
            }
    }
}

@Composable
fun SendReceiveTransactionsSection(
    totalReceiveTransaction: Double,
    onTransactionClick: () -> Unit = {}
) {
    val tempList = listOf<String>(
        "واریز به کارت",
        "واریر به سپرده",
        "حواله",
        "سود ماهیانه"
    )

    RowOfMonth(modifier = Modifier.padding(bottom = 32.dp), 0)
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp),
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(containerColor = AppTheme.colorScheme.background1Neutral),
        border = BorderStroke(1.dp, Color(0xFFB8B8BC)),
        elevation = CardDefaults.cardElevation(0.dp)
    ) {

        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            BodySmallText(
                modifier = Modifier.padding(vertical = 8.dp),
                text = "مجموع دریافت ها"
            )
            Row(
                modifier = Modifier.padding(bottom = 16.dp)
            ) {
                Headline6Text(text = totalReceiveTransaction.toCurrency())

                BodySmallText(text = "ریال")
            }

            LazyColumn(
                contentPadding = PaddingValues(12.dp),
                verticalArrangement = Arrangement.spacedBy(24.dp)
            ) {
                items(tempList.size) { item ->
                    SingleRow(
                        title = tempList[item],
                        amount = 12300.0,
                        onClick = onTransactionClick
                    )
                }
            }

        }
    }
}

@Composable
private fun AllTransactionsSection(
    viewModel: TransactionsViewModel,
    viewState: TransactionsViewState
) {
    StatementAndFilters(
        filterList = viewState.filterList,
        onFilterClick = {
            viewModel.handle(TransactionsViewActions.NavigateToTransactionFilterScreen)
        },
        onFilterItemClick = {
            viewModel.handle(TransactionsViewActions.RemoveFilterFromList(it))
        },
        onStatementClick = {
            viewModel.handle(TransactionsViewActions.NavigateToDepositStatementScreen)
        })
    LazyColumn(
        contentPadding = PaddingValues(12.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        items(viewState.allTransactions.size) { item ->
            TransactionRow(viewState.allTransactions[item]) {
                viewModel.handle(TransactionsViewActions.NavigateToTransactionInfoScreen)
            }
        }
    }
}

@Composable
private fun SendTransactionsSection(
    totalSentTransaction: Double,
    onTransactionClick: () -> Unit = {}
) {
    val tempList = listOf<String>(
        "خرید اینترنتی",
        "خرید از فروشگاه",
        "کارت به کارت",
        "حواله",
        "پرداخت قبض",
    )
    RowOfMonth(modifier = Modifier.padding(bottom = 32.dp), 0)
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp),
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(containerColor = AppTheme.colorScheme.background1Neutral),
        border = BorderStroke(1.dp, Color(0xFFB8B8BC)),
        elevation = CardDefaults.cardElevation(0.dp)
    ) {

        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            BodySmallText(
                modifier = Modifier.padding(vertical = 8.dp),
                text = "مجموع برداشت ها"
            )
            Row(
                modifier = Modifier.padding(bottom = 16.dp)
            ) {
                Headline6Text(text = totalSentTransaction.toCurrency())

                BodySmallText(text = "ریال")
            }

            LazyColumn(
                contentPadding = PaddingValues(12.dp),
                verticalArrangement = Arrangement.spacedBy(24.dp)
            ) {
                items(tempList.size) { item ->
                    SingleRow(
                        title = tempList[item],
                        amount = 12300.0,
                        onClick = onTransactionClick
                    )
                }
            }

        }
    }
}

@Composable
private fun SingleRow(
    title: String,
    amount: Double,
    onClick: () -> Unit
) {
    Row(
        modifier = Modifier.clickable { onClick },
        verticalAlignment = Alignment.CenterVertically
    ) {
        Headline6Text(text = title)
        Spacer(modifier = Modifier.weight(1f))
        CaptionText(
            text = amount.toCurrency(),
            color = AppTheme.colorScheme.onBackgroundNeutralSubdued
        )
        CaptionText(
            text = "ریال",
            color = AppTheme.colorScheme.onBackgroundNeutralSubdued
        )
        AppIcon(
            icon = Icons.Default.ChevronLeft,
            style = IconStyle(tint = AppTheme.colorScheme.onBackgroundNeutralSubdued)
        )
    }
}

@Composable
fun AppTopBar(
    model: DepositModel?,
    modifier: Modifier = Modifier,
    requiredHeight: Boolean = true,
    startIcon: ClickableIcon? = null,
    endIcon: ClickableIcon? = null,
    onClick: () -> Unit
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .then(
                if (requiredHeight)
                    modifier.height(64.dp)
                else
                    Modifier
            ),
    ) {
        if (startIcon != null) {
            AppButtonIcon(
                modifier = Modifier.align(Alignment.CenterStart),
                icon = startIcon.icon,
                onClick = startIcon.onClick
            )
        }
        if (endIcon != null) {
            AppButtonIcon(
                modifier = Modifier.align(Alignment.CenterEnd),
                icon = endIcon.icon,
                onClick = endIcon.onClick
            )
        }

        ChipWithIcon(
            modifier = Modifier
                .align(Alignment.Center)
                .border(
                    width = 1.dp,
                    color = AppTheme.colorScheme.strokeNeutral1Default,
                    shape = RoundedCornerShape(12.dp)
                ),
            roundedShape = 12.dp,
            value = model?.desc ?: (model?.depositNumber).toString(),
            startIcon = Icons.Default.ArrowDropDown,
            clickable = onClick,
            color = Color.Transparent,
            assetColor = AppTheme.colorScheme.onBackgroundNeutralDefault
        )

    }
}

@Composable
private fun StatementAndFilters(
    filterList: List<TransactionFilter> = listOf(),
    itemSpacing: Dp = 8.dp,
    onFilterClick: () -> Unit,
    onFilterItemClick: (Int) -> Unit,
    onStatementClick: () -> Unit,
) {
    Column {
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Row(
                modifier = Modifier
                    .padding(all = 8.dp)
                    .clickable { onFilterClick() },
            ) {
                Icon(
                    imageVector = Icons.Outlined.Tune,
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
                    text = "صورتحساب", color = AppTheme.colorScheme.onBackgroundNeutralCTA
                )
            }
        }
        if (filterList.isNotEmpty())
            LazyRow(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 16.dp),
                contentPadding = PaddingValues(horizontal = itemSpacing),
                horizontalArrangement = Arrangement.spacedBy(itemSpacing)
            ) {
                items(filterList.size) {
                    val item = filterList[it]
                    ChipWithIcon(
                        modifier = Modifier
                            .border(
                                width = 1.dp,
                                color = AppTheme.colorScheme.strokeNeutral1Default,
                                shape = RoundedCornerShape(16.dp)
                            ),
                        roundedShape = 16.dp,
                        value = item.title,
                        endIcon = Icons.Default.Close,
                        clickable = { onFilterItemClick(it) },
                        color = AppTheme.colorScheme.background1Neutral,
                        assetColor = AppTheme.colorScheme.onBackgroundNeutralDefault
                    )
                }
            }
    }
}


@Composable
fun RowOfMonth(modifier: Modifier = Modifier, currentMonth: Int) {
    val realMonth: List<Pair<String, Int>> = listOf(
        "فروردین" to 0,
        "اردیبهشت" to 1,
        "خرداد" to 2,
        "تیر" to 3,
        "مرداد" to 4,
        "شهریور" to 5,
        "مهر" to 6,
        "آبان" to 7,
        "آذر" to 8,
        "دی" to 9,
        "بهمن" to 10,
        "اسفند" to 11,
    )

    val showMonth = realMonth.apply {
        val shiftIndex = (currentMonth + 2) % this.size

        this.drop(shiftIndex) + this.take(shiftIndex)
    }

    var selectedMonth by remember { mutableIntStateOf(currentMonth) }
    val listState = rememberLazyListState()

    LaunchedEffect(selectedMonth) {
        val index = showMonth.indexOfFirst { it.second == selectedMonth }
        if (index != -1) {
            listState.animateScrollToItem(index, scrollOffset = (showMonth.size / 2) * -70)
        }
    }
    LazyRow(
        modifier = modifier.fillMaxWidth(),
        state = listState,
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        contentPadding = PaddingValues(horizontal = 16.dp)
    ) {
        itemsIndexed(showMonth) { _, month ->
            val isSelected = month.second == selectedMonth
            MonthItem(
                month = month.first,
                isSelected = isSelected,
                onClick = { selectedMonth = month.second },
                year = "1404",
                isEnabled = showMonth.last() != month
            )
        }
    }
}

@Composable
fun MonthItem(
    modifier: Modifier = Modifier,
    year: String,
    month: String,
    isSelected: Boolean,
    isEnabled: Boolean,
    onClick: () -> Unit
) {
    Box(
        modifier = modifier
            .clip(RoundedCornerShape(8.dp))
            .background(
                if (isSelected)
                    AppTheme.colorScheme.foregroundPrimaryDefault
                else
                    AppTheme.colorScheme.backgroundTintNeutralDefault
            )
            .clickable { if (isEnabled) onClick() }
            .padding(horizontal = 16.dp, vertical = 8.dp)
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            AppText(
                title = year,
                style = TextStyle(
                    color =
                        if (isSelected) AppTheme.colorScheme.onForegroundNeutralDefault
                        else if (!isEnabled) Color(0xFFB8B8BC)
                        else AppTheme.colorScheme.onBackgroundTintNeutralDefault,
                    typography = AppTheme.typography.caption,
                ),
            )
            Spacer(modifier = Modifier.height(8.dp))
            AppText(
                title = month,
                style = TextStyle(
                    color =
                        if (isSelected) AppTheme.colorScheme.onForegroundNeutralDefault
                        else if (!isEnabled) Color(0xFFB8B8BC)
                        else AppTheme.colorScheme.onBackgroundTintNeutralDefault,
                    typography =
                        if (isSelected) AppTheme.typography.buttonLarge
                        else AppTheme.typography.buttonSmall,
                ),
            )

        }
    }
}

@Composable
fun AppText(modifier: Modifier = Modifier, title: String, style: TextStyle? = null) {
    val textColor = style?.color ?: Color.Unspecified
    val typography = style?.typography ?: AppTypography.bodyLarge
    Text(
        modifier = modifier,
        text = title,
        color = textColor,
        style = typography,
        textAlign = style?.textAlign
    )
}

@Composable
fun TransactionRow(item: TransactionModel, onClick: () -> Unit = {}) {
    Row(
        modifier = Modifier
            .height(48.dp)
            .clickable {
                onClick()
            },
        verticalAlignment = Alignment.CenterVertically
    ) {
        AppImage(
            image = if (item.type == TransactionType.WITHDRAWAL) R.drawable.ic_withdraw else R.drawable.ic_receive,
            style = ImageStyle(size = Size.FIX(42.dp))
        )
        Spacer(modifier = Modifier.width(13.dp))
        Column(
            modifier = Modifier.fillMaxHeight(),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Headline6Text(text = item.title)
                Spacer(modifier = Modifier.weight(1f))//Color/On Background/Neutral/Subdued
                CaptionText(
                    text = item.amount.toCurrency(),
                    color = AppTheme.colorScheme.onBackgroundNeutralSubdued
                )
                CaptionText(
                    text = item.currency,
                    color = AppTheme.colorScheme.onBackgroundNeutralSubdued
                )
                AppIcon(
//                    modifier = Modifier
//                        .padding(8.dp)
//                        .clickable {  },
                    icon = Icons.Default.ChevronLeft,
                    style = IconStyle(tint = AppTheme.colorScheme.onBackgroundNeutralSubdued)
                )
            }
            CaptionText(text = item.date)
        }
    }
}

data class TransactionFilter(val title: String)