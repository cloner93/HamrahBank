package com.pmb.auth.di

import com.pmb.auth.presentation.activate.viewModel.ActivateViewState
import com.pmb.auth.presentation.ekyc.authentication_confirm.viewModel.AuthenticationConfirmStepViewState
import com.pmb.auth.presentation.ekyc.authentication_select_services.viewModel.AuthenticationSelectServicesViewState
import com.pmb.auth.presentation.ekyc.authentication_video.viewModel.AuthenticationCapturingVideoViewState
import com.pmb.auth.presentation.ekyc.face_photo.viewModel.FacePhotoCapturedViewState
import com.pmb.auth.presentation.ekyc.fee_details.viewModel.FeeDetailsViewState
import com.pmb.auth.presentation.ekyc.open_account.viewModel.OpenAccountViewState
import com.pmb.auth.presentation.ekyc.signature.viewModel.SignatureViewState
import com.pmb.auth.presentation.first_login.viewModel.FirstLoginViewState
import com.pmb.auth.presentation.first_login_confirm.viewModel.FirstLoginConfirmViewState
import com.pmb.auth.presentation.foget_password.viewmodel.ForgetPasswordViewState
import com.pmb.auth.presentation.login.viewmodel.LoginViewState
import com.pmb.auth.presentation.new_password.viewModel.NewPasswordViewState
import com.pmb.auth.presentation.register.checkPostalCode.viewModel.CheckPostalCodeViewState
import com.pmb.auth.presentation.register.national_id.viewModel.RegisterNationalIdViewState
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

    @Provides
    @Singleton
    fun provideRegisterNationalIdViewState(): RegisterNationalIdViewState =
        RegisterNationalIdViewState()

    @Provides
    @Singleton
    fun provideCheckPostalCodeViewState(): CheckPostalCodeViewState = CheckPostalCodeViewState()
}