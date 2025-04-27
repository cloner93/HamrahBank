package com.pmb.facilities.charge.di

import com.pmb.facilities.charge.presentation.charge.ChargeViewState
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object StateModule {

    @Provides
    fun provideChargeViewState():ChargeViewState = ChargeViewState()
}