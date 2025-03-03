package com.pmb.mobile

import com.pmb.mobile.presentation.viewmodel.MainActivityViewState
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    fun provideMainViewState():MainActivityViewState = MainActivityViewState("mas mas")
}