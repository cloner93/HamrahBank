package com.pmb.core.compression

import android.media.MediaCodec
import android.media.MediaCodecInfo
import android.media.MediaExtractor
import android.media.MediaFormat
import android.media.MediaMuxer
import com.pmb.core.qualifier.IoDispatcher
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

class VideoCompressor @Inject constructor(
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher
) {
    suspend fun compressVideo(
        inputPath: String,
        outputPath: String,
        compressionPercentage: Int = 50,
        frameRate: Int = 30
    ): Boolean = withContext(ioDispatcher) {
        require(compressionPercentage in 0..100) {
            "compression percentage must be between 0 and 100"
        }
        try {
            val mediaExtractor = MediaExtractor().apply {
                setDataSource(inputPath)
            }
            val videoTrackIndex = selectVideoTrack(mediaExtractor)
            if (videoTrackIndex < 0) {
                mediaExtractor.release()
                return@withContext false
            }
            mediaExtractor.selectTrack(videoTrackIndex)
            val format = mediaExtractor.getTrackFormat(videoTrackIndex)
            val mime = format.getString(MediaFormat.KEY_MIME) ?: return@withContext false
            val originalBitRate = format.getInteger(MediaFormat.KEY_BIT_RATE)
            val targetBitRate = (originalBitRate * (compressionPercentage / 100f).toInt())
            val mediaCodec = MediaCodec.createEncoderByType(mime).apply {
                configure(
                    MediaFormat.createVideoFormat(
                        mime,
                        format.getInteger(MediaFormat.KEY_WIDTH),
                        format.getInteger(MediaFormat.KEY_HEIGHT)
                    ).apply {
                        setInteger(MediaFormat.KEY_BIT_RATE, targetBitRate)
                        setInteger(MediaFormat.KEY_FRAME_RATE, frameRate)
                        setInteger(
                            MediaFormat.KEY_COLOR_FORMAT,
                            MediaCodecInfo.CodecCapabilities.COLOR_FormatSurface
                        )
                        setInteger(MediaFormat.KEY_I_FRAME_INTERVAL, 1)
                    },
                    null, null, MediaCodec.CONFIGURE_FLAG_ENCODE
                )
                start()
            }
            val mediaMuxer = MediaMuxer(outputPath, MediaMuxer.OutputFormat.MUXER_OUTPUT_MPEG_4)
            mediaExtractor.release()
            mediaCodec.stop()
            mediaCodec.release()
            mediaMuxer.stop()
            mediaMuxer.release()
            true
        } catch (exception: Exception) {
            exception.printStackTrace()
            false
        }
    }

    private fun selectVideoTrack(mediaExtractor: MediaExtractor): Int {
        for (index in 0 until mediaExtractor.trackCount) {
            val format = mediaExtractor.getTrackFormat(index)
            val mime = format.getString(MediaFormat.KEY_MIME)
            if (mime?.startsWith("video/") == true)
                return 1
        }
        return -1
    }
}