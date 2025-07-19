package com.pmb.auth.presentation.component.cardComponent

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun CardFlip(
    front: @Composable () -> Unit,
    back: @Composable () -> Unit,
    isBack: Boolean
) {
    val rotation by animateFloatAsState(
        targetValue = if (isBack) 180f else 0f,
        animationSpec = tween(durationMillis = 600),
        label = "flipAnim"
    )

    val cameraDistancePx = with(LocalDensity.current) {
        30.dp.toPx()
    }

    Box(
        modifier = Modifier
            .graphicsLayer {
                rotationY = rotation
                this.cameraDistance = cameraDistancePx
            }

    ) {
        if (rotation <= 90f) {
            front()
        } else {
            Box(
                modifier = Modifier.graphicsLayer { rotationY = 180f }
            ) {
                back()
            }
        }
    }
}

@Composable
fun CardImage(
    imageAddress: Int, //todo it should change with image url from backend
    isHorizontal: Boolean,
    horizontalPadding : Dp = 40.dp
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .then(
                if (isHorizontal)
                    Modifier
                        .aspectRatio(1.5f)
                else
                    Modifier
                        .aspectRatio(0.9f)
                        .padding(horizontal = horizontalPadding)
            )
            .background(Color.Transparent, RoundedCornerShape(16.dp)),
        contentAlignment = Alignment.Center
    ) {
        Image(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.Transparent, RoundedCornerShape(16.dp)),
            contentScale = ContentScale.FillBounds,
            painter = painterResource(id = imageAddress), contentDescription = null
        )
    }
}

@Composable
fun HorizontalCardBack(
    imageAddress: Int, //todo it should change with image url from backend,

) {
    Box(
        modifier = Modifier
            .width(240.dp)
            .height(160.dp)
            .background(Color.Transparent, RoundedCornerShape(16.dp)),
        contentAlignment = Alignment.Center
    ) {
        Image(
            modifier = Modifier
                .width(240.dp)
                .height(160.dp)
                .background(Color.Transparent, RoundedCornerShape(16.dp)),
            painter = painterResource(id = imageAddress), contentDescription = null
        )
    }
}

@Composable
fun VerticalCardFront(
    imageAddress: Int, //todo it should change with image url from backend
) {
    Box(
        modifier = Modifier

            .background(Color(0xFFD61C4E), RoundedCornerShape(16.dp))
            .padding(16.dp),
        contentAlignment = Alignment.Center
    ) {
        Text("نام و نام خانوادگی", color = Color.White)
    }
}

@Composable
fun VerticalCardBack() {
    Box(
        modifier = Modifier
            .width(160.dp)
            .height(240.dp)
            .background(Color(0xFFB41635), RoundedCornerShape(16.dp))
            .padding(16.dp),
        contentAlignment = Alignment.Center
    ) {
        Text("cvv2: ***", color = Color.White)
    }
}

