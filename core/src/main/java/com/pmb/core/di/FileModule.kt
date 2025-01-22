package com.pmb.core.di

import android.content.Context
import com.pmb.core.fileManager.FileManager
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object FileModule {

    @Provides
    @Singleton
    fun provideFileManager(
        @ApplicationContext context: Context
    ) = FileManager(context)

}