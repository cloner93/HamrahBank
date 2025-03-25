package com.pmb.core.fileManager

import android.net.Uri
import java.io.File

interface FileManager {
    fun writeFile(filePath: String, data: ByteArray): Boolean
    fun deleteFile(filePath: String): Boolean
    fun replaceFile(originalFilePath:String,newFilePath:String):Boolean
    fun createTempFile(prefix:String,suffix:String):File
    fun getOutputDirectory(): File
    fun createImageFile(): File
    fun createVideoFile(): File
    fun getFileUri(file: File): Uri
}