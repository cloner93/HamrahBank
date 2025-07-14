package com.pmb.data.di

import com.pmb.data.appManager.AppManager
import com.pmb.data.appManager.AppManagerImpl
import com.pmb.data.appManager.auth.AuthService
import com.pmb.data.appManager.auth.AuthServiceImpl
import com.pmb.data.appManager.card.CardService
import com.pmb.data.appManager.card.CardServiceImpl
import com.pmb.data.appManager.persistence.PersistenceService
import com.pmb.data.appManager.persistence.PersistenceServiceImpl
import com.pmb.data.appManager.persistence.user.UserDataStore
import com.pmb.data.appManager.persistence.user.UserDataStoreImpl
import com.pmb.data.appManager.deposit.DepositService
import com.pmb.data.appManager.deposit.DepositServiceImpl
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

    @Binds
    abstract fun bindDepositService(depositServiceImpl: DepositServiceImpl): DepositService

    @Binds
    abstract fun bindUserDataStore(userDataStoreImpl: UserDataStoreImpl): UserDataStore

    @Binds
    abstract fun bindDataStoreService(dataStoreServiceImpl: PersistenceServiceImpl): PersistenceService
}