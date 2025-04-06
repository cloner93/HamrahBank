package com.pmb.account.presentation.transactions.filterScreen

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.outlined.FilterAltOff
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.pmb.account.R
import com.pmb.account.presentation.component.ChipWithIcon
import com.pmb.account.presentation.transactions.filterScreen.viewmodel.TransactionsFilterViewActions
import com.pmb.account.presentation.transactions.filterScreen.viewmodel.TransactionsFilterViewEvents
import com.pmb.account.presentation.transactions.filterScreen.viewmodel.TransactionsFilterViewModel
import com.pmb.account.presentation.transactions.filterScreen.viewmodel.entity.TransactionFilter
import com.pmb.ballon.component.PersianDatePicker
import com.pmb.ballon.component.base.AppBottomSheet
import com.pmb.ballon.component.base.AppButton
import com.pmb.ballon.component.base.AppButtonIcon
import com.pmb.ballon.component.base.AppClickableReadOnlyTextField
import com.pmb.ballon.component.base.AppTopBar
import com.pmb.ballon.component.base.BodyMediumText
import com.pmb.ballon.component.base.ButtonMediumText
import com.pmb.ballon.component.base.ClickableIcon
import com.pmb.ballon.component.base.IconType
import com.pmb.ballon.ui.theme.AppTheme
import com.pmb.core.presentation.NavigationManager


// TODO:
/**
- handle numbers in textFields ---> set separator
 */
