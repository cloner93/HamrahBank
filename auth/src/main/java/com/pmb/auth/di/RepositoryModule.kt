package com.pmb.auth.di

import com.pmb.auth.data.activate.ActivateRepositoryImpl
import com.pmb.auth.data.ekyc.authenticationSelectServices.AuthenticationSelectServicesRepositoryImpl
import com.pmb.auth.data.ekyc.feeDtails.FeeDetailsRepositoryImpl
import com.pmb.auth.data.first_login.FirstLoginRepositoryImpl
import com.pmb.auth.data.first_login_confirm.FirstLoginConfirmRepositoryImpl
import com.pmb.auth.data.forget_password.ForgetPasswordRepositoryImpl
import com.pmb.auth.data.login.LoginRepositoryImpl
import com.pmb.auth.data.new_password.NewPasswordRepositoryImpl
import com.pmb.auth.domain.activate.repository.ActivateRepository
import com.pmb.auth.domain.ekyc.authenticationSelectServices.repository.AuthenticationSelectServicesRepository
import com.pmb.auth.domain.ekyc.feeDetails.repository.FeeDetailsRepository
import com.pmb.auth.domain.first_login.repository.FirstLoginRepository
import com.pmb.auth.domain.first_login_confirm.repository.FirstLoginConfirmRepository
import com.pmb.auth.domain.forget_password.repository.ForgetPasswordRepository
import com.pmb.auth.domain.login.repository.LoginRepository
import com.pmb.auth.domain.new_password.repository.NewPasswordRepository
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
}