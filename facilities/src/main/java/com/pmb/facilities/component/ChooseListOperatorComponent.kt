package com.pmb.facilities.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.pmb.ballon.component.base.BodyMediumText
import com.pmb.ballon.ui.theme.AppTheme
import com.pmb.facilities.R
import com.pmb.facilities.charge.presentation.buying_charge.ChooseOperator

@Composable
fun ChooseListOperatorComponent(
    modifier: Modifier = Modifier,
    chooseOperator: List<ChooseOperator>
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        BodyMediumText(
            text = stringResource(R.string.choose_transaction_operator),
            color = AppTheme.colorScheme.onBackgroundNeutralDefault
        )
        chooseOperator.forEach { item ->
            ChooseOperatorComponent(Modifier.fillMaxWidth(), item)
        }
    }
}