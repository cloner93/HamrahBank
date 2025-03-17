package com.pmb.account.presentation.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.pmb.ballon.R
import com.pmb.ballon.component.AmountText
import com.pmb.ballon.component.base.AppImage
import com.pmb.ballon.component.base.BodySmallText
import com.pmb.ballon.component.base.Headline4Text
import com.pmb.ballon.models.ImageStyle
import com.pmb.ballon.models.Size


data class CardModel(
    val cardNumber: String,
    val amount: Double,
    val currency: String,
    val placeholder: String,
    val expiredDate: String
)

@Composable
fun CardRow(item: CardModel, onClick: (CardModel) -> Unit) {
    val cardNumberSplint = item.cardNumber.chunked(4)
    Column(modifier = Modifier.fillMaxWidth()) {
        Card(
            modifier = Modifier
                .aspectRatio(320f / 186f)
                .clip(shape = RoundedCornerShape(16.dp))
                .clickable { onClick(item) },
            colors = CardDefaults.cardColors(containerColor = Color(0xFFF68080)),
            elevation = CardDefaults.cardElevation(8.dp)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(24.dp),
                verticalArrangement = Arrangement.SpaceBetween
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    AppImage(
                        image = R.drawable.img_mellat_logo,
                        style = ImageStyle(size = Size.FIX(34.dp))
                    )
                    AmountText(amount = item.amount, currency = item.currency)
                }

                Spacer(modifier = Modifier.height(20.dp))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Headline4Text(text = cardNumberSplint[3])
                    Headline4Text(text = cardNumberSplint[2])
                    Headline4Text(text = cardNumberSplint[1])
                    Headline4Text(text = cardNumberSplint[0])
                }

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    BodySmallText(text = item.placeholder)
                    BodySmallText(text = item.expiredDate)
                }
            }
        }
    }
}