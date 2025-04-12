package com.pmb.transfer.di

import com.pmb.transfer.data.repository.TransferRepositoryImpl
import com.pmb.transfer.domain.repository.TransferRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    abstract fun bindTransferRepository(transferRepositoryImpl: TransferRepositoryImpl): TransferRepository

}