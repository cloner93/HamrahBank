package com.pmb.core.fileManager

import android.net.Uri
import java.io.File

interface FileManager {
    fun writeFile(absolutePath: String, data: ByteArray): Boolean
    fun deleteFile(absolutePath: String): Boolean
    fun replaceFile(originalAbsolutePath: String, newAbsolutePath: String): Boolean
    fun createTempFile(prefix: String, suffix: String): File
    fun getOutputDirectory(): File
    fun createImageFile(): File
    fun createVideoFile(): File
    fun getFileUri(file: File): Uri
}