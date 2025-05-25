package com.pmb.profile.di

import com.pmb.profile.presentaion.privacyAndSecurity.changePassword.ChangePasswordScreenViewState
import com.pmb.profile.presentaion.profile.viewModel.ProfileViewState
import com.pmb.profile.presentaion.themeScreen.viewmodel.ThemeScreenViewState
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
    fun provideProfileViewState(): ProfileViewState = ProfileViewState()

    @Provides
    @Singleton
    fun provideThemeScreenViewState(): ThemeScreenViewState = ThemeScreenViewState()

    @Provides
    @Singleton
    fun provideChangePasswordScreenViewState(): ChangePasswordScreenViewState =
        ChangePasswordScreenViewState()
}