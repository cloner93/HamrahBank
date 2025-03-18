package com.pmb.compressor.di

import com.pmb.compressor.compression.ImageCompressor
import com.pmb.compressor.compression.ImageCompressorImpl
import com.pmb.compressor.compression.VideoCompressor
import com.pmb.compressor.compression.VideoCompressorImpl
import com.pmb.core.fileManager.FileManager
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
        @IoDispatcher ioDispatcher: CoroutineDispatcher,
        fileManager: FileManager
    ): ImageCompressor =
        ImageCompressorImpl(ioDispatcher, fileManager)


    @Provides
    @Singleton
    fun provideVideoCompressor1(
        @IoDispatcher ioDispatcher: CoroutineDispatcher,
        fileManager: FileManager
    ): VideoCompressor =
        VideoCompressorImpl(ioDispatcher, fileManager)
}