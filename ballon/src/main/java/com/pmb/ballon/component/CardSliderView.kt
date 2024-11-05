package com.pmb.ballon.component

import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun CardSliderView(cards: List<String>) {
    var selectedIndex by remember { mutableStateOf(0) }
    val itemSpacing = 16.dp
    val cardWidth = 280.dp
    val nextCardOffset = 60.dp

    LazyRow(
        modifier = Modifier.fillMaxWidth(),
        contentPadding = PaddingValues(horizontal = itemSpacing),
        horizontalArrangement = Arrangement.spacedBy(itemSpacing)
    ) {
        items(cards.size) { index ->
            Box(
                modifier = Modifier
                    .width(cardWidth)
                    .height(200.dp)
                    .offset(
                        x = if (index == selectedIndex) 0.dp else nextCardOffset
                    )
                    .background(
                        color = if (index == selectedIndex) Color(0xFF3F51B5) else Color(0xFFBBDEFB),
                        shape = RoundedCornerShape(16.dp)
                    )
                    .scrollable(
                        state = rememberScrollState(),
                        orientation = Orientation.Horizontal
                    )
                    .padding(16.dp)
            ) {
                Text(
                    text = cards[index],
                    color = Color.White,
                    fontSize = 20.sp,
                    modifier = Modifier.align(Alignment.Center)
                )
            }
        }
    }
}

@Composable
fun CardSliderExample() {
    val cards = listOf("Card 1", "Card 2", "Card 3", "Card 4", "Card 5")
    CardSliderView(cards = cards)
}
