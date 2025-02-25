package com.pmb.core.fileManager

import android.content.Context
import android.net.Uri
import android.os.Environment
import android.util.Log
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
            file.parentFile?.let { parent ->
                if (!parent.exists()) {
                    if (!parent.mkdirs()) {
                        Log.e("FileManager", "Failed to create parent directories: ${parent.absolutePath}")
                        return false
                    }
                }
            }
            if (!file.exists()) {
                if (!file.createNewFile()) {
                    Log.e("FileManager", "Failed to create file: ${file.absolutePath}")
                    return false
                }
            }
            FileOutputStream(file).use { fos ->
                fos.write(data)
            }

            Log.d("FileManager", "File written successfully at ${file.absolutePath}")
            true
        } catch (exception: Exception) {
            Log.e("FileManager", "Error writing file: ${exception.message}")
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

            Log.i("FileManager", "Replacing file. Original: $originalFilePath, New: $newFilePath")

            if (!originalFile.exists()) {
                Log.e("FileManager", "Original file does not exist: $originalFilePath")
                return false
            }

            if (!newFile.exists()) {
                Log.e("FileManager", "New file does not exist: $newFilePath")
                return false
            }

            newFile.copyTo(originalFile, overwrite = false)

            newFile.delete()
            true
        } catch (exception: Exception) {
            Log.e("FileManager", "Error replacing file: ${exception.message}", exception)
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
        val timeStamp = SimpleDateFormat("yyyy-MM-dd-HH-mm-ss-SSS", Locale.US)
            .format(System.currentTimeMillis())
        val directory = context.getExternalFilesDir(Environment.DIRECTORY_PICTURES) ?: context.filesDir
        return File(directory, "photo_$timeStamp.jpg")
    }

    override fun createVideoFile(): File {
        val timeStamp = SimpleDateFormat("yyyy-MM-dd-HH-mm-ss-SSS", Locale.US)
            .format(System.currentTimeMillis())
        val directory = context.getExternalFilesDir(Environment.DIRECTORY_MOVIES) ?: context.filesDir
        return File(directory, "video_$timeStamp.mp4")
    }

    override fun getFileUri(file: File): Uri {
        return Uri.fromFile(file)
    }

}