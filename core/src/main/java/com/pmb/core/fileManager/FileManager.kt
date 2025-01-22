package com.pmb.core.fileManager

import android.content.Context
import android.net.Uri
import dagger.hilt.android.qualifiers.ApplicationContext
import java.io.File
import java.text.SimpleDateFormat
import java.util.Locale
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class FileManager @Inject constructor(
    @ApplicationContext val context: Context
)  {
    fun getOutputDirectory(): File {
        return context.externalCacheDir ?: context.cacheDir
    }

    fun createImageFile(): File {
        val timeStamp = SimpleDateFormat(
            "yyyy-MM-dd-HH-mm-ss-SSS",
            Locale.US
        ).format(System.currentTimeMillis())
        return File(getOutputDirectory(), "photo_$timeStamp.jpg")
    }

    fun createVideoFile(): File {
        val timeStamp = SimpleDateFormat(
            "yyyy-MM-dd-HH-mm-ss-SSS",
            Locale.US
        ).format(System.currentTimeMillis())
        return File(getOutputDirectory(), "video_$timeStamp.mp4")
    }

    fun getFileUri(file: File): Uri {
        return Uri.fromFile(file)
    }

}