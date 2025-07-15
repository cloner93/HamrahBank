package com.pmb.data.di

import com.pmb.data.serviceProvider.local.LocalServiceProvider
import com.pmb.data.serviceProvider.local.LocalServiceProviderImpl
import com.pmb.data.serviceProvider.local.user.UserDataStore
import com.pmb.data.serviceProvider.local.user.UserDataStoreImpl
import com.pmb.data.serviceProvider.remote.RemoteServiceProvider
import com.pmb.data.serviceProvider.remote.RemoteServiceProviderImpl
import com.pmb.data.serviceProvider.remote.auth.AuthService
import com.pmb.data.serviceProvider.remote.auth.AuthServiceImpl
import com.pmb.data.serviceProvider.remote.card.CardService
import com.pmb.data.serviceProvider.remote.card.CardServiceImpl
import com.pmb.data.serviceProvider.remote.deposit.DepositService
import com.pmb.data.serviceProvider.remote.deposit.DepositServiceImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class RemoteServiceProviderModule {

    @Binds
    abstract fun bindRemoteServiceProvider(appManagerImpl: RemoteServiceProviderImpl): RemoteServiceProvider

    @Binds
    abstract fun bindAuthService(authServiceImpl: AuthServiceImpl): AuthService

    @Binds
    abstract fun bindCardService(cardService: CardServiceImpl): CardService

    @Binds
    abstract fun bindDepositService(depositServiceImpl: DepositServiceImpl): DepositService

}