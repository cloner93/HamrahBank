package com.pmb.compressor.compression

import com.pmb.compressor.`interface`.CompressionListener
import com.pmb.compressor.config.Configuration

interface VideoCompressor {
    fun compress(
        uris: String,
        configureWith: Configuration,
        listener: CompressionListener,
    )
}