package com.pmb.core.fileManager

import android.content.Context
import android.net.Uri
import android.util.Log
import androidx.core.content.FileProvider
import dagger.hilt.android.qualifiers.ApplicationContext
import java.io.File
import java.io.FileOutputStream
import java.text.SimpleDateFormat
import java.util.Locale
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class FileManagerImpl @Inject constructor(
    @ApplicationContext private val context: Context
) : FileManager {

    override fun writeFile(absolutePath: String, data: ByteArray): Boolean {
        return try {
            val file = File(absolutePath)
            file.parentFile?.let { parent ->
                if (!parent.exists() && !parent.mkdirs()) {
                    Log.e(
                        "FileManager",
                        "Failed to create parent directories: ${parent.absolutePath}"
                    )
                    return false
                }
            }
            FileOutputStream(file).use { it.write(data) }
            Log.d("FileManager", "File written successfully at ${file.absolutePath}")
            true
        } catch (e: Exception) {
            Log.e("FileManager", "Error writing file: ${e.message}", e)
            false
        }
    }

    override fun deleteFile(absolutePath: String): Boolean {
        return try {
            File(absolutePath).delete()
        } catch (e: Exception) {
            e.printStackTrace()
            false
        }
    }

    override fun replaceFile(originalAbsolutePath: String, newAbsolutePath: String): Boolean {
        return try {
            val originalFile = File(originalAbsolutePath)
            val newFile = File(newAbsolutePath)

            if (!originalFile.exists() || !newFile.exists()) {
                Log.e("FileManager", "One or both files do not exist")
                return false
            }

            newFile.copyTo(originalFile, overwrite = true)
            newFile.delete()
            true
        } catch (e: Exception) {
            Log.e("FileManager", "Error replacing file: ${e.message}", e)
            false
        }
    }

    override fun createTempFile(prefix: String, suffix: String): File {
        return File.createTempFile(prefix, suffix, context.cacheDir)
    }

    override fun getOutputDirectory(): File = context.cacheDir

    override fun createImageFile(): File {
        val fileName = "photo_${timestamp()}.jpg"
        return File(context.filesDir, fileName)
    }

    override fun createVideoFile(): File {
        val fileName = "video_${timestamp()}.mp4"
        return File(context.filesDir, fileName)
    }


    override fun getFileUri(file: File): Uri = Uri.fromFile(file)
    override fun getFileUriFromProvider(file: File): Uri =
        FileProvider.getUriForFile(
            context,
            "${context.packageName}.provider", // must match provider authority in manifest
            file
        )

    private fun timestamp(): String =
        SimpleDateFormat("yyyy-MM-dd-HH-mm-ss-SSS", Locale.US).format(System.currentTimeMillis())

    override fun deleteFileFromUri(uri: Uri?): Boolean {
        val fileName = uri?.lastPathSegment ?: return false
        val file = File(context.filesDir, fileName)
        return file.exists() && file.delete()
    }

    override fun savePickedFileToInternalStorage(
        uri: Uri,
        fileName: String,
        fileManager: FileManager
    ): Boolean {
        return try {
            val inputStream = context.contentResolver.openInputStream(uri) ?: return false
            val fileBytes = inputStream.readBytes()
            val internalFile = File(context.filesDir, fileName)
            fileManager.writeFile(internalFile.absolutePath, fileBytes)
        } catch (e: Exception) {
            e.printStackTrace()
            false
        }
    }
}