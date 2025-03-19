package com.pmb.account.di

import com.pmb.account.presentation.balance.BalanceViewState
import com.pmb.account.presentation.deposits.viewmodel.DepositsViewState
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
    fun provideDepositsViewState(): DepositsViewState = DepositsViewState()

    @Provides
    @Singleton
    fun provideBalanceViewState(): BalanceViewState = BalanceViewState()

}