@OptIn(ExperimentalLayoutApi::class)
@Composable
fun TransactionFilterScreen(navigationManager: NavigationManager) {

    val viewModel = hiltViewModel<TransactionsFilterViewModel>()
    val viewState by viewModel.viewState.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.viewEvent.collect { event ->
            when (event) {
                TransactionsFilterViewEvents.NavigateBack -> {
                    navigationManager.navigateBack()
                }

                TransactionsFilterViewEvents.NavigateBackWithData -> {
                    navigationManager.setPreviousScreenData<TransactionFilter>(
                        "transactionFilter",
                        viewState.transactionFilter!!
                    )

                    navigationManager.navigateBack()
                }
            }
        }
    }

    val focusRequesterTo = remember { FocusRequester() }

    Box(
        modifier = Modifier
            .background(color = AppTheme.colorScheme.background1Neutral)
            .fillMaxSize()
            .padding(all = 12.dp)
            .verticalScroll(rememberScrollState()),
    ) {
        Column(
            horizontalAlignment = Alignment.Start
        ) {

            AppTopBar(
                title = "فیلتر ها",
                onBack = {
                    viewModel.handle(TransactionsFilterViewActions.NavigateBack)
                }
            )
            Spacer(modifier = Modifier.height(24.dp))
            BodyMediumText(
                text = "نوع تراکنش",
                color = AppTheme.colorScheme.onBackgroundNeutralSubdued
            )
            Spacer(modifier = Modifier.height(24.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
            ) {
                ChipWithIcon(
                    modifier = Modifier.weight(1f),
                    value = "همه",
                    clickable = {
                        if (viewState.transactionType == TransactionType.ALL)
                            viewModel.handle(
                                TransactionsFilterViewActions.SelectTransactionType(
                                    null
                                )
                            )
                        else
                            viewModel.handle(
                                TransactionsFilterViewActions.SelectTransactionType(TransactionType.ALL)
                            )
                    },
                    color = if (viewState.transactionType == TransactionType.ALL) AppTheme.colorScheme.stateLayerNeutralPressed else Color.White,
                    assetColor = Color.Black,
                    borderColor = AppTheme.colorScheme.strokeNeutral1Default,
                    fillMaxWidth = true
                )
                Spacer(modifier = Modifier.width(8.dp))
                ChipWithIcon(
                    modifier = Modifier.weight(1f),
                    value = "برداشت",
                    clickable = {
                        if (viewState.transactionType == TransactionType.SEND)
                            viewModel.handle(
                                TransactionsFilterViewActions.SelectTransactionType(
                                    null
                                )
                            )
                        else
                            viewModel.handle(
                                TransactionsFilterViewActions.SelectTransactionType(
                                    TransactionType.SEND
                                )
                            )
                    },
                    color = if (viewState.transactionType == TransactionType.SEND) AppTheme.colorScheme.stateLayerNeutralPressed else Color.White,
                    assetColor = Color.Black,
                    borderColor = AppTheme.colorScheme.strokeNeutral1Default,
                    fillMaxWidth = true
                )
                Spacer(modifier = Modifier.width(8.dp))
                ChipWithIcon(
                    modifier = Modifier.weight(1f),
                    value = "واریز",
                    clickable = {
                        if (viewState.transactionType == TransactionType.RECEIVE)
                            viewModel.handle(
                                TransactionsFilterViewActions.SelectTransactionType(
                                    null
                                )
                            )
                        else
                            viewModel.handle(
                                TransactionsFilterViewActions.SelectTransactionType(
                                    TransactionType.RECEIVE
                                )
                            )
                    },
                    color = if (viewState.transactionType == TransactionType.RECEIVE) AppTheme.colorScheme.stateLayerNeutralPressed else Color.White,
                    assetColor = Color.Black,
                    borderColor = AppTheme.colorScheme.strokeNeutral1Default,
                    fillMaxWidth = true
                )

            }

            Spacer(modifier = Modifier.height(44.dp))
            BodyMediumText(
                text = "مبلغ تراکنش",
                color = AppTheme.colorScheme.onBackgroundNeutralSubdued
            )
            Spacer(modifier = Modifier.height(24.dp))

            MBTextField(
                modifier = Modifier.fillMaxWidth(),
                label = "از",
                action = ImeAction.Next,
                value = viewState.fromPrice ?: "",
                onValueChange = {
                    viewModel.handle(TransactionsFilterViewActions.ChangeFromPrice(it))
                },
                onAction = {
                    focusRequesterTo.requestFocus()
                }
            )
            Spacer(modifier = Modifier.height(24.dp))
            MBTextField(
                modifier = Modifier.fillMaxWidth(),
                label = "تا",
                action = ImeAction.Done,
                value = viewState.toPrice ?: "",
                onValueChange = {
                    viewModel.handle(TransactionsFilterViewActions.ChangeToPrice(it))
                },
                focusRequester = focusRequesterTo,
                onAction = {

                }
            )

            Spacer(modifier = Modifier.height(44.dp))
            BodyMediumText(
                text = "انتخاب تاریخ",
                color = AppTheme.colorScheme.onBackgroundNeutralSubdued
            )
            Spacer(modifier = Modifier.height(24.dp))
            FlowRow(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                maxItemsInEachRow = 3,
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {

                ChipWithIcon(
                    modifier = Modifier.weight(1f),
                    fillMaxWidth = true,
                    value = "امروز",
                    clickable = {
                        if (viewState.dateType == DateType.TODAY)
                            viewModel.handle(TransactionsFilterViewActions.SelectDateType(null))
                        else
                            viewModel.handle(
                                TransactionsFilterViewActions.SelectDateType(
                                    DateType.TODAY
                                )
                            )
                    },
                    color = if (viewState.dateType == DateType.TODAY) AppTheme.colorScheme.stateLayerNeutralPressed else Color.White,
                    assetColor = Color.Black,
                    borderColor = AppTheme.colorScheme.strokeNeutral1Default,
                )
                ChipWithIcon(
                    modifier = Modifier.weight(1f),
                    fillMaxWidth = true,
                    value = "هفته گذشته",
                    clickable = {
                        if (viewState.dateType == DateType.LAST_WEEK)
                            viewModel.handle(TransactionsFilterViewActions.SelectDateType(null))
                        else
                            viewModel.handle(
                                TransactionsFilterViewActions.SelectDateType(
                                    DateType.LAST_WEEK
                                )
                            )
                    },
                    color = if (viewState.dateType == DateType.LAST_WEEK) AppTheme.colorScheme.stateLayerNeutralPressed else Color.White,
                    assetColor = Color.Black,
                    borderColor = AppTheme.colorScheme.strokeNeutral1Default,
                )

                ChipWithIcon(
                    modifier = Modifier.weight(1f),
                    fillMaxWidth = true,
                    value = "ماه گذشته",
                    clickable = {
                        if (viewState.dateType == DateType.LAST_MONTH)
                            viewModel.handle(TransactionsFilterViewActions.SelectDateType(null))
                        else
                            viewModel.handle(
                                TransactionsFilterViewActions.SelectDateType(
                                    DateType.LAST_MONTH
                                )
                            )
                    },
                    color = if (viewState.dateType == DateType.LAST_MONTH) AppTheme.colorScheme.stateLayerNeutralPressed else Color.White,
                    assetColor = Color.Black,
                    borderColor = AppTheme.colorScheme.strokeNeutral1Default,
                )
                ChipWithIcon(
                    modifier = Modifier.weight(1f),
                    fillMaxWidth = true,
                    value = "ماه جاری",
                    clickable = {
                        if (viewState.dateType == DateType.CURRENT_MONTH)
                            viewModel.handle(TransactionsFilterViewActions.SelectDateType(null))
                        else
                            viewModel.handle(
                                TransactionsFilterViewActions.SelectDateType(
                                    DateType.CURRENT_MONTH
                                )
                            )
                    },
                    color = if (viewState.dateType == DateType.CURRENT_MONTH) AppTheme.colorScheme.stateLayerNeutralPressed else Color.White,
                    assetColor = Color.Black,
                    borderColor = AppTheme.colorScheme.strokeNeutral1Default,
                )
                ChipWithIcon(
                    modifier = Modifier.weight(1f),
                    fillMaxWidth = true,
                    value = "انتخاب بازه دلخواه",
                    clickable = {
                        if (viewState.dateType == DateType.CUSTOM)
                            viewModel.handle(TransactionsFilterViewActions.SelectDateType(null))
                        else
                            viewModel.handle(
                                TransactionsFilterViewActions.SelectDateType(
                                    DateType.CUSTOM
                                )
                            )
                    },
                    color = if (viewState.dateType == DateType.CUSTOM) AppTheme.colorScheme.stateLayerNeutralPressed else Color.White,
                    assetColor = Color.Black,
                    borderColor = AppTheme.colorScheme.strokeNeutral1Default,
                )
            }

            if (viewState.dateType == DateType.CUSTOM) {
                Column {
                    Spacer(modifier = Modifier.height(32.dp))
                    AppClickableReadOnlyTextField(
                        modifier = Modifier.height(56.dp),
                        value = viewState.fromDate ?: "",
                        label = "از تاریخ",
                        trailingIcon = {
                            AppButtonIcon(
                                icon = IconType.Painter(painterResource(com.pmb.ballon.R.drawable.ic_calendar_month)),
                                onClick = {}
                            )
                        },
                        onClick = {
                            viewModel.handle(TransactionsFilterViewActions.ShowFromDatePicker)
                        })

                    Spacer(modifier = Modifier.height(24.dp))
                    AppClickableReadOnlyTextField(
                        modifier = Modifier.height(56.dp),
                        value = viewState.toDate ?: "",
                        label = "تا تاریخ",
                        trailingIcon = {
                            AppButtonIcon(
                                icon = IconType.Painter(painterResource(com.pmb.ballon.R.drawable.ic_calendar_month)),
                                onClick = {}
                            )
                        },
                        onClick = {
                            viewModel.handle(TransactionsFilterViewActions.ShowToDatePicker)
                        })
                }
            }
        }

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.BottomCenter)
                .background(color = AppTheme.colorScheme.background1Neutral)
                .padding(16.dp)
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Row(
                    modifier = Modifier
                        .padding(horizontal = 10.dp, vertical = 8.dp)
                        .clickable {
                            viewModel.handle(TransactionsFilterViewActions.ClearFilters)
                        }) {
                    ButtonMediumText(
                        modifier = Modifier.padding(horizontal = 8.dp),
                        text = "حذف فیلترها",
                        color = AppTheme.colorScheme.onBackgroundErrorDefault
                    )
                    Icon(
                        imageVector = Icons.Outlined.FilterAltOff,
                        contentDescription = null,
                        tint = AppTheme.colorScheme.onBackgroundErrorDefault
                    )
                }

                Spacer(modifier = Modifier.height(12.dp))

                AppButton(
                    modifier = Modifier.fillMaxWidth(),
                    title = "اعمال فیلترها",
                    onClick = {
                        viewModel.handle(TransactionsFilterViewActions.ApplyFilters)
                    })
            }
        }
    }


    if (viewState.showFromDatePicker) {
        ShowPersianDatePickerBottomSheet(
            onChangeValue = { year, month, day ->
                viewModel.handle(TransactionsFilterViewActions.CloseFromDatePicker("$year$month$day"))
            },
            onDismiss = {
                viewModel.handle(TransactionsFilterViewActions.CloseFromDatePicker(null))
            },
            title = "از تاریخ"
        )
    }
    if (viewState.showToDatePicker) {
        ShowPersianDatePickerBottomSheet(
            onChangeValue = { year, month, day ->
                viewModel.handle(TransactionsFilterViewActions.CloseToDatePicker("$year$month$day"))
            },
            onDismiss = {
                viewModel.handle(TransactionsFilterViewActions.CloseToDatePicker(null))
            },
            title = "تا تاریخ"
        )
    }
}

