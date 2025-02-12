package com.pmb.auth.presentaion.ekyc.authenticationVideo

import androidx.camera.view.PreviewView
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
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
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import com.pmb.auth.R
import com.pmb.auth.presentaion.AuthScreens
import com.pmb.ballon.component.base.AppButton
import com.pmb.ballon.component.base.AppContent
import com.pmb.ballon.component.base.AppTopBar
import com.pmb.ballon.component.base.BodyMediumText
import com.pmb.ballon.component.base.Headline4Text
import com.pmb.ballon.ui.theme.AppTheme
import com.pmb.core.presentation.NavigationManager

@Composable
fun AuthenticationVideoScreen(navigationManager: NavigationManager) {
    var isVideoCaptured by remember {
        mutableStateOf(false)
    }
    var isVideoStarted by remember {
        mutableStateOf(false)
    }
    val title = "00:06/00:20"

    AppContent(
        modifier = Modifier.padding(horizontal = 16.dp),
        topBar = {
            AppTopBar(
                title = stringResource(R.string.video_authentication),
                onBack = {
                    navigationManager.navigateBack()
                }
            )
        },
        footer = {
            if (isVideoStarted && !isVideoCaptured) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically
                ) {

                    BodyMediumText(
                        text = title,
                        textAlign = TextAlign.Center,
                        color = AppTheme.colorScheme.onBackgroundPrimarySubdued

                    )
                    Spacer(modifier = Modifier.size(5.dp))
                    Box(
                        modifier = Modifier
                            .padding(bottom = 3.dp)
                            .size(8.dp)
                            .background(AppTheme.colorScheme.foregroundPrimaryDefault, CircleShape)
                    )
                }
            }
            Spacer(modifier = Modifier.size(10.dp))
            AnimatedVisibility(
                visible = !isVideoCaptured && !isVideoStarted,
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
                            isVideoStarted = true
                        }
                    ) {
                        Image(
                            painter = painterResource(com.pmb.ballon.R.drawable.ic_video_camera),
                            contentDescription = "camera Icon"
                        )
                    }
                }
            }
            AnimatedVisibility(
                visible = isVideoStarted && !isVideoCaptured,
            ) {
                Box(modifier = Modifier.fillMaxWidth()) {
                    IconButton(
                        modifier = Modifier
                            .align(Alignment.Center)
                            .padding(bottom = 16.dp)
                            .size(66.dp),
                        onClick = {
                            isVideoCaptured = true
                        }
                    ) {
                        Image(
                            painter = painterResource(com.pmb.ballon.R.drawable.ic_video_stop),
                            contentDescription = "camera Icon"
                        )
                    }
                }
            }
            AnimatedVisibility(
                visible = isVideoCaptured,
            ) {
                AppButton(modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 16.dp, end = 16.dp, top = 16.dp),
                    enable = true,
                    title = stringResource(R.string._continue),
                    onClick = {
                        navigationManager.navigate(AuthScreens.AuthenticationStep)
                    })
            }
        },
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Spacer(modifier = Modifier.size(24.dp))
        if (!isVideoCaptured && !isVideoStarted)
            Text(
                modifier = Modifier.fillMaxWidth(),
                text = buildAnnotatedString {
                    append("پس از فشردن علامت فیلمبرداری،")
                    withStyle(style = SpanStyle(fontWeight = FontWeight.Bold)) {
                        append(" یک جمله ")
                    }
                    append("برای شما نمایش داده می شود که باید آن را بخوانید.")
                },
                color = AppTheme.colorScheme.onBackgroundPrimarySubdued,
                textAlign = TextAlign.Start,
                style = AppTheme.typography.bodyMedium
            )
        else {
            BodyMediumText(
                text = if (isVideoStarted && !isVideoCaptured) stringResource(
                    R.string.repeat_sentence
                ) else stringResource(
                    R.string.take_your_video_continue
                ),
                textAlign = TextAlign.Center,
                color = AppTheme.colorScheme.onBackgroundPrimarySubdued

            )
            if (isVideoStarted && !isVideoCaptured)
                Headline4Text(
                    text = stringResource(
                        R.string.repeated_sentence
                    ),
                    textAlign = TextAlign.Center,
                    color = AppTheme.colorScheme.foregroundPrimaryDefault
                )
        }
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