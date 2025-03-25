package com.pmb.auth.presentation.reentry.reentry_face_detection

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
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.lifecycle.compose.LocalLifecycleOwner
import com.pmb.auth.R
import com.pmb.auth.presentation.ekyc.face_photo.viewModel.FacePhotoCapturedViewEvents
import com.pmb.auth.presentation.reentry.reentry_face_detection.viewModel.ReentryFaceDetectionViewActions
import com.pmb.auth.presentation.reentry.reentry_face_detection.viewModel.ReentryFaceDetectionViewModel
import com.pmb.ballon.component.AlertComponent
import com.pmb.ballon.component.base.AppButton
import com.pmb.ballon.component.base.AppContent
import com.pmb.ballon.component.base.AppLoading
import com.pmb.ballon.component.base.AppTopBar
import com.pmb.ballon.component.base.BodyMediumText
import com.pmb.ballon.ui.theme.AppTheme
import com.pmb.camera.platform.PhotoViewActions
import com.pmb.core.presentation.NavigationManager
import com.pmb.home.presentation.HomeScreens

@Composable
fun ReentryFaceDetectionScreen(
    navigationManager: NavigationManager,
    viewModel: ReentryFaceDetectionViewModel
) {
    val lifecycleOwner = LocalLifecycleOwner.current
    val viewState by viewModel.viewState.collectAsState()
    val context = LocalContext.current
    val previewView = remember { PreviewView(context) }

    LaunchedEffect(viewState.isCapturingPhoto) {
        if (viewState.isCapturingPhoto)
            viewModel.handle(PhotoViewActions.PreviewCamera(previewView, lifecycleOwner))
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
        viewModel.handle(PhotoViewActions.RequestCameraPermission(permissionLauncher))
    }
    LaunchedEffect(viewState.hasCameraPermission) {
        viewModel.handle(PhotoViewActions.RequestFilePermission(multiplePermissionLauncher))
    }
    LaunchedEffect(Unit) {
        viewModel.viewEvent.collect { event ->
            when (event) {
                FacePhotoCapturedViewEvents.FacePhotoCaptured -> {
                    navigationManager.navigate(HomeScreens.Home)
                }
            }
        }
    }
    AppContent(
        modifier = Modifier.padding(horizontal = 16.dp),
        topBar = {
            AppTopBar(
                title = stringResource(R.string.enter_face_detection),
                onBack = {
                    navigationManager.navigateBack()
                }
            )
        },
        footer = {

            AppButton(modifier = Modifier
                .fillMaxWidth()
                .padding(start = 16.dp, end = 16.dp, top = 16.dp),
                enable = true,
                title = stringResource(R.string.login),
                onClick = {
                    viewModel.handle(ReentryFaceDetectionViewActions.SendFacePhoto("DD"))
                })
        },
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Spacer(modifier = Modifier.size(24.dp))

        BodyMediumText(
            text = stringResource(R.string.face_detection_description),
            textAlign = TextAlign.Center,
            color = AppTheme.colorScheme.onBackgroundPrimarySubdued

        )
        Spacer(modifier = Modifier.size(41.dp))
        AnimatedVisibility(
            visible = !viewState.photoCaptured,
            exit = fadeOut(tween(100, easing = LinearEasing)),
            enter = fadeIn(tween(100, easing = LinearEasing))
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(355.dp)
                    .border(
                        1.dp,
                        AppTheme.colorScheme.strokeNeutral3Rest,
                        RoundedCornerShape(16.dp)
                    )
                    .background(
                        AppTheme.colorScheme.stateLayerNeutralModalBackground,
                        RoundedCornerShape(size = 16.dp)
                    )
            ) {
                AndroidView(

                    factory = { context ->
                        previewView
                    },
                    modifier = Modifier
                        .fillMaxSize()
                        .clip(RoundedCornerShape(16.dp))
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