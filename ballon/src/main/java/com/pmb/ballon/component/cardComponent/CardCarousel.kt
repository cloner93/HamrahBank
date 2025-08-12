package com.pmb.ballon.component.cardComponent

import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.launch
import java.io.File
import kotlin.math.abs

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun CardCarousel(
    items: List<File?>,
    selectedIndex: Int,
    onCardSelected: (Int) -> Unit
) {
    val pagerState = rememberPagerState(
        initialPage = selectedIndex,
        pageCount = { items.size }
    )
    val scope = rememberCoroutineScope()

    val screenWidth = LocalConfiguration.current.screenWidthDp.dp
    val cardWidth = screenWidth * 0.7f // Show ~1.2 cards
    val contentPadding = (screenWidth - cardWidth) / 4

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(230.dp)
    ) {
        HorizontalPager(
            state = pagerState,
            pageSpacing = 0.dp,
            contentPadding = PaddingValues(horizontal = contentPadding),
            modifier = Modifier.fillMaxSize(),
            userScrollEnabled = false
        ) { page ->

            val pageOffset = (
                    (pagerState.currentPage - page) + pagerState.currentPageOffsetFraction
                    ).coerceIn(-1f, 1f)

            val scale = 1f - 0.2f * abs(pageOffset)
            val isLandscape = remember(items[page]) {
                items[page]?.let { isImageActuallyHorizontal(it) } ?: true
            }
            val animatedWidth by animateDpAsState(
                targetValue = if (isLandscape) cardWidth else cardWidth * 0.5f,
                label = "animatedCardWidth"
            )
            Box(
                modifier = Modifier
                    .width(animatedWidth) // Always reserve full width
                    .fillMaxHeight()
                    .graphicsLayer {
                        scaleX = scale
                        scaleY = scale
                        cameraDistance = 20 * density
                    }
                    .clickable {
                        scope.launch {
                            pagerState.animateScrollToPage(page)
                            onCardSelected(page)
                        }
                    },
                contentAlignment = Alignment.Center
            ) {
                AsyncInnerCardImage(
                    imageFile = items[page],
                    cardWidth = animatedWidth
                )
            }
        }
    }
}



