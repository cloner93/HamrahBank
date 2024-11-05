package com.pmb.account.presentation.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.pmb.ballon.R
import com.pmb.ballon.component.base.AppButtonIcon
import com.pmb.ballon.component.base.BodySmallText
import com.pmb.ballon.component.base.CaptionText
import com.pmb.ballon.component.base.Headline2Text
import com.pmb.ballon.ui.theme.AppTheme
import com.pmb.core.utils.toCurrency


data class DepositModel(
    val title: String,
    val depositNumber: String,
    val amount: Double,
    val currency: String,
    val ibanNumber: String,
    val cardNumber: String
)

@Composable
fun DepositWidget(
    item: DepositModel,
    moreClick: () -> Unit,
    copyDepositNumberClick: () -> Unit,
) {
    Card(
        modifier = Modifier
            .fillMaxWidth(),
        shape = RoundedCornerShape(20.dp), // Corner radius of 20.dp
        colors = CardDefaults.cardColors(containerColor = AppTheme.colorScheme.background1Neutral),
        elevation = CardDefaults.cardElevation(8.dp)
    ) {
        Column(
            modifier = Modifier
                .padding(16.dp)
                .background(color = AppTheme.colorScheme.background1Neutral)
        ) {
            Row(
                modifier = Modifier.offset(y = (-8).dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                AppButtonIcon(
                    modifier = Modifier.offset(x = (-8).dp),
                    icon = R.drawable.ic_more_horizontal,
                    onClick = moreClick
                )
                Spacer(modifier = Modifier.weight(1f))
                AppButtonIcon(icon = R.drawable.ic_copy, onClick = copyDepositNumberClick)
                BodySmallText(text = item.depositNumber)
            }
            Spacer(modifier = Modifier.height(20.dp))
            CaptionText(modifier = Modifier.padding(horizontal = 8.dp), text = item.title)
            Spacer(modifier = Modifier.height(12.dp))
            Row(verticalAlignment = Alignment.CenterVertically) {
                Spacer(modifier = Modifier.weight(1f))
                Headline2Text(text = item.amount.toCurrency())
                Spacer(modifier = Modifier.width(6.dp))
                BodySmallText(text = item.currency)
            }
        }
    }
}