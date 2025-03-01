package com.pmb.compressor.config

import com.pmb.compressor.compression.VideoQuality

data class Configuration(
    var quality: VideoQuality = VideoQuality.MEDIUM,
    var isMinBitrateCheckEnabled: Boolean = true,
    var videoBitrateInMbps: Int? = null,
    var disableAudio: Boolean = false,
    val resizer: VideoResizer? = VideoResizer.auto,
    var videoNames: String
) {
    constructor(
        quality: VideoQuality = VideoQuality.MEDIUM,
        isMinBitrateCheckEnabled: Boolean = true,
        videoBitrateInMbps: Int? = null,
        disableAudio: Boolean = false,
        keepOriginalResolution: Boolean,
        videoNames: String
    ) : this(
        quality,
        isMinBitrateCheckEnabled,
        videoBitrateInMbps,
        disableAudio,
        resizer = if (keepOriginalResolution) null else VideoResizer.auto,
        videoNames
    )

    constructor(
        quality: VideoQuality = VideoQuality.MEDIUM,
        isMinBitrateCheckEnabled: Boolean = true,
        videoBitrateInMbps: Int? = null,
        disableAudio: Boolean = false,
        videoHeight: Double? = null,
        keepOriginalResolution: Boolean= false,
        videoWidth: Double? = null,
        videoNames: String
    ) : this(
        quality,
        isMinBitrateCheckEnabled,
        videoBitrateInMbps,
        disableAudio,
        resizer = VideoResizer.matchSize(videoWidth ?: 0.0, videoHeight ?: 0.0),
        videoNames
    )
}






