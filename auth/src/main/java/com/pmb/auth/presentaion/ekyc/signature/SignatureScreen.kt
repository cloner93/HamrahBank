package com.pmb.auth.presentaion.ekyc.signature

import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
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
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
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
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.lifecycle.compose.LocalLifecycleOwner
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.pmb.auth.R
import com.pmb.auth.presentaion.AuthScreens
import com.pmb.auth.presentaion.ekyc.signature.viewModel.SignatureViewActions
import com.pmb.auth.presentaion.ekyc.signature.viewModel.SignatureViewEvents
import com.pmb.auth.presentaion.ekyc.signature.viewModel.SignatureViewModel
import com.pmb.ballon.component.AlertComponent
import com.pmb.ballon.component.base.AppButton
import com.pmb.ballon.component.base.AppContent
import com.pmb.ballon.component.base.AppLoading
import com.pmb.ballon.component.base.AppTopBar
import com.pmb.ballon.component.base.BodyMediumText
import com.pmb.ballon.component.base.BodySmallText
import com.pmb.ballon.ui.theme.AppTheme
import com.pmb.camera.platform.PhotoViewActions
import com.pmb.core.presentation.NavigationManager

@Composable
fun SignatureScreen(
    navigationManager: NavigationManager,
    viewModel: SignatureViewModel
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
    LaunchedEffect(Unit) {
        viewModel.handle(PhotoViewActions.RequestFilePermission(multiplePermissionLauncher))
    }
    LaunchedEffect(Unit) {
        viewModel.viewEvent.collect { event ->
            when (event) {
                SignatureViewEvents.SignaturePhotoCaptured -> {
                    navigationManager.navigate(AuthScreens.Authentication)
                }
            }
        }
    }
    AppContent(
        modifier = Modifier.padding(horizontal = 16.dp),
        topBar = {
            AppTopBar(
                title = stringResource(R.string.account_signature),
                onBack = {
                    navigationManager.navigateBack()
                }
            )
        },
        footer = {
            AnimatedVisibility(
                visible = !viewState.photoCaptured,
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
                            viewModel.handle(PhotoViewActions.CapturePhoto)
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
                visible = viewState.photoCaptured,
            ) {
                AppButton(modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 16.dp, end = 16.dp, top = 16.dp),
                    enable = true,
                    title = stringResource(R.string._continue),
                    onClick = {
                        viewModel.handle(SignatureViewActions.SendSignaturePhoto("D"))
                    })
            }
        },
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Spacer(modifier = Modifier.size(24.dp))

        BodyMediumText(
            text = stringResource(R.string.take_your_signature),
            textAlign = TextAlign.Center,
            color = AppTheme.colorScheme.onBackgroundPrimarySubdued

        )
        BodySmallText(
            text = stringResource(R.string.coverage_signature_message),
            textAlign = TextAlign.Center,
            color = AppTheme.colorScheme.onBackgroundPrimarySubdued
        )
        Spacer(modifier = Modifier.size(20.dp))
//        if (state.hasCameraPermission && state.isCameraReady) {
        AnimatedVisibility(
            visible = !viewState.photoCaptured,
            exit = fadeOut(tween(100, easing = LinearEasing)),
            enter = fadeIn(tween(100, easing = LinearEasing))
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(312.dp)
                    .border(
                        1.dp,
                        AppTheme.colorScheme.strokeNeutral3Rest,
                        RoundedCornerShape(16.dp)
                    )
                    .background(Color.White, RoundedCornerShape(size = 16.dp))
            ) {
                AndroidView(

                    factory = { context ->
//                        val previewView = PreviewView(context)
//                        viewModel.handle(
//                            PhotoViewActions.PreviewCamera(
//                                previewView,
//                                lifecycleOwner
//                            )
//                        )
                        previewView
                    },
                    modifier = Modifier
                        .fillMaxSize()
                        .clip(RoundedCornerShape(16.dp))
                )
            }

        }
        AnimatedVisibility(
            visible = !viewState.isCapturingPhoto && viewState.photoCaptured,
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(312.dp)
                    .clip(RoundedCornerShape(16.dp))
                    .border(
                        1.dp,
                        AppTheme.colorScheme.strokeNeutral3Rest,
                        RoundedCornerShape(16.dp)
                    )
            ) {
                AsyncImage(
                    modifier = Modifier.fillMaxSize(),
                    model = ImageRequest.Builder(LocalContext.current)
                        .data(viewState.savedFileUri)
                        .crossfade(true)
                        .build(),
//                    placeholder = painterResource(com.pmb.ballon.R.drawable.ic_open_account),
                    contentDescription = "SignatureImage",
                    contentScale = ContentScale.Crop
                )
                IconButton(
                    modifier = Modifier
                        .align(Alignment.BottomStart)
                        .padding(8.dp)
                        .size(48.dp),
                    onClick = {
                        viewModel.handle(PhotoViewActions.ClearPhoto)
                    }
                ) {
                    Image(
                        painter = painterResource(com.pmb.ballon.R.drawable.ic_rounded_delete),
                        contentDescription = "Delete icon"
                    )
                }
            }
        }
//        }

    }
    if (viewState.isLoading) {
        AppLoading()
    }
    if (viewState.alertModelState != null) {
        AlertComponent(viewState.alertModelState!!)
    }

}

