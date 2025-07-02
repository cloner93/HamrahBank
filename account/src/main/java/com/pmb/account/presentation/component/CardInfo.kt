package com.pmb.account.presentation.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import com.pmb.account.R
import com.pmb.ballon.component.base.AppImage
import com.pmb.ballon.component.base.ButtonMediumText
import com.pmb.ballon.component.base.ButtonSmallText
import com.pmb.ballon.component.base.Headline4Text
import com.pmb.ballon.component.base.Headline5Text
import com.pmb.ballon.component.base.Headline6Text
import com.pmb.ballon.models.ImageStyle
import com.pmb.ballon.models.Size
import com.pmb.core.utils.toCurrency
import com.pmb.domain.model.CardModel
import com.pmb.domain.model.CardType

@Composable
fun CardInfo(item: CardModel, onClick: (CardModel) -> Unit) {
    val cardNumberSplint = item.cardNumber.chunked(4)
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .aspectRatio(320f / 170f)
            .clip(shape = RoundedCornerShape(16.dp))
            .clickable { onClick(item) },
        elevation = CardDefaults.cardElevation(8.dp)
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    brush = Brush.linearGradient(
                        colors = listOf(Color(0xFFC1112E), Color(0xFFFF4861))
                    )
                )
        ) {
            Image(
                painter = painterResource(id = R.drawable.mellat_logo_line),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier.fillMaxSize()
            )
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .drawBehind {
                        val arcWidth = size.width * 1.5f
                        val arcHeight = size.height * 1.2f

                        val topLeft = Offset(
                            x = -size.width * 0.25f,
                            y = size.height * 0.4f
                        )

                        drawArc(
                            brush = Brush.radialGradient(
                                colors = listOf(
                                    Color.Transparent,
                                    Color.White.copy(alpha = 0.16f),
                                ),
                                center = Offset(
                                    x = size.width / 2f,
                                    y = size.height * 2
                                ),
                                radius = size.minDimension
                            ),
                            startAngle = -180f,
                            sweepAngle = 180f,
                            useCenter = false,
                            topLeft = topLeft,
                            size = androidx.compose.ui.geometry.Size(arcWidth, arcHeight)
                        )
                    }
            )
            Column(
                modifier = Modifier
                    .padding(24.dp)
                    .fillMaxSize(),
                verticalArrangement = Arrangement.SpaceBetween
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        AppImage(
                            image = R.drawable.mellat_logo_white,
                            style = ImageStyle(size = Size.FIX(34.dp))
                        )

                        Spacer(modifier = Modifier.width(6.dp))
                        Headline6Text(text = item.cardType.cardType, color = Color.White)
                    }
                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Headline5Text(text = item.amount.toCurrency(), color = Color.White)
                        Spacer(modifier = Modifier.width(6.dp))
                        ButtonSmallText(text = item.currency, color = Color.White)
                    }
                }

                Spacer(modifier = Modifier.height(20.dp))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Headline4Text(text = cardNumberSplint[3], color = Color.White)
                    Headline4Text(text = cardNumberSplint[2], color = Color.White)
                    Headline4Text(text = cardNumberSplint[1], color = Color.White)
                    Headline4Text(text = cardNumberSplint[0], color = Color.White)
                }

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    ButtonMediumText(text = item.placeholder, color = Color.White)
                    ButtonMediumText(text = "انقضاء " + item.expiredDate, color = Color.White)
                }
            }
        }

    }
}

@Preview(showBackground = true)
@Composable
private fun CardInfoPreview() {
    CompositionLocalProvider(
        LocalLayoutDirection provides LayoutDirection.Rtl
    ) {
        val cardModel = CardModel(
            cardNumber = "1234123412341234",
            amount = 200000.0,
            currency = "ریال",
            placeholder = "میلاد طارقلی",
            expiredDate = "04/07",
            cardType = CardType.MELLAT_CARD
        )
        CardInfo(item = cardModel) { }
    }
}