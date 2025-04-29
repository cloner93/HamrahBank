package com.pmb.facilities.bill.di

import com.pmb.facilities.bill.data.BillRepositoryImpl
import com.pmb.facilities.bill.domain.bill.repository.BillRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {
    @Binds
    abstract fun bindBillRepository(billRepositoryImpl: BillRepositoryImpl): BillRepository
}