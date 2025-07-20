package com.pmb.core.utils

import android.content.Context
import android.net.Uri
import android.util.Base64
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.File
import java.io.FileOutputStream
import java.nio.charset.StandardCharsets

object Base64FileHelper {
    suspend fun encodeToBase64(input: File): String = withContext(Dispatchers.IO) {
        val bytes = input.readBytes()
        Base64.encodeToString(bytes, Base64.NO_WRAP)
            .toByteArray(StandardCharsets.UTF_8)
            .toString(StandardCharsets.UTF_8)
    }

    suspend fun encodeToBase64(context: Context, input: Uri): String = withContext(Dispatchers.IO) {
        val inputStream = context.contentResolver.openInputStream(input)
            ?: throw IllegalArgumentException("Cannot open URI: $input")
        val bytes = inputStream.use { it.readBytes() }
        Base64.encodeToString(bytes, Base64.NO_WRAP)
            .toByteArray(StandardCharsets.UTF_8)
            .toString(StandardCharsets.UTF_8)
    }

    suspend fun decodeFromBase64(base64Utf8String: String, outputFile: File): File = withContext(Dispatchers.IO) {
        val decodedBytes = Base64.decode(
            base64Utf8String.toByteArray(StandardCharsets.UTF_8),
            Base64.NO_WRAP
        )
        FileOutputStream(outputFile).use { it.write(decodedBytes) }
        outputFile
    }
}