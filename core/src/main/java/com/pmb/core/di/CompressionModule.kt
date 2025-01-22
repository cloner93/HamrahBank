package com.pmb.core.di

import com.pmb.core.compression.ImageCompressor
import com.pmb.core.compression.VideoCompressor
import com.pmb.core.qualifier.IoDispatcher
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object CompressionModule {
    @Provides
    @Singleton
    fun provideImageCompressor(
        @IoDispatcher ioDispatcher: CoroutineDispatcher
    ) = ImageCompressor(ioDispatcher)

    @Provides
    @Singleton
    fun provideVideoCompressor(
        @IoDispatcher ioDispatcher: CoroutineDispatcher
    ) = VideoCompressor(ioDispatcher)
}