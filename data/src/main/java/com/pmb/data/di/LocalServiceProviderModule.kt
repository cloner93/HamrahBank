package com.pmb.data.di

import com.pmb.data.serviceProvider.local.LocalServiceProvider
import com.pmb.data.serviceProvider.local.LocalServiceProviderImpl
import com.pmb.data.serviceProvider.local.user.UserDataStore
import com.pmb.data.serviceProvider.local.user.UserDataStoreImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class LocalServiceProviderModule {

    @Binds
    abstract fun bindUserDataStore(userDataStoreImpl: UserDataStoreImpl): UserDataStore

    @Binds
    abstract fun bindLocalServiceProvider(localDataProvider: LocalServiceProviderImpl): LocalServiceProvider
}