package com.pmb.core.compression

import android.graphics.Bitmap

interface ImageCompressor {
    suspend fun compressAndSave(
        imagePath: String, outputPath: String, maxWidth: Int,
        maxHeight: Int,
        format: Bitmap.CompressFormat = Bitmap.CompressFormat.JPEG,
        compressionPercentage: Int
    ): Boolean

    suspend fun Bitmap.compress(
        format: Bitmap.CompressFormat,
        compressionPercentage: Int
    ): ByteArray?

    suspend fun compressImageFromPath(
        imagePath: String,
        maxWidth: Int,
        maxHeight: Int,
        format: Bitmap.CompressFormat = Bitmap.CompressFormat.JPEG,
        compressionPercentage: Int
    ): ByteArray?

    suspend fun compressAndReplaceImage(
        imagePath: String,
        maxWidth: Int,
        maxHeight: Int,
        format: Bitmap.CompressFormat = Bitmap.CompressFormat.JPEG,
        compressionPercentage: Int
    ): Boolean
}