package com.pmb.auth.di

import com.pmb.auth.presentaion.activate.viewModel.ActivateViewState
import com.pmb.auth.presentaion.first_login.viewModel.FirstLoginViewState
import com.pmb.auth.presentaion.first_login_confirm.viewModel.FirstLoginConfirmViewState
import com.pmb.auth.presentaion.foget_password.viewmodel.ForgetPasswordViewState
import com.pmb.auth.presentaion.login.viewmodel.LoginViewState
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
}