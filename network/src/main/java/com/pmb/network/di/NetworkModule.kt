package com.pmb.network.di

import com.pmb.network.NetworkManger
import com.pmb.network.UserSessionImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    @Singleton
    fun provideNetworkManger(userSession: UserSessionImpl): NetworkManger {
        return NetworkManger(userSession)
    }
}
