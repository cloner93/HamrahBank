package com.pmb.auth.presentation.register.choose_card

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil.compose.AsyncImage
import com.pmb.auth.presentation.component.cardComponent.AsyncCardImage
import com.pmb.auth.presentation.component.cardComponent.CardFlip
import com.pmb.auth.presentation.component.cardComponent.FlipToggle
import com.pmb.auth.presentation.component.cardComponent.Orientation
import com.pmb.auth.presentation.component.cardComponent.OrientationToggle
import com.pmb.auth.presentation.register.choose_card.viewModel.ChooseCardViewActions
import com.pmb.auth.presentation.register.choose_card.viewModel.ChooseCardViewModel
import com.pmb.ballon.component.base.AppButton
import com.pmb.ballon.component.base.AppContent
import com.pmb.ballon.component.base.AppLoading
import com.pmb.ballon.component.base.AppTopBar
import com.pmb.ballon.ui.theme.AppTheme
import com.pmb.core.utils.toCurrency
import com.pmb.navigation.manager.LocalNavigationManager
import com.pmb.navigation.manager.NavigationManager
import com.pmb.navigation.moduleScreen.RegisterScreens

//
//@Composable
//fun ChooseCardScreen(
//    viewModel: ChooseCardViewModel ,
//    onConfirm: (CardFormatModel?) -> Unit = {}
//) {
//    val state by viewModel.viewState.collectAsState()
//    val coroutineScope = rememberCoroutineScope()
//
////    var orientation by remember { mutableStateOf("vertical") }
//    var flipped by remember { mutableStateOf(false) }
//    var orientationSelected by remember { mutableStateOf(Orientation.Horizontal) }
//    var sideSelected by remember { mutableStateOf(false) }
//    var selectedIndex by remember { mutableIntStateOf(0) }
//    // Auto-detect orientation for cards without it (simulate React useEffect)
////    LaunchedEffect(state.cardFormats) {
////        val updatedCards = state.cardFormats.map { card ->
////            if (card.orientation.isNullOrEmpty()) {
////                // We might do actual image dimension check here (complex in Compose),
////                // For demo, default to vertical:
////                card.copy(orientation = "vertical")
////            } else card
////        }
////        viewModel.setState { it.copy(cardFormats = updatedCards) }
////    }
//
//    // Filter cards by orientation
//    val filteredCards = state.list.filter { if (orientationSelected == Orientation.Horizontal) it.isHorizontal == true else it.isHorizontal == false }
//
//    // Sync selected card and orientation
//    LaunchedEffect(state.selectedCard, filteredCards) {
//        state.selectedCard?.let { selected ->
//            orientationSelected = if (selected.isHorizontal == true) Orientation.Horizontal else Orientation.Vertical
//        } ?: run {
//            filteredCards.firstOrNull()?.let {
//                viewModel.handle(ChooseCardViewActions.SelectCardFormat(it))
//            }
//        }
//    }
//
//    Column(
//        modifier = Modifier
//            .fillMaxSize()
//            .padding(16.dp),
//        horizontalAlignment = Alignment.CenterHorizontally
//    ) {
//        // Tabs
//                    OrientationToggle(
//                modifier = Modifier
//                    .fillMaxWidth()
//                    .padding(horizontal = 50.dp),
//                orientationSelected
//            ) {
//
////                selectedIndex = 0
//                orientationSelected = it
//            }
//
//        Spacer(modifier = Modifier.height(8.dp))
//
//        Text(
//            "برای نمایش پشت کارت، ضربه بزنید.",
//            style = MaterialTheme.typography.bodyMedium,
//            modifier = Modifier.padding(8.dp),
//            textAlign = TextAlign.Center
//        )
//
//        Spacer(modifier = Modifier.height(8.dp))
//
//        // Card preview with flip animation
//        Box(
//            modifier = Modifier
//                .fillMaxWidth()
//                .weight(1f)
//                .aspectRatio(if (orientationSelected == Orientation.Vertical) 2f / 3f else 3f / 2f)
//                .clickable { flipped = !flipped },
//            contentAlignment = Alignment.Center
//        ) {
//            val rotation by animateFloatAsState(targetValue = if (flipped) 180f else 0f)
//
//            val card = state.selectedCard
//            if (card != null) {
//                Box(
//                    modifier = Modifier
//                        .graphicsLayer {
//                            rotationY = rotation
//                            cameraDistance = 8 * density
//                        }
//                        .clip(RoundedCornerShape(12.dp))
//                        .background(MaterialTheme.colorScheme.surface)
//                        .fillMaxSize()
//                ) {
//                    if (rotation <= 90f) {
//                        // Front
//                        AsyncImage(
//                            model = card.imageUris, // imageFile from ViewModel, fallback to base64 string URI
//                            contentDescription = "Card Front",
//                            modifier = Modifier.fillMaxSize(),
//                            contentScale = ContentScale.Crop
//                        )
//                    } else {
//                        // Back
//                        Box(
//                            modifier = Modifier
//                                .graphicsLayer {
//                                    rotationY = 180f
//                                }
//                                .fillMaxSize()
//                        ) {
//                            AsyncImage(
//                                model = card.backImageUris,
//                                contentDescription = "Card Back",
//                                modifier = Modifier.fillMaxSize(),
//                                contentScale = ContentScale.Crop
//                            )
//                        }
//                    }
//                }
//            } else {
//                // Loading or no card
//                CircularProgressIndicator()
//            }
//        }
//
//        Spacer(modifier = Modifier.height(8.dp))
//
//        // Description
//        Text(
//            text = state.selectedCard?.let { card ->
//                if (card.commonAmount == null || card.commonAmount == 0) {
//                    card.formatDescription
//                } else {
//                    "${card.formatDescription} - کارمزد: ${card.commonAmount.toString().toCurrency()} "
//                }
//            } ?: "",
//            style = MaterialTheme.typography.bodySmall,
//            textAlign = TextAlign.Center,
//            modifier = Modifier.padding(horizontal = 16.dp)
//        )
//
//        Spacer(modifier = Modifier.height(8.dp))
//
//        // Carousel for filtered cards
//        LazyRow(
//            modifier = Modifier
//                .fillMaxWidth()
//                .height(140.dp),
//            horizontalArrangement = Arrangement.spacedBy(12.dp),
//            contentPadding = PaddingValues(horizontal = 16.dp)
//        ) {
//            items(filteredCards) { card ->
//                val isSelected = card.formatId == state.selectedCard?.formatId
//                val aspectRatio = if (card.isHorizontal == false) 2f / 3f else 3f / 2f
//
//                Card(
//                    modifier = Modifier
//                        .aspectRatio(aspectRatio)
//                        .clickable {
//                            flipped = false
//                            viewModel.handle(ChooseCardViewActions.SelectCardFormat(card))
//                        }
//                        .shadow(if (isSelected) 8.dp else 2.dp, RoundedCornerShape(8.dp)),
//                    shape = RoundedCornerShape(8.dp),
////                    elevation = if (isSelected) 8.dp else 2.dp,
//                ) {
//                    AsyncImage(
//                        model = card.imageUris,
//                        contentDescription = null,
//                        contentScale = ContentScale.Crop,
//                        modifier = Modifier.fillMaxSize()
//                    )
//                    if (state.isLoading) {
//                        Box(
//                            Modifier
//                                .fillMaxSize()
//                                .background(Color.Black.copy(alpha = 0.2f)),
//                            contentAlignment = Alignment.Center
//                        ) {
//                            CircularProgressIndicator()
//                        }
//                    }
//                }
//            }
//        }
//
//        Spacer(modifier = Modifier.height(16.dp))
//
//        Button(
//            onClick = { onConfirm(state.selectedCard) },
//            enabled = state.selectedCard != null,
//            modifier = Modifier
//                .fillMaxWidth()
//                .height(48.dp)
//        ) {
//            Text("تایید")
//        }
//    }
//}
//
//
//


@Composable
fun ChooseCardScreen(
    viewModel: ChooseCardViewModel
) {
    val navigationManager: NavigationManager = LocalNavigationManager.current
    val viewState by viewModel.viewState.collectAsStateWithLifecycle()
    var orientationSelected by remember { mutableStateOf(Orientation.Vertical) }
    var sideSelected by remember { mutableStateOf(false) }
    var selectedIndex by remember { mutableIntStateOf(0) }

    AppContent(
        modifier = Modifier.padding(horizontal = 16.dp),
        topBar = {
            AppTopBar(
                title = stringResource(com.pmb.ballon.R.string.choose_card),
                onBack = {
                    navigationManager.navigateBack()
                }
            )
        },
        footer = {
            AppButton(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 16.dp, end = 16.dp, top = 16.dp),
                enable = true,
                title = stringResource(com.pmb.ballon.R.string.confirm),
                onClick = {
                    navigationManager.navigate(RegisterScreens.RegisterAuthentication)
                })
        },
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.padding(top = 16.dp))
        if (!viewState.isLoading && viewState.horizontalCardList.isNotEmpty()) {
            OrientationToggle(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 50.dp),
                orientationSelected
            ) {
                orientationSelected = it
                sideSelected = false
                viewModel.handle(ChooseCardViewActions.SelectCardFormat(if (orientationSelected == Orientation.Horizontal) viewState.horizontalCardList.first() else viewState.verticalCardList.first()))
            }
            Spacer(modifier = Modifier.padding(top = 33.dp))
            FlipToggle{
                sideSelected = !sideSelected
            }
            Spacer(modifier = Modifier.padding(top = 20.dp))
            Box(
                modifier = Modifier.fillMaxWidth(),
                contentAlignment = Alignment.Center
            ) {
                CardFlip(
                    front = {
                        AsyncCardImage(
                            viewState.selectedCard?.imageUris,
                            orientationSelected == Orientation.Horizontal
                        )
                    },
                    back = {
                        AsyncCardImage(
                            viewState.selectedCard?.backImageUris,
                            orientationSelected == Orientation.Horizontal
                        )
                    },
                    onClick = {
                        sideSelected = !sideSelected
                    },
                    isBack = sideSelected
                )
            }
            Spacer(modifier = Modifier.padding(top = 16.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center
            ) {
                Text(
                    style = AppTheme.typography.bodySmall,
                    color = AppTheme.colorScheme.onBackgroundNeutralDefault,
                    text = if ((viewState.selectedCard?.commonAmount
                            ?: 0) > 0
                    ) "${viewState.selectedCard?.formatDescription} - کارمزد: ${
                        viewState.selectedCard?.commonAmount?.toString()?.toCurrency()
                    } " else "${viewState.selectedCard?.formatDescription}"
                )
            }
            Spacer(modifier = Modifier.padding(top = 20.dp))
            LazyRow(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(160.dp),
                horizontalArrangement = Arrangement.spacedBy(30.dp),
                contentPadding = PaddingValues(horizontal = 12.dp)
            ) {
                items(if (orientationSelected == Orientation.Horizontal) viewState.horizontalCardList else viewState.verticalCardList) { card ->
                    val isSelected = card.formatId == viewState.selectedCard?.formatId
                    val aspectRatio = if (card.isHorizontal == false) 2f / 3f else 3f / 2f

                    Card(
                        modifier = Modifier
                            .aspectRatio(aspectRatio)
                            .clickable {
//                                flipped = false
                                viewModel.handle(ChooseCardViewActions.SelectCardFormat(card))
                            }
                            .shadow(if (isSelected) 8.dp else 2.dp, RoundedCornerShape(8.dp)),
                        shape = RoundedCornerShape(8.dp),
//                    elevation = if (isSelected) 8.dp else 2.dp,
                    ) {
                        AsyncImage(
                            model = card.imageUris,
                            contentDescription = null,
                            contentScale = ContentScale.Crop,
                            modifier = Modifier.fillMaxSize()
                        )
                        if (viewState.isLoading) {
                            Box(
                                Modifier
                                    .fillMaxSize()
                                    .background(Color.Black.copy(alpha = 0.2f)),
                                contentAlignment = Alignment.Center
                            ) {
                                CircularProgressIndicator()
                            }
                        }
                    }
                }
            }
        } else {
            AppLoading()
        }
    }
}