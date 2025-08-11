package com.pmb.data.di

import com.pmb.data.repository.auth.AuthRepositoryImpl
import com.pmb.data.repository.card.CardsRepositoryImpl
import com.pmb.data.repository.deposit.DepositRepositoryImpl
import com.pmb.data.repository.theme.ThemeRepositoryImpl
import com.pmb.data.repository.transaction.TransactionRepositoryImpl
import com.pmb.data.repository.favorite.FavoriteRepositoryImpl
import com.pmb.domain.repository.auth.AuthRepository
import com.pmb.domain.repository.card.CardsRepository
import com.pmb.domain.repository.deposit.DepositsRepository
import com.pmb.domain.repository.theme.ThemeRepository
import com.pmb.domain.repository.transactions.TransactionRepository
import com.pmb.domain.repository.favorite.FavoriteRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    abstract fun provideThemeRepository(themeRepositoryImpl: ThemeRepositoryImpl): ThemeRepository

    @Binds
    abstract fun bindAuthRepository(authRepositoryImpl: AuthRepositoryImpl): AuthRepository

    @Binds
    abstract fun bindCardsRepository(cardsRepository: CardsRepositoryImpl): CardsRepository

    @Binds
    abstract fun bindDepositListRepository(depositLIstRepository: DepositRepositoryImpl): DepositsRepository

    @Binds
    abstract fun bindTransactionsRepository(transactionRepository: TransactionRepositoryImpl): TransactionRepository

    @Binds
    abstract fun bindFavoriteRepository(favoriteRepository: FavoriteRepositoryImpl): FavoriteRepository
}