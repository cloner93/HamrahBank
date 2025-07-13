package com.pmb.data.di

import com.pmb.data.appManager.AppManager
import com.pmb.data.appManager.AppManagerImpl
import com.pmb.data.appManager.auth.AuthService
import com.pmb.data.appManager.auth.AuthServiceImpl
import com.pmb.data.appManager.card.CardService
import com.pmb.data.appManager.card.CardServiceImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class AppManagerModule {

    @Binds
    abstract fun bindAppManager(appManagerImpl: AppManagerImpl): AppManager

    @Binds
    abstract fun bindAuthService(authServiceImpl: AuthServiceImpl): AuthService

    @Binds
    abstract fun bindCardService(cardService: CardServiceImpl): CardService
}