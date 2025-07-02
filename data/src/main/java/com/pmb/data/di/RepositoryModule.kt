package com.pmb.data.di

import com.pmb.data.repository.auth.FirstLoginConfirmRepositoryImpl
import com.pmb.data.repository.auth.LoginRepositoryImpl
import com.pmb.data.repository.auth.RegisterRepositoryImpl
import com.pmb.data.repository.card.CardLIstRepositoryImpl
import com.pmb.data.repository.theme.ThemeRepositoryImpl
import com.pmb.domain.repository.auth.FirstLoginConfirmRepository
import com.pmb.domain.repository.auth.LoginRepository
import com.pmb.domain.repository.auth.RegisterRepository
import com.pmb.domain.repository.card.CardListRepository
import com.pmb.domain.repository.theme.ThemeRepository
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
    abstract fun bindLoginRepository(loginRepositoryImpl: LoginRepositoryImpl): LoginRepository

    @Binds
    abstract fun bindRegisterRepository(registerRepositoryImpl: RegisterRepositoryImpl): RegisterRepository

    @Binds
    abstract fun bindFirstLoginConfirmRepository(firstLoginConfirmRepositoryImpl: FirstLoginConfirmRepositoryImpl): FirstLoginConfirmRepository

    @Binds
    abstract fun bindCardListRepository(cardLIstRepository: CardLIstRepositoryImpl): CardListRepository
}