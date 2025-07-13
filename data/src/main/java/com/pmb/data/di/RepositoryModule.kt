package com.pmb.data.di

import com.pmb.data.repository.auth.AuthRepositoryImpl
import com.pmb.data.repository.card.CardsRepositoryImpl
import com.pmb.data.repository.deposit.DepositsRepositoryImpl
import com.pmb.data.repository.theme.ThemeRepositoryImpl
import com.pmb.data.repository.transaction.TransactionsByCountRepositoryImpl
import com.pmb.domain.repository.DepositRepository
import com.pmb.domain.repository.auth.FirstLoginConfirmRepository
import com.pmb.domain.repository.auth.LoginRepository
import com.pmb.domain.repository.auth.RegisterRepository
import com.pmb.domain.repository.card.CardListRepository
import com.pmb.domain.repository.DepositsRepository
import com.pmb.domain.repository.auth.AuthRepository
import com.pmb.domain.repository.card.CardsRepository
import com.pmb.domain.repository.theme.ThemeRepository
import com.pmb.domain.repository.transactions.TransactionsByCountRepository
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
    abstract fun bindDepositsRepository(depositsRepositoryImpl: DepositsRepositoryImpl): DepositsRepository

    @Binds
    abstract fun bindDepositListRepository(depositLIstRepository: DepositRepositoryImpl): DepositRepository

    @Binds
    abstract fun bindTransactionsByCountRepository(transactionsByCountRepository: TransactionsByCountRepositoryImpl): TransactionsByCountRepository
}