enum class TransactionType(val string: String) {
    ALL("همه"), SEND("برداشت"), RECEIVE("واریز")
}

enum class DateType(val string: String) {
    TODAY("امروز"),
    LAST_WEEK("هفته گذشته"),
    LAST_MONTH("ماه گذشته"),
    CURRENT_MONTH("ماه جاری"),
    CUSTOM("انتخاب بازه دلخواه")
}

@Composable
fun ShowPersianDatePickerBottomSheet(
    onChangeValue: (year: String, month: String, day: String) -> Unit,
    onDismiss: () -> Unit,
    title: String
) {
    var isVisible by remember { mutableStateOf(true) }
    var _year by remember { mutableStateOf("") }
    var _month by remember { mutableStateOf("") }
    var _day by remember { mutableStateOf("") }

    AppBottomSheet(
        isVisible = isVisible,
        cancelable = true,
        onDismiss = { onDismiss() },
        content = { nestedConnection ->
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(color = AppTheme.colorScheme.onForegroundNeutralDefault)
                    .padding(24.dp)
                    .nestedScroll(nestedConnection),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                AppTopBar(
                    title = title,
                    requiredHeight = false,
                    startIcon = ClickableIcon(
                        icon = IconType.ImageVector(Icons.Default.Close),
                        onClick = { isVisible = false })
                )
                Spacer(modifier = Modifier.size(24.dp))
                PersianDatePicker(
                    onChangeValue = { year, month, day ->
                        _year = year
                        _month = month
                        _day = day
                    }
                )
                Spacer(modifier = Modifier.size(8.dp))
                AppButton(
                    modifier = Modifier.fillMaxWidth(),
                    title = stringResource(R.string._continue),
                    onClick = {
                        isVisible = false
                        onChangeValue(_year, _month, _day)
                    })
            }
        })
}

@Composable
fun MBTextField(
    modifier: Modifier,
    label: String,
    action: ImeAction,
    value: String,
    onValueChange: (String) -> Unit,
    focusRequester: FocusRequester = remember { FocusRequester() },
    onAction: () -> Unit = {}
) {
    val keyboardController = LocalSoftwareKeyboardController.current

    OutlinedTextField(
        modifier = modifier
            .focusRequester(focusRequester),
        value = value,
        onValueChange = { input ->
            if (input.all { it.isDigit() }) onValueChange(input)
        },
        label = {
            BodyMediumText(
                text = label,
                color = AppTheme.colorScheme.onBackgroundNeutralDefault
            )
        },
        keyboardOptions = KeyboardOptions.Default.copy(
            keyboardType = KeyboardType.Number,
            imeAction = action
        ),
        keyboardActions = KeyboardActions(
            onNext = { onAction() },
            onDone = {
                onAction()
                keyboardController?.hide()
            }
        ),
        trailingIcon = {
            BodyMediumText(
                text = stringResource(com.pmb.ballon.R.string.real_carrency),
                color = AppTheme.colorScheme.onBackgroundNeutralSubdued
            )
        },
        colors = OutlinedTextFieldDefaults.colors(
            focusedBorderColor = AppTheme.colorScheme.onBackgroundNeutralDefault,
            unfocusedBorderColor = AppTheme.colorScheme.strokeNeutral1Default,
            focusedLabelColor = AppTheme.colorScheme.onBackgroundNeutralDefault,
            unfocusedLabelColor = AppTheme.colorScheme.strokeNeutral1Default,
            cursorColor = AppTheme.colorScheme.onBackgroundNeutralDefault,
            errorBorderColor = AppTheme.colorScheme.onBackgroundErrorDefault,
        ),
        singleLine = true
    )
}