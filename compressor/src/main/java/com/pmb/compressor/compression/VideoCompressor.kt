package com.pmb.compressor.compression

import com.pmb.compressor.config.Configuration
import com.pmb.compressor.listeners.CompressionListener

interface VideoCompressor {
    fun compress(
        path: String,
        configureWith: Configuration,
        listener: CompressionListener,
    )
}