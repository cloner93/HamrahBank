package com.pmb.transfer.di

import com.pmb.transfer.data.mapper.ClientBankMapper
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object MapperModule {

    @Provides
    @Singleton
    fun provideClientBankMapper(): ClientBankMapper = ClientBankMapper()

}