package com.pmb.ballon.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.outlined.Circle
import androidx.compose.material3.BottomSheetDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import com.pmb.ballon.R
import com.pmb.ballon.component.base.AppBottomSheet
import com.pmb.ballon.component.base.BodyMediumText
import com.pmb.ballon.component.base.CaptionText
import com.pmb.ballon.component.base.Headline6Text
import com.pmb.ballon.models.DepositBottomSheetModel
import com.pmb.ballon.ui.theme.AppTheme
import com.pmb.core.utils.toCurrency

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DepositBottomSheet(
    title: String? = null,
    items: List<DepositBottomSheetModel> = listOf(),
    onDismiss: () -> Unit,
    onSelect: (DepositBottomSheetModel) -> Unit
) {
    var isVisible by remember { mutableStateOf(true) }
    AppBottomSheet(
        isVisible = isVisible,
        cancelable = true,
        dragHandle = { BottomSheetDefaults.DragHandle() },
        onDismiss = { onDismiss() },
        content = {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                title?.let { Headline6Text(text = it) }
                LazyColumn {
                    items(items) { item ->
                        DepositRow(item, onClick = { selectedItem ->
                            isVisible = false
                            onSelect(selectedItem)
                        })
                    }
                }
            }
        })
}

@Composable
private fun DepositRow(
    deposit: DepositBottomSheetModel,
    onClick: (DepositBottomSheetModel) -> Unit = {}
) {
    var checkBoxState by remember { mutableStateOf(deposit.selected) }

    val color =
        if (deposit.state == 1)
            AppTheme.colorScheme.onBackgroundNeutralDefault
        else
            AppTheme.colorScheme.onBackgroundNeutralDisabled

    Row(
        modifier = Modifier
            .padding(end = 12.dp, top = 12.dp, bottom = 12.dp)
            .clickable(deposit.state == 1) {
                checkBoxState = !checkBoxState
                onClick(deposit.copy(selected = checkBoxState))
            },
        verticalAlignment = Alignment.CenterVertically
    ) {
        CircleCheckbox(selected = checkBoxState, onChecked = {
            checkBoxState = !checkBoxState
            onClick(deposit.copy(selected = checkBoxState))
        })

        Column(modifier = Modifier.weight(1f)) {
            Headline6Text(text = deposit.title, color = color)
            Spacer(modifier = Modifier.height(4.dp))
            CaptionText(text = deposit.desc ?: "", color = color)
        }

        Column(horizontalAlignment = Alignment.End) {
            BodyMediumText(
                text = deposit.depositNumber,
                color = color
            )
            Spacer(modifier = Modifier.height(4.dp))
            Row(verticalAlignment = Alignment.CenterVertically) {
                Headline6Text(text = deposit.amount.toCurrency(), color = color)
                Spacer(modifier = Modifier.width(4.dp))
                CaptionText(text = deposit.currency, color = color)
            }
        }
    }
}

@Composable
fun CircleCheckbox(selected: Boolean, enabled: Boolean = false, onChecked: () -> Unit) {
    val imageVector = if (selected) Icons.Filled.CheckCircle else Icons.Outlined.Circle
    val tint =
        if (selected)
            AppTheme.colorScheme.onBackgroundNeutralCTA
        else
            AppTheme.colorScheme.onBackgroundNeutralSubdued

    IconButton(
        onClick = { onChecked() },
        enabled = enabled
    ) {
        Icon(
            imageVector = imageVector,
            tint = tint,
            modifier = Modifier.background(Color.Transparent, shape = CircleShape),
            contentDescription = "checkbox"
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun DepositItemPrev() {
    val deposit = DepositBottomSheetModel(
        title = "حساب قرض الحسنه آقای مشتاق مودت",
        desc = "تنخواه",
        depositNumber = "123456",
        amount = 100323232.233,
        currency = stringResource(R.string.real_carrency),
        ibanNumber = "IR1234567890098765432112",
        cardNumber = "6219861920241234",
        state = 1,
        categoryCode = 0
    )
    CompositionLocalProvider(LocalLayoutDirection provides LayoutDirection.Rtl) {
        DepositRow(deposit)
    }
}

@Preview(showBackground = true)
@Composable
private fun DepositItem2Prev() {
    val deposit = DepositBottomSheetModel(
        title = "حساب قرض الحسنه آقای مشتاق مودت",
        desc = "تنخواه",
        depositNumber = "123456",
        amount = 100323232.233,
        selected = true,
        currency = stringResource(R.string.real_carrency),
        ibanNumber = "IR1234567890098765432112",
        cardNumber = "6219861920241234",
        state = 1,
        categoryCode = 0
    )
    CompositionLocalProvider(LocalLayoutDirection provides LayoutDirection.Rtl) {
        DepositRow(deposit)
    }
}

@Preview(showBackground = true)
@Composable
private fun DepositItemDisablePrev() {
    val deposit = DepositBottomSheetModel(
        title = "حساب قرض الحسنه",
        desc = "تنخواه",
        depositNumber = "123456",
        amount = 100323232.233,
        selected = false,
        currency = stringResource(R.string.real_carrency),
        ibanNumber = "IR1234567890098765432112",
        cardNumber = "6219861920241234",
        state = 0,
        categoryCode = 0
    )
    CompositionLocalProvider(LocalLayoutDirection provides LayoutDirection.Rtl) {
        DepositRow(deposit)
    }
}