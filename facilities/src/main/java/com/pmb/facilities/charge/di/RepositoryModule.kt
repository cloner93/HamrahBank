package com.pmb.facilities.charge.di

import com.pmb.facilities.charge.data.charge.ChargeRepositoryImpl
import com.pmb.facilities.charge.domain.charge.repository.ChargeRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {
    @Binds
    abstract fun bindChargeRepository(chargeRepositoryImpl: ChargeRepositoryImpl): ChargeRepository
}