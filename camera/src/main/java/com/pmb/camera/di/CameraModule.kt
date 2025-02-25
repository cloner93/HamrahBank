package com.pmb.camera.di

import android.content.Context
import com.pmb.camera.platform.CameraManager
import com.pmb.camera.platform.CameraManagerImpl
import com.pmb.camera.platform.CameraManagerPrincipleImpl
import com.pmb.camera.platform.VideoCameraManagerImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object CameraModule {
    @Provides
    @Singleton
    fun provideCameraController(
        @ApplicationContext context: Context
    ): CameraManagerPrincipleImpl {
        return CameraManagerImpl(
            context
        )
    }
    @Provides
    @Singleton
    fun provideVideoController(
        @ApplicationContext context: Context
    ): CameraManagerPrincipleImpl {
        return VideoCameraManagerImpl(
            context
        )
    }
}