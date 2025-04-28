package com.pmb.facilities.charge.di

import com.pmb.facilities.charge.presentation.charge.ChargeViewState
import com.pmb.facilities.charge.presentation.charge_history.viewModel.ChargeHistoryViewState
import com.pmb.facilities.charge.presentation.purchase_charge.PurchaseChargeViewState
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object StateModule {

    @Provides
    fun provideChargeViewState(): ChargeViewState = ChargeViewState()

    @Provides
    fun provideChargeHistoryViewState(): ChargeHistoryViewState = ChargeHistoryViewState()

    @Provides
    fun providePurchaseChargeViewState(): PurchaseChargeViewState = PurchaseChargeViewState()
}