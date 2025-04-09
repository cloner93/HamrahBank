package com.pmb.account.presentation.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.pmb.account.R
import com.pmb.ballon.component.base.AppImage
import com.pmb.ballon.component.base.CaptionText
import com.pmb.ballon.component.base.Headline6Text
import com.pmb.ballon.models.ImageStyle
import com.pmb.ballon.models.Size
import com.pmb.core.utils.toCurrency

enum class TransactionType {
    DEPOSIT,
    WITHDRAWAL,
    TRANSFER,
    RECEIVE,
    FEE
}


data class TransactionModel(
    val type: TransactionType,
    val title: String,
    val amount: Double,
    val currency: String,
    val date: String
)

@Composable
fun TransactionRow(item: TransactionModel, isAmountVisible: Boolean, onClick: () -> Unit = {}) {
    Row(
        modifier = Modifier
            .height(48.dp)
            .clickable {
                onClick()
            },
        verticalAlignment = Alignment.CenterVertically
    ) {
        AppImage(
            image = when (item.type) {
                TransactionType.DEPOSIT -> R.drawable.ic_transfer
                TransactionType.WITHDRAWAL -> R.drawable.ic_transfer
                TransactionType.TRANSFER -> R.drawable.ic_transfer
                TransactionType.RECEIVE -> R.drawable.ic_receive
                TransactionType.FEE -> R.drawable.ic_transfer
            },
            style = ImageStyle(size = Size.FIX(42.dp))
        )
        Spacer(modifier = Modifier.width(13.dp))
        Column(
            modifier = Modifier.fillMaxHeight(),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Headline6Text(text = item.title)
                Spacer(modifier = Modifier.weight(1f))
                if (isAmountVisible) {
                    Headline6Text(text = item.amount.toCurrency())
                    Spacer(modifier = Modifier.width(6.dp))
                    CaptionText(text = item.currency)
                } else {
                    Headline6Text(text = "********")
                }
            }
            CaptionText(text = item.date)
        }
    }
}