package com.pmb.auth.di

//import com.pmb.auth.presentation.register.authentication_video.viewModel.AuthenticationCapturingVideoViewState
//import com.pmb.auth.presentation.ekyc.face_photo.viewModel.FacePhotoCapturedViewState
import com.pmb.auth.presentation.activation.activate.viewModel.ActivationViewState
import com.pmb.auth.presentation.activation.activation_tax_details.viewModel.ActivationTaxDetailsViewState
import com.pmb.auth.presentation.ekyc.authentication_confirm.viewModel.AuthenticationConfirmStepViewState
import com.pmb.auth.presentation.ekyc.ekyc_face_photo.viewModel.EKYCFacePhotoCapturedViewState
import com.pmb.auth.presentation.ekyc.ekyc_register_national_id.viewModel.EKYCRegisterNationalIdViewState
import com.pmb.auth.presentation.ekyc.ekyc_video_capture.viewModel.EKYCAuthenticationCapturingVideoViewState
import com.pmb.auth.presentation.ekyc.open_account.viewModel.OpenAccountViewState
import com.pmb.auth.presentation.first_login.viewModel.FirstLoginViewState
import com.pmb.auth.presentation.first_login_confirm.viewModel.FirstLoginConfirmViewState
import com.pmb.auth.presentation.foget_password.viewmodel.ForgetPasswordViewState
import com.pmb.auth.presentation.login.viewmodel.LoginViewState
import com.pmb.auth.presentation.new_password.viewModel.NewPasswordViewState
import com.pmb.auth.presentation.reentry.reentry_password.viewModel.ReentryPasswordViewState
import com.pmb.auth.presentation.register.authentication_information.viewModel.AuthenticationInformationViewState
import com.pmb.auth.presentation.register.authentication_select_services.viewModel.AuthenticationSelectServicesViewState
import com.pmb.auth.presentation.register.check_postal_code.viewModel.CheckPostalCodeViewState
import com.pmb.auth.presentation.register.deposit_information.viewModel.DepositInformationViewState
import com.pmb.auth.presentation.register.fee_details.viewModel.FeeDetailsViewState
import com.pmb.auth.presentation.register.job_information.viewModel.JobInformationViewState
import com.pmb.auth.presentation.register.national_id.viewModel.RegisterNationalIdViewState
import com.pmb.auth.presentation.register.register_confirm.viewModel.RegisterConfirmStepViewState
import com.pmb.auth.presentation.register.register_face_photo.viewModel.RegisterFacePhotoCapturedViewState
import com.pmb.auth.presentation.register.register_video.viewModel.RegisterCapturingVideoViewState
import com.pmb.auth.presentation.register.search_opening_branch.viewModel.SearchOpeningBranchViewState
import com.pmb.auth.presentation.register.select_job_information.viewModel.SelectJobInformationViewState
import com.pmb.auth.presentation.register.signature.viewModel.SignatureViewState
import com.pmb.auth.presentation.scan_card_info.card_confirm.viewModel.CardInformationConfirmViewState
import com.pmb.auth.presentation.scan_card_info.card_info.viewModel.CardInfoViewState
import com.pmb.auth.presentation.scan_card_info.scan_card.viewModel.ScanCardViewState
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
    fun provideActivateViewState(): ActivationViewState = ActivationViewState()

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
    fun provideEKYCAuthenticationCapturingVideoViewState(): EKYCAuthenticationCapturingVideoViewState =
        EKYCAuthenticationCapturingVideoViewState()
    @Provides
    @Singleton
    fun provideEKYCFacePhotoCapturedViewState(): EKYCFacePhotoCapturedViewState =
        EKYCFacePhotoCapturedViewState()
    @Provides
    @Singleton
    fun provideEKYCRegisterNationalIdViewState(): EKYCRegisterNationalIdViewState =
        EKYCRegisterNationalIdViewState()
    @Provides
    @Singleton
    fun provideRegisterCapturingVideoViewState(): RegisterCapturingVideoViewState =
        RegisterCapturingVideoViewState()
    @Provides
    @Singleton
    fun provideRegisterConfirmStepViewState(): RegisterConfirmStepViewState =
        RegisterConfirmStepViewState()
    @Provides
    @Singleton
    fun provideRegisterFacePhotoCapturedViewState(): RegisterFacePhotoCapturedViewState =
        RegisterFacePhotoCapturedViewState()

    //    @Provides
//    @Singleton
//    fun provideFacePhotoCaptureViewState(): FacePhotoCapturedViewState =
//        FacePhotoCapturedViewState()
//
//    @Provides
//    @Singleton
//    fun provideAuthenticationCapturingVideoViewState(): AuthenticationCapturingVideoViewState =
//        AuthenticationCapturingVideoViewState()
    @Provides
    @Singleton
    fun provideRegisterNationalIdViewState(): RegisterNationalIdViewState =
        RegisterNationalIdViewState()

    @Provides
    @Singleton
    fun provideCheckPostalCodeViewState(): CheckPostalCodeViewState = CheckPostalCodeViewState()

    @Provides
    @Singleton
    fun provideDepositInformationViewState(): DepositInformationViewState =
        DepositInformationViewState()

    @Provides
    @Singleton
    fun provideSearchOpeningBranchViewState(): SearchOpeningBranchViewState =
        SearchOpeningBranchViewState()

    @Provides
    @Singleton
    fun provideSelectJonInformationViewState(): SelectJobInformationViewState =
        SelectJobInformationViewState()

    @Provides
    @Singleton
    fun provideReentryPasswordViewState(): ReentryPasswordViewState = ReentryPasswordViewState()


    @Provides
    @Singleton
    fun provideActivationTaxDetailsViewState(): ActivationTaxDetailsViewState =
        ActivationTaxDetailsViewState()

    @Provides
    @Singleton
    fun provideCardInfoViewState(): CardInfoViewState = CardInfoViewState()

    @Provides
    @Singleton
    fun provideScanCardViewState(): ScanCardViewState = ScanCardViewState()

    @Provides
    @Singleton
    fun provideCardInformationConfirmViewState(): CardInformationConfirmViewState =
        CardInformationConfirmViewState()

    @Provides
    @Singleton
    fun provideJobInformationViewState(): JobInformationViewState = JobInformationViewState()

    @Provides
    @Singleton
    fun provideAuthenticationInformationViewState(): AuthenticationInformationViewState =
        AuthenticationInformationViewState()
}
