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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.FilterAltOff
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.pmb.account.presentation.component.ChipWithIcon
import com.pmb.account.presentation.component.ShowPersianDatePickerBottomSheet
import com.pmb.account.presentation.transactions.filterScreen.viewmodel.TransactionsFilterViewActions
import com.pmb.account.presentation.transactions.filterScreen.viewmodel.TransactionsFilterViewEvents
import com.pmb.account.presentation.transactions.filterScreen.viewmodel.TransactionsFilterViewModel
import com.pmb.account.presentation.transactions.filterScreen.viewmodel.entity.TransactionFilter
import com.pmb.ballon.component.base.AppButton
import com.pmb.ballon.component.base.AppButtonIcon
import com.pmb.ballon.component.base.AppClickableReadOnlyTextField
import com.pmb.ballon.component.base.AppNumberTextField
import com.pmb.ballon.component.base.AppTopBar
import com.pmb.ballon.component.base.BodyMediumText
import com.pmb.ballon.component.base.ButtonMediumText
import com.pmb.ballon.component.base.IconType
import com.pmb.ballon.ui.theme.AppTheme
import com.pmb.core.presentation.NavigationManager

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun TransactionFilterScreen(navigationManager: NavigationManager) {

    val viewModel = hiltViewModel<TransactionsFilterViewModel>()
    val viewState by viewModel.viewState.collectAsState()

    val keyboardController = LocalSoftwareKeyboardController.current
    val focusRequesterTo = remember { FocusRequester() }

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

            AppNumberTextField(
                modifier = Modifier.fillMaxWidth(),
                label = "از",
                value = viewState.fromPrice ?: "",
                keyboardActions = KeyboardActions(
                    onNext = {
                        focusRequesterTo.requestFocus()
                    },
                    onDone = {
                        focusRequesterTo.requestFocus()
                        keyboardController?.hide()
                    }
                ),
                keyboardOptions = KeyboardOptions.Default.copy(
                    keyboardType = KeyboardType.Number,
                    imeAction = ImeAction.Next
                ),
                onValueChange = { it ->
                    viewModel.handle(TransactionsFilterViewActions.ChangeFromPrice(it))

                },
                trailingIcon = {
                    BodyMediumText(
                        text = stringResource(com.pmb.ballon.R.string.real_carrency),
                        color = AppTheme.colorScheme.onBackgroundNeutralSubdued
                    )
                },
            )
            Spacer(modifier = Modifier.height(24.dp))

            AppNumberTextField(
                modifier = Modifier.fillMaxWidth(),
                label = "تا",
                value = viewState.toPrice ?: "",
                keyboardOptions = KeyboardOptions.Default.copy(
                    keyboardType = KeyboardType.Number,
                    imeAction = ImeAction.Done
                ),
                onValueChange = {
                    viewModel.handle(TransactionsFilterViewActions.ChangeToPrice(it))
                },
                focusRequester = focusRequesterTo,
                trailingIcon = {
                    BodyMediumText(
                        text = stringResource(com.pmb.ballon.R.string.real_carrency),
                        color = AppTheme.colorScheme.onBackgroundNeutralSubdued
                    )
                },
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
                    Icon(
                        imageVector = Icons.Outlined.FilterAltOff,
                        contentDescription = null,
                        tint = AppTheme.colorScheme.onBackgroundErrorDefault
                    )
                    ButtonMediumText(
                        modifier = Modifier.padding(horizontal = 8.dp),
                        text = "حذف فیلترها",
                        color = AppTheme.colorScheme.onBackgroundErrorDefault
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