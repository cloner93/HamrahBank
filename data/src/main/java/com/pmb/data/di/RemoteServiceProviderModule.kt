package com.pmb.data.di

import com.pmb.data.serviceProvider.remote.RemoteServiceProvider
import com.pmb.data.serviceProvider.remote.RemoteServiceProviderImpl
import com.pmb.data.serviceProvider.remote.auth.AuthService
import com.pmb.data.serviceProvider.remote.auth.AuthServiceImpl
import com.pmb.data.serviceProvider.remote.card.CardService
import com.pmb.data.serviceProvider.remote.card.CardServiceImpl
import com.pmb.data.serviceProvider.remote.deposit.DepositService
import com.pmb.data.serviceProvider.remote.deposit.DepositServiceImpl
import com.pmb.data.serviceProvider.remote.transaction.TransactionService
import com.pmb.data.serviceProvider.remote.transaction.TransactionServiceImpl
import com.pmb.data.serviceProvider.remote.favorite.FavoriteService
import com.pmb.data.serviceProvider.remote.favorite.FavoriteServiceImpl
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

    @Binds
    abstract fun bindTransactionService(transactionServiceImpl: TransactionServiceImpl): TransactionService

    @Binds
    abstract fun bindFavoriteService(favoriteService :FavoriteServiceImpl): FavoriteService

}