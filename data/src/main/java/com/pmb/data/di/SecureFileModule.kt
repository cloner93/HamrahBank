package com.pmb.data.di

import android.content.Context
import com.pmb.data.SecureFile
import com.pmb.data.SecurityManager
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object SecureFileModule {

    @Provides
    @Singleton
    fun provideSecureFile(@ApplicationContext context: Context) = SecureFile(context)

    @Provides
    @Singleton
    fun provideSecurityManager() = SecurityManager()
}