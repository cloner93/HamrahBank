package com.pmb.data.di

import com.pmb.data.repository.auth.login.LoginRepositoryImpl
import com.pmb.data.repository.theme.ThemeRepositoryImpl
import com.pmb.domain.repository.auth.login.LoginRepository
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
}