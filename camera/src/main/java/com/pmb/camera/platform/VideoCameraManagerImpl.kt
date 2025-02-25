package com.pmb.camera.platform

import android.content.Context
import android.media.MediaRecorder
import android.util.Log
import androidx.camera.core.Preview
import androidx.camera.lifecycle.ProcessCameraProvider
import androidx.camera.video.FileOutputOptions
import androidx.camera.video.Quality
import androidx.camera.video.QualitySelector
import androidx.camera.video.Recorder
import androidx.camera.video.VideoCapture
import androidx.camera.video.VideoRecordEvent
import androidx.camera.view.PreviewView
import androidx.core.content.ContextCompat
import androidx.lifecycle.LifecycleOwner
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.io.File
import javax.inject.Inject

class VideoCameraManagerImpl @Inject constructor(
    @ApplicationContext private val context: Context,
) : CameraManagerPrincipleImpl() {
    private var videoCapture: VideoCapture<Recorder>? = null
    private var mediaRecorder: MediaRecorder? = null
    override fun startCamera(
        previewView: PreviewView,
        lifecycleOwner: LifecycleOwner,
        onSuccess: () -> Unit,
        onError: (String) -> Unit
    ) {
        val cameraProviderFuture = ProcessCameraProvider.getInstance(context)
        cameraProviderFuture.addListener(
            {
                try {
                    val cameraProvider = cameraProviderFuture.get()
                    val preview = Preview.Builder().build().also {
                        it.surfaceProvider = previewView.surfaceProvider
                    }
                    val recorder =
                        Recorder.Builder().setQualitySelector(QualitySelector.from(Quality.HD))
                            .build()
                    videoCapture = VideoCapture.withOutput(recorder)
                    cameraProvider.unbindAll()
                    cameraProvider.bindToLifecycle(
                        lifecycleOwner,
                        cameraSelector,
                        preview,
                        videoCapture
                    )
                    onSuccess()
                } catch (e: Exception) {
                    onError("Failed to start camera: ${e.message}")
                }
            }, ContextCompat.getMainExecutor(context)
        )
    }

    override fun startRecording(
        outputFile: File,
        onCaptured: (Boolean) -> Unit,
        onError: (String) -> Unit
    ) {
        val fileOutputOptions = FileOutputOptions.Builder(outputFile).build()
        val recording = videoCapture?.output
            ?.prepareRecording(context, fileOutputOptions)
            ?.start(ContextCompat.getMainExecutor(context)) { event ->
                if (event is VideoRecordEvent.Finalize) {
                    if (event.hasError()) {
                        Log.d("video","video failed")
                        onError("Recording failed: ${event.error}")
                    } else {
                        Log.d("video","video captured")
                        onCaptured(true)
                    }
                }
            }

        CoroutineScope(Dispatchers.Main).launch {
            delay(20000)
            Log.i("video","video Stopped")
            recording?.stop()
        }
    }


}