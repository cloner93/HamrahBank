package com.pmb.ballon.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.pmb.ballon.ui.theme.AppTheme
import kotlinx.coroutines.delay

@Composable
fun SliderPagerComponent(
    images: List<String>,
    modifier: Modifier = Modifier,
    isInfinitePageCount: Boolean,
    pageCount: Int,
    isAutoScroll: Boolean,
    autoScrollIntervalMs: Long = 3000,
) {
    val actualItemCount = images.size
    val startIndex =
        if (isInfinitePageCount) pageCount / 2 - (pageCount / 2 % actualItemCount) else 0
    val pagerState = rememberPagerState(initialPage = startIndex, pageCount = { pageCount })
    if (isAutoScroll)
        LaunchedEffect(Unit) {
            while (true) {
                delay(autoScrollIntervalMs)
                pagerState.animateScrollToPage(pagerState.currentPage + 1)
            }
        }

    Column(modifier = modifier.padding()) {
        HorizontalPager(
            state = pagerState,
            modifier = Modifier
                .fillMaxWidth()
                .aspectRatio(16f / 9f),
            pageSpacing = 12.dp
        ) { index ->
            val imageUrl = images[index % actualItemCount]
            AsyncImage(
                modifier = Modifier
                    .fillMaxWidth()
                    .aspectRatio(16f / 8f),
                model = ImageRequest.Builder(LocalContext.current)
                    .data(imageUrl)
                    .crossfade(true)
                    .build(),
                placeholder = painterResource(com.pmb.ballon.R.drawable.ic_bank_ansar),
                contentDescription = "SliderImageHeader",
            )
        }

        Row(
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                ,
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            val currentRealPage = pagerState.currentPage % actualItemCount
            repeat(actualItemCount) { index ->
                val isSelected = index == currentRealPage
                Box(
                    modifier = Modifier
                        .padding(horizontal = 4.dp)
                        .size(6.dp)
                        .clip(CircleShape)
                        .background(
                            if (isSelected) AppTheme.colorScheme.foregroundNeutralDefault
                            else AppTheme.colorScheme.foregroundNeutralRest
                        )

                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun SliderPagerComponentPreview() {
    SliderPagerComponent(
        images = arrayListOf("1", "2","3","4"),
        modifier = Modifier.fillMaxWidth(),
        isInfinitePageCount = true,
        pageCount = Int.MAX_VALUE,
        isAutoScroll = false
    )
}