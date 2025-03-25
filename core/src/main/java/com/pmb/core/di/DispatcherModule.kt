package com.pmb.core.di

import com.pmb.core.qualifier.ApplicationDefaultDispatcher
import com.pmb.core.qualifier.DefaultDispatcher
import com.pmb.core.qualifier.IoDispatcher
import com.pmb.core.qualifier.MainDispatcher
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

@Module
@InstallIn(SingletonComponent::class)
object DispatcherModule {
    @Provides
    @IoDispatcher
    fun provideIoDispatcher(): CoroutineDispatcher = Dispatchers.IO

    @Provides
    @MainDispatcher
    fun provideMainDispatcher(): CoroutineDispatcher = Dispatchers.Main

    @Provides
    @DefaultDispatcher
    fun provideDefaultDispatcher(): CoroutineDispatcher = Dispatchers.Default

    @Provides
    @ApplicationDefaultDispatcher
    fun provideApplicationDefaultDispatcher(): CoroutineDispatcher = Dispatchers.IO
}