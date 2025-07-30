package com.pmb.core.utils

import android.content.ContentValues
import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import android.os.Build
import android.provider.MediaStore
import androidx.core.content.FileProvider
import java.io.File
import java.io.FileOutputStream
import java.io.OutputStream

fun saveBitmapToGallery(
    context: Context,
    bitmap: Bitmap,
    displayName: String = "receipt_${System.currentTimeMillis()}"
): Boolean {
    val resolver = context.contentResolver

    val contentValues = ContentValues().apply {
        put(MediaStore.Images.Media.DISPLAY_NAME, "$displayName.jpg")
        put(MediaStore.Images.Media.MIME_TYPE, "image/jpeg")
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            put(MediaStore.Images.Media.IS_PENDING, 1)
        }
    }

    val imageUri = resolver.insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, contentValues)
        ?: return false

    resolver.openOutputStream(imageUri)?.use { outputStream: OutputStream ->
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, outputStream)
    }

    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
        contentValues.clear()
        contentValues.put(MediaStore.Images.Media.IS_PENDING, 0)
        resolver.update(imageUri, contentValues, null, null)
    }

    return true
}


fun shareImage(context: Context, uri: Uri) {
    val intent = Intent(Intent.ACTION_SEND).apply {
        type = "image/png"
        putExtra(Intent.EXTRA_STREAM, uri)
        addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)
    }

    context.startActivity(Intent.createChooser(intent, "Share Image"))
}

fun getImageUri(context: Context, imageFile: File): Uri {
    return FileProvider.getUriForFile(context, "${context.packageName}.provider", imageFile)
}

fun saveBitmapToCache(context: Context, bitmap: Bitmap): File {
    val cachePath = File(context.cacheDir, "images")
    cachePath.mkdirs() // Create directory if not exists
    val file = File(cachePath, "shared_image.png")

    FileOutputStream(file).use { out ->
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, out)
    }
    return file
}