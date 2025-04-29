package com.pmb.facilities.bill.di

import com.pmb.facilities.bill.data.BillRepositoryImpl
import com.pmb.facilities.bill.data.BillsHistoryRepositoryImpl
import com.pmb.facilities.bill.domain.bill.repository.BillRepository
import com.pmb.facilities.bill.domain.bills_history.repository.BillsHistoryRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {
    @Binds
    abstract fun bindBillRepository(billRepositoryImpl: BillRepositoryImpl): BillRepository

    @Binds
    abstract fun bindBillsHistoryRepository(billsHistoryRepositoryImpl: BillsHistoryRepositoryImpl): BillsHistoryRepository
}