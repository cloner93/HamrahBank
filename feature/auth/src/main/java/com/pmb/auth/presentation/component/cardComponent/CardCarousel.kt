package com.pmb.auth.presentation.component.cardComponent

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.pmb.auth.R
import com.pmb.ballon.ui.theme.HamrahBankTheme

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun CardCarousel(
    items: List<Int>,
    selectedIndex: Int,
    isHorizontal: Boolean,
    onCardSelected: (Int) -> Unit
) {
    val pagerState = rememberPagerState(
        initialPage = selectedIndex,
        pageCount = { items.size }
    )

    LaunchedEffect(pagerState.currentPage) {
        onCardSelected(pagerState.currentPage)
    }

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(260.dp)
            .graphicsLayer {
                clip = false
            }
    ) {
        HorizontalPager(
            state = pagerState,
            pageSpacing = 8.dp,
            contentPadding = PaddingValues(horizontal = 95.dp),
            modifier = Modifier
                .fillMaxSize()
                .align(Alignment.Center)
                .graphicsLayer {
                    clip = false
                }
        ) { page ->

            val pageOffset = (
                    (pagerState.currentPage - page) + pagerState.currentPageOffsetFraction
                    ).coerceIn(-1f, 1f)

            val scale = 1f - 0.15f * kotlin.math.abs(pageOffset)
            val alpha = 1f - 0.5f * kotlin.math.abs(pageOffset)

            Box (
                modifier = Modifier
                    .graphicsLayer {
                        scaleX = scale
                        scaleY = scale
                        this.alpha = alpha
                        cameraDistance = 12 * density
                    }
//                    .aspectRatio(0.6f),

            ) {
                CardImage(
                    items[page], isHorizontal,horizontalPadding = 0.dp
                )
            }
        }
    }

}

