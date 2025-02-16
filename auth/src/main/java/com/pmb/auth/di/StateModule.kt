package com.pmb.auth.di

import com.pmb.auth.presentaion.activate.viewModel.ActivateViewState
import com.pmb.auth.presentaion.ekyc.authenthicationConfirm.viewModel.AuthenticationConfirmStepViewState
import com.pmb.auth.presentaion.ekyc.authenticationSelectServices.viewModel.AuthenticationSelectServicesViewState
import com.pmb.auth.presentaion.ekyc.authenticationVideo.viewModel.AuthenticationCapturingVideoViewState
import com.pmb.auth.presentaion.ekyc.facePhoto.viewModel.FacePhotoCapturedViewState
import com.pmb.auth.presentaion.ekyc.feeDetails.viewModel.FeeDetailsViewState
import com.pmb.auth.presentaion.ekyc.openAccount.viewModel.OpenAccountViewState
import com.pmb.auth.presentaion.ekyc.signature.viewModel.SignatureViewState
import com.pmb.auth.presentaion.first_login.viewModel.FirstLoginViewState
import com.pmb.auth.presentaion.first_login_confirm.viewModel.FirstLoginConfirmViewState
import com.pmb.auth.presentaion.foget_password.viewmodel.ForgetPasswordViewState
import com.pmb.auth.presentaion.login.viewmodel.LoginViewState
import com.pmb.auth.presentaion.new_password.viewModel.NewPasswordViewState
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object StateModule {

    @Provides
    @Singleton
    fun provideLoginViewState(): LoginViewState = LoginViewState()

    @Provides
    @Singleton
    fun provideForgetPasswordViewState(): ForgetPasswordViewState = ForgetPasswordViewState()

    @Provides
    @Singleton
    fun provideFirstLoginViewState(): FirstLoginViewState = FirstLoginViewState()


    @Provides
    @Singleton
    fun provideFirstLoginConfirmViewState(): FirstLoginConfirmViewState =
        FirstLoginConfirmViewState()


    @Provides
    @Singleton
    fun provideActivateViewState(): ActivateViewState = ActivateViewState()

    @Provides
    @Singleton
    fun provideNewPasswordViewState(): NewPasswordViewState = NewPasswordViewState()

    @Provides
    @Singleton
    fun provideSignatureViewState(): SignatureViewState = SignatureViewState()

    @Provides
    @Singleton
    fun provideFeeDetailsViewState(): FeeDetailsViewState = FeeDetailsViewState()

    @Provides
    @Singleton
    fun provideAuthenticationSelectServicesViewState(): AuthenticationSelectServicesViewState =
        AuthenticationSelectServicesViewState()

    @Provides
    @Singleton
    fun provideAuthenticationConfirmStepViewState(): AuthenticationConfirmStepViewState =
        AuthenticationConfirmStepViewState()

    @Provides
    @Singleton
    fun provideOpenAccountViewState(): OpenAccountViewState = OpenAccountViewState()

    @Provides
    @Singleton
    fun provideFacePhotoCaptureViewState(): FacePhotoCapturedViewState =
        FacePhotoCapturedViewState()

    @Provides
    @Singleton
    fun provideAuthenticationCapturingVideoViewState(): AuthenticationCapturingVideoViewState =
        AuthenticationCapturingVideoViewState()
}