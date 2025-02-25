package com.pmb.compressor.compression

import com.pmb.compressor.compressor.Compressor.compressVideo
import com.pmb.compressor.compressor.Compressor.isRunning
import com.pmb.compressor.config.Configuration
import com.pmb.compressor.listeners.CompressionListener
import com.pmb.compressor.listeners.CompressionProgressListener
import com.pmb.compressor.video.Result
import com.pmb.core.fileManager.FileManager
import com.pmb.core.qualifier.IoDispatcher
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject


enum class VideoQuality {
    VERY_HIGH, HIGH, MEDIUM, LOW, VERY_LOW
}

class VideoCompressorImpl @Inject constructor(
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher,
    private val fileManager: FileManager,
) : CoroutineScope by MainScope(), VideoCompressor {

    private var job: Job? = null


    override fun compress(
        path: String,
        configureWith: Configuration,
        listener: CompressionListener,
    ) {
        doVideoCompression(
            path,
            configureWith,
            listener,
        )
    }

    private fun doVideoCompression(
        path: String,
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
                    path,
                    desFile.path,
                    configuration,
                    listener,
                )

                if (result.success) {
                    path?.let { it1 -> fileManager.deleteFile(it1) }
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
