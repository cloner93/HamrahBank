package com.pmb.facilities.charge.di

import com.pmb.facilities.charge.data.charge.ChargeRepositoryImpl
import com.pmb.facilities.charge.data.charge_history.ChargeHistoryRepositoryImpl
import com.pmb.facilities.charge.data.choose_charge_price.ChooseChargePriceRepositoryImpl
import com.pmb.facilities.charge.data.purchase_charge.PurchaseChargeRepositoryImpl
import com.pmb.facilities.charge.domain.charge.repository.ChargeRepository
import com.pmb.facilities.charge.domain.charge_history.reository.ChargeHistoryRepository
import com.pmb.facilities.charge.domain.choose_charge_price.repository.ChooseChargePriceRepository
import com.pmb.facilities.charge.domain.purchase_charge.repository.PurchaseChargeRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {
    @Binds
    abstract fun bindChargeRepository(chargeRepositoryImpl: ChargeRepositoryImpl): ChargeRepository

    @Binds
    abstract fun bindChargeHistoryRepository(chargeHistoryRepositoryImpl: ChargeHistoryRepositoryImpl): ChargeHistoryRepository

    @Binds
    abstract fun bindPurchaseChargeRepository(purchaseChargeRepositoryImpl: PurchaseChargeRepositoryImpl): PurchaseChargeRepository

    @Binds
    abstract fun bindChooseChargePriceRepository(chooseChargePriceRepositoryImpl: ChooseChargePriceRepositoryImpl): ChooseChargePriceRepository
}