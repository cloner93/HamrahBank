package com.pmb.auth.presentaion.ekyc.facePhoto

import androidx.camera.view.PreviewView
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import com.pmb.auth.R
import com.pmb.auth.presentaion.AuthScreens
import com.pmb.ballon.component.base.AppButton
import com.pmb.ballon.component.base.AppContent
import com.pmb.ballon.component.base.AppTopBar
import com.pmb.ballon.component.base.BodyMediumText
import com.pmb.ballon.ui.theme.AppTheme
import com.pmb.core.presentation.NavigationManager

@Composable
fun FacePhotoCapture(navigationManager: NavigationManager) {
    var isPhotoCaptured by remember {
        mutableStateOf(false)
    }
    AppContent(
        modifier = Modifier.padding(horizontal = 16.dp),
        topBar = {
            AppTopBar(
                title = stringResource(R.string.face_photo),
                onBack = {
                    navigationManager.navigateBack()
                }
            )
        },
        footer = {
            AnimatedVisibility(
                visible = !isPhotoCaptured,
                exit = fadeOut(tween(100, easing = LinearEasing)),
                enter = fadeIn(tween(100, easing = LinearEasing))
            ) {
                Box(modifier = Modifier.fillMaxWidth()) {
                    IconButton(
                        modifier = Modifier
                            .align(Alignment.Center)
                            .padding(bottom = 16.dp)
                            .size(66.dp),
                        onClick = {
                            isPhotoCaptured = true
                        }
                    ) {
                        Image(
                            painter = painterResource(com.pmb.ballon.R.drawable.ic_camera_button),
                            contentDescription = "camera Icon"
                        )
                    }
                }
            }
            AnimatedVisibility(
                visible = isPhotoCaptured,
            ) {
                AppButton(modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 16.dp, end = 16.dp, top = 16.dp),
                    enable = true,
                    title = stringResource(R.string._continue),
                    onClick = {
                        navigationManager.navigate(AuthScreens.AuthenticationVideo)
                    })
            }
        },
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Spacer(modifier = Modifier.size(24.dp))

        BodyMediumText(
            text = if (!isPhotoCaptured) stringResource(R.string.take_your_face_photo) else stringResource(
                R.string.take_your_face_photo_continue
            ),
            textAlign = TextAlign.Center,
            color = AppTheme.colorScheme.onBackgroundPrimarySubdued

        )
        Spacer(modifier = Modifier.size(20.dp))
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(312.dp)
                .background(Color.White, RoundedCornerShape(size = 16.dp))
        ) {
            AndroidView(

                factory = { context ->
                    val previewView = PreviewView(context).apply {
                        scaleType = PreviewView.ScaleType.FIT_CENTER
                    }
                    previewView
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(312.dp)
                    .clip(RoundedCornerShape(16.dp))
                    .border(
                        1.dp,
                        AppTheme.colorScheme.strokeNeutral3Rest,
                        RoundedCornerShape(16.dp)
                    )
            )
        }

    }
}