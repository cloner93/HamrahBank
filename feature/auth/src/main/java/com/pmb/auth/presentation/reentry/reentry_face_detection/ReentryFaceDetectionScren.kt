package com.pmb.auth.presentation.reentry.reentry_face_detection

import androidx.compose.runtime.Composable
import com.pmb.auth.presentation.reentry.reentry_face_detection.viewModel.ReentryFaceDetectionViewModel

@Composable
fun ReentryFaceDetectionScreen(
    viewModel: ReentryFaceDetectionViewModel
) {
//    val navigationManager: NavigationManager = LocalNavigationManager.current
//    val lifecycleOwner = LocalLifecycleOwner.current
//    val viewState by viewModel.viewState.collectAsState()
//    val context = LocalContext.current
//    val previewView = remember { PreviewView(context) }
//
//    LaunchedEffect(viewState.isCapturingPhoto) {
//        if (viewState.isCapturingPhoto)
//            viewModel.handle(PhotoViewActions.PreviewCamera(previewView, lifecycleOwner))
//    }
//    val permissionLauncher = rememberLauncherForActivityResult(
//        contract = ActivityResultContracts.RequestPermission()
//    ) { isGranted ->
//        viewModel.onSinglePermissionResult(isGranted)
//    }
//
//    val multiplePermissionLauncher = rememberLauncherForActivityResult(
//        contract = ActivityResultContracts.RequestMultiplePermissions()
//    ) {
//        viewModel.onMultiplePermissionResult(it)
//    }
//    LaunchedEffect(Unit) {
//        viewModel.handle(PhotoViewActions.RequestCameraPermission(permissionLauncher))
//    }
//    LaunchedEffect(viewState.hasCameraPermission) {
//        viewModel.handle(PhotoViewActions.RequestFilePermission(multiplePermissionLauncher))
//    }
//    LaunchedEffect(Unit) {
//        viewModel.viewEvent.collect { event ->
//            when (event) {
//                FacePhotoCapturedViewEvents.FacePhotoCaptured -> {
//                    navigationManager.navigate(HomeScreens.Home)
//                }
//            }
//        }
//    }
//    AppContent(
//        modifier = Modifier.padding(horizontal = 16.dp),
//        topBar = {
//            AppTopBar(
//                title = stringResource(R.string.enter_face_detection),
//                onBack = {
//                    navigationManager.navigateBack()
//                }
//            )
//        },
//        footer = {
//
//            AppButton(
//                modifier = Modifier
//                    .fillMaxWidth()
//                    .padding(start = 16.dp, end = 16.dp, top = 16.dp),
//                enable = true,
//                title = stringResource(R.string.login),
//                onClick = {
//                    viewModel.handle(ReentryFaceDetectionViewActions.SendFacePhoto("DD"))
//                })
//        },
//        horizontalAlignment = Alignment.CenterHorizontally,
//    ) {
//        Spacer(modifier = Modifier.size(24.dp))
//
//        BodyMediumText(
//            text = stringResource(R.string.face_detection_description),
//            textAlign = TextAlign.Center,
//            color = AppTheme.colorScheme.onBackgroundPrimarySubdued
//
//        )
//        Spacer(modifier = Modifier.size(41.dp))
//        AnimatedVisibility(
//            visible = !viewState.photoCaptured,
//            exit = fadeOut(tween(100, easing = LinearEasing)),
//            enter = fadeIn(tween(100, easing = LinearEasing))
//        ) {
//            Box(
//                modifier = Modifier
//                    .fillMaxWidth()
//                    .height(355.dp)
//                    .border(
//                        1.dp,
//                        AppTheme.colorScheme.strokeNeutral3Rest,
//                        RoundedCornerShape(16.dp)
//                    )
//                    .background(
//                        AppTheme.colorScheme.stateLayerNeutralModalBackground,
//                        RoundedCornerShape(size = 16.dp)
//                    )
//            ) {
//                AndroidView(
//
//                    factory = { context ->
//                        previewView
//                    },
//                    modifier = Modifier
//                        .fillMaxSize()
//                        .clip(RoundedCornerShape(16.dp))
//                )
//            }
//
//        }
//
//    }
//    if (viewState.isLoading) {
//        AppLoading()
//    }
//    if (viewState.alertModelState != null) {
//        AlertComponent(viewState.alertModelState!!)
//    }
}