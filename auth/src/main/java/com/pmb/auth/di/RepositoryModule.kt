package com.pmb.auth.di

import com.pmb.auth.data.activate.ActivateRepositoryImpl
import com.pmb.auth.data.ekyc.authentication_confirm_step.AuthenticationStepConfirmRepositoryImpl
import com.pmb.auth.data.ekyc.authentication_select_services.AuthenticationSelectServicesRepositoryImpl
import com.pmb.auth.data.ekyc.face_photo.FacePhotoRepositoryImpl
import com.pmb.auth.data.ekyc.fee_details.FeeDetailsRepositoryImpl
import com.pmb.auth.data.ekyc.open_account.OpenAccountRepositoryImpl
import com.pmb.auth.data.ekyc.signature.SignatureRepositoryImpl
import com.pmb.auth.data.ekyc.video_capture.CapturingVideoRepositoryImpl
import com.pmb.auth.data.first_login.FirstLoginRepositoryImpl
import com.pmb.auth.data.first_login_confirm.FirstLoginConfirmRepositoryImpl
import com.pmb.auth.data.forget_password.ForgetPasswordRepositoryImpl
import com.pmb.auth.data.login.LoginRepositoryImpl
import com.pmb.auth.data.new_password.NewPasswordRepositoryImpl
import com.pmb.auth.data.register.check_postal_code.CheckPostalCodeRepositoryImpl
import com.pmb.auth.data.register.deposit_information.DepositInformationRepositoryImpl
import com.pmb.auth.data.register.national_id.RegisterNationalIdRepositoryImpl
import com.pmb.auth.data.register.search_opening_branch.OpeningBranchRepositoryImpl
import com.pmb.auth.data.register.select_job_information.SelectJobInformationRepositoryImpl
import com.pmb.auth.domain.activate.repository.ActivateRepository
import com.pmb.auth.domain.ekyc.authentication_confirm_step.repository.AuthenticationConfirmStepRepository
import com.pmb.auth.domain.ekyc.authentication_select_services.repository.AuthenticationSelectServicesRepository
import com.pmb.auth.domain.ekyc.capture_video.repository.CapturingVideoRepository
import com.pmb.auth.domain.ekyc.face_photo.repository.FacePhotoRepository
import com.pmb.auth.domain.ekyc.fee_details.repository.FeeDetailsRepository
import com.pmb.auth.domain.ekyc.open_account.repository.OpenAccountRepository
import com.pmb.auth.domain.ekyc.signature.repository.SignatureRepository
import com.pmb.auth.domain.first_login.repository.FirstLoginRepository
import com.pmb.auth.domain.first_login_confirm.repository.FirstLoginConfirmRepository
import com.pmb.auth.domain.forget_password.repository.ForgetPasswordRepository
import com.pmb.auth.domain.login.repository.LoginRepository
import com.pmb.auth.domain.new_password.repository.NewPasswordRepository
import com.pmb.auth.domain.register.check_postal_code.repository.CheckPostalCodeRepository
import com.pmb.auth.domain.register.deposit_information.repository.DepositInformationRepository
import com.pmb.auth.domain.register.national_id.repository.RegisterNationalIdRepository
import com.pmb.auth.domain.register.search_opening_branch.repository.OpeningBranchRepository
import com.pmb.auth.domain.register.select_job_information.repository.SelectJobInformationRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    abstract fun bindLoginRepository(loginRepositoryImpl: LoginRepositoryImpl): LoginRepository

    @Binds
    abstract fun bindForgetPasswordRepository(forgetPasswordRepositoryImpl: ForgetPasswordRepositoryImpl): ForgetPasswordRepository

    @Binds
    abstract fun bindFirstLoginRepository(firstLoginRepositoryImpl: FirstLoginRepositoryImpl): FirstLoginRepository

    @Binds
    abstract fun bindFirstLoginConfirm(firstLoginConfirmRepositoryImpl: FirstLoginConfirmRepositoryImpl): FirstLoginConfirmRepository


    @Binds
    abstract fun bindActivationUser(activateRepositoryImpl: ActivateRepositoryImpl): ActivateRepository

    @Binds
    abstract fun bindNewPasswordRepository(newPasswordRepositoryImpl: NewPasswordRepositoryImpl): NewPasswordRepository

    @Binds
    abstract fun bindFeeDetailsRepository(feeDetailsRepositoryImpl: FeeDetailsRepositoryImpl): FeeDetailsRepository

    @Binds
    abstract fun bindAuthenticationSelectServicesRepository(
        authenticationSelectServicesRepositoryImpl: AuthenticationSelectServicesRepositoryImpl
    ): AuthenticationSelectServicesRepository

    @Binds
    abstract fun bindAuthenticationConfirmStepRepository(
        authenticationConfirmStepRepositoryImpl: AuthenticationStepConfirmRepositoryImpl
    ): AuthenticationConfirmStepRepository

    @Binds
    abstract fun bindOpenAccountRepository(openAccountRepositoryImpl: OpenAccountRepositoryImpl): OpenAccountRepository


    @Binds
    abstract fun bindSignatureRepository(signatureRepositoryImpl: SignatureRepositoryImpl): SignatureRepository


    @Binds
    abstract fun bindFacePhotoRepository(facePhotoRepositoryImpl: FacePhotoRepositoryImpl): FacePhotoRepository

    @Binds
    abstract fun bindCapturingVideoRepository(capturingVideoRepositoryImpl: CapturingVideoRepositoryImpl): CapturingVideoRepository

    @Binds
    abstract fun bindRegisterNationalIdRepository(registerNationalIdRepositoryImpl: RegisterNationalIdRepositoryImpl): RegisterNationalIdRepository

    @Binds
    abstract fun bindCheckPostalCodeRepository(checkPostalCodeRepositoryImpl: CheckPostalCodeRepositoryImpl): CheckPostalCodeRepository

    @Binds
    abstract fun bindDepositInformationRepository(depositInformationRepositoryImpl: DepositInformationRepositoryImpl): DepositInformationRepository

    @Binds
    abstract fun bindOpeningBranchRepository(openingBranchRepositoryImpl: OpeningBranchRepositoryImpl): OpeningBranchRepository

    @Binds
    abstract fun bindSelectJobInformationRepository(selectJobInformationRepositoryImpl: SelectJobInformationRepositoryImpl): SelectJobInformationRepository


}