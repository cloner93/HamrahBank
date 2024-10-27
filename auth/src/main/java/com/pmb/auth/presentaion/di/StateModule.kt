package com.pmb.auth.presentaion.di

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
    fun provideLoginViewState():LoginViewState = LoginViewState()
}