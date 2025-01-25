package com.pmb.core.compression

interface VideoCompressor {
    suspend fun compressVideo(
        inputPath: String,
        outputPath: String,
        compressionPercentage: Int = 50,
        frameRate: Int = 30
    ): Boolean

    suspend fun compressAndReplaceVideo(
        inputPath: String,
        compressionPercentage: Int = 50,
        frameRate: Int = 30
    ): Boolean
}