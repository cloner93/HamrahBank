package com.pmb.core.fileManager

import android.content.Context
import android.net.Uri
import dagger.hilt.android.qualifiers.ApplicationContext
import java.io.File
import java.io.FileOutputStream
import java.text.SimpleDateFormat
import java.util.Locale
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class FileManagerImpl @Inject constructor(
    @ApplicationContext val context: Context
) : FileManager {
    override fun writeFile(filePath: String, data: ByteArray): Boolean {
        return try {
            val file = File(filePath)
            FileOutputStream(file).use { fos ->
                fos.write(data)
            }
            true
        } catch (exception: Exception) {
            exception.printStackTrace()
            false
        }
    }

    override fun deleteFile(filePath: String): Boolean {
        return try {
            val file = File(filePath)
            file.delete()
        } catch (exception: Exception) {
            exception.printStackTrace()
            false
        }
    }

    override fun replaceFile(originalFilePath: String, newFilePath: String): Boolean {
        return try {
            val originalFile = File(originalFilePath)
            val newFile = File(newFilePath)
            originalFile.delete() && newFile.renameTo(originalFile)
        } catch (exception: Exception) {
            exception.printStackTrace()
            false
        }
    }

    override fun createTempFile(prefix: String, suffix: String): File {
        return File.createTempFile(prefix, suffix)
    }

    override fun getOutputDirectory(): File {
        return context.externalCacheDir ?: context.cacheDir
    }

    override fun createImageFile(): File {
        val timeStamp = SimpleDateFormat(
            "yyyy-MM-dd-HH-mm-ss-SSS",
            Locale.US
        ).format(System.currentTimeMillis())
        return File(getOutputDirectory(), "photo_$timeStamp.jpg")
    }

    override fun createVideoFile(): File {
        val timeStamp = SimpleDateFormat(
            "yyyy-MM-dd-HH-mm-ss-SSS",
            Locale.US
        ).format(System.currentTimeMillis())
        return File(getOutputDirectory(), "video_$timeStamp.mp4")
    }

    override fun getFileUri(file: File): Uri {
        return Uri.fromFile(file)
    }

}