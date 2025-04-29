package com.pmb.facilities.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.pmb.ballon.component.base.CaptionText
import com.pmb.ballon.component.base.Headline4Text
import com.pmb.ballon.ui.theme.AppTheme
import com.pmb.core.utils.toCurrency

@Composable
fun PriceWithTaxColumnComponent(
    modifier: Modifier = Modifier,
    price: String,
    tax: String
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Headline4Text(
            text = price.toCurrency(),
            color = AppTheme.colorScheme.onBackgroundNeutralDefault
        )
        CaptionText(
            text = tax,
            color = AppTheme.colorScheme.onBackgroundNeutralSubdued
        )
    }
}