package com.pmb.auth.presentation.ekyc.authentication_video

import android.view.Gravity
import android.widget.FrameLayout
import android.widget.VideoView
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.camera.view.PreviewView
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.max
import androidx.compose.ui.viewinterop.AndroidView
import androidx.lifecycle.compose.LocalLifecycleOwner
import com.pmb.auth.R
import com.pmb.auth.presentation.AuthScreens
import com.pmb.auth.presentation.ekyc.authentication_video.viewModel.AuthenticationCapturingVideoViewActions
import com.pmb.auth.presentation.ekyc.authentication_video.viewModel.AuthenticationCapturingVideoViewEvents
import com.pmb.auth.presentation.ekyc.authentication_video.viewModel.AuthenticationCapturingVideoViewModel
import com.pmb.auth.presentation.first_login_confirm.viewModel.TimerStatus
import com.pmb.auth.presentation.first_login_confirm.viewModel.TimerTypeId
import com.pmb.ballon.component.AlertComponent
import com.pmb.ballon.component.base.AppButton
import com.pmb.ballon.component.base.AppContent
import com.pmb.ballon.component.base.AppImage
import com.pmb.ballon.component.base.AppLoading
import com.pmb.ballon.component.base.AppTopBar
import com.pmb.ballon.component.base.BodyMediumText
import com.pmb.ballon.component.base.Headline4Text
import com.pmb.ballon.ui.theme.AppTheme
import com.pmb.camera.platform.VideoViewActions
import com.pmb.core.presentation.NavigationManager

@Composable
fun AuthenticationVideoScreen(
    navigationManager: NavigationManager,
    viewModel: AuthenticationCapturingVideoViewModel
) {
    val lifecycleOwner = LocalLifecycleOwner.current
    val viewState by viewModel.viewState.collectAsState()
    val context = LocalContext.current
    val previewView = remember { PreviewView(context) }
    val title: String =
        if (viewState.timerState?.get(TimerTypeId.VIDEO_TAKEN_TIMER)?.timerStatus != TimerStatus.IS_LOADING) {
            "${viewState.calculateMinute(TimerTypeId.VIDEO_TAKEN_TIMER)}:${
                viewState.calculateSecond(
                    TimerTypeId.VIDEO_TAKEN_TIMER
                )
            }/00:20"
        } else ""
    LaunchedEffect(viewState.hasCameraPermission) {
        if (viewState.hasCameraPermission)
            viewModel.handle(VideoViewActions.PreviewCamera(previewView, lifecycleOwner))
    }

    val permissionLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestPermission()
    ) { isGranted ->
        viewModel.onSinglePermissionResult(isGranted)
    }

    val multiplePermissionLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestMultiplePermissions()
    ) {
        viewModel.onMultiplePermissionResult(it)
    }
    LaunchedEffect(Unit) {
        viewModel.handle(VideoViewActions.RequestCameraPermission(permissionLauncher))
    }
    LaunchedEffect(viewState.hasCameraPermission) {
        viewModel.handle(VideoViewActions.RequestFilePermission(multiplePermissionLauncher))
    }
    LaunchedEffect(Unit) {
        viewModel.viewEvent.collect { event ->
            when (event) {
                AuthenticationCapturingVideoViewEvents.VideoCaptured -> {
                    navigationManager.navigate(AuthScreens.AuthenticationConfirmStep)
                }
            }
        }
    }
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
            Spacer(modifier = Modifier.size(10.dp))
            AnimatedVisibility(
                visible = !viewState.videoCaptured && !viewState.isCapturingVideo,
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
                            viewModel.handle(VideoViewActions.VideoCaptured)
                        }
                    ) {
                        AppImage(
                            image = painterResource(com.pmb.ballon.R.drawable.ic_video_camera),
                        )
                    }
                }
            }
            AnimatedVisibility(
                visible = viewState.isCapturingVideo && !viewState.videoCaptured,
            ) {
                Column {
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
                                .background(
                                    AppTheme.colorScheme.foregroundPrimaryDefault,
                                    CircleShape
                                )
                        )
                    }
                    Spacer(modifier = Modifier.size(10.dp))
                    Box(modifier = Modifier.fillMaxWidth()) {
                        IconButton(
                            modifier = Modifier
                                .align(Alignment.Center)
                                .padding(bottom = 16.dp)
                                .size(66.dp),
                            onClick = {
                                viewModel.handle(AuthenticationCapturingVideoViewActions.FinishTimer)
                            },
                            enabled = !viewState.isCompressing
                        ) {
                            AppImage(
                                image = painterResource(com.pmb.ballon.R.drawable.ic_video_stop)
                            )
                        }
                    }
                }
            }
            AnimatedVisibility(
                visible = viewState.videoCaptured,
            ) {
                AppButton(modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 16.dp, end = 16.dp, top = 16.dp),
                    enable = true,
                    title = stringResource(R.string._continue),
                    onClick = {
                        viewModel.handle(AuthenticationCapturingVideoViewActions.SendVideo("FF"))
                    })
            }
        },
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Spacer(modifier = Modifier.size(24.dp))
        if (!viewState.videoCaptured && !viewState.isCapturingVideo)
            BodyMediumText(
                modifier = Modifier.fillMaxWidth(),
                text = buildAnnotatedString {
                    append("پس از فشردن علامت فیلمبرداری،")
                    withStyle(style = SpanStyle(fontWeight = FontWeight.Bold)) {
                        append(" یک جمله ")
                    }
                    append("برای شما نمایش داده می شود که باید آن را بخوانید.")
                },
                color = AppTheme.colorScheme.onBackgroundPrimarySubdued,
            )
        else {
            BodyMediumText(
                text = if (viewState.isCapturingVideo && !viewState.videoCaptured) stringResource(
                    R.string.repeat_sentence
                ) else stringResource(
                    R.string.take_your_video_continue
                ),
                textAlign = TextAlign.Center,
                color = AppTheme.colorScheme.onBackgroundPrimarySubdued

            )
            if (viewState.isCapturingVideo && !viewState.videoCaptured)
                Headline4Text(
                    text = stringResource(
                        R.string.repeated_sentence
                    ),
                    textAlign = TextAlign.Center,
                    color = AppTheme.colorScheme.foregroundPrimaryDefault
                )
        }
        Spacer(modifier = Modifier.size(20.dp))
        if (viewState.savedFileUri != null && viewState.videoCaptured) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .heightIn(max=312.dp)
                    .background(Color.White, RoundedCornerShape(size = 16.dp))
            ) {
                AndroidView(
                    factory = { ctx ->
                        VideoView(ctx).apply {
                            layoutParams = FrameLayout.LayoutParams(
                                FrameLayout.LayoutParams.MATCH_PARENT,
                                FrameLayout.LayoutParams.MATCH_PARENT
                            ).apply { gravity = Gravity.CENTER }
                            setVideoPath(viewState.savedFileUri)
                            setOnPreparedListener { mediaPlayer ->
                                mediaPlayer.start()
                            }
                        }
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(16.dp))
                        .border(
                            1.dp,
                            AppTheme.colorScheme.strokeNeutral3Rest,
                            RoundedCornerShape(16.dp)
                        )
                )

                IconButton(
                    modifier = Modifier
                        .align(Alignment.BottomStart)
                        .padding(8.dp)
                        .size(48.dp),
                    onClick = {
                        viewModel.handle(AuthenticationCapturingVideoViewActions.ClearVideo)
                    }
                ) {
                    AppImage(
                        image = painterResource(com.pmb.ballon.R.drawable.ic_rounded_delete),
                    )
                }
            }
        } else {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(312.dp)
                    .background(Color.White, RoundedCornerShape(size = 16.dp))
            ) {
                AndroidView(
                    factory = { context ->
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
    if (viewState.isLoading) {
        AppLoading()
    }
    if (viewState.alertModelState != null) {
        AlertComponent(viewState.alertModelState!!)
    }
}
