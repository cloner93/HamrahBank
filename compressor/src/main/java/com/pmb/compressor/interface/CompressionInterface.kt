package com.pmb.compressor.`interface`

import androidx.annotation.MainThread
import androidx.annotation.WorkerThread


interface CompressionListener {
    @MainThread
    fun onStart()

    @MainThread
    fun onSuccess( size: Long, path: String?)

    @MainThread
    fun onFailure( failureMessage: String)

    @WorkerThread
    fun onProgress( percent: Float)

    @WorkerThread
    fun onCancelled()
}

interface CompressionProgressListener {
    fun onProgressChanged(percent: Float)
    fun onProgressCancelled()
}
