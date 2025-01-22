package com.pmb.core.compression

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import com.pmb.core.qualifier.IoDispatcher
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import java.io.ByteArrayOutputStream
import javax.inject.Inject

class ImageCompressor @Inject constructor(
    @IoDispatcher private val dispatcher: CoroutineDispatcher
) {
    suspend fun Bitmap.compress(
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

    suspend fun String.compressImageFromPath(
        maxWidth: Int,
        maxHeight: Int,
        format: Bitmap.CompressFormat = Bitmap.CompressFormat.JPEG,
        compressionPercentage: Int
    ): ByteArray? = withContext(dispatcher) {
        require(compressionPercentage in 0..100) {
            "compression percentage must be between 0 and 100"
        }
        try {
            val options = BitmapFactory.Options().apply { inJustDecodeBounds = true }
            BitmapFactory.decodeFile(this@compressImageFromPath, options)

            options.inSampleSize = calculateInSampleSize(options, maxWidth, maxHeight)
            options.inJustDecodeBounds = false

            val bitmap = BitmapFactory.decodeFile(this@compressImageFromPath, options)

            bitmap?.compress(format, compressionPercentage)
        } catch (exception: Exception) {
            exception.printStackTrace()
            null
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