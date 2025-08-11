package com.pmb.auth.presentation.ekyc

import android.util.Log
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.pmb.auth.AuthSharedViewModel
import com.pmb.auth.presentation.ekyc.ekyc_authentication.EKYCAuthenticationScreen
import com.pmb.auth.presentation.ekyc.ekyc_face_photo.EKYCFacePhotoCaptureScreen
import com.pmb.auth.presentation.ekyc.ekyc_face_photo.viewModel.EKYCFacePhotoCapturedViewModel
import com.pmb.auth.presentation.ekyc.ekyc_register_national_id.EkycRegisterNationalIdScreen
import com.pmb.auth.presentation.ekyc.ekyc_register_national_id.viewModel.EKYCRegisterNationalIdViewModel
import com.pmb.auth.presentation.ekyc.ekyc_video_capture.EKYCVideoCaptureScreen
import com.pmb.auth.presentation.ekyc.ekyc_video_capture.viewModel.EKYCAuthenticationCapturingVideoViewModel
import com.pmb.auth.presentation.register.RegisterSharedViewModel
import com.pmb.navigation.manager.navigationManager
import com.pmb.navigation.moduleScreen.EKYCScreens
import com.pmb.navigation.moduleScreen.RegisterScreens
import com.pmb.navigation.moduleScreen.SharedAuthAndActivationScopeGraph

fun NavGraphBuilder.ekycScreenHandler() {
    navigation(
        route = EKYCScreens.EKYCGraph.route,
        startDestination = EKYCScreens.EKYCRegisterNationalId.route
    ) {
        composable(route = EKYCScreens.EKYCRegisterNationalId.route) {
            val sharedViewModel =
                it.navigationManager.retrieveSharedViewModel<EKYCSHaredViewModel>(
                    screen = EKYCScreens.EKYCGraph, navBackStackEntry = it
                )
            val sharedState = sharedViewModel.state.collectAsStateWithLifecycle()
            val topSharedViewModel =
                it.navigationManager.retrieveSharedViewModel<AuthSharedViewModel>(
                    screen = SharedAuthAndActivationScopeGraph, navBackStackEntry = it
                )
            val topSharedState = topSharedViewModel.state.collectAsStateWithLifecycle()
            EkycRegisterNationalIdScreen(
                viewModel = hiltViewModel<EKYCRegisterNationalIdViewModel>(),
            ){childState->
                Log.d("Ali Tag", "ekycScreenHandler: ${topSharedState.value.changePasswordPhoneNumber}")
                sharedViewModel.updateState {
                    sharedState.value.copy(
                        cardSerialNo = childState.nationalSerialId,
                        changePasswordPhoneNumber = topSharedState.value.changePasswordPhoneNumber,
                        changePasswordPassword = topSharedState.value.changePasswordPassword,
                        changePasswordNationalId = topSharedState.value.changePasswordNationalId
                    )
                }
            }
        }
        composable(route = EKYCScreens.EKYCAuthentication.route) {
            EKYCAuthenticationScreen()
        }
        composable(route = EKYCScreens.EKYCFacePhotoCapture.route) {
            val sharedViewModel =
                it.navigationManager.retrieveSharedViewModel<EKYCSHaredViewModel>(
                    screen = EKYCScreens.EKYCGraph, navBackStackEntry = it
                )
            val sharedState = sharedViewModel.state.collectAsStateWithLifecycle()
            EKYCFacePhotoCaptureScreen(
                viewModel = hiltViewModel<EKYCFacePhotoCapturedViewModel>()
            ){childState->
                sharedViewModel.updateState {
                    sharedState.value.copy(
                        authImage = childState
                    )
                }
            }
        }
        composable(route = EKYCScreens.EKYCVideoCapture.route) {
            val sharedViewModel =
                it.navigationManager.retrieveSharedViewModel<EKYCSHaredViewModel>(
                    screen = EKYCScreens.EKYCGraph, navBackStackEntry = it
                )
            val sharedState = sharedViewModel.state.collectAsStateWithLifecycle()
            val topSharedViewModel =
                it.navigationManager.retrieveSharedViewModel<AuthSharedViewModel>(
                    screen = SharedAuthAndActivationScopeGraph, navBackStackEntry = it
                )
            val topSharedState = topSharedViewModel.state.collectAsStateWithLifecycle()
            EKYCVideoCaptureScreen(
                viewModel = hiltViewModel<EKYCAuthenticationCapturingVideoViewModel>(),
                sharedState = sharedState.value
            ){
                topSharedViewModel.setState(
                    topSharedState.value.copy(
                        isPasswordChanged = true
                    )
                )
            }
        }
    }
}
