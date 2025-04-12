package com.pmb.home.di

import com.pmb.home.presentation.home.viewModel.HomeViewState
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
    fun provideHomeViewState():HomeViewState = HomeViewState()
}