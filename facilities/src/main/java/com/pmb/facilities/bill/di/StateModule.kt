package com.pmb.facilities.bill.di

import com.pmb.facilities.bill.presentation.bill.viewModel.BillViewState
import com.pmb.facilities.bill.presentation.bills_history.viewModel.BillsHistoryViewState
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object StateModule {
    @Provides
    fun provideBillViewState(): BillViewState = BillViewState()
    @Provides
    fun provideBillsHistoryViewState(): BillsHistoryViewState = BillsHistoryViewState()
}