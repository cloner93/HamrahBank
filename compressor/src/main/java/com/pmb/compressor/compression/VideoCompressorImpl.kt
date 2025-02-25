package com.pmb.compressor.compression

import com.pmb.compressor.`interface`.CompressionListener
import com.pmb.compressor.`interface`.CompressionProgressListener
import com.pmb.compressor.compressor.Compressor.compressVideo
import com.pmb.compressor.compressor.Compressor.isRunning
import com.pmb.compressor.config.*
import com.pmb.compressor.video.Result
import com.pmb.core.fileManager.FileManager
import com.pmb.core.qualifier.IoDispatcher
import kotlinx.coroutines.*
import javax.inject.Inject


enum class VideoQuality {
    VERY_HIGH, HIGH, MEDIUM, LOW, VERY_LOW
}

class VideoCompressorImpl @Inject constructor(
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher,
    private val fileManager: FileManager,
) : VideoCompressor {

    private var job: Job? = null


   override fun compress(
       uris: String,
       configureWith: Configuration,
       listener: CompressionListener,
    ) {
        doVideoCompression(
            uris,
            configureWith,
            listener,
        )
    }
    private fun doVideoCompression(
        uris: String,
        configuration: Configuration,
        listener: CompressionListener,
    ) {
        val coroutineExceptionHandler = CoroutineExceptionHandler { _, throwable ->
            listener.onFailure(throwable.message ?: "")
        }
        val coroutineScope = CoroutineScope(Job() + coroutineExceptionHandler)

        job = coroutineScope.launch(ioDispatcher) {
            val desFile = fileManager.createVideoFile()

            desFile?.let {
                isRunning = true
                listener.onStart()
                val result = startCompression(
                    uris,
                    desFile.path,
                    configuration,
                    listener,
                )

                if (result.success) {
                    uris?.let { it1 -> fileManager.deleteFile(uris) }
                    listener.onSuccess(result.size, result.path)
                } else {
                    listener.onFailure(result.failureMessage ?: "An error has occurred!")
                }
            }
        }
    }

    private suspend fun startCompression(
        srcUri: String,
        destPath: String,
        configuration: Configuration,
        listener: CompressionListener,
    ): Result = withContext(ioDispatcher) {
        return@withContext compressVideo(
            srcUri,
            destPath,
            configuration,
            object : CompressionProgressListener {
                override fun onProgressChanged(percent: Float) {
                    listener.onProgress(percent)
                }

                override fun onProgressCancelled() {
                    listener.onCancelled()
                }
            },
        )
    }

}
