package com.pmb.home.presentation.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.pmb.ballon.component.AlertComponent
import com.pmb.ballon.component.SliderPagerComponent
import com.pmb.ballon.component.base.AppButtonIcon
import com.pmb.ballon.component.base.AppContent
import com.pmb.ballon.component.base.AppImage
import com.pmb.ballon.component.base.AppLoading
import com.pmb.ballon.component.base.BodyMediumText
import com.pmb.ballon.component.base.Headline5Text
import com.pmb.ballon.models.ImageStyle
import com.pmb.ballon.models.Size
import com.pmb.core.presentation.NavigationManager
import com.pmb.home.presentation.home.viewModel.HomeViewModel

@Composable
fun HomeScreen(
    navigationManager: NavigationManager,
    viewModel: HomeViewModel
) {
    val viewState by viewModel.viewState.collectAsState()
    AppContent(
        scrollState = null,
        modifier = Modifier.padding(horizontal = 16.dp),
        topBar = {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
                    .height(64.dp),
            ) {
                AppButtonIcon(
                    modifier = Modifier.align(Alignment.CenterStart),
                    icon = painterResource(com.pmb.ballon.R.drawable.ic_help),
                    onClick = {

                    }
                )
                Row(modifier = Modifier.align(Alignment.CenterEnd)) {
                    AppButtonIcon(
                        icon = painterResource(com.pmb.ballon.R.drawable.ic_search),
                        onClick = {

                        }
                    )
                    AppButtonIcon(
                        icon = painterResource(com.pmb.ballon.R.drawable.ic_notification),
                        onClick = {

                        }
                    )
                }
                Headline5Text(modifier = Modifier.align(Alignment.Center), text = "همراه بانک ملت")
            }
        }
    ) {
        viewState.homeData?.let {
            SliderPagerComponent(
                images = it.sliderImage,
                modifier = Modifier.fillMaxWidth(),
                isInfinitePageCount = false,
                pageCount = 4,
                isAutoScroll = true
            )
            LazyVerticalGrid(
                columns = GridCells.Fixed(3),
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp),
                horizontalArrangement = Arrangement.spacedBy(16.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp),
                userScrollEnabled = true,
                contentPadding = PaddingValues(bottom = 32.dp)
            ) {
                items(it.homeItems) { item ->
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        modifier = Modifier
                            .padding(8.dp),
                        verticalArrangement = Arrangement.Center
                    ) {
                        AppImage(
                            image = painterResource(item.img),
                            style = ImageStyle(size = Size.FIX(all = 58.dp))
                        )
                        BodyMediumText(
                            text = item.title,
                            textAlign = TextAlign.Center,
                            modifier = Modifier.fillMaxWidth()
                        )
                    }
                }
            }
        }
    }

    if (viewState.isLoading) {
        AppLoading()
    }
    if (viewState.alertModelState != null) {
        AlertComponent(viewState.alertModelState!!)
    }
}


