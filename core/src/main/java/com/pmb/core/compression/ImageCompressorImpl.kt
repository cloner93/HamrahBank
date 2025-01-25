package com.pmb.core.compression

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import com.pmb.core.fileManager.FileManager
import com.pmb.core.qualifier.IoDispatcher
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import java.io.ByteArrayOutputStream
import java.io.File
import java.io.FileOutputStream
import javax.inject.Inject

class ImageCompressorImpl @Inject constructor(
    @IoDispatcher private val dispatcher: CoroutineDispatcher,
    private val fileManager: FileManager
) : ImageCompressor {
    override suspend fun compressAndSave(
        imagePath: String, outputPath: String, maxWidth: Int,
        maxHeight: Int,
        format: Bitmap.CompressFormat,
        compressionPercentage: Int
    ): Boolean = withContext(dispatcher) {
        require(compressionPercentage in 0..100) {
            "compression percentage must be between 0 and 100"
        }
        try {
            val compressedImage = compressImageFromPath(
                imagePath, maxWidth, maxHeight, format, compressionPercentage
            )
            if (compressedImage != null) {
                val outputFile = File(outputPath)
                FileOutputStream(outputFile).use { fos ->
                    fos.write(compressedImage)
                }
                true
            } else
                false

        } catch (expression: Exception) {
            false
        }
    }

    override suspend fun Bitmap.compress(
        format: Bitmap.CompressFormat,
        compressionPercentage: Int
    ): ByteArray? = withContext(dispatcher) {
        require(compressionPercentage in 0..100) {
            "compression percentage must be between 0 and 100"
        }
        try {
            val outputStream = ByteArrayOutputStream()
            this@compress.compress(format, compressionPercentage)
            outputStream.toByteArray()
        } catch (exception: Exception) {
            exception.printStackTrace()
            null
        }
    }

    override suspend fun compressImageFromPath(
        imagePath: String,
        maxWidth: Int,
        maxHeight: Int,
        format: Bitmap.CompressFormat,
        compressionPercentage: Int
    ): ByteArray? = withContext(dispatcher) {
        require(compressionPercentage in 0..100) {
            "compression percentage must be between 0 and 100"
        }
        try {
            val options = BitmapFactory.Options().apply { inJustDecodeBounds = true }
            BitmapFactory.decodeFile(imagePath, options)

            options.inSampleSize = calculateInSampleSize(options, maxWidth, maxHeight)
            options.inJustDecodeBounds = false

            val bitmap = BitmapFactory.decodeFile(imagePath, options)

            bitmap?.compress(format, compressionPercentage)
        } catch (exception: Exception) {
            exception.printStackTrace()
            null
        }
    }

    override suspend fun compressAndReplaceImage(
        imagePath: String,
        maxWidth: Int,
        maxHeight: Int,
        format: Bitmap.CompressFormat,
        compressionPercentage: Int
    ): Boolean = withContext(dispatcher) {
        require(compressionPercentage in 0..100) {
            "compression percentage must be between 0 and 100"
        }
        try {
            val compressedImage = compressImageFromPath(
                imagePath, maxWidth, maxHeight, format, compressionPercentage
            )
            if (compressedImage != null) {
                val tempFile = fileManager.createTempFile("compressed", ".jpg")
                if (fileManager.writeFile(tempFile.absolutePath, compressedImage))
                    fileManager.replaceFile(imagePath, tempFile.absolutePath)
                else
                    false
            } else {
                false
            }
        } catch (expression: Exception) {
            false
        }
    }

    private fun calculateInSampleSize(
        options: BitmapFactory.Options,
        reqWidth: Int,
        reqHeight: Int
    ): Int {
        val (height: Int, width: Int) = options.run { outHeight to outWidth }
        var inSampleSize = 1
        if (height > reqHeight || width > reqWidth) {
            val halfHeight = height / 2
            val halfWidth = width / 2
            while (halfHeight / inSampleSize >= reqHeight && halfWidth / inSampleSize >= reqWidth) {
                inSampleSize *= 2
            }
        }
        return inSampleSize
    }
}