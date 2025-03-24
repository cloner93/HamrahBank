package com.pmb.account.di

import com.pmb.account.presentation.balance.viewmodel.BalanceViewState
import com.pmb.account.presentation.deposits.viewmodel.DepositsViewState
import com.pmb.account.presentation.transactions.viewmodel.TransactionsViewState
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

    @Provides
    @Singleton
    fun provideTransactionsViewState(): TransactionsViewState = TransactionsViewState()

}