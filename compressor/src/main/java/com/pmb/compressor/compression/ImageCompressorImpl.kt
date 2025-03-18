package com.pmb.compressor.compression

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Matrix
import android.util.Log
import androidx.exifinterface.media.ExifInterface
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
            this@compress.compress(format, compressionPercentage, outputStream)
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

            var bitmap = BitmapFactory.decodeFile(imagePath, options)
            if (bitmap != null) {
                // Fix rotation
                bitmap = rotateBitmapIfRequired(imagePath, bitmap)
            }

            return@withContext bitmap?.let {
                val outputStream = ByteArrayOutputStream()
                it.compress(format, compressionPercentage, outputStream)
                outputStream.toByteArray()
            }
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
            "Compression percentage must be between 0 and 100"
        }
        try {
            val compressedImage = compressImageFromPath(
                imagePath, maxWidth, maxHeight, format, compressionPercentage
            )
            if (compressedImage == null) {
                Log.e("ImageCompressor", "Image compression failed for $imagePath")
                return@withContext false
            }
            val tempFile = fileManager.createTempFile("compressed", ".jpg")
            Log.d("ImageCompressor", "Temporary file created at: ${tempFile.absolutePath}")
            val writeSuccess = fileManager.writeFile(tempFile.absolutePath, compressedImage)
            if (!writeSuccess) {
                Log.e(
                    "ImageCompressor",
                    "Failed to write compressed image to temp file: ${tempFile.absolutePath}"
                )
                tempFile.delete()
                return@withContext false
            }

            val replaceSuccess = fileManager.replaceFile(imagePath, tempFile.absolutePath)
            if (!replaceSuccess) {
                Log.e("ImageCompressor", "Failed to replace original file: $imagePath")
                tempFile.delete()
                return@withContext false
            }
            true
        } catch (exception: Exception) {
            Log.e(
                "ImageCompressor",
                "Error during compression and replacement: ${exception.message}",
                exception
            )
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

    /* when we take a photo from camera and we want to compress it , it rotates photo that means top of photo rotates to left and ... so we need a method to control
        our rotation so we should use Exif interface to handle it , we should read our original file orientation and hold them to rotate our compress file
     */
    private fun rotateBitmapIfRequired(imagePath: String, bitmap: Bitmap): Bitmap {
        return try {
            val exif = ExifInterface(imagePath)
            val rotation = when (exif.getAttributeInt(
                ExifInterface.TAG_ORIENTATION,
                ExifInterface.ORIENTATION_NORMAL
            )) {
                ExifInterface.ORIENTATION_ROTATE_90 -> 90
                ExifInterface.ORIENTATION_ROTATE_180 -> 180
                ExifInterface.ORIENTATION_ROTATE_270 -> 270
                else -> 0
            }

            if (rotation != 0) {
                val matrix = Matrix().apply { postRotate(rotation.toFloat()) }
                Bitmap.createBitmap(bitmap, 0, 0, bitmap.width, bitmap.height, matrix, true)
            } else {
                bitmap
            }
        } catch (e: Exception) {
            e.printStackTrace()
            bitmap
        }
    }
}