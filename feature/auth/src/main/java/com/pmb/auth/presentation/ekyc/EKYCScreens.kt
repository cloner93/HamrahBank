package com.pmb.auth.presentation.ekyc

import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.pmb.auth.presentation.ekyc.ekyc_authentication.EKYCAuthenticationScreen
import com.pmb.auth.presentation.ekyc.ekyc_face_photo.EKYCFacePhotoCaptureScreen
import com.pmb.auth.presentation.ekyc.ekyc_face_photo.viewModel.EKYCFacePhotoCapturedViewModel
import com.pmb.auth.presentation.ekyc.ekyc_register_national_id.EkycRegisterNationalIdScreen
import com.pmb.auth.presentation.ekyc.ekyc_register_national_id.viewModel.EKYCRegisterNationalIdViewModel
import com.pmb.auth.presentation.ekyc.ekyc_video_capture.EKYCVideoCaptureScreen
import com.pmb.auth.presentation.ekyc.ekyc_video_capture.viewModel.EKYCAuthenticationCapturingVideoViewModel
import com.pmb.navigation.moduleScreen.EKYCScreens

fun NavGraphBuilder.ekycScreenHandler() {
    navigation(
        route = EKYCScreens.EKYCGraph.route,
        startDestination = EKYCScreens.EKYCRegisterNationalId.route
    ) {
        composable(route = EKYCScreens.EKYCRegisterNationalId.route) {
            EkycRegisterNationalIdScreen(
                viewModel = hiltViewModel<EKYCRegisterNationalIdViewModel>(),
            )
        }
        composable(route = EKYCScreens.EKYCAuthentication.route) {
            EKYCAuthenticationScreen()
        }
        composable(route = EKYCScreens.EKYCFacePhotoCapture.route) {
            EKYCFacePhotoCaptureScreen(
                viewModel = hiltViewModel<EKYCFacePhotoCapturedViewModel>()
            )
        }
        composable(route = EKYCScreens.EKYCVideoCapture.route) {
            EKYCVideoCaptureScreen(
                viewModel = hiltViewModel<EKYCAuthenticationCapturingVideoViewModel>()
            )
        }
    }
}
