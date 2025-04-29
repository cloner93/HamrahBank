package com.pmb.account.presentation.transactions.statement

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.pmb.account.presentation.component.ShowPersianDatePickerBottomSheet
import com.pmb.account.presentation.transactions.statement.viewmodel.DepositStatementViewActions
import com.pmb.account.presentation.transactions.statement.viewmodel.DepositStatementViewEvents
import com.pmb.account.presentation.transactions.statement.viewmodel.DepositStatementViewModel
import com.pmb.account.utils.toPersianDate
import com.pmb.ballon.component.base.AppButton
import com.pmb.ballon.component.base.AppButtonIcon
import com.pmb.ballon.component.base.AppClickableReadOnlyTextField
import com.pmb.ballon.component.base.AppContent
import com.pmb.ballon.component.base.AppTopBar
import com.pmb.ballon.component.base.BodyMediumText
import com.pmb.ballon.component.base.ChipWithIcon
import com.pmb.ballon.component.base.IconType
import com.pmb.ballon.component.datePicker.ShowPersianDatePickerBottomSheet
import com.pmb.ballon.ui.theme.AppTheme
import com.pmb.navigation.manager.LocalNavigationManager

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun DepositStatementScreen() {

    val viewModel = hiltViewModel<DepositStatementViewModel>()
    val viewState by viewModel.viewState.collectAsState()
    val navigationManager = LocalNavigationManager.current
    LaunchedEffect(Unit) {
        viewModel.viewEvent.collect { event ->
            when (event) {
                DepositStatementViewEvents.GenerateStatement -> {
                    navigationManager.navigateBack()
                }

                DepositStatementViewEvents.NavigateBack -> {
                    navigationManager.navigateBack()
                }
            }
        }
    }

    AppContent(
        modifier = Modifier.padding(horizontal = 16.dp),
        scrollState = rememberScrollState(),
        topBar = {
            AppTopBar(
                title = "دریافت صورت\u200Cحساب",
                onBack = {
                    viewModel.handle(DepositStatementViewActions.NavigateBack)
                }
            )
        },
        footer = {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                AppButton(
                    modifier = Modifier.fillMaxWidth(),
                    enable = viewState.dateType != null,
                    title = "اعمال فیلترها",
                    onClick = {
                        viewModel.handle(DepositStatementViewActions.Apply)
                    })
            }
        }
    ) {
        Column(
            horizontalAlignment = Alignment.Start
        ) {
            Spacer(modifier = Modifier.height(24.dp))
            BodyMediumText(
                text = "انتخاب مدت زمان مورد نظر",
                color = AppTheme.colorScheme.onBackgroundNeutralSubdued
            )
            Spacer(modifier = Modifier.height(24.dp))
            FlowRow(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                maxItemsInEachRow = 3,

                verticalArrangement = Arrangement.spacedBy(16.dp),
            ) {

                ChipWithIcon(
                    modifier = Modifier.weight(1f),
                    fillMaxWidth = true,
                    value = "امروز",
                    clickable = {
                        if (viewState.dateType == DateType.TODAY)
                            viewModel.handle(DepositStatementViewActions.SelectDateType(null))
                        else
                            viewModel.handle(
                                DepositStatementViewActions.SelectDateType(
                                    DateType.TODAY
                                )
                            )
                    },
                    color = if (viewState.dateType == DateType.TODAY) AppTheme.colorScheme.stateLayerNeutralPressed else Color.Transparent,
                    assetColor = AppTheme.colorScheme.onBackgroundNeutralDefault,
                    borderColor = AppTheme.colorScheme.strokeNeutral1Default,
                )
                ChipWithIcon(
                    modifier = Modifier.weight(1f),
                    fillMaxWidth = true,
                    value = "هفته گذشته",
                    clickable = {
                        if (viewState.dateType == DateType.LAST_WEEK)
                            viewModel.handle(DepositStatementViewActions.SelectDateType(null))
                        else
                            viewModel.handle(
                                DepositStatementViewActions.SelectDateType(
                                    DateType.LAST_WEEK
                                )
                            )
                    },
                    color = if (viewState.dateType == DateType.LAST_WEEK) AppTheme.colorScheme.stateLayerNeutralPressed else Color.Transparent,
                    assetColor = AppTheme.colorScheme.onBackgroundNeutralDefault,
                    borderColor = AppTheme.colorScheme.strokeNeutral1Default,
                )

                ChipWithIcon(
                    modifier = Modifier.weight(1f),
                    fillMaxWidth = true,
                    value = "ماه گذشته",
                    clickable = {
                        if (viewState.dateType == DateType.LAST_MONTH)
                            viewModel.handle(DepositStatementViewActions.SelectDateType(null))
                        else
                            viewModel.handle(
                                DepositStatementViewActions.SelectDateType(
                                    DateType.LAST_MONTH
                                )
                            )
                    },
                    color = if (viewState.dateType == DateType.LAST_MONTH) AppTheme.colorScheme.stateLayerNeutralPressed else Color.Transparent,
                    assetColor = AppTheme.colorScheme.onBackgroundNeutralDefault,
                    borderColor = AppTheme.colorScheme.strokeNeutral1Default,
                )
                ChipWithIcon(
                    modifier = Modifier.weight(1f),
                    fillMaxWidth = true,
                    value = "ماه جاری",
                    clickable = {
                        if (viewState.dateType == DateType.CURRENT_MONTH)
                            viewModel.handle(DepositStatementViewActions.SelectDateType(null))
                        else
                            viewModel.handle(
                                DepositStatementViewActions.SelectDateType(
                                    DateType.CURRENT_MONTH
                                )
                            )
                    },
                    color = if (viewState.dateType == DateType.CURRENT_MONTH) AppTheme.colorScheme.stateLayerNeutralPressed else Color.Transparent,
                    assetColor = AppTheme.colorScheme.onBackgroundNeutralDefault,
                    borderColor = AppTheme.colorScheme.strokeNeutral1Default,
                )
                ChipWithIcon(
                    modifier = Modifier.weight(1f),
                    fillMaxWidth = true,
                    value = "دو ماه",
                    clickable = {
                        if (viewState.dateType == DateType.TWO_MONTH)
                            viewModel.handle(DepositStatementViewActions.SelectDateType(null))
                        else
                            viewModel.handle(
                                DepositStatementViewActions.SelectDateType(
                                    DateType.TWO_MONTH
                                )
                            )
                    },
                    color = if (viewState.dateType == DateType.TWO_MONTH) AppTheme.colorScheme.stateLayerNeutralPressed else Color.Transparent,
                    assetColor = AppTheme.colorScheme.onBackgroundNeutralDefault,
                    borderColor = AppTheme.colorScheme.strokeNeutral1Default,
                )
                ChipWithIcon(
                    modifier = Modifier.weight(1f),
                    fillMaxWidth = true,
                    value = "سه ماه",
                    clickable = {
                        if (viewState.dateType == DateType.THREE_MONTH)
                            viewModel.handle(DepositStatementViewActions.SelectDateType(null))
                        else
                            viewModel.handle(
                                DepositStatementViewActions.SelectDateType(
                                    DateType.THREE_MONTH
                                )
                            )
                    },
                    color = if (viewState.dateType == DateType.THREE_MONTH) AppTheme.colorScheme.stateLayerNeutralPressed else Color.Transparent,
                    assetColor = AppTheme.colorScheme.onBackgroundNeutralDefault,
                    borderColor = AppTheme.colorScheme.strokeNeutral1Default,
                )
                ChipWithIcon(
                    modifier = Modifier.weight(1f),
                    fillMaxWidth = false,
                    value = "انتخاب بازه دلخواه",
                    clickable = {
                        if (viewState.dateType == DateType.CUSTOM)
                            viewModel.handle(DepositStatementViewActions.SelectDateType(null))
                        else
                            viewModel.handle(
                                DepositStatementViewActions.SelectDateType(
                                    DateType.CUSTOM
                                )
                            )
                    },
                    color = if (viewState.dateType == DateType.CUSTOM) AppTheme.colorScheme.stateLayerNeutralPressed else Color.Transparent,
                    assetColor = AppTheme.colorScheme.onBackgroundNeutralDefault,
                    borderColor = AppTheme.colorScheme.strokeNeutral1Default,
                )
            }

            if (viewState.dateType == DateType.CUSTOM) {
                Column {
                    Spacer(modifier = Modifier.height(56.dp))
                    AppClickableReadOnlyTextField(
                        value = viewState.fromDate.toPersianDate(),
                        label = "از تاریخ",
                        trailingIcon = {
                            AppButtonIcon(
                                icon = IconType.Painter(painterResource(com.pmb.ballon.R.drawable.ic_calendar_month)),
                                onClick = {}
                            )
                        },
                        onClick = {
                            viewModel.handle(DepositStatementViewActions.ShowFromDatePicker)
                        })

                    Spacer(modifier = Modifier.height(24.dp))
                    AppClickableReadOnlyTextField(
                        value = viewState.toDate.toPersianDate(),
                        label = "تا تاریخ",
                        trailingIcon = {
                            AppButtonIcon(
                                icon = IconType.Painter(painterResource(com.pmb.ballon.R.drawable.ic_calendar_month)),
                                onClick = {}
                            )
                        },
                        onClick = {
                            viewModel.handle(DepositStatementViewActions.ShowToDatePicker)
                        })
                }
            }
        }
    }

    if (viewState.showFromDatePicker) {
        ShowPersianDatePickerBottomSheet(
            onChangeValue = { year, month, day ->
                viewModel.handle(DepositStatementViewActions.CloseFromDatePicker("$year$month$day"))
            },
            onDismiss = {
                viewModel.handle(DepositStatementViewActions.CloseFromDatePicker(null))
            },
            title = "از تاریخ"
        )
    }
    if (viewState.showToDatePicker) {
        ShowPersianDatePickerBottomSheet(
            onChangeValue = { year, month, day ->
                viewModel.handle(DepositStatementViewActions.CloseToDatePicker("$year$month$day"))
            },
            onDismiss = {
                viewModel.handle(DepositStatementViewActions.CloseToDatePicker(null))
            },
            title = "تا تاریخ"
        )
    }
}

enum class DateType(val string: String) {
    TODAY("امروز"),
    LAST_WEEK("هفته گذشته"),
    LAST_MONTH("ماه گذشته"),
    CURRENT_MONTH("ماه جاری"),
    TWO_MONTH("دو ماه"),
    THREE_MONTH("سه ماه"),
    CUSTOM("انتخاب بازه دلخواه")